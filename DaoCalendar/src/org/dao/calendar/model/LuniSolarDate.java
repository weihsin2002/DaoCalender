package org.dao.calendar.model;

import org.dao.calendar.config.Configurator;
import org.dao.calendar.utils.Utils;

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
	
	public String lunarYearToGanZhi(int lunarYear){
		return Configurator.tianGan()[(lunarYear-4) % 10] + Configurator.diZhi()[(lunarYear-4) % 12];
	}

	public LuniSolarDate SolarToLunar(SolarDate solar) {
		int index = solar.solarYear() - Configurator.solar_1_1()[0];
		int data = (solar.solarYear() << 9) | (solar.solarMonth() << 5)
				| (solar.solarDay());
		int solar11 = 0;
		if (Configurator.solar_1_1()[index] > data) {
			index--;
		}
		solar11 = Configurator.solar_1_1()[index];
		int y = Utils.GetBitInt(solar11, 12, 9);
		int m = Utils.GetBitInt(solar11, 4, 5);
		int d = Utils.GetBitInt(solar11, 5, 0);
		long offset = Utils.SolarToInt(solar.solarYear(), solar.solarMonth(),
				solar.solarDay()) - Utils.SolarToInt(y, m, d);

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
		
		LuniSolarDate lunar = new LuniSolarDate(lunarY, lunarM, lunarD, isleap);
		
		return lunar;
	}
}
