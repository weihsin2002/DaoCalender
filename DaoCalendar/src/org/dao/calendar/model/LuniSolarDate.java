package org.dao.calendar.model;

import org.apache.log4j.Logger;
import org.dao.calendar.config.Configurator;
import org.dao.calendar.utils.Utils;

public class LuniSolarDate {
	private static final Logger logger = Logger.getLogger(LuniSolarDate.class);

	private boolean isleap;
	private int day;
	private int month;
	private int year;
	private int hour;
	private int days;
	private int leap; // leap month
	
	FourPillars fourPillars;
	
	public LuniSolarDate (int year, int month, int day, boolean isleap) { // initializing lunar object by entering lunar date 
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = 0;
		this.isleap = false;
		
		this.days = Configurator.lunar_month_days()[this.year - Configurator.lunar_month_days()[0]];
		this.leap = Utils.GetBitInt(days, 4, 13);
		
		if (this.leap != 0 && this.month == this.leap) {
			this.isleap = isleap;
		}
		
		setFourPillars();
	}
	
	public LuniSolarDate (int year, int month, int day, int hour, boolean isleap) { // initializing lunar object by entering lunar date 
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hour;
		this.isleap = false;
		
		this.days = Configurator.lunar_month_days()[this.year - Configurator.lunar_month_days()[0]];
		this.leap = Utils.GetBitInt(days, 4, 13);
		
		if (this.leap != 0 && this.month == this.leap) {
			this.isleap = isleap;
		}
		
		setFourPillars();
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
		
		LuniSolarDate lunar = new LuniSolarDate(lunarY, lunarM, lunarD, solar.hour(), isleap);
		
		setFourPillars();
		
		return lunar;
	}
	
	private void setFourPillars() {
		logger.info("YEAR = " + this.year + " Month = " + this.month + " Day = " + this.day + " Hour = " + this.hour);
		
	    int ng = (year+7) % 10 == 0 ? 10 : (year+7) % 10;
	    int nz = (year+7) % 12 == 0 ? 12 : (year+7) % 12;
	    int yz = (this.month+2)%12 == 0 ? 12 : (this.month+2)%12;
	    int yg = Configurator.yuebynian()[ng];
	    int yuezhu = yz < 3 ? yz+12 : yz;
	    yg = (yg+yuezhu-3)%10 == 0 ? 10 : (yg+yuezhu-3)%10;
	    int rg = (this.days+7)%10==0 ? 10: (this.days+7)%10;
	    int rz = (this.days+5)%12==0 ? 12: (this.days+5)%12;

	    int sg = Configurator.shibyri()[rg];
	    
	    int i=1;
	    for(; i<=12; i++) {
	      if(Configurator.hourNum()[i] > this.hour && i==1) {
	        break;
	      }else if(Configurator.hourNum()[i] <= this.hour && i==12) {
	        i=1;
	        rg = (rg+1)%10==0?10:(rg+1)%10;
	        rz = (rz+1)%12==0?12:(rz+1)%12;
	        sg = Configurator.shibyri()[rg];
	        break;
	      }else if(Configurator.hourNum()[i] <= this.hour && Configurator.hourNum()[i+1] > this.hour) {
	        i++;
	        break;
	      }
	    }
	    
	    int sz = i;
	    sg = (sz+sg-1)%10==0 ? 10 : (sz+sg-1)%10;
	    
	    fourPillars = new FourPillars(ng, nz, yg, yz, rg, rz, sg, sz);
	    
	    logger.info("Four Pillars = " + fourPillars.toString());
	}	
	
	public FourPillars fourPillars () {
		return fourPillars;
	}
}