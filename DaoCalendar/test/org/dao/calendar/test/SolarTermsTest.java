package org.dao.calendar.test;

import java.util.Date;

import org.apache.log4j.Logger;
import org.dao.calendar.SolarTerms;
import org.dao.calendar.config.Configurator;
import org.dao.calendar.model.SolarDate;
import org.dao.calendar.model.Term;

public class SolarTermsTest {

	private static final Logger logger = Logger.getLogger(SolarTermsTest.class);
	
	public static void main(String[] args) {
		SolarTermsTest solarTermsTest = new SolarTermsTest();
		solarTermsTest.JQtest(2018);
		
		SolarTerms solarTerms = new SolarTerms();
		SolarDate solarDate = new SolarDate(new Date());
		Term term = solarTerms.Term(solarDate);
		
		logger.info(term.toString());
//		solarTerms.paiYue(2018);
	}
	
	public void JQtest(int y) { // 节气使计算范例,y是年分,这是个测试函数
		SolarTerms solarTerms = new SolarTerms();
		  double jd = 365.2422 * (y - 2000), q;  
		  String s1, s2;  
		  for (int i = 0; i < 24; i++) {  
		   q = solarTerms.jiaoCal(jd + i * 15.2, i * 15, 0);  
		   q = q + solarTerms.j2000() + (double)8 / 24; // 计算第i个节气(i=0是春风),结果转为北京时  
		   solarTerms.setFromJD(q, true);  
		   s1 = solarTerms.toStr(); // 将儒略日转成世界时  
		   solarTerms.setFromJD(q, false);  
		   s2 = solarTerms.toStr(); // 将儒略日转成日期格式(输出日期形式的力学时)  
		   logger.info(Configurator.jieQi()[i] + " : " + s1 + " " + s2); // 显示  
		  }    
		 }  
}
