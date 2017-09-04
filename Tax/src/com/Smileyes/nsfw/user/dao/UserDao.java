package com.Smileyes.nsfw.user.dao;

import java.util.List;

import com.Smileyes.core.dao.BaseDao;
import com.Smileyes.nsfw.user.entity.User;

/*
 * UserDao接口
 * 
 * @author Smileyes
 *
 */
public interface UserDao extends BaseDao<User> {

	// 查询用户名或帐号
	public List<User> findByNameOrAccount(String name);

	// 查询用户名
	public boolean isDuplicate(String account);
}
