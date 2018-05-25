package org.dao.calendar.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.dao.calendar.solarcalculations.SolarCalculations;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CityResponse;

public class SolarCalculationsTest {
	
	private static final Logger logger = Logger.getLogger(SolarCalculationsTest.class);
	
	public static void main(String[] args) {
		
		ZonedDateTime t = ZonedDateTime.now();
			
 		logger.info(t.getZone().getRules().isDaylightSavings(t.toInstant()));
 		
 		String dbLocation = "geodb/GeoLite2-City.mmdb";
 		
 		File database = new File(dbLocation);
 		
 		logger.info("file path=" + database.getAbsolutePath());
 		logger.info("file exist=" + database.exists());
 		
  		URL whatismyip;
  		CityResponse response = null;
 		try {
 	 		DatabaseReader dbReader = new DatabaseReader.Builder(database).build();
 	 		
			whatismyip = new URL("http://checkip.amazonaws.com");
		
	 		BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
	
	 		String ip = in.readLine(); //you get the IP as a String
	 		
	 		logger.info("IP=" + ip);
	 		
	 		InetAddress ipAddress = InetAddress.getByName(ip);
	 		response = dbReader.city(ipAddress);
		} catch (Exception e) {
			logger.error("Error getting IP", e);
		}
		
        String cityName = response.getCity().getName();
        
        logger.info("city=" + cityName);
        
        double latitude = response.getLocation().getLatitude();
        double longitude = response.getLocation().getLongitude();
        
        logger.info("latitude=" + latitude);
        logger.info("longtitude=" + longitude);
 		
		SolarCalculations solarCalculations = new  SolarCalculations(latitude, longitude, t.getZone().getRules().isDaylightSavings(t.toInstant()));
//		SolarCalculations solarCalculations = new  SolarCalculations(latitude, longitude, false);
		
		Calendar cal = Calendar.getInstance();;
		cal.setTime(new Date());
		
		double minutes = solarCalculations.calcTrueSolarTime(cal);
		
		Date date = new Date ();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		
		try {
			date = sdf.parse("2018-05-24-00-00-00");
		} catch (ParseException e) {
			logger.error("Erro parsing date", e);
		}
		
		long now = date.getTime() + (long) minutes*60*1000;
		
		date.setTime(now);
		
		logger.info("date=" + sdf.format(date));
	}

}
