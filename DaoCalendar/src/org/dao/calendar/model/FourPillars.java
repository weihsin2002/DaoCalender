package org.dao.calendar.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.dao.calendar.config.Configurator;
import org.dao.core.EarthlyBranches;
import org.dao.core.HeavenlyStems;

import com.google.gson.JsonObject;

public class FourPillars {
	private Logger logger = Logger.getLogger(this.getClass());

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
		  this.ng = HeavenlyStems.fromTianGan(ng);
		  this.yg = HeavenlyStems.fromTianGan(yg);
		  this.rg = HeavenlyStems.fromTianGan(rg);
		  this.sg = HeavenlyStems.fromTianGan(sg);
		  
		  this.nz = EarthlyBranches.fromDiZhi(nz);
		  this.yz = EarthlyBranches.fromDiZhi(yz);
		  this.rz = EarthlyBranches.fromDiZhi(rz);
		  this.sz = EarthlyBranches.fromDiZhi(sz);
	  }
	  
	  public FourPillars (SolarDate solarDate, LuniSolarDate luniSolarDate) {
	    	int bazi[] = new int[8]; 
	    	
	        //1864年是甲子年，每隔六十年一个甲子
	        int idx = (luniSolarDate.year() - 1864) % 60;
	        //没有过春节的话那么年还算上一年的，此处求的年份的干支
	        String y = Configurator.jiazhi()[idx];
	                
	        //Xulun
	        bazi[0] = idx % 10;
	        bazi[1] = idx % 12;
	        
	        String m="";
	        String d="";
	        String h="";
	        idx = idx % 5;
	        int idxm=0;
	        /**
	         * 年上起月
	         * 甲己之年丙作首，乙庚之岁戊为头，
	         * 丙辛必定寻庚起，丁壬壬位顺行流，
	         * 更有戊癸何方觅，甲寅之上好追求。
	         */
	        idxm=(idx+1)*2;
	        if(idxm==10) idxm=0;
	        //求的月份的干支
	        m=HeavenlyStems.fromOrder((idxm+luniSolarDate.month()-1)%10 + 1)+ EarthlyBranches.fromOrder((luniSolarDate.month()+2-1)%12 +1);
	        
	        //Xulun
	        bazi[2]=(idxm+luniSolarDate.month()-1)%10;
	        bazi[3]=(luniSolarDate.month()+2-1)%12;
	        
	        /*求出和1900年1月31日甲辰日相差的天数 
	         * 甲辰日是第四十天
	        */
	        Date baseDate = null;
	        SimpleDateFormat chineseDateFormat = new SimpleDateFormat("yyyy-MM-dd HH mm ss");
	        
	        try {
	        	baseDate = chineseDateFormat.parse("1900-1-30 23 00 00");
	        } catch (ParseException e) {
	            logger.error("Error Parsing Base Date", e);
	        }
	        
	        Calendar cal = Calendar.getInstance();
	        cal.set(solarDate.year(), solarDate.month()-1, solarDate.day(), solarDate.hour(), solarDate.min(), solarDate.sec());
	        
	        int offset = (int) ((cal.getTime().getTime() - baseDate.getTime()) / 86400000L);
	        offset=(offset+40)%60;
	        //求的日的干支
	        d=Configurator.jiazhi()[offset];
	        
	        //Xulun
	        bazi[4]= offset%10;
	        bazi[5]= offset%12;
	        
	        /**
	         * 日上起时
	         * 甲己还生甲，乙庚丙作初，
	         * 丙辛从戊起，丁壬庚子居，
	         * 戊癸何方发，壬子是真途。  
	         */ 
	        int gHour = (cal.get(Calendar.HOUR_OF_DAY) + 1) / 2;
	        int zHour = (cal.get(Calendar.HOUR_OF_DAY) + 1) / 2;
	        offset=(offset % 5 )*2;

	        if (cal.get(Calendar.HOUR_OF_DAY) == 23) {
	        	zHour = 0;
	        }
	        
	        //求得时辰的干支   
	        h=HeavenlyStems.fromOrder((offset+gHour)%10+1)+EarthlyBranches.fromOrder(zHour+1);
	        
	        //Xulun 
	        bazi[6]=(offset+gHour)%10;
	        bazi[7]=zHour;
        
	        if (cal.get(Calendar.HOUR_OF_DAY) == 23) {
	        	gHour = 11; //(22 + 1) / 2;
	        	
	        	int gIndex = (offset+gHour)%10;
	        	
	        	gIndex ++;
	        	if (gIndex > 9) gIndex = 0; 
	        	
		        h=HeavenlyStems.fromOrder(gIndex+1)+EarthlyBranches.fromOrder(zHour+1);
		        bazi[6]=gIndex;
	        }
	        
	        logger.info(y+","+m+","+d+","+h);
	        
	        this.ng = bazi[0]+1;
	        this.nz = bazi[1]+1;
	        this.yg = bazi[2]+1;
	        this.yz = bazi[3]+1;
	        this.rg = bazi[4]+1;
	        this.rz = bazi[5]+1;
	        this.sg = bazi[6]+1;
	        this.sz = bazi[7]+1;
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
		return "Year GanZhi = " + HeavenlyStems.fromOrder(ng) +  EarthlyBranches.fromOrder(nz) + 
			   " Month GanZhi = " + HeavenlyStems.fromOrder(yg) + EarthlyBranches.fromOrder(yz) + 
			   " Day GanZhi = " + HeavenlyStems.fromOrder(rg) + EarthlyBranches.fromOrder(rz) + 
			   " Hour GanZhi = " + HeavenlyStems.fromOrder(sg) + EarthlyBranches.fromOrder(sz);
	}
	
	public JsonObject toJson () {
		JsonObject json = new JsonObject();
		json.addProperty("YearGanZhi", HeavenlyStems.fromOrder(ng) +  EarthlyBranches.fromOrder(nz));
		json.addProperty("MonthGanZhi", HeavenlyStems.fromOrder(yg) + EarthlyBranches.fromOrder(yz));
		json.addProperty("DayGanZhi", HeavenlyStems.fromOrder(rg) + EarthlyBranches.fromOrder(rz));
		json.addProperty("HourGanZhi", HeavenlyStems.fromOrder(sg) + EarthlyBranches.fromOrder(sz));
		
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