package org.dao.calendar.model;

import org.dao.calendar.config.Configurator;

import com.google.gson.JsonObject;

public class FourPillars {
	  /**
	   * 四柱干支
	   * 年上起月法
	   * 日上起时法
	   */
	  private int ng = 0;
	  private int nz = 0;
	  private int yg = 0;
	  private int yz = 0;
	  private int rg = 0;
	  private int rz = 0;
	  private int sg = 0;
	  private int sz = 0;

	  public FourPillars (int ng, int nz, int yg, int yz, int rg, int rz, int sg, int sz) {
		  this.ng = ng;
		  this.yg = yg;
		  this.rg = rg;
		  this.sg = sg;
		  
		  this.nz = nz;
		  this.yz = yz;
		  this.rz = rz;
		  this.sz = sz;
	  }

	  public FourPillars (String ng, String nz, String yg, String yz, String rg, String rz, String sg, String sz) {
		  this.ng = getGanNumber(ng);
		  this.yg = getGanNumber(yg);
		  this.rg = getGanNumber(rg);
		  this.sg = getGanNumber(sg);
		  
		  this.nz = getZhiNumber(nz);
		  this.yz = getZhiNumber(yz);
		  this.rz = getZhiNumber(rz);
		  this.sz = getZhiNumber(sz);
	  }
	  
	  private int getGanNumber (String tianGang) {
		  for (int i=0; i<10; i++) {
			  if (tianGang.equalsIgnoreCase(Configurator.tianGan()[i])) {
				  return i;
			  }
		  }
		  
		  return -1;
	  }
	  
	  private int getZhiNumber (String diZhi) {
		  for (int i=0; i<12; i++) {
			  if (diZhi.equalsIgnoreCase(Configurator.diZhi()[i])) {
				  return i;
			  }
		  }
		  
		  return -1;		  
	  }

	public int ng() {
		return ng;
	}

	public int nz() {
		return nz;
	}

	public int yg() {
		return yg;
	}

	public int yz() {
		return yz;
	}

	public int rg() {
		return rg;
	}

	public int rz() {
		return rz;
	}

	public int sg() {
		return sg;
	}

	public int sz() {
		return sz;
	}
	
	public String toString () {
		return "Year GanZhi = " + Configurator.tianGan()[ng] +  Configurator.diZhi()[nz] + 
			   " Month GanZhi = " + Configurator.tianGan()[yg] + Configurator.diZhi()[yz] + 
			   " Day GanZhi = " + Configurator.tianGan()[rg] + Configurator.diZhi()[rz] + 
			   " Hour GanZhi = " + Configurator.tianGan()[sg] + Configurator.diZhi()[sz];
	}
	
	public JsonObject toJson () {
		JsonObject json = new JsonObject();
		json.addProperty("YearGanZhi", Configurator.tianGan()[ng] +  Configurator.diZhi()[nz]);
		json.addProperty("MonthGznZhi", Configurator.tianGan()[yg] + Configurator.diZhi()[yz]);
		json.addProperty("DayGanZhi", Configurator.tianGan()[rg] + Configurator.diZhi()[rz]);
		json.addProperty("HourGanZhi", Configurator.tianGan()[sg] + Configurator.diZhi()[sz]);
		
		JsonObject indexJson = new JsonObject();
		indexJson.addProperty("ng", ng);
		indexJson.addProperty("nz", nz);
		indexJson.addProperty("yg", yg);
		indexJson.addProperty("yz", yz);
		indexJson.addProperty("rg", rg);
		indexJson.addProperty("rz", rz);
		indexJson.addProperty("sg", sg);
		indexJson.addProperty("sz", sz);
		
		json.add("index", indexJson);
		
		return json;
	}
}