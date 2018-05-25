package org.dao.calendar.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

import com.google.gson.JsonObject;

public class SolarDate {
	private Logger logger = Logger.getLogger(SolarDate.class);
	
	private int day;
	private int month;
	private int year;
	private int hour;
	private int min;
	private int sec;
	
	private Date date;
	
	public SolarDate () {
		
	}
	
	public SolarDate (int y, int m, int d) {
		this.year = y;
		this.month = m;
		this.day = d;
		this.hour = 0;
		this.min = 0;
		this.sec = 0;
	}
	
	public SolarDate (Date date) {
		this.date = date;
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		this.year = cal.get(Calendar.YEAR);
		this.month = cal.get(Calendar.MONDAY) + 1;
		this.day = cal.get(Calendar.DAY_OF_MONTH);
		this.hour = cal.get(Calendar.HOUR_OF_DAY);
		this.min = cal.get(Calendar.MINUTE);
		this.sec = cal.get(Calendar.SECOND);
	}
	
	public SolarDate (int y, int m, int d, int h, int min) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		this.year = y;
		this.month = m;
		this.day = d;
		this.hour = h;
		this.min = min;
		this.sec = 0;
		
		try {
			Date date = dateFormat.parse(y + "-" + m + "-" + d + " " + h + ":" + m);
			this.date = date;
		} catch (Exception e) {
			logger.error("Errer parsing SolarDate");
		}
	}

	public SolarDate (int y, int m, int d, int h, int min, int sec) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.year = y;
		this.month = m;
		this.day = d;
		this.hour = h;
		this.min = min;
		this.sec = sec;
		
		try {
			Date date = dateFormat.parse(y + "-" + m + "-" + d + " " + h + ":" + min + ":" + sec);
			this.date = date;
		} catch (Exception e) {
			logger.error("Errer parsing SolarDate");
		}
	}

	public int day() {
		return day;
	}

	public int month() {
		return month;
	}

	public int year() {
		return year;
	}
	
	public int hour() {
		return hour;
	}

	public int min() {
		return min;
	}

	public int sec() {
		return sec;
	}

	public Date date() {
		return date;
	}
	
	public String toString () {
		return "Year=" + this.year + ",Month=" + this.month + ",Day=" + this.day + ",Hour=" + this.hour + ",Minute="+this.min + ",Second="+this.sec;
	}
	
	public JsonObject toJson () {
		JsonObject json = new JsonObject();
		json.addProperty("year", this.year);
		json.addProperty("month", this.month);
		json.addProperty("day", this.day);
		json.addProperty("hour", this.hour);
		json.addProperty("minute", this.min);
		json.addProperty("second", this.sec);
				
		return json;
	}	
}