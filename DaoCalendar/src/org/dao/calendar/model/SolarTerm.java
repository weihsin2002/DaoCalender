package org.dao.calendar.model;

import java.util.Calendar;
import java.util.Date;

public class SolarTerm {
	private Date time;
	private String qi;
	private boolean isLeap;
	
	public SolarTerm (Date time, String qi, boolean isLeap) {
		//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		this.qi = qi;
		this.time = time;
		this.isLeap = isLeap;
	}
	
	public Date time() {
		return time;
	}
	
	public String qi() {
		return qi;
	}
	
	public boolean isLeap () {
		return isLeap;
	}
}
