package com.Smileyes.core.exception;

/*
 *Action控制层异常 
 * 
 * @author Smileyes
 *
 */
public class ActionException extends SysException {

	public ActionException() {
		super("访问出错");
	}
	public ActionException(String message) {
		super(message);
	}
}
