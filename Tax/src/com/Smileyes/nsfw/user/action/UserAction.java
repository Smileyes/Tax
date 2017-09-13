package com.Smileyes.nsfw.user.action;

import java.io.File;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import com.Smileyes.nsfw.role.service.RoleService;
import com.Smileyes.nsfw.user.entity.User;
import com.Smileyes.nsfw.user.entity.UserRole;
import com.Smileyes.nsfw.user.service.UserService;

/*
 * 用户Action
 * 
 * @author Smileyes
 *
 */
@Controller
@Scope("prototype")
public class UserAction extends BaseAction {
	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;
	private User user;

	// 添加用户前
	public String addUI() throws Exception {
		// 加载角色列表
		List<Role> list = roleService.list();
		ServletActionContext.getContext().getContextMap().put("roleList",
				roleService.list());
		return "addUI";
	}

	// 添加用户
	public String add() throws Exception {
		if (user != null && user.getName() != null
				&& (!"".equals(user.getName().trim()))) {
			if (upload != null) {
				// 1.设置文件名
				uploadFileName = UUID.randomUUID() + "_" + uploadFileName;
				uploadFileName = uploadFileName.replace("-", "");
				user.setHeadImg("upload/user/" + uploadFileName);
				// 2. 保存文件
				String path = ServletActionContext.getServletContext()
						.getRealPath("upload/user") + "/" + uploadFileName;
				FileUtils.copyFile(upload, new File(path));
			}
			// 保存用户与角色
			this.userService.saveUserAndRole(user, selectedRow);
		}
		return "list";

	}

	// 删除用户
	public String delete() throws Exception {
		if (user != null & user.getId() != null) {
			this.userService.delete(user.getId());
		}
		return "list";
	}

	// 删除选中的用户
	public String deleteSelect() throws Exception {
		if (selectedRow != null && selectedRow.length != 0) {
			for (String id : selectedRow) {
				System.out.println(id);
				this.userService.delete(id);
			}
		}
		return "list";
	}

	// 修改用户前
	public String editUI() throws Exception {
		if (user != null & user.getId() != null) {
			// 获得用户信息
			user = this.userService.findById(user.getId());
			// 加载角色列表
			ServletActionContext.getContext().getContextMap().put("roleList",
					roleService.list());
			// 加载用户已有角色信息
			List<UserRole> userRoleList = userService.findRoleInfo(user.getId());
			// 将已有的角色信息保存至数组selectedRow
			if (userRoleList != null) {
				selectedRow = new String[userRoleList.size()];
				Iterator<UserRole> iterator = userRoleList.iterator();
				int i = 0;
				while (iterator.hasNext()) {
					selectedRow[i++] = iterator.next().getRole().getId();
				}
			}
			return "editUI";
		} else {
			return "list";
		}
	}

	// 修改用户
	public String update() throws Exception {
		if (upload != null) {
			// 1.设置文件名
			uploadFileName = UUID.randomUUID() + "_" + uploadFileName;
			uploadFileName = uploadFileName.replace("-", "");
			user.setHeadImg("/upload/user/" + uploadFileName);
			// 2. 保存文件
			String path = ServletActionContext.getServletContext()
					.getRealPath("upload/user") + "/" + uploadFileName;
			FileUtils.copyFile(upload, new File(path));
		}
		this.userService.updateUserAndRole(user, selectedRow);
		return "list";
	}

	// 显示所有用户
	public String listUI() throws Exception {
		// 新建查询助手,按照新建时间倒序排列
		if (pageResult == null) {
			pageResult = new PageResult(1);
		}
		// 添加数据来源
		QueryHelper qh = new QueryHelper(User.class);
		// 添加条件
		if (StringUtils.isNotBlank(search)) {
			qh.addCondition("name like ?", "%" + search.trim() + "%",
					QueryHelper.CONDITIONS_NULL)
					.addCondition("account like ?", search, QueryHelper.CONDITIONS_OR);
		}
		// 每页显示3条数据
		pageResult.setPageSize(3);
		// 查询数据，获取pageResult的其它数据
		pageResult = this.userService.getPageResult(qh, pageResult);
		// 获得所有信息的分类集合
		ServletActionContext.getContext().getContextMap().put("infoTypeMap",
				Info.INFO_TYPE_MAP);
		return "listUI";
	}

	// 导出文件
	public void exportExcel() throws Exception {
		// 1.获取response
		HttpServletResponse response = ServletActionContext.getResponse();
		// 2.设置响应头
		response.setHeader("content-Disposition",
				"attchment;filename=" + URLEncoder.encode("用户列表.xls", "utf-8"));
		// 3.获取输出流
		OutputStream outputStream = response.getOutputStream();
		// 4.执行方法
		this.userService.exportExcel(outputStream);
	}

	// 导入文件
	public String importExcel() throws Exception {
		// 如果上传的文件为excel文件
		System.out.println(uploadContentType);
		if (uploadFileName.matches("^.+\\.(?i)(xls|xlsx)$")) {
			this.userService.importExcel(upload, uploadFileName);
		}
		return "list";
	}

	// 检查账户名
	public void verifyAccount() throws Exception {
		try {
			boolean exist = this.userService.checkAccountAndId(user.getAccount(),
					user.getId());
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			PrintWriter writer = response.getWriter();
			writer.write(exist ? "true" : "false");
		} catch (Exception e) {
			throw new ActionException();
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}