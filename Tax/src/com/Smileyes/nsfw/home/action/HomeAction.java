package com.Smileyes.nsfw.home.action;

import com.opensymphony.xwork2.ActionSupport;

/*
 * 纳税服务模块的主页
 * 
 * @author Smileyes
 *
 */
public class HomeAction extends ActionSupport{

	
	
	public String frame() throws Exception {
		
		return "frame";
	}
	public String left() throws Exception {
		
		return "left";
	}
	public String top() throws Exception {
		
		return "top";
	}

	
}
