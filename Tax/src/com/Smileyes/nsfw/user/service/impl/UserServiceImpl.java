package com.Smileyes.nsfw.user.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.Smileyes.core.utils.ExcelUtil;
import com.Smileyes.nsfw.user.dao.UserDao;
import com.Smileyes.nsfw.user.entity.User;
import com.Smileyes.nsfw.user.service.UserService;

/*
 * UserService的实现类
 * 
 * @author Smileyes
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	@Resource
	private UserDao userDao;

	public void add(User user) {
		this.userDao.add(user);
	}

	public void delete(Serializable id) {
		this.userDao.delete(id);
	}

	public void update(User user) {
		this.userDao.update(user);
	}

	public User findById(Serializable id) {
		return this.userDao.findById(id);
	}

	public List<User> list() {
		return this.userDao.list();
	}

	public List<User> findByNameOrAccount(String name) {
		return this.userDao.findByNameOrAccount(name);
	}

	public void exportExcel(OutputStream outputStream) {
		// 1.创建工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 2.获取数据列表
		List<User> list = this.userDao.list();
		// 3.将数据添加到工作簿中
		ExcelUtil.exportExcel(workbook, list);
		// 4.输出文件
		try {
			workbook.write(outputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void importExcel(File excelFile, String excelFileName) {
		try {
			// 1.创建输入流
			FileInputStream fis = new FileInputStream(excelFile);
			// 2.获得工作簿
			Workbook workbook = (excelFileName.matches("^.+\\.(?i)(xls)$"))
					? new HSSFWorkbook(fis)
					: new XSSFWorkbook(fis);
			// 3.读取excel文件内容
			List<User> list = ExcelUtil.importExcel(workbook);
			// 添加用户
			if (list != null && list.size() != 0) {
				for (User user : list) {
					if (!userDao.isDuplicate(user.getAccount())) {
						this.userDao.add(user);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
