package com.Smileyes.core.filter;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Smileyes.core.constant.Constant;
import com.Smileyes.core.utils.PermissionCheckUitls;
import com.Smileyes.nsfw.user.entity.User;

/*
 * 登录过滤器，判断是否登录
 * 
 * @author Smileyes
 *
 */
public class LoginFilter implements Filter {
	/*
	 * 初始化
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	/*
	 * 摧毁过滤器
	 */
	public void destroy() {
	}

	/*
	 * 执行过滤器的服务
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		// 获取session中的登录信息
		HttpSession session = httpServletRequest.getSession();
		Object loginInfo = session.getAttribute(Constant.USER);
		// 查看当前的访问的链接
		String uri = httpServletRequest.getRequestURI();
		if (uri.contains("login")) {
			// 当前为登录请求,检查是否已经登录
			if (loginInfo != null && !uri.contains("noPermissionUI")
					&& !uri.contains("logout")) {
				// 已登录且不是权限警告请求和退出请求
				httpServletResponse.sendRedirect(
						httpServletRequest.getContextPath() + "/sys/home.action");
			} else {
				// 其它情况
				chain.doFilter(request, response);
			}
		} else {
			// 非登录请求，检查登录情况
			if (loginInfo == null) {
				// 不存在登录信息，也就是未登录,跳转到登录页面
				httpServletResponse.sendRedirect(httpServletRequest.getContextPath()
						+ "/sys/login_loginUI.action");
			} else {
				// 存在登录信息，已登录，获取登录的用户
				User user = (User) loginInfo;
				// 判断请求的是否是权限模块
				boolean needPrivilege = false;
				// 遍历各大模块
				for (String privilege : Constant.PRIVILEGE_MAP.keySet()) {
					if (uri.contains(privilege)) {
						// 正在访问需要权限的模块
						needPrivilege = true;
						if (PermissionCheckUitls.check(user, privilege)) {
							// 有权限,放行
							chain.doFilter(request, response);
						} else {
							// 无权限，警告
							httpServletResponse
									.sendRedirect(httpServletRequest.getContextPath()
											+ "/sys/login_noPermissionUI.action");
						}
						break;
					}
				}
				if (!needPrivilege) {
					// 不需要权限的模块,放行
					chain.doFilter(request, response);
				}
			}

		}
	}

}
