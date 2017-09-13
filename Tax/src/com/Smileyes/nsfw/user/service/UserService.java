package com.Smileyes.nsfw.user.service;

import java.io.File;
import java.io.OutputStream;
import java.util.List;

import com.Smileyes.core.exception.ServiceException;
import com.Smileyes.core.service.BaseService;
import com.Smileyes.nsfw.user.entity.User;
import com.Smileyes.nsfw.user.entity.UserRole;

/*
 * 用于处理用户的Service服务类
 * 
 * @author Smileyes
 *
 */
public interface UserService extends BaseService<User>{


	// 模糊查询
	public List<User> findByNameOrAccount(String name);

	// 导出用户列表到Excel
	public void exportExcel(OutputStream outputStream) throws Exception;

	// 从Excel导入用户列表
	public void importExcel(File excelFile, String FileName) throws ServiceException;

	// 判断账户名与Id是否存在
	public boolean checkAccountAndId(String account, String id);

	// 保存用户与角色信息
	public void saveUserAndRole(User user, String... roles);

	// 更新用户与角色信息
	public void updateUserAndRole(User user, String... selectedRow);

	// 查询用户的角色信息
	public List<UserRole> findRoleInfo(String id);

	// 通过账户与密码查询用户
	public User finByAccountAndPassword(String account, String password);
}
