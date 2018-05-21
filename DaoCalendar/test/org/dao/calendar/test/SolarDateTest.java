package org.dao.calendar.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.dao.calendar.model.FourPillars;
import org.dao.calendar.model.LuniSolarDate;
import org.dao.calendar.model.SolarDate;

public class SolarDateTest {
	private final static Logger logger = Logger.getLogger(SolarDateTest.class);
	
	public static void main(String[] args) throws ParseException {
		Date date = new Date();
		
		String year = "2018-05-20 00 00 00";
		
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH mm ss");  
		date = sdf.parse(year);
		
		SolarDate solarDate = new SolarDate(date);
		
		logger.info(solarDate.toString());
		logger.info(solarDate.toJson());
		
		LuniSolarDate luniSolarDate = new LuniSolarDate();
		luniSolarDate = luniSolarDate.SolarToLunar(solarDate);
		
		logger.info(luniSolarDate.toJson().toString());
		
		FourPillars fourPillars = new FourPillars (solarDate, luniSolarDate);
		logger.info(fourPillars.toJson().toString());
	}

}
