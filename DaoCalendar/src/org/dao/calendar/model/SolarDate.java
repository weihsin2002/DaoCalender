package org.dao.calendar.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SolarDate {
	private int solarDay;
	private int solarMonth;
	private int solarYear;
	private int hour;
	private int min;
	private int sec;
	
	private Date date;
	
	public SolarDate (int y, int m, int d) {
		this.solarYear = y;
		this.solarMonth = m;
		this.solarDay = d;
	}
	
	public SolarDate (int y, int m, int d, int h, int min) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		this.hour = h;
		this.min = min;
		this.sec = 0;
		
		try {
			Date date = dateFormat.parse(y + "-" + m + "-" + d + " " + h + ":" + m);
			this.date = date;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int solarDay() {
		return solarDay;
	}
	public void setSolarDay(int solarDay) {
		this.solarDay = solarDay;
	}
	public int solarMonth() {
		return solarMonth;
	}
	public void setSolarMonth(int solarMonth) {
		this.solarMonth = solarMonth;
	}
	public int solarYear() {
		return solarYear;
	}
	public void setSolarYear(int solarYear) {
		this.solarYear = solarYear;
	}

	public int hour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int min() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int sec() {
		return sec;
	}

	public void setSec(int sec) {
		this.sec = sec;
	}

	public Date date() {
		return date;
	}

	public void setSolarDate(Date date) {
		this.date = date;
	}
}
