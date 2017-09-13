package com.Smileyes.core.exception;

/*
 * 系统异常基类
 * 
 * @author Smileyes
 *
 */
public class SysException extends Exception {
	// 异常信息
	private String errorMsg;

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public SysException(String errorMsg) {
		super(errorMsg);
		this.errorMsg = errorMsg;
	}

	public SysException() {
		super("系统出错");
		this.errorMsg = "系统出错";
	}

}
