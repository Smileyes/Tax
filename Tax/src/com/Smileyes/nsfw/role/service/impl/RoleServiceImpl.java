package com.Smileyes.nsfw.role.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.Smileyes.core.exception.ServiceException;
import com.Smileyes.core.service.impl.BaseServiceImpl;
import com.Smileyes.nsfw.role.dao.RoleDao;
import com.Smileyes.nsfw.role.entity.Role;
import com.Smileyes.nsfw.role.service.RoleService;

/*
 * RoleService的实现类
 * 
 * @author Smileyes
 *
 */
@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {
	private RoleDao roleDao;

	@Resource
	public void setRoleDao(RoleDao roleDao) {
		super.setBaseDao(roleDao);
		this.roleDao = roleDao;
	}

	public RoleDao getRoleDao() {
		return roleDao;
	}


	// 通过名称查询
	public List<Role> findByName(String name) {
		return this.roleDao.findByName(name);
	}

	// 在修改角色名称时，异步检查角色名称
	public boolean checkNameAndId(String name, String id) {
		return this.roleDao.checkNameAndId(name, id);
	}

	// 删除角色的旧权限
	public void removeOldPrivilege(String id) {
		this.roleDao.removeOldPrivilege(id);
	}

}
