package org.dao.calendar.model;

import org.dao.calendar.config.Configurator;
import org.dao.calendar.utils.Utils;

import com.google.gson.JsonObject;

public class LuniSolarDate {
	private boolean isleap;
	private int day;
	private int month;
	private int year;
	private int hour;
	private int minute;
	private int sec;
	private int days;
	private int leap; // leap month
	
	public LuniSolarDate () {
		
	}
	
	public LuniSolarDate (int year, int month, int day, boolean isleap) { // initializing lunar object by entering lunar date 
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = 0;
		this.minute = 0;
		this.sec = 0;
		this.isleap = false;
		
		this.days = Configurator.lunar_month_days()[this.year - Configurator.lunar_month_days()[0]];
		this.leap = Utils.GetBitInt(days, 4, 13);
		
		if (this.leap != 0 && this.month == this.leap) {
			this.isleap = isleap;
		}				
	}
	
	public LuniSolarDate (int year, int month, int day, int hour, int minute, boolean isleap) { // initializing lunar object by entering lunar date 
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hour;
		this.minute = minute;
		this.sec = 0;
		this.isleap = false;
		
		this.days = Configurator.lunar_month_days()[this.year - Configurator.lunar_month_days()[0]];
		this.leap = Utils.GetBitInt(days, 4, 13);
		
		if (this.leap != 0 && this.month == this.leap) {
			this.isleap = isleap;
		}
	}

	public LuniSolarDate (int year, int month, int day, int hour, int minute, int second, boolean isleap) { // initializing lunar object by entering lunar date 
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hour;
		this.minute = minute;
		this.sec = second;
		this.isleap = false;
		
		this.days = Configurator.lunar_month_days()[this.year - Configurator.lunar_month_days()[0]];
		this.leap = Utils.GetBitInt(days, 4, 13);
		
		if (this.leap != 0 && this.month == this.leap) {
			this.isleap = isleap;
		}
	}
	
	public LuniSolarDate(SolarDate solar) {
		int index = solar.year() - Configurator.solar_1_1()[0];
		int data = (solar.year() << 9) | (solar.month() << 5)
				| (solar.day());
		int solar11 = 0;
		if (Configurator.solar_1_1()[index] > data) {
			index--;
		}
		solar11 = Configurator.solar_1_1()[index];
		int y = Utils.GetBitInt(solar11, 12, 9);
		int m = Utils.GetBitInt(solar11, 4, 5);
		int d = Utils.GetBitInt(solar11, 5, 0);
		long offset = Utils.SolarToInt(solar.year(), solar.month(),
				solar.day()) - Utils.SolarToInt(y, m, d);

		int days = Configurator.lunar_month_days()[index];

		int lunarY = index + Configurator.solar_1_1()[0];
		int lunarM = 1;
		int lunarD = 1;
		offset += 1;

		for (int i = 0; i < 13; i++) {
			int dm = Utils.GetBitInt(days, 1, 12 - i) == 1 ? 30 : 29;
			if (offset > dm) {
				lunarM++;
				offset -= dm;
			} else {
				break;
			}
		}
		lunarD = (int) (offset);
		
		int leapDays = Configurator.lunar_month_days()[lunarY - Configurator.lunar_month_days()[0]];
		int leap = Utils.GetBitInt(leapDays, 4, 13);
		
		boolean isleap = false;
		
		if (leap != 0 && lunarM > leap) {
			lunarM = lunarM - 1;
			if (lunarM == leap) {
				isleap = true;
			}
		}
		
		this.year = lunarY;
		this.month = lunarM;
		this.day = lunarD;
		this.hour = solar.hour();
		this.minute = solar.min();
		this.sec = solar.sec();
		this.isleap = isleap;
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
	
	public int hour() {
		return hour;
	}
	
	public int minute() {
		return minute;
	}
	
	public int sec() {
		return sec;
	}
	
	public String lunarYearToGanZhi(int lunarYear){
		return Configurator.tianGan()[(lunarYear-4) % 10] + Configurator.diZhi()[(lunarYear-4) % 12];
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
		
	public String toString () {
		return "Year=" + this.year + ",Month=" + this.month + ",Day=" + this.day + ",Hour=" + this.hour + ",Minute="+this.minute + ",Second="+this.sec + ",isLeap=" + this.isleap + ",days=" + this.days;
	}
	
	public JsonObject toJson () {
		JsonObject json = new JsonObject();
		json.addProperty("year", this.year);
		json.addProperty("month", this.month);
		json.addProperty("day", this.day);
		json.addProperty("hour", this.hour);
		json.addProperty("minute", this.minute);
		json.addProperty("second", this.sec);
		json.addProperty("isLeap", this.isleap);
		if (this.hour == 0 || this.hour == 23) {
			json.addProperty("shi", Configurator.zhi()[0]);
		} else {
			json.addProperty("shi", Configurator.zhi()[(this.hour+1)/2]);
		}
		
		
		return json;
	}
}