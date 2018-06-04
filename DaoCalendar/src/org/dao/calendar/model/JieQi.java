package org.dao.calendar.model;

public enum JieQi {
	START_OF_SPRING (1, "Start of Spring"),
	RAIN_WATER (2, "Rain Water"),
	INSECTS_AWAKEN (3, "Insects Awaken"),
	SPRING_EQUINOX (4, "Spring Equinox"),
	PURE_BRIGHTNESS (5, "Pure Brightness"),
	GRAIN_RAIN (6, "Grain Rain"),	
	START_OF_SUMMER (7, "Start of Summer"),
	GRAIN_FULL (8, "Grain Full"),
	GRAIN_IN_EAR (9, "Grain in Ear"),
	SUMMER_SOLSTICE (10, "Summer Solstice"),
	SLIGHT_HEAT (11, "Slight Heat"),
	GREAT_HEAT (12, "Great Heat"),
	START_OF_AUTUMN (13, "Start of Autumn"),
	THE_END_OF_HEAT (14, "The End of Heat"),
	WHITE_DEW (15, "White Dew"),
	AUTUMN_EQUINOX (16, "Autumn Equinox"),
	COLD_DEW (17, "Cold Dew"),
	FROST_DESCENT (18, "Frost Descent"),
	START_OF_WINTER (19, "Start of Winter"),
	LIGHT_SNOW (20, "Light Snow"),
	HEAVY_SNOW (21, "Heavy Snow"),
	WINTER_SOLSTICE (22, "Winter Solstice"),
	LESSER_COLD (23, "Lesser Cold"),
	GREATER_COLD (24, "Greater Cold");

	
	private int order;
	private String jieQi;
	
	JieQi (int order, String jieQi) {
		this.order = order;
		this.jieQi = jieQi;
	}

	public int order() {
		return order;
	}

	public String jieQi() {
		return jieQi;
	}

	public static String fromOrder(int order) {
		for (JieQi j : JieQi.values()) {
			if (order == j.order()) {
				return j.jieQi();
			}
		}
		return null;
	}
	
	public static int fromJieQi(String jieQi) {
		for (JieQi j : JieQi.values()) {
			if (jieQi.equalsIgnoreCase(j.jieQi())) {
				return j.order();
			}
		}
		return -1;
	}
}
