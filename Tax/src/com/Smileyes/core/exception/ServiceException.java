package com.Smileyes.core.exception;

/*
 * Service服务异常
 * 
 * @author Smileyes
 *
 */
public class ServiceException extends SysException {
	public ServiceException() {
		super("服务出错");
	}

	public ServiceException(String message) {
		super(message);
	}
}
