package org.dao.calendar.model;

import com.google.gson.JsonObject;

public class Term {
	private String currentTerm;
	private int thisYear;
	private int thisMonth;
	private int thisDay;

	private String nextTerm;
	private int termYear;
	private int termMonth;
	private int termDay;

	public Term (String currentTerm, int thisYear, int thisMonth, int thisDay, String nextTerm, int termYear, int termMonth, int termDay) {
		 this.currentTerm = currentTerm;
		 this.thisYear = thisYear;
		 this.thisMonth = thisMonth;
		 this.thisDay = thisDay;

		 this.nextTerm = nextTerm;
		 this.termYear = termYear;
		 this.termMonth = termMonth;
		 this.termDay = termDay;
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
		return ("thisTerm=" + this.currentTerm + ",thisYear=" + this.thisYear + ",thisMonth=" + this.thisMonth + ",thisDay=" + this.thisDay + ",nextTerm=" + this.nextTerm + ",nextYear=" + this.termYear + ",nextMonth=" + this.termMonth + ",nextDay="+this.termDay);
	}
	
	public JsonObject toJson() {
		JsonObject json = new JsonObject();
		json.addProperty("thisTerm", this.currentTerm);
		json.addProperty("thisYear", this.thisYear);
		json.addProperty("thisMonth", this.thisMonth);
		json.addProperty("thisDay", this.thisDay);

		json.addProperty("nextTerm", this.nextTerm);
		json.addProperty("termYear", this.termYear);
		json.addProperty("termMonth", this.termMonth);
		json.addProperty("termDay", this.termDay);
		
		return json;
	}
	
	
}
