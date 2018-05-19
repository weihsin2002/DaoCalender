package org.dao.calendar.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.dao.calendar.config.Configurator;
import org.dao.calendar.utils.Utils;

public class SolarDate {
	private Logger logger = Logger.getLogger(SolarDate.class);
	
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
	
	public SolarDate (Date date) {
		this.date = date;
	}
	
	public SolarDate (int y, int m, int d, int h, int min) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
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
		
	public SolarDate LunarToSolar(LuniSolarDate lunar) {
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

		return Utils.SolarFromInt(Utils.SolarToInt(y, m, d) + offset - 1);
	}
}
