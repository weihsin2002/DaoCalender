package org.dao.calendar.model;

import com.google.gson.JsonObject;

public class Term {
	private String currentTerm;
	private int thisYear;
	private int thisMonth;
	private int thisDay;
	private int thisHour;
	private int thisMinute;
	private int thisSecond;

	private String nextTerm;
	private int termYear;
	private int termMonth;
	private int termDay;
	private int termHour;
	private int termMinute;
	private int termSecond;

	public Term (String currentTerm, int thisYear, int thisMonth, int thisDay, int thisHour, int thisMinute, int thisSecond, String nextTerm, int termYear, int termMonth, int termDay, int termHour, int termMinute, int termSecond) {
		 this.currentTerm = currentTerm;
		 this.thisYear = thisYear;
		 this.thisMonth = thisMonth;
		 this.thisDay = thisDay;
		 this.thisHour = thisHour;
		 this.thisMinute = thisMinute;
		 this.thisSecond = thisSecond;

		 this.nextTerm = nextTerm;
		 this.termYear = termYear;
		 this.termMonth = termMonth;
		 this.termDay = termDay;
		 this.termHour = termHour;
		 this.termMinute = termMinute;
		 this.termSecond = termSecond;
	}

	public String currentTerm() {
		return currentTerm;
	}

	public String nextTerm() {
		return nextTerm;
	}

	public int termYear() {
		return termYear;
	}

	public int termMonth() {
		return termMonth;
	}

	public int termDay() {
		return termDay;
	}
	
	public String toString() {
		return ("thisTerm=" + this.currentTerm + ",thisYear=" + this.thisYear + ",thisMonth=" + this.thisMonth + ",thisDay=" + this.thisDay + ",thisHour=" + this.thisHour + ",thisMinute=" + this.thisMinute + ",thisSecond=" + this.thisSecond + ",nextTerm=" + this.nextTerm + ",nextYear=" + this.termYear + ",nextMonth=" + this.termMonth + ",nextDay="+this.termDay + ",nextHour=" + this.termHour + ",nextMinute=" + this.termMinute + ",nextSecond=" + this.termSecond);
	}
	
	public JsonObject toJson() {
		JsonObject json = new JsonObject();
		json.addProperty("thisTerm", this.currentTerm);
		json.addProperty("thisYear", this.thisYear);
		json.addProperty("thisMonth", this.thisMonth);
		json.addProperty("thisDay", this.thisDay);
		json.addProperty("thisHour", this.thisHour);
		json.addProperty("thisMinute", this.thisMinute);
		json.addProperty("thisSecond", this.thisSecond);

		json.addProperty("nextTerm", this.nextTerm);
		json.addProperty("nextYear", this.termYear);
		json.addProperty("nextMonth", this.termMonth);
		json.addProperty("nextDay", this.termDay);
		json.addProperty("nextHour", this.termHour);
		json.addProperty("nextMinute", this.termMinute);
		json.addProperty("nextSecond", this.termSecond);
		
		return json;
	}
	
	
}
