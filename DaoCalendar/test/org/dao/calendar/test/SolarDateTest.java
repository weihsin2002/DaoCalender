package org.dao.calendar.test;

import java.util.Date;

import org.apache.log4j.Logger;
import org.dao.calendar.model.LuniSolarDate;
import org.dao.calendar.model.SolarDate;

public class SolarDateTest {
	private final static Logger logger = Logger.getLogger(SolarDateTest.class);
	
	public static void main(String[] args) {
		Date date = new Date();
		SolarDate solarDate = new SolarDate(date);
		
		logger.info(solarDate.toString());
		logger.info(solarDate.toJson());
		
		LuniSolarDate luniSolarDate = new LuniSolarDate();
		luniSolarDate = luniSolarDate.SolarToLunar(solarDate);
		
		logger.info(luniSolarDate.toJson().toString());
	}

}
