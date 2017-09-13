package com.Smileyes.nsfw.role.service;

import java.io.Serializable;
import java.util.List;

import com.Smileyes.core.exception.ServiceException;
import com.Smileyes.core.service.BaseService;
import com.Smileyes.nsfw.role.entity.Role;

/*
 * 用于处理角色的Service服务类
 * 
 * @author Smileyes
 *
 */
public interface RoleService extends BaseService<Role> {

	// 模糊查询
	public List<Role> findByName(String name);

	// 判断账户名与Id是否存在
	public boolean checkNameAndId(String name, String id);

	// 删除旧权限
	public void removeOldPrivilege(String id);
}
