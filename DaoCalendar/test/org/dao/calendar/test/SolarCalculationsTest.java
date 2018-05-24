package org.dao.calendar.test;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

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
		
		double minutes = solarCalculations.calcTrueSolarTime(cal);
		
		logger.info("minutes=" + minutes);
		
		int [] d = solarCalculations.calcHourMinSec(minutes);
		
		String[] strArray = Arrays.stream(d).mapToObj(String::valueOf).toArray(String[]::new);
				
		logger.info("date=" + Arrays.toString(strArray));
	}

}
