package com.Smileyes.nsfw.user.dao;

import java.util.List;

import com.Smileyes.core.dao.BaseDao;
import com.Smileyes.nsfw.role.entity.Role;
import com.Smileyes.nsfw.user.entity.User;
import com.Smileyes.nsfw.user.entity.UserRole;

/*
 * UserDao接口
 * 
 * @author Smileyes
 *
 */
public interface UserDao extends BaseDao<User> {

	// 查询用户名或帐号
	public List<User> findByNameOrAccount(String name);

	// 判断账户与id是否存在
	public boolean checkAccountAndId(String account, String id);

	// 保存用户的角色信息
	public void saveUserRole(Role role, String id);

	// 查询用户的角色信息
	public List<UserRole> findRoleInfo(String id);

	// 删除用户的就角色信息
	public void removeOldROle(String id);

	// 查询账户与密码
	public User finByAccountAndPassword(String account, String password);
}
