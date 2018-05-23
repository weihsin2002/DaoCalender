package org.dao.calendar.test;

import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.dao.calendar.solarcalculations.SolarCalculations;

public class SolarCalculationsTest {
	
	private static final Logger logger = Logger.getLogger(SolarCalculationsTest.class);
	
	public static void main(String[] args) {
		
		ZonedDateTime t = ZonedDateTime.now();
			
 		logger.info(t.getZone().getRules().isDaylightSavings(t.toInstant()));
 		
		SolarCalculations solarCalculations = new  SolarCalculations(37.7749, -122.4194, t.getZone().getRules().isDaylightSavings(t.toInstant()));
		
		Calendar cal = Calendar.getInstance();;
		cal.setTime(new Date());
		
		double minute = solarCalculations.calcEquationOfTime(cal);
		
		Date date = new Date(minute);
	}

}
