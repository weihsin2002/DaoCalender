package org.dao.calendar.model;

public enum LunarMonth {
	FIRST_LUNAR_MONTH (1, "First Lunar Month"),
	SECOND_LUNAR_MONTH (2, "Second Lunar Month"),
	THIRD_LUNAR_MONTH (3, "Third Lunar Month"),
	FOURTH_LUNAR_MONTH (4, "Fourth Lunar Month"),
	FIFTH_LUNAR_MONTH (5, "Fifth Lunar Month"),
	SIXTH_LUNAR_MONTH (6, "Sixth Lunar Month"),
	SEVENTH_LUNAR_MONTH (7, "Seventh Lunar Month"),
	EIGHTH_LUNAR_MONTH (8, "Eighth Lunar Month"),
	NINTH_LUNAR_MONTH (9, "Ninth Lunar Month"),
	TENTH_LUNAR_MONTH (10, "Tenth Lunar Month"),
	ELEVENTH_LUNAR_MONTH (11, "Eleventh Lunar Month"),
	TWELFTH_LUNAR_MONTH (12, "Twelfth Lunar Month");
	
	private int order;
	private String lunarMonth;
	
	LunarMonth (int order, String lunarMonth) {
		this.order = order;
		this.lunarMonth = lunarMonth;
	}
	
	public int order() {
		return order;
	}

	public String lunarMonth() {
		return lunarMonth;
	}

	public static String fromOrder(int order) {
		for (LunarMonth l : LunarMonth.values()) {
			if (order == l.order()) {
				return l.lunarMonth();
			}
		}
		return null;
	}
	
	public static int fromLunarMonth(String lunarMonth) {
		for (LunarMonth l : LunarMonth.values()) {
			if (lunarMonth.equalsIgnoreCase(l.lunarMonth())) {
				return l.order();
			}
		}
		return -1;
	}
}
