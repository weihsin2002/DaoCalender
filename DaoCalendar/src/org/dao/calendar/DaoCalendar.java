package org.dao.calendar;

import java.util.Date;
import java.util.logging.Logger;

import org.dao.calendar.model.FourPillars;
import org.dao.calendar.model.LuniSolarDate;
import org.dao.calendar.model.QiDivision;
import org.dao.calendar.model.SolarDate;
import org.dao.calendar.utils.Utils;

import com.google.gson.JsonObject;

import org.dao.calendar.model.QiTerm;

public class DaoCalendar {
	
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	SolarDate solarDate;
	LuniSolarDate luniSolarDate;
	FourPillars fourPillars;
	QiTerm qiTerm;
	QiDivision qiDivision;
	
	
	SolarDate trueSolarDate;
	LuniSolarDate trueLuniSolarDate;
	FourPillars trueFourPillars;
	QiTerm trueQiTerm;
	QiDivision trueQiDivision;
	
	public DaoCalendar (Date date) {
		SolarTerms solarTerms = new SolarTerms();
		
		this.solarDate = new SolarDate(date);
		this.luniSolarDate = new LuniSolarDate(this.solarDate);
		this.fourPillars = new FourPillars(solarDate, this.luniSolarDate);
		this.qiTerm = solarTerms.Term(this.solarDate);
		this.qiDivision = new QiDivision(this.fourPillars);
		
		
		this.trueSolarDate = new SolarDate(Utils.calTrueSolarTime(date));
		if (this.trueSolarDate != null) {
			this.trueLuniSolarDate = new LuniSolarDate(this.trueSolarDate);
			this.trueFourPillars = new FourPillars(this.trueSolarDate, this.trueLuniSolarDate);
			this.trueQiTerm = solarTerms.Term(this.trueSolarDate);
			this.trueQiDivision = new QiDivision(this.trueFourPillars);
		}
	}

	public SolarDate solarDate() {
		return solarDate;
	}

	public LuniSolarDate luniSolarDate() {
		return luniSolarDate;
	}

	public FourPillars fourPillars() {
		return fourPillars;
	}

	public QiDivision qiDivision() {
		return qiDivision;
	}

	public SolarDate trueSolarDate() {
		return trueSolarDate;
	}

	public LuniSolarDate trueLuniSolarDate() {
		return trueLuniSolarDate;
	}

	public QiTerm qiTerm() {
		return qiTerm;
	}

	public QiTerm trueQiTerm() {
		return trueQiTerm;
	}
	
	public QiDivision trueQiDivision() {
		return trueQiDivision;
	}
	
	public JsonObject toJson () {
		JsonObject json = new JsonObject();
		JsonObject solarJson = new JsonObject();
		solarJson.add("solarDate", solarDate.toJson());
		solarJson.add("luniSolarDate", luniSolarDate.toJson());
		solarJson.add("fourPillars", fourPillars.toJson());
		solarJson.add("qiTerm", qiTerm.toJson());
		solarJson.add("qiDivision", qiDivision.toJson());
		
		JsonObject trueSolarJson = new JsonObject();
		trueSolarJson.add("trueSolarDate", trueSolarDate.toJson());
		trueSolarJson.add("trueLuniSolarDate", trueLuniSolarDate.toJson());
		trueSolarJson.add("trueFourPillars", fourPillars.toJson());
		trueSolarJson.add("trueQiTerm", trueQiTerm.toJson());
		trueSolarJson.add("trueQiDivision", trueQiDivision.toJson());
		
		json.add("solarCalendar", solarJson);
		json.add("trueSolarCalendar", trueSolarJson);
		
		return json;
	}
}
