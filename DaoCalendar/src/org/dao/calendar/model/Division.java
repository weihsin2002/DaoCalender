package org.dao.calendar.model;

public enum Division {
	EARLY (1, "early"),
	MIDDLE (2, "middle"),
	LATE (3, "late");
	
	private int order;
	private String yuan;
	
	Division (int order, String yuan) {
		this.order = order;
		this.yuan = yuan;
	}

	public int order() {
		return order;
	}

	public String yuan() {
		return yuan;
	}
	
	public static String fromOrder(int order) {
		for (Division d : Division.values()) {
			if (order == d.order()) {
				return d.yuan();
			}
		}
		return null;
	}
	
	public static int fromYuan(String yuan) {
		for (Division d : Division.values()) {
			if (yuan.equalsIgnoreCase(d.yuan())) {
				return d.order();
			}
		}
		return -1;
	}
}