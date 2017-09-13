package com.Smileyes.nsfw.user.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.Smileyes.core.exception.ServiceException;
import com.Smileyes.core.service.impl.BaseServiceImpl;
import com.Smileyes.core.utils.ExcelUtil;
import com.Smileyes.nsfw.role.entity.Role;
import com.Smileyes.nsfw.user.dao.UserDao;
import com.Smileyes.nsfw.user.entity.User;
import com.Smileyes.nsfw.user.entity.UserRole;
import com.Smileyes.nsfw.user.service.UserService;

/*
 * UserService的实现类
 * 
 * @author Smileyes
 *
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	private UserDao userDao;

	@Resource
	public void setUserDao(UserDao userDao) {
		super.setBaseDao(userDao);
		this.userDao = userDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public List<User> findByNameOrAccount(String name) {
		return this.userDao.findByNameOrAccount(name);
	}

	public void exportExcel(OutputStream outputStream) throws ServiceException {
		HSSFWorkbook workbook = null;
		try {
			// 1.创建工作簿
			workbook = new HSSFWorkbook();
			// 2.获取数据列表
			List<User> list = this.userDao.list();
			// 3.将数据添加到工作簿中
			ExcelUtil.exportExcel(workbook, list);
			// 4.输出文件
			workbook.write(outputStream);
		} catch (Exception e) {
			throw new ServiceException("Srvice出错：" + e.getMessage());
		} finally {
			try {
				if (workbook != null) {
					workbook.close();
				}
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
	}

	public void importExcel(File excelFile, String excelFileName)
			throws ServiceException {
		Workbook workbook = null;
		FileInputStream fis = null;
		try {
			// 1.创建输入流
			fis = new FileInputStream(excelFile);
			// 2.获得工作簿
			workbook = (excelFileName.matches("^.+\\.(?i)(xls)$"))
					? new HSSFWorkbook(fis)
					: new XSSFWorkbook(fis);
			// 3.读取excel文件内容
			List<User> list = ExcelUtil.importExcel(workbook);
			// 添加用户
			if (list != null && list.size() != 0) {
				for (User user : list) {
					if (!userDao.checkAccountAndId(user.getAccount(), user.getId())) {
						this.userDao.add(user);
					}
				}
			}
		} catch (Exception e) {
			throw new ServiceException("业务执行出错");
		} finally {
			try {
				if (workbook != null) {
					workbook.close();
				}
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				throw new ServiceException("Service出现IO异常：" + e.getMessage());
			}
		}
	}

	public boolean checkAccountAndId(String account, String id) {
		return this.userDao.checkAccountAndId(account, id);
	}

	public void saveUserAndRole(User user, String... roles) {
		// 保存用户
		this.userDao.add(user);
		// 保存用户的角色信息
		for (String roleId : roles) {
			userDao.saveUserRole(new Role(roleId), user.getId());
		}
	}

	// 更新用户与角色信息
	public void updateUserAndRole(User user, String... roles) {
		// 更新用户
		this.userDao.update(user);
		// 删除旧的角色信息
		this.userDao.removeOldROle(user.getId());
		// 保存新的角色信息
		for (String roleId : roles) {
			userDao.saveUserRole(new Role(roleId), user.getId());
		}
	}

	public List<UserRole> findRoleInfo(String id) {
		return userDao.findRoleInfo(id);
	}

	// 通过账户与密码查询用户
	public User finByAccountAndPassword(String account, String password) {
		return this.userDao.finByAccountAndPassword(account, password);
	}
}
