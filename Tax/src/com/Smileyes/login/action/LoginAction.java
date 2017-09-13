package com.Smileyes.login.action;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.Smileyes.core.constant.Constant;
import com.Smileyes.nsfw.user.entity.User;
import com.Smileyes.nsfw.user.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;

/*
 * 登录Action
 * 
 * @author Smileyes
 *
 */
@Controller
public class LoginAction extends ActionSupport {
	@Resource
	private UserService userService;
	private User user;

	/*
	 * 登录页面
	 */
	public String loginUI() throws Exception {
		Map<String, Object> session = ServletActionContext.getContext().getSession();
		return "loginUI";
	}

	/*
	 * 登录:判断是否可以登录
	 */
	public String login() throws Exception {
		User user2 = userService.finByAccountAndPassword(user.getAccount(),
				user.getPassword());
		Map<String, Object> session = ServletActionContext.getContext().getSession();
		if (user2 != null) {
			// 用户存在,登录成功
			session.put(Constant.USER, user2);
			Log log = LogFactory.getLog(this.getClass());
			log.info("用户" + user2.getName() + "登录成功");
			return LOGIN;
		} else {
			// 用户不存在，登录失败
			session.put("resultInfo", "登录失败,账户与密码不匹配！");
			return NONE;
		}
	}

	/*
	 * 退出
	 */
	public String logout() throws Exception {
		Map<String, Object> session = ServletActionContext.getContext().getSession();
		session.remove(Constant.USER);
		return "logout";
	}

	/*
	 * 没有访问权限
	 */
	public String noPermissionUI() throws Exception{
		return "noPermissionUI";
	}
	/*
	 * 删除登录失败的消息
	 */
	public void removeInfo() {
		Map<String, Object> session = ServletActionContext.getContext().getSession();
		session.remove("resultInfo");
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
