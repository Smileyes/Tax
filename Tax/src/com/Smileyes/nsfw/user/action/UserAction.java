package com.Smileyes.nsfw.user.action;

import java.io.File;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.Smileyes.nsfw.user.entity.User;
import com.Smileyes.nsfw.user.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

/*
 * 用户Action
 * 
 * @author Smileyes
 *
 */
@Controller
@Scope("prototype")
public class UserAction extends ActionSupport {
	@Resource
	private UserService userService;
	private User user;
	private List<User> userList;
	private String[] selectedRow;
	private String search;
	private File headImg;
	private String headImgContentType;
	private String headImgFileName;

	// 添加用户前
	public String addUI() throws Exception {
		return "addUI";
	}

	// 添加用户
	public String add() throws Exception {
		if (user != null && user.getName() != null
				&& (!"".equals(user.getName().trim()))) {
			if (headImg != null) {
				// 1.设置文件名
				headImgFileName = UUID.randomUUID() + "_" + headImgFileName;
				headImgFileName = headImgFileName.replace("-", "");
				user.setHeadImg("upload/user/" + headImgFileName);
				// 2. 保存文件
				String path = ServletActionContext.getServletContext()
						.getRealPath("upload/user") + "/" + headImgFileName;
				FileUtils.copyFile(headImg, new File(path));
			}
			this.userService.add(user);
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
			user = this.userService.findById(user.getId());
			return "editUI";
		} else {
			return "list";
		}
	}

	// 修改用户
	public String update() throws Exception {
		System.out.println(headImgFileName);
		if (headImg != null) {
			// 1.设置文件名
			headImgFileName = UUID.randomUUID() + "_" + headImgFileName;
			headImgFileName = headImgFileName.replace("-", "");
			user.setHeadImg("/upload/user/" + headImgFileName);
			// 2. 保存文件
			String path = ServletActionContext.getServletContext()
					.getRealPath("upload/user") + "/" + headImgFileName;
			FileUtils.copyFile(headImg, new File(path));
		}
		this.userService.update(user);
		return "list";
	}

	// 显示所有用户
	public String listUI() throws Exception {
		this.userList = this.userService.list();
		return "listUI";
	}

	// 查询
	public String doSearch() throws Exception {
		if (search != null && (!"".equals(search.trim()))) {
			userList = this.userService.findByNameOrAccount(search.trim());
			return "search";
		} else {
			return "list";
		}

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
		System.out.println(headImgContentType);
		if (headImgFileName.matches("^.+\\.(?i)(xls|xlsx)$")) {
			this.userService.importExcel(headImg, headImgFileName);
		}
		return "list";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public String[] getSelectedRow() {
		return selectedRow;
	}

	public void setSelectedRow(String[] selectedRow) {
		this.selectedRow = selectedRow;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public File getHeadImg() {
		return headImg;
	}

	public void setHeadImg(File headImg) {
		this.headImg = headImg;
	}

	public String getHeadImgContentType() {
		return headImgContentType;
	}

	public void setHeadImgContentType(String headImgContentType) {
		this.headImgContentType = headImgContentType;
	}

	public String getHeadImgFileName() {
		return headImgFileName;
	}

	public void setHeadImgFileName(String headImgFileName) {
		this.headImgFileName = headImgFileName;
	}

}