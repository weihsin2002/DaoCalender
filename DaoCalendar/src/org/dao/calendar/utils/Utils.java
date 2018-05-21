package org.dao.calendar.utils;

import org.dao.calendar.config.Configurator;
import org.dao.calendar.model.LuniSolarDate;
import org.dao.calendar.model.SolarDate;

public class Utils {
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

		return SolarFromInt(SolarToInt(y, m, d) + offset - 1);
	}

	  public static int getGanNumber (String tianGang) {
		  for (int i=0; i<10; i++) {
			  if (tianGang.equalsIgnoreCase(Configurator.tianGan()[i])) {
				  return i;
			  }
		  }
		  
		  return -1;
	  }
	  
	  public static int getZhiNumber (String diZhi) {
		  for (int i=0; i<12; i++) {
			  if (diZhi.equalsIgnoreCase(Configurator.diZhi()[i])) {
				  return i;
			  }
		  }
		  
		  return -1;		  
	  }
}
