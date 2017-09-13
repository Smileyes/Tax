package com.Smileyes.nsfw.user.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.springframework.util.StringUtils;

import com.Smileyes.core.dao.impl.BaseDaoImpl;
import com.Smileyes.nsfw.role.entity.Role;
import com.Smileyes.nsfw.user.dao.UserDao;
import com.Smileyes.nsfw.user.entity.User;
import com.Smileyes.nsfw.user.entity.UserRole;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

/*
 * 处理用户操作的DAO
 * 
 * @author Smileyes
 *
 */

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
	// 查询用户名或帐号
	public List<User> findByNameOrAccount(String name) {
		List<User> list = this.getSession() //
				.createQuery("FROM User WHERE name like ? OR account like ?") //
				.setParameter(0, "%" + name + "%") //
				.setParameter(1, "%" + name + "%") //
				.list();
		return list;
	}

	// 判断帐号名与id是否存在
	public boolean checkAccountAndId(String account, String id) {
		boolean isDuplicate = false;
		String sql = "FROM User WHERE account = ?";
		if (!StringUtils.isEmpty(id)) {
			sql += " AND id != ?";
		}
		Query query = this.getSession() //
				.createQuery(sql) //
				.setParameter(0, account);
		if (!StringUtils.isEmpty(id)) {
			query.setParameter(1, id);
		}
		List<User> list = query.list();
		if (list != null && list.size() > 0) {
			isDuplicate = true;
		}
		return isDuplicate;
	}

	// 保存用户角色信息
	public void saveUserRole(Role role, String id) {
		this.getHibernateTemplate().save(new UserRole(role, id));
	}

	// 查询用户的角色信息
	public List<UserRole> findRoleInfo(String id) {
		return this.getSession().createQuery("FROM UserRole WHERE userId=?")
				.setParameter(0, id).list();
	}

	// 删除旧的角色
	public void removeOldROle(String id) {
		Query query = this.getSession()
				.createQuery("DELETE FROM UserRole WHERE userId=?").setParameter(0, id);
		query.executeUpdate();
	}

	// 通过用户名与密码查找用户
	public User finByAccountAndPassword(String account, String password) {
		Query query = this.getSession()
				.createQuery("FROM User WHERE account=? AND password=?");
		query.setParameter(0, account).setParameter(1, password);
		List<User> list = query.list();
		if (list == null || list.size() == 0) {
			return null;
		} else {
			// 存在该员工
			User user = list.get(0);
			// 查询员工的角色信息,并转化为set
			Set userRoles = new HashSet<UserRole>(findRoleInfo(user.getId()));
			// 存储角色信息
			user.setUserRoles(userRoles);
			return user;
		}
	}

}
