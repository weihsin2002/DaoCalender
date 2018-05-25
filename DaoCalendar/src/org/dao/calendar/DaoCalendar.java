package org.dao.calendar;

import java.util.Date;

import org.dao.calendar.model.FourPillars;
import org.dao.calendar.model.LuniSolarDate;
import org.dao.calendar.model.QiDivision;
import org.dao.calendar.model.SolarDate;
import org.dao.calendar.utils.Utils;
import org.dao.calendar.model.QiTerm;

public class DaoCalendar {
	SolarDate solarDate;
	LuniSolarDate luniSolarDate;
	FourPillars fourPillars;
	QiDivision qiDivision;
	SolarDate trueSolarDate;
	LuniSolarDate trueLuniSolarDate;
	QiTerm qiTerm;
	QiTerm trueQiTerm;
	
	public DaoCalendar (Date date) {
		this.solarDate = new SolarDate(date);
		this.luniSolarDate = new LuniSolarDate(solarDate);
		this.fourPillars = new FourPillars(solarDate, luniSolarDate);
		this.qiDivision = new QiDivision(fourPillars);
		this.trueSolarDate = new SolarDate(Utils.calTrueSolarTime(date));
		this.trueLuniSolarDate = new LuniSolarDate(trueSolarDate);
		
		SolarTerms solarTerms = new SolarTerms();
		this.qiTerm = solarTerms.Term(solarDate);
		this.trueQiTerm = solarTerms.Term(trueSolarDate);
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
}
