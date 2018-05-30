package org.dao.calendar.test;

import java.util.Date;

import org.apache.log4j.Logger;
import org.dao.calendar.DaoCalendar;
import org.dao.calendar.utils.Utils;

public class Test {
	private static final Logger logger = Logger.getLogger(Test.class);

	public static void main(String[] args) {
		
		Utils.calTrueSolarTime(new Date());
	}

}
