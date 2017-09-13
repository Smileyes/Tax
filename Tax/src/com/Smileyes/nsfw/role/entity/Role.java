package com.Smileyes.nsfw.role.entity;

import java.util.Set;

/*
 *  角色类
 * 
 * @author Smileyes
 *
 */
public class Role {
	// 角色id
	private String id;
	// 角色名
	private String name;
	// 角色拥有的权限
	private Set<RolePrivilege> rolePrivileges;
	// 角色状态
	private String state;
	private static String ROLE_VAILD = "1";
	private static String ROLE_INVAILD = "0";

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<RolePrivilege> getRolePrivileges() {
		return rolePrivileges;
	}

	public void setRolePrivileges(Set<RolePrivilege> rolePrivileges) {
		this.rolePrivileges = rolePrivileges;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Role() {
	}

	public Role(String id) {
		this.id=id;
	}

}
