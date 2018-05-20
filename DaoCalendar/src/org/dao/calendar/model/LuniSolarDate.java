package org.dao.calendar.model;

import org.apache.log4j.Logger;
import org.dao.calendar.config.Configurator;
import org.dao.calendar.utils.Utils;

import com.google.gson.JsonObject;

public class LuniSolarDate {
	private static final Logger logger = Logger.getLogger(LuniSolarDate.class);

	private boolean isleap;
	private int day;
	private int month;
	private int year;
	private int hour;
	private int minute;
	private int sec;
	private int days;
	private int leap; // leap month
	
	FourPillars fourPillars;
	
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
		
		setFourPillars();
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
		
		setFourPillars();
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
		
		LuniSolarDate lunar = new LuniSolarDate(lunarY, lunarM, lunarD, solar.hour(), solar.min(), solar.sec(), isleap);
		
		return lunar;
	}
	
	private void setFourPillars() {
		logger.info("Lunar Year = " + this.year + " Lunar Month = " + this.month + " Lunar Day = " + this.day + " Hour = " + this.hour + " Lunar days=" + this.days);
		
		int gyear = this.year - 1930;
	    int ng = (gyear+7) % 10 == 0 ? 10 : (gyear+7) % 10;
	    int nz = (gyear+7) % 12 == 0 ? 12 : (gyear+7) % 12;
	    
	    int yz = (this.month+2)%12 == 0 ? 12 : (this.month+2)%12;
	    
	    int yg = Configurator.yuebynian()[ng];
	    int yuezhu = yz < 3 ? yz+12 : yz;
	    yg = (yg+yuezhu-3)%10 == 0 ? 10 : (yg+yuezhu-3)%10;
	    int rg = (this.days+2)%10==0 ? 10: (this.days+2)%10;
	    int rz = (this.days+6)%12==0 ? 12: (this.days+6)%12;

	    int sg = Configurator.shibyri()[rg];
	    
	    int i=1;
	    for(; i<=12; i++) {
	      if(Configurator.hourNum()[i] > this.hour*100+this.minute && i==1) {
	        break;
	      }else if(Configurator.hourNum()[i] <= this.hour*100+this.minute && i==12) {
	        i=1;
	        rg = (rg+1)%10==0?10:(rg+1)%10;
	        rz = (rz+1)%12==0?12:(rz+1)%12;
	        sg = Configurator.shibyri()[rg];
	        break;
	      }else if(Configurator.hourNum()[i] <= this.hour*100+this.minute && Configurator.hourNum()[i+1] > this.hour*100+this.minute) {
	        i++;
	        break;
	      }
	    }
	    
	    int sz = i;
	    sg = (sz+sg-1)%10==0 ? 10 : (sz+sg-1)%10;
	    
	    logger.info("ng=" + ng + " nz=" + nz + " yg=" + yg + " yz=" + yz + " rg=" + rg + " rz=" +  rz + " sg=" + sg + " sz=" + sz);
	    
	    fourPillars = new FourPillars(ng, nz, yg, yz, rg, rz, sg, sz);

	    logger.info("Four Pillars = " + fourPillars.toString());
	}	
	
	public FourPillars fourPillars () {
		return fourPillars;
	}
	
	public String toString () {
		return "Year=" + this.year + ",Month=" + this.month + ",Day=" + this.day + ",Hour=" + this.hour + ",Minute="+this.minute + ",Second="+this.sec + ",isLeap=" + this.isleap + ",days=" + this.days + ",Four Pillars=" + fourPillars.toString();
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
		json.addProperty("days", this.days);
		json.add("FourPillars", this.fourPillars.toJson());
		
		return json;
	}
}