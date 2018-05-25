package org.dao.calendar.test;

import java.util.Date;

import org.apache.log4j.Logger;
import org.dao.calendar.DaoCalendar;

public class Test {
	private static final Logger logger = Logger.getLogger(Test.class);

	public static void main(String[] args) {

		Date date = new Date();
		DaoCalendar daoCalendar = new DaoCalendar(date);

		logger.info("DAO Calendar=" + daoCalendar.toJson().toString());
	}

}
