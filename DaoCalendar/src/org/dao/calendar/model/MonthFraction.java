package org.dao.calendar.model;

public class MonthFraction {
	String stemLead; //FuTou
	String hourLead; //XunShou
	String fraction; //Yuan : early, middle, late
	String days; // the X days from the XunShou
	
	public MonthFraction (LuniSolarDate luniSolarDate) {
		
	}
	
	  /**
	   * 得到符头，专用于拆补法
	   * @return
	   */
	  public int[] getFutou(int dg, int dz) {
	    int day=0,g=0,z=0;
	    int[] futou = new int[4];

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
	      if (QiMen.sanyuan[i][g][z] == 1) {
	        futou[3] = i;
	        break;
	      }
	    }

	    return futou;
	  }

	  private String getWhichYuan(int i) {
		    String str = "";
		    if(i==1)
		      str = "上元";
		    else if(i==2)
		      str = "中元";
		    else if(i==3)
		      str = "下元";

		    return str;
		  }
	
	public String getStemLead() {
		return stemLead;
	}
	public String getHourLead() {
		return hourLead;
	}
	public String getFraction() {
		return fraction;
	}
	public String getDays() {
		return days;
	}
}
