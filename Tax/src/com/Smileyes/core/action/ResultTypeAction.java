package com.Smileyes.core.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.StrutsResultSupport;

import com.opensymphony.xwork2.ActionInvocation;

/*
 * 返回类型配置
 * 
 * @author Smileyes
 *
 */
public class ResultTypeAction extends StrutsResultSupport {

	protected void doExecute(String arg0, ActionInvocation arg1) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		/*
		 * 执行业务
		 */
		System.out.println("进入了ResultTypeAction类中......");
		response.sendRedirect("error.jsp");
	}

}
