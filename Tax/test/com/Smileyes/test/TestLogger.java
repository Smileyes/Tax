package com.Smileyes.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import com.mchange.lang.StringUtils;

import freemarker.template.utility.StringUtil;

public class TestLogger {

	public void TestLog() {
		Log log = LogFactory.getLog(this.getClass());
		log.debug("debug错误");
		log.info("info信息");
		log.warn("warn警告");
		log.error("error错误");
		log.fatal("fatal致命错误");
	}
	@Test
	public void TestStringUtil() {
		String str=null;
		boolean b = org.springframework.util.StringUtils.isEmpty(str);
		System.out.println(b);
	}
}
