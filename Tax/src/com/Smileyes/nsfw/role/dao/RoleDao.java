package com.Smileyes.nsfw.role.dao;

import java.util.List;

import com.Smileyes.core.dao.BaseDao;
import com.Smileyes.nsfw.role.entity.Role;

/*
 * RoleDao接口
 * 
 * @author Smileyes
 *
 */
public interface RoleDao extends BaseDao<Role> {

	// 通过角色名称查询
	public List<Role> findByName(String name);

	// 判断角色与id是否存在
	public boolean checkNameAndId(String name, String id);

	// 删除旧的权限
	public void removeOldPrivilege(String id);
	
}
