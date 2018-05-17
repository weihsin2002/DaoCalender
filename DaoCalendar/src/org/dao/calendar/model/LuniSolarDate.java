package org.dao.calendar.model;

import org.dao.Utils;
import org.dao.calendar.config.Configurator;

public class LuniSolarDate {
	private boolean isleap;
	private int day;
	private int month;
	private int year;
	private int days;
	private int leap; // leap month	
	
	public LuniSolarDate (int year, int month, int day, boolean isleap) { // initializing lunar object by entering lunar date 
		this.year = year;
		this.month = month;
		this.day = day;
		this.isleap = false;
		
		this.days = Configurator.lunar_month_days()[this.year - Configurator.lunar_month_days()[0]];
		this.leap = Utils.GetBitInt(days, 4, 13);
		
		if (this.leap != 0 && this.month == this.leap) {
			this.isleap = isleap;
		}				
	}

	public boolean isleap() {
		return isleap;
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

	public int days() {
		return days;
	}

	public int leap() {
		return leap;
	}
}
