package org.dao.calendar.config;

import org.apache.log4j.Logger;

public class Configurator {
	private static final Logger logger = Logger.getLogger(Configurator.class);
	
	private static int[] lunar_month_days = { 1887, 0x1694, 0x16aa, 0x4ad5,
			0xab6, 0xc4b7, 0x4ae, 0xa56, 0xb52a, 0x1d2a, 0xd54, 0x75aa, 0x156a,
			0x1096d, 0x95c, 0x14ae, 0xaa4d, 0x1a4c, 0x1b2a, 0x8d55, 0xad4,
			0x135a, 0x495d, 0x95c, 0xd49b, 0x149a, 0x1a4a, 0xbaa5, 0x16a8,
			0x1ad4, 0x52da, 0x12b6, 0xe937, 0x92e, 0x1496, 0xb64b, 0xd4a,
			0xda8, 0x95b5, 0x56c, 0x12ae, 0x492f, 0x92e, 0xcc96, 0x1a94,
			0x1d4a, 0xada9, 0xb5a, 0x56c, 0x726e, 0x125c, 0xf92d, 0x192a,
			0x1a94, 0xdb4a, 0x16aa, 0xad4, 0x955b, 0x4ba, 0x125a, 0x592b,
			0x152a, 0xf695, 0xd94, 0x16aa, 0xaab5, 0x9b4, 0x14b6, 0x6a57,
			0xa56, 0x1152a, 0x1d2a, 0xd54, 0xd5aa, 0x156a, 0x96c, 0x94ae,
			0x14ae, 0xa4c, 0x7d26, 0x1b2a, 0xeb55, 0xad4, 0x12da, 0xa95d,
			0x95a, 0x149a, 0x9a4d, 0x1a4a, 0x11aa5, 0x16a8, 0x16d4, 0xd2da,
			0x12b6, 0x936, 0x9497, 0x1496, 0x1564b, 0xd4a, 0xda8, 0xd5b4,
			0x156c, 0x12ae, 0xa92f, 0x92e, 0xc96, 0x6d4a, 0x1d4a, 0x10d65,
			0xb58, 0x156c, 0xb26d, 0x125c, 0x192c, 0x9a95, 0x1a94, 0x1b4a,
			0x4b55, 0xad4, 0xf55b, 0x4ba, 0x125a, 0xb92b, 0x152a, 0x1694,
			0x96aa, 0x15aa, 0x12ab5, 0x974, 0x14b6, 0xca57, 0xa56, 0x1526,
			0x8e95, 0xd54, 0x15aa, 0x49b5, 0x96c, 0xd4ae, 0x149c, 0x1a4c,
			0xbd26, 0x1aa6, 0xb54, 0x6d6a, 0x12da, 0x1695d, 0x95a, 0x149a,
			0xda4b, 0x1a4a, 0x1aa4, 0xbb54, 0x16b4, 0xada, 0x495b, 0x936,
			0xf497, 0x1496, 0x154a, 0xb6a5, 0xda4, 0x15b4, 0x6ab6, 0x126e,
			0x1092f, 0x92e, 0xc96, 0xcd4a, 0x1d4a, 0xd64, 0x956c, 0x155c,
			0x125c, 0x792e, 0x192c, 0xfa95, 0x1a94, 0x1b4a, 0xab55, 0xad4,
			0x14da, 0x8a5d, 0xa5a, 0x1152b, 0x152a, 0x1694, 0xd6aa, 0x15aa,
			0xab4, 0x94ba, 0x14b6, 0xa56, 0x7527, 0xd26, 0xee53, 0xd54, 0x15aa,
			0xa9b5, 0x96c, 0x14ae, 0x8a4e, 0x1a4c, 0x11d26, 0x1aa4, 0x1b54,
			0xcd6a, 0xada, 0x95c, 0x949d, 0x149a, 0x1a2a, 0x5b25, 0x1aa4,
			0xfb52, 0x16b4, 0xaba, 0xa95b, 0x936, 0x1496, 0x9a4b, 0x154a,
			0x136a5, 0xda4, 0x15ac };
	
	private static int[] solar_1_1 = { 1887, 0xec04c, 0xec23f, 0xec435, 0xec649,
			0xec83e, 0xeca51, 0xecc46, 0xece3a, 0xed04d, 0xed242, 0xed436,
			0xed64a, 0xed83f, 0xeda53, 0xedc48, 0xede3d, 0xee050, 0xee244,
			0xee439, 0xee64d, 0xee842, 0xeea36, 0xeec4a, 0xeee3e, 0xef052,
			0xef246, 0xef43a, 0xef64e, 0xef843, 0xefa37, 0xefc4b, 0xefe41,
			0xf0054, 0xf0248, 0xf043c, 0xf0650, 0xf0845, 0xf0a38, 0xf0c4d,
			0xf0e42, 0xf1037, 0xf124a, 0xf143e, 0xf1651, 0xf1846, 0xf1a3a,
			0xf1c4e, 0xf1e44, 0xf2038, 0xf224b, 0xf243f, 0xf2653, 0xf2848,
			0xf2a3b, 0xf2c4f, 0xf2e45, 0xf3039, 0xf324d, 0xf3442, 0xf3636,
			0xf384a, 0xf3a3d, 0xf3c51, 0xf3e46, 0xf403b, 0xf424e, 0xf4443,
			0xf4638, 0xf484c, 0xf4a3f, 0xf4c52, 0xf4e48, 0xf503c, 0xf524f,
			0xf5445, 0xf5639, 0xf584d, 0xf5a42, 0xf5c35, 0xf5e49, 0xf603e,
			0xf6251, 0xf6446, 0xf663b, 0xf684f, 0xf6a43, 0xf6c37, 0xf6e4b,
			0xf703f, 0xf7252, 0xf7447, 0xf763c, 0xf7850, 0xf7a45, 0xf7c39,
			0xf7e4d, 0xf8042, 0xf8254, 0xf8449, 0xf863d, 0xf8851, 0xf8a46,
			0xf8c3b, 0xf8e4f, 0xf9044, 0xf9237, 0xf944a, 0xf963f, 0xf9853,
			0xf9a47, 0xf9c3c, 0xf9e50, 0xfa045, 0xfa238, 0xfa44c, 0xfa641,
			0xfa836, 0xfaa49, 0xfac3d, 0xfae52, 0xfb047, 0xfb23a, 0xfb44e,
			0xfb643, 0xfb837, 0xfba4a, 0xfbc3f, 0xfbe53, 0xfc048, 0xfc23c,
			0xfc450, 0xfc645, 0xfc839, 0xfca4c, 0xfcc41, 0xfce36, 0xfd04a,
			0xfd23d, 0xfd451, 0xfd646, 0xfd83a, 0xfda4d, 0xfdc43, 0xfde37,
			0xfe04b, 0xfe23f, 0xfe453, 0xfe648, 0xfe83c, 0xfea4f, 0xfec44,
			0xfee38, 0xff04c, 0xff241, 0xff436, 0xff64a, 0xff83e, 0xffa51,
			0xffc46, 0xffe3a, 0x10004e, 0x100242, 0x100437, 0x10064b, 0x100841,
			0x100a53, 0x100c48, 0x100e3c, 0x10104f, 0x101244, 0x101438,
			0x10164c, 0x101842, 0x101a35, 0x101c49, 0x101e3d, 0x102051,
			0x102245, 0x10243a, 0x10264e, 0x102843, 0x102a37, 0x102c4b,
			0x102e3f, 0x103053, 0x103247, 0x10343b, 0x10364f, 0x103845,
			0x103a38, 0x103c4c, 0x103e42, 0x104036, 0x104249, 0x10443d,
			0x104651, 0x104846, 0x104a3a, 0x104c4e, 0x104e43, 0x105038,
			0x10524a, 0x10543e, 0x105652, 0x105847, 0x105a3b, 0x105c4f,
			0x105e45, 0x106039, 0x10624c, 0x106441, 0x106635, 0x106849,
			0x106a3d, 0x106c51, 0x106e47, 0x10703c, 0x10724f, 0x107444,
			0x107638, 0x10784c, 0x107a3f, 0x107c53, 0x107e48 };

	
	private static String JieQi[] = {  
			   "Spring Equinox","Pure Brightness","Grain Rain","Start of Summer","Grain Full","Grain in Ear","Summer Solstice","Slight Heat","Great Heat","Start of Autumn","The End of Heat","White Dew",  
			   "Autumn Equinox","Cold Dew","Frost Descent","Start of Winter","Light Snow","Heavy Snow","Winter Solstice","Lesser Cold","Greater Cold","Start of Spring","Rain Water","Insects Awaken"};  
	
	private static String[] tianGan = {"","Jia","Yi","Bing","Ding","Wu","Ji","Geng","Xin","Ren","Gui"};
	private static String[] diZhi = {"","Zi","Chou","Yin","Mao","Chen","Si","Wu","Wei","Shen","You","Xu","Hai"};
	
	private static String lunarMonth[] = { "First Lunar Month", "Second Lunar Month", "Third Lunar Month", "Fourth Lunar Month", "Fifth Lunar Month", "Sixth Lunar Month", "Seventh Lunar Month", "Eighth Lunar Month", "Ninth Lunar Month", "Tenth Lunar Month", "Eleventh Lunar Month", "Twelfth Lunar Month" };
	
	private static int[] yueByNian = {0,3,5,7,9,1,3,5,7,9,1};
	private static int[] shiByRi =   {0,1,3,5,7,9,1,3,5,7,9};

	
	//为h*100+m。子时序号为1，最大为59分，丑时序号为2，最大259分
	private static int[] HOURNUM = {0,60,300,500,700,900,1100,1300,1500,1700,1900,2100,2300};
	//由时辰反推12时辰，如0点为序号1即子时，1点为序号2即丑时
	private static int[] RHOURNUM = {1,2,2,3,3,4,4,5,5,6,6,7,7,8,8,9,9,10,10,11,11,12,12,0};
	//干支阴阳
	private static int[] GANYINYANG = {0,1,0,1,0,1,0,1,0,1,0};
	private static int[] ZIYINYANG =  {0,1,0,1,0,1,0,1,0,1,0,1,0};
	  
    private static final String[] jiazhi = {
            "JiaZi", "YiChou", "BingYen", "DingMou", "WuChen", "JiSi", "GenWu", "XinWei", "RenShen", "GuiYou",
            "JiaXu", "YiHai", "BingZi", "DingChou", "WuYin", "JiMou", "GenChen", "XinSi", "RenWu", "GuiWei",
            "JiaShen", "YiYou", "BingXu", "DingHai", "WuZi", "JiChou", "GenYin", "XinMou", "RenChen", "GuiSi",
            "JiaWu", "YiWei", "BingShen", "DingYou", "WuXu", "JiHai", "GenZi", "XinChou", "RenYin", "GuiMou",
            "JiaChen", "YiSi", "BingWu", "DingWeu", "WuShen", "JiYou", "GenXu", "XinHai", "RenZi", "GuiChou",
            "JiaYin", "YiMou", "BingChen", "DingSi", "WuWu", "JiWei", "GenShen", "XinYou", "RenXu", "GuiHai"
        };
    
    private final static String[] Gan = {"Jia","Yi","Bing","Ding","Wu","Ji","Geng","Xin","Ren","Gui"};
    private final static String[] Zhi = {"Zi","Chou","Yin","Mao","Chen","Si","Wu","Wei","Shen","You","Xu","Hai"};

    //每个节气上元头一天的干支不是甲子或甲午，就是己卯或己酉；
    //中元头一天的干支不是甲申或甲寅，就是己巳乙亥；
    //下元头一天的干支不是甲戌或甲辰，就是己丑或己未。
    private static final int[][][] sanyuan = new int[4][11][13];
    
    private static final String[] xunname = {"","JiaZi(Wu)","JiaXu(Ji)","JiaShen(Geng)","JiaWu(Xin)","JiaChen(Ren)","JiaYen(Gui)"};
    
    //甲子（戊）旬、甲戌（己）旬、甲申（庚）旬、甲午（辛）旬、甲辰（壬）旬、甲寅（癸）旬；分别对应数字：1，2，3，4，5，6；
    //60甲子共分6旬，每旬分别对应数字：0甲子，10甲戌，8甲申，6甲午，4甲辰，2甲寅  此数即（旬支-旬干+12）%12
    //旬0对应： 旬序数xunshu[0]=1,对应旬名为：xunname[1],旬支为xunzi[0],旬干为xungan[0]
    private static final int[] xunshu = {1, 0, 6, 0, 5, 0, 4, 0, 3, 0, 2}; 
    
    private static final String[] qiDivision = {"early", "middle", "late"}; 
        
	private static final String dbLocation = "geodb/GeoLite2-City.mmdb";
    
	static {
		initialize();
	}

	private Configurator(){}

	private static void initialize() {
		logger.info("Initializing Calendar Configurator");
		
	    sanyuan[1][1][1] = sanyuan[1][1][7] = sanyuan[1][6][4] = sanyuan[1][6][10] = 1;
	    sanyuan[2][1][3] = sanyuan[2][1][9] = sanyuan[2][6][6] = sanyuan[2][6][12] = 1;
	    sanyuan[3][1][5] = sanyuan[3][1][11] = sanyuan[3][6][2] = sanyuan[3][6][8] = 1;
	}

	public static int[] lunar_month_days() {
		return lunar_month_days;
	}

	public static String[] jieQi() {
		return JieQi;
	}

	public static String[] tianGan() {
		return tianGan;
	}

	public static String[] diZhi() {
		return diZhi;
	}

	public static int[] solar_1_1() {
		return solar_1_1;
	}

	public static String[] lunarMonth() {
		return lunarMonth;
	}

	public static int[] yuebynian() {
		return yueByNian;
	}

	public static int[] shibyri() {
		return shiByRi;
	}

	public static int[] hourNum() {
		return HOURNUM;
	}

	public static String[] jiazhi() {
		return jiazhi;
	}

	public static String[] gan() {
		return Gan;
	}

	public static String[] zhi() {
		return Zhi;
	}

	public static int[][][] sanyuan() {
		return sanyuan;
	}

	public static String[] xunname() {
		return xunname;
	}

	public static int[] xunshu() {
		return xunshu;
	}

	public static String dblocation() {
		return dbLocation;
	}
	
	public static String[] qiDivision () {
		return qiDivision;
	}
}
