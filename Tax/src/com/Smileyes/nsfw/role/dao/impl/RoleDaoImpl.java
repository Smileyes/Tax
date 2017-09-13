package com.Smileyes.nsfw.role.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.util.StringUtils;

import com.Smileyes.core.dao.impl.BaseDaoImpl;
import com.Smileyes.nsfw.role.dao.RoleDao;
import com.Smileyes.nsfw.role.entity.Role;

/*
 * 处理角色操作的DAO
 * 
 * @author Smileyes
 *
 */

public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {
	// 角色名称查询
	public List<Role> findByName(String name) {
		List<Role> list = this.getSession() //
				.createQuery("FROM Role WHERE name like ? ") //
				.setParameter(0, "%" + name + "%") //
				.list();
		return list;
	}

	// 判断角色名与id是否存在
	public boolean checkNameAndId(String name, String id) {
		boolean isDuplicate = false;
		String sql = "FROM Role WHERE name = ?";
		if (!StringUtils.isEmpty(id)) {
			sql += " AND id != ?";
		}
		Query query = this.getSession() //
				.createQuery(sql) //
				.setParameter(0, name);
		if (!StringUtils.isEmpty(id)) {
			query.setParameter(1, id);
		}
		List<Role> list = query.list();
		if (list != null && list.size() > 0) {
			isDuplicate = true;
		}
		return isDuplicate;
	}

	// 删除旧的权限
	public void removeOldPrivilege(String id) {
		Query query = this.getSession()
				.createQuery("DELETE FROM RolePrivilege WHERE role.id = ?");
		query.setParameter(0, id);
		query.executeUpdate();
	}
}
