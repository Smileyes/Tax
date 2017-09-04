package com.Smileyes.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

public class TestLogger {
	@Test
	public void TestLog() {
		Log log = LogFactory.getLog(this.getClass());
		log.debug("debug错误");
		log.info("info信息");
		log.warn("warn警告");
		log.error("error错误");
		log.fatal("fatal致命错误");
	}
}
