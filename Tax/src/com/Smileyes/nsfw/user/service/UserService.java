package com.Smileyes.nsfw.user.service;

import java.io.File;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;

import com.Smileyes.nsfw.user.entity.User;

/*
 * 用于处理用户的Service服务类
 * 
 * @author Smileyes
 *
 */
public interface UserService {
	// 添加用户
	public void add(User user);

	// 删除用户
	public void delete(Serializable id);

	// 修改用户
	public void update(User user);

	// 查询用户
	public User findById(Serializable id);

	// 模糊查询
	public List<User> findByNameOrAccount(String name);

	// 查询所有用户
	public List<User> list();

	// 导出用户列表到Excel
	public void exportExcel(OutputStream outputStream);

	public void importExcel(File excelFile, String FileName);
}
