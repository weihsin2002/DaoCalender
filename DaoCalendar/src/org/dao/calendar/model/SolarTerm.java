package org.dao.calendar.model;

import java.util.Date;

public class SolarTerm {
	private Date time;
	private String qi;
	
	public SolarTerm (Date time, String qi) {
		//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		this.qi = qi;
		this.time = time;
	}
	
	public Date time() {
		return time;
	}
	public String qi() {
		return qi;
	}
}
