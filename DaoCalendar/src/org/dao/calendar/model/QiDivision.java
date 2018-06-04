package org.dao.calendar.model;

import org.apache.log4j.Logger;
import org.dao.calendar.config.Configurator;
import org.dao.calendar.test.Test;
import org.dao.core.EarthlyBranches;
import org.dao.core.HeavenlyStems;

import com.google.gson.JsonObject;

public class QiDivision {
	String stemLead; //FuTou  节气“上元”的第一天的干支
	String hourLead; //XunShou
	String division; //Yuan : early, middle, late
	int days; // the X days from the XunShou
	
	private static final Logger logger = Logger.getLogger(QiDivision.class);
	
	public QiDivision (FourPillars fourPillars) {
	    this.hourLead = Configurator.xunname()[getXunShu(fourPillars.sg(), fourPillars.sz())];

		getFuTou(fourPillars.rg(), fourPillars.rz());
		
	}
	
	public QiDivision (SolarDate solarDate, LuniSolarDate luniSolarDate) {
		FourPillars fourPillars = new FourPillars(solarDate, luniSolarDate);
	    this.hourLead = Configurator.xunname()[getXunShu(fourPillars.sg(), fourPillars.sz())];

		getFuTou(fourPillars.rg(), fourPillars.rz());
	}
	
	private void getFuTou(int dg, int dz) {
		int[] futou = new int[4];
		int day=0,g=0,z=0;

	    if(dg==6 || dg==1) { //DiGan == Jia or Ji
	      g = dg;
	      z = dz;
	      futou = new int[]{0,dg,dz,0};
	    }else if(futou[1]==0 && dg>6) {
	      g = 6;
	      day = dg - g;
	      z = (dz - day)<=0 ? dz - day + 12 : dz - day;
	          futou = new int[]{day,g,z,0};
	    }else if(futou[1]==0 && dg>1) {
	      g = 1;
	      day = dg - g;
	      z = (dz - day)<=0 ? dz - day + 12 : dz - day;
	          futou = new int[]{day,g,z,0};
	    }

	    for(int i=1; i<4; i++) {
	      if (Configurator.sanyuan()[i][g][z] == 1) {
	        futou[3] = i;
	        break;
	      }
	    }

	    this.stemLead = HeavenlyStems.fromOrder(futou[1])+ EarthlyBranches.fromOrder(futou[2]);
	    this.days = futou[0]+1;
	    		
	    this.division = Division.fromOrder(futou[3]);
	  }
	
	  private int getXunShu(int sg, int sz) {
		    return Configurator.xunshu()[(sz-sg+120)%12];
		  }
	
	public String stemLead() {
		return stemLead;
	}
	public String hourLead() {
		return hourLead;
	}
	public String division() {
		return division;
	}
	public int getDays() {
		return days;
	}
	
	public String toString() {
		return "stemLead" + this.stemLead + "hourLead" + this.hourLead + "division" + this.division + "days" + this.days;
	}
	
	public JsonObject toJson() {
		JsonObject json = new JsonObject();
		json.addProperty("stemLead", this.stemLead);
		json.addProperty("hourLead", this.hourLead);
		json.addProperty("divsion", division);
		json.addProperty("days", this.days);
		
		return json;
	}

}
