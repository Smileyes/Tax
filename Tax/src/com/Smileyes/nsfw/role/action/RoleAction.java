package com.Smileyes.nsfw.role.action;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.Smileyes.core.action.BaseAction;
import com.Smileyes.core.constant.Constant;
import com.Smileyes.core.entity.PageResult;
import com.Smileyes.core.exception.ActionException;
import com.Smileyes.core.utils.QueryHelper;
import com.Smileyes.nsfw.info.entity.Info;
import com.Smileyes.nsfw.role.entity.Role;
import com.Smileyes.nsfw.role.entity.RolePrivilege;
import com.Smileyes.nsfw.role.service.RoleService;

/*
 * 角色Action
 * 
 * @author Smileyes
 *
 */
@Controller
@Scope("prototype")
public class RoleAction extends BaseAction {
	@Resource
	private RoleService roleService;
	private Role role;

	// 添加角色前
	public String addUI() throws Exception {
		// 获得所有权限的集合
		Map<String, String> privilegeMap = Constant.PRIVILEGE_MAP;
		ServletActionContext.getContext().getContextMap().put("privilegeMap",
				privilegeMap);
		return "addUI";
	}

	// 添加角色
	public String add() throws Exception {
		if (role != null && !StringUtils.isEmpty(role.getName())) {
			HashSet<RolePrivilege> rolePrivileges = new HashSet<RolePrivilege>();
			for (String code : selectedRow) {
				rolePrivileges.add(new RolePrivilege(role, code));
			}
			role.setRolePrivileges(rolePrivileges);
			this.roleService.add(role);
		}
		return "list";
	}

	// 删除角色
	public String delete() throws Exception {
		if (role != null & role.getId() != null) {
			this.roleService.delete(role.getId());
		}
		return "list";
	}

	// 删除选中的角色
	public String deleteSelect() throws Exception {
		if (selectedRow != null && selectedRow.length != 0) {
			for (String id : selectedRow) {
				System.out.println(id);
				this.roleService.delete(id);
			}
		}
		return "list";
	}

	// 修改角色前
	public String editUI() throws Exception {
		if (role != null & role.getId() != null) {
			role = this.roleService.findById(role.getId());
			// 获得所有权限的集合
			ServletActionContext.getContext().getContextMap().put("privilegeMap",
					Constant.PRIVILEGE_MAP);
			// 保存之前的权限
			selectedRow = new String[role.getRolePrivileges().size()];
			Iterator<RolePrivilege> iterator = role.getRolePrivileges().iterator();
			int i = 0;
			while (iterator.hasNext()) {
				selectedRow[i++] = iterator.next().getCode();
			}
			return "editUI";
		} else {
			return "list";
		}
	}

	// 修改角色
	public String update() throws Exception {
		if (role != null && !StringUtils.isEmpty(role.getName())) {
			// 删除旧的权限
			this.roleService.removeOldPrivilege(role.getId());
			// 保存新权限
			HashSet<RolePrivilege> rolePrivileges = new HashSet<RolePrivilege>();
			for (String code : selectedRow) {
				rolePrivileges.add(new RolePrivilege(role, code));
			}
			role.setRolePrivileges(rolePrivileges);
			this.roleService.update(role);
		}
		return "list";
	}

	// 显示所有角色
	public String listUI() throws Exception {
		// 新建查询助手,按照新建时间倒序排列
		if (pageResult == null) {
			// 当前页为第一页
			pageResult = new PageResult(1);
		}
		// 添加数据来源
		QueryHelper qh = new QueryHelper(Role.class);
		// 添加条件
		if (StringUtils.isNotBlank(search)) {
			qh.addCondition("name like ?", "%" + search.trim() + "%",
					QueryHelper.CONDITIONS_NULL);
		}
		// 每页显示3条数据
		pageResult.setPageSize(3);
		// 查询数据，获取pageResult的其它数据
		pageResult = this.roleService.getPageResult(qh, pageResult);
		// 获取权限列表
		ServletActionContext.getContext().getContextMap().put("privilegeMap",
				Constant.PRIVILEGE_MAP);
		return "listUI";
	}

	// 检查角色名
	public void verifyName() throws Exception {
		try {
			boolean exist = this.roleService.checkNameAndId(role.getName(),
					role.getId());
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			PrintWriter writer = response.getWriter();
			writer.write(exist ? "true" : "false");
		} catch (Exception e) {
			throw new ActionException();
		}
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}