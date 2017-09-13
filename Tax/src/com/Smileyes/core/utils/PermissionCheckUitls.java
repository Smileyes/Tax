package com.Smileyes.core.utils;

import java.util.Set;

import com.Smileyes.nsfw.role.entity.RolePrivilege;
import com.Smileyes.nsfw.user.entity.User;
import com.Smileyes.nsfw.user.entity.UserRole;

/*
 * 检查访问权限
 * 
 * @author Smileyes
 *
 */
public class PermissionCheckUitls {
	public static boolean check(User user, String privilege) {
		// 获取角色信息
		Set<UserRole> roleSet = user.getUserRoles();
		// 遍历角色信息
		for (UserRole userRole : roleSet) {
			Set<RolePrivilege> rps = userRole.getRole().getRolePrivileges();
			for (RolePrivilege rp : rps) {
				// 获取权限名称
				String code = rp.getCode();
				if (privilege.equals(code)) {
					return true;
				}
			}
		}
		return false;
	}
}
