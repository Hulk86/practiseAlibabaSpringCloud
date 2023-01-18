package com.study.cloud.alibaba.commons.log;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SystemLogger {

	private static Logger logger = LoggerFactory.getLogger(SystemLogger.class);

	public static void error(String format, Object... arguments) {
		logger.error(format, arguments);
	}

	public static void warn(String format, Object... arguments) {
		logger.warn(format, arguments);
	}

	public static void info(String format, Object... arguments) {
		logger.info(format, arguments);
	}

	public static void debug(String format, Object... arguments) {
		logger.debug(format, arguments);
	}

}