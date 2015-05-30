package com.demo.junit;

import org.apache.log4j.Logger;

import junit.framework.TestCase;

/**
 * Main test for junit.
 * @author Fajie Han (hfajie@cntomorrow.com)
 * @since 2015-4-2 上午10:42:52
 */
public class MainTest extends TestCase
{
	/** The logger. */
	private Logger _logger = Logger.getLogger(MainTest.class);

	/**
	 * Get the logger.
	 * @return the logger.
	 */
	public Logger get_logger() {
		return _logger;
	}

	/**
	 * Set the logger.
	 * @param logger the logger.
	 */
	public void set_logger(Logger logger) {
		_logger = logger;
	}
}
