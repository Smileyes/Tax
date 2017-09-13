package com.Smileyes.nsfw.role.entity;

import java.io.Serializable;

/*
 * 角色——权限类
 * 采用联合主键
 * 1.实现Serializable接口
 * 2.重写hashCode与equals方法
 * @author Smileyes
 *
 */
public class RolePrivilege implements Serializable {
	//角色
	private Role role;
	//权限代码
	private String code;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public RolePrivilege(Role role, String code) {
		this.role = role;
		this.code = code;
	}

	public RolePrivilege() {
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RolePrivilege other = (RolePrivilege) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		return true;
	}

}
