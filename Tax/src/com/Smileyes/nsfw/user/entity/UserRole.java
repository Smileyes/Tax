package com.Smileyes.nsfw.user.entity;

import java.io.Serializable;

import com.Smileyes.nsfw.role.entity.Role;

/*
 * 用户—角色类
 * 
 * @author Smileyes
 *
 */
public class UserRole implements Serializable {
	private Role role;
	private String userId;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRole other = (UserRole) obj;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	public UserRole() {
	}

	public UserRole(Role role, String userId) {
		this.role = role;
		this.userId = userId;
	}

}
