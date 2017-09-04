package com.Smileyes.nsfw.user.dao.impl;

import java.util.List;

import com.Smileyes.core.dao.impl.BaseDaoImpl;
import com.Smileyes.nsfw.user.dao.UserDao;
import com.Smileyes.nsfw.user.entity.User;
/*
 * 处理用户操作的DAO
 * 
 * @author Smileyes
 *
 */

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
	// 查询用户名或帐号
	public List<User> findByNameOrAccount(String name) {
		List<User> list = this.getSessionFactory().getCurrentSession() //
				.createQuery("FROM User WHERE name like ? OR account like ?") //
				.setParameter(0, "%" + name + "%") //
				.setParameter(1, "%" + name + "%") //
				.list();
		return list;
	}

	// 查询用户名
	public boolean isDuplicate(String account) {
		boolean isDuplicate = true;
		List<User> list = this.getSessionFactory().getCurrentSession() //
				.createQuery("FROM User WHERE account = ?") //
				.setParameter(0, account).list();
		if (list.isEmpty()) {
			isDuplicate = false;
		}
		return isDuplicate;
	}

}
