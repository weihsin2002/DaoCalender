package org.dao.calendar.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Term {
	private Date time;
	private String qi;
	
	public Term (String time, String qi) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		setQi(qi);
		 
		try {
			setTime(dateFormat.parse(time));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Date time() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String qi() {
		return qi;
	}
	public void setQi(String qi) {
		this.qi = qi;
	}
}
