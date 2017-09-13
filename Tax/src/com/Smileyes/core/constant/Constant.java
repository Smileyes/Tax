package com.Smileyes.core.constant;

import java.util.HashMap;
import java.util.Map;

/*
 * 权限类
 * 
 * @author Smileyes
 *
 */
public class Constant {
	/*
	 * 权限列表
	 */
	private final static String PRIVILEGE_XZGL = "xzgl";
	private final static String PRIVILEGE_HQFW = "hqfw";
	private final static String PRIVILEGE_ZXXX = "zxxx";
	private final static String PRIVILEGE_NSFW = "nsfw";
	private final static String PRIVILEGE_WDKJ = "wdkj";
	// 用户
	public final static String USER = "SYS_USER";
	/*
	 * 权限集合
	 */
	public final static Map<String, String> PRIVILEGE_MAP = new HashMap<String, String>();
	static {
		PRIVILEGE_MAP.put(PRIVILEGE_XZGL, "行政管理");
		PRIVILEGE_MAP.put(PRIVILEGE_HQFW, "后勤服务");
		PRIVILEGE_MAP.put(PRIVILEGE_ZXXX, "在线学习");
		PRIVILEGE_MAP.put(PRIVILEGE_NSFW, "纳税服务");
		PRIVILEGE_MAP.put(PRIVILEGE_WDKJ, "我的空间");
	}
}
