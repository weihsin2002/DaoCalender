package org.dao.calendar.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.dao.calendar.config.Configurator;
import org.dao.calendar.model.LuniSolarDate;
import org.dao.calendar.model.SolarDate;
import org.dao.calendar.solarcalculations.SolarCalculations;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CityResponse;

public class Utils {
	private static Logger logger = Logger.getLogger(Utils.class);

	public static int GetBitInt(int data, int length, int shift) {
		return (data & (((1 << length) - 1) << shift)) >> shift;
	}
	
	// WARNING: Dates before Oct. 1582 are inaccurate
	public static long SolarToInt(int y, int m, int d) {
		m = (m + 9) % 12;
		y = y - m / 10;
		return 365 * y + y / 4 - y / 100 + y / 400 + (m * 306 + 5) / 10
				+ (d - 1);
	}
	
	public static SolarDate SolarFromInt(long g) {
		long y = (10000 * g + 14780) / 3652425;
		long ddd = g - (365 * y + y / 4 - y / 100 + y / 400);
		if (ddd < 0) {
			y--;
			ddd = g - (365 * y + y / 4 - y / 100 + y / 400);
		}
		long mi = (100 * ddd + 52) / 3060;
		long mm = (mi + 2) % 12 + 1;
		y = y + (mi + 2) / 12;
		long dd = ddd - (mi * 306 + 5) / 10 + 1;
		SolarDate solar = new SolarDate((int) y, (int) mm, (int) dd);
		return solar;
	}
	
	public static SolarDate LunarToSolar(LuniSolarDate lunar) {
		int offset = 0;
		int loopend = lunar.leap();
		if (!lunar.isleap()) {
			if (lunar.month() <= lunar.leap() || lunar.leap() == 0) {
				loopend = lunar.month() - 1;
			} else {
				loopend = lunar.month();
			}
		}

		for (int i = 0; i < loopend; i++) {
			offset += Utils.GetBitInt(lunar.days(), 1, 12 - i) == 1 ? 30 : 29;
		}
		offset += lunar.day();

		int solar11 = Configurator.solar_1_1()[lunar.year() - Configurator.solar_1_1()[0]];

		int y = Utils.GetBitInt(solar11, 12, 9);
		int m = Utils.GetBitInt(solar11, 4, 5);
		int d = Utils.GetBitInt(solar11, 5, 0);
		int h = lunar.hour();
		int min = lunar.minute();
		int sec = lunar.sec();

		SolarDate solarDate = SolarFromInt(SolarToInt(y, m, d) + offset - 1);  
		
		return (new SolarDate (solarDate.year(), solarDate.month(), solarDate.day(), h, min, sec));
	}
	  	  
	  public static Date calTrueSolarTime (Date date) {
		  ZonedDateTime t = ZonedDateTime.now();
			
		  File database = new File(Configurator.dblocation());
	 		
		  logger.info("Use DB latitude / longtitude DB at =" + database.getAbsolutePath());
	 		
		  URL whatismyip;
		  CityResponse response = null;
	  		
		  try {
			  DatabaseReader dbReader = new DatabaseReader.Builder(database).build();
	 	 		
			  whatismyip = new URL("http://checkip.amazonaws.com");
			
			  BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
		
			  String ip = in.readLine(); //you get the IP as a String
		 		
			  logger.info("Your IP=" + ip);
		 		
			  InetAddress ipAddress = InetAddress.getByName(ip);
			  	response = dbReader.city(ipAddress);
		  } catch (Exception e) {
			  logger.error("Error getting IP", e);
			  return null;
		  }
			
		  String cityName = response.getCity().getName();
	        
		  logger.info("city=" + cityName);
	        
		  double latitude = response.getLocation().getLatitude();
		  double longitude = response.getLocation().getLongitude();
	        
		  logger.info("Your latitude=" + latitude + " Your longtitude=" + longitude);
	 		
		  SolarCalculations solarCalculations = new  SolarCalculations(latitude, longitude, t.getZone().getRules().isDaylightSavings(t.toInstant()));
			
		  SimpleDateFormat sdf;
			
		  Calendar cal = Calendar.getInstance();;
		  cal.setTime(date);
		  
		  double minutes;
			
		  try {			
			  minutes = solarCalculations.calcTrueSolarTime(cal);
			  
			  sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			  
			  date = sdf.parse(Calendar.getInstance().get(Calendar.YEAR) + "-" + (Calendar.getInstance().get(Calendar.MONTH)+1) + "-" + Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + " 00:00:00");
			} catch (Exception e) {
				logger.error("Erro getting today's date", e);
				return null;
			}
			
			long adjTime = date.getTime() + (long) minutes*60*1000;
			Date adjDate = new Date();
			adjDate.setTime(adjTime); 

			logger.info("The true solar time is " + sdf.format(adjDate));
			
			return (adjDate);
		}

}
