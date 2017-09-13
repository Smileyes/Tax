package powerDesigner.entity;
// Generated 2017-9-1 20:02:51 by Hibernate Tools 4.3.5.Final

import java.util.HashSet;
import java.util.Set;

/**
 * TRole generated by hbm2java
 */
public class TRole implements java.io.Serializable {

	private String roleId;
	private String roleName;
	private Set TUsers = new HashSet(0);
	private Set TPrivileges = new HashSet(0);

	public TRole() {
	}

	public TRole(String roleId, String roleName) {
		this.roleId = roleId;
		this.roleName = roleName;
	}

	public TRole(String roleId, String roleName, Set TUsers, Set TPrivileges) {
		this.roleId = roleId;
		this.roleName = roleName;
		this.TUsers = TUsers;
		this.TPrivileges = TPrivileges;
	}

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Set getTUsers() {
		return this.TUsers;
	}

	public void setTUsers(Set TUsers) {
		this.TUsers = TUsers;
	}

	public Set getTPrivileges() {
		return this.TPrivileges;
	}

	public void setTPrivileges(Set TPrivileges) {
		this.TPrivileges = TPrivileges;
	}

}
