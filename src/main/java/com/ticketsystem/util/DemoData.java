package com.ticketsystem.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.alibaba.fastjson.JSONObject;
import com.ticketsystem.model.DemoOrder;

public class DemoData {
	// 倒计时时长
	public static long COUNTDOWNMILLIS = (long) (1000 * 60 * 5);
	// 每个账号预定多少张
	public static int PERSONTICKETS = 4;
	
	public static int ThreadNo = 0;
	
	public static TreeMap<String, String> OrderStatusMap = new TreeMap<String, String>();
	
	public static TreeMap<String, DemoOrder> OrderBeanMap = new TreeMap<String, DemoOrder>();
	
	public static String hotCityUrl = "http://www.flycua.com/app/hierarchy/hotCity";
	public static String queryUrl = "http://ws.51pid.com/VIBE.ASMX/AVH";
	
	public static String queryUrl2 = "http://www.flycua.com/booking/search.html";
	public static String queryUrl3 = "http://www.flycua.com/app/searchFlights/queryFlight";
	public static String loginUrl = "https://higo.flycua.com/ffp/member/login";
	public static String checkUrl = "http://www.flycua.com/app/login/queryUserStatus";
	public static String pointsInfoUrl = "http://www.flycua.com/app/searchFlights/queryPointsInfo";
	public static String chooseUrl = "http://www.flycua.com/app/searchFlights/chooseFlight";
	public static String add2CartUrl = "http://www.flycua.com/app/booking/addToCart";
	public static String bookUrl = "http://www.flycua.com/app/booking/book";
	public static String paymentDetailUrl = "http://www.flycua.com/app/booking/paymentDetails";
	public static String orderDetailUrl = "http://www.flycua.com/app/order/detail";
	public static String cancelUrl = "http://www.flycua.com/app/order/cancelOrder";
	
	public static String sHashCode = "VIBE_6c8512026e194d939046d17983b6e426";
	
	public static String CURR_YEAR = "2021";
	public static HashMap<String, String> CALENDAR_MAP = new HashMap<String, String>(){
		private static final long serialVersionUID = 1L;
	{
		put("JAN","01");
		put("FEB","02");
		put("MAR","03");
		put("APR","04");
		put("MAY","05");
		put("JUN","06");
		put("JUL","07");
		put("AUG","08");
		put("SEP","09");
		put("OCT","10");
		put("NOV","11");
		put("DEC","12");
	}};
	
	/**
	 * 系统信息
	 * @return 系统信息
	 */
	public static JSONObject getSysData() {
		Map<String, Object> sysDataMap1 = new HashMap<String, Object>();
		
		sysDataMap1.put("appKey", "f46a96420331ea3be28eaf1036af4252");
		sysDataMap1.put("secretKey", "4c4d668e890976a42ff5c3e9e76076a7");
		sysDataMap1.put("queryUrl", "http://api.panhe.net/flight/query");
		sysDataMap1.put("createUrl", "http://api.panhe.net/flight/createOrder");
		sysDataMap1.put("cancelUrl", "http://api.panhe.net/flight/cancelOrder");
		
        JSONObject sysDataJson1 = new JSONObject(sysDataMap1);
		return sysDataJson1;
	}
	
	/**
	 * 订票信息
	 * @return 订票信息
	 */
	public static JSONObject getData1() {
		Map<String, Object> dataMap1 = new HashMap<String, Object>();
        dataMap1.put("tripStr", "AVH/CANPKX/20210629/D/KN");
        dataMap1.put("fightNo", "KN5900");
        dataMap1.put("cabinCode", "H");
        JSONObject dataJson1 = new JSONObject(dataMap1);
		return dataJson1;
	}
	
	/**
	 * 取消信息
	 * @return 取消信息
	 */
	public static JSONObject getData2() {
		Map<String, Object> dataMap2 = new HashMap<String, Object>();
        dataMap2.put("officeNo", "0000000000");
        dataMap2.put("orderNo", "0000000000");
        JSONObject dataJson2 = new JSONObject(dataMap2);
		return dataJson2;
	}
	
	/**
	 * 重启信息
	 * @return 重启信息
	 */
	public static JSONObject getData3() {
		Map<String, Object> dataMap1 = new HashMap<String, Object>();
		dataMap1.put("oiId", "1624912930528X89510");
        dataMap1.put("tripStr", "AVH/CANPKX/20210629/D/KN");
        dataMap1.put("fightNo", "KN5900");
        dataMap1.put("cabinCode", "H");
        JSONObject dataJson1 = new JSONObject(dataMap1);
		return dataJson1;
	}
	
	/**
	 * 客户信息
	 * @return 客户信息
	 */
	public static JSONObject getCustomerData() {
		Map<String, Object> customerMap1 = new HashMap<String, Object>();
        customerMap1.put("name", "郑继青");
        customerMap1.put("cardType", 1);
        customerMap1.put("cardNo", "410725199510022430");
        customerMap1.put("mobile", "17656175477");
        customerMap1.put("birthday", "1995-10-02");
        customerMap1.put("ticketType", 1);
        JSONObject customerDataJson = new JSONObject(customerMap1);
		return customerDataJson;
	}
	
	/**
	 * 官网账号信息
	 * @return 官网账号信息
	 */
	public static JSONObject getOfficeData() {
		Map<String, Object> officeMap1 = new HashMap<String, Object>();
		officeMap1.put("name", "郑继青");
		officeMap1.put("cardType", 1);
		officeMap1.put("cardNo", "410725199510022430");
		officeMap1.put("mobile", "17656175477");
		officeMap1.put("birthday", "1995-10-02");
		officeMap1.put("ticketType", 1);
        JSONObject officeDataJson = new JSONObject(officeMap1);
		return officeDataJson;
	}
	
	/**
	 * 获取允许订票的余票范围
	 * @return 允许订票的余票范围
	 */
	public static ArrayList<String> getSeatLeft() {
		ArrayList<String> seatLeftNumList = new ArrayList<String>();
		seatLeftNumList.add("0");
		seatLeftNumList.add("1");
		seatLeftNumList.add("2");
		seatLeftNumList.add("3");
		seatLeftNumList.add("4");
		seatLeftNumList.add("5");
		seatLeftNumList.add("6");
		seatLeftNumList.add("7");
		seatLeftNumList.add("8");
		seatLeftNumList.add("9");
		seatLeftNumList.add("10");
		return seatLeftNumList;
	}
	
	public static ArrayList<Map<String, Object>> getCustomerArr (){
		ArrayList<Map<String, Object>> customerArr = new ArrayList<Map<String, Object>>();
        Map<String, Object> customerMap1 = new HashMap<String, Object>();
        customerMap1.put("name", "石竟革");
        customerMap1.put("cardType", 1);
        customerMap1.put("cardNo", "511823198401101378");
        customerMap1.put("mobile", "17656175477");
        customerMap1.put("birthday", "1995-10-02");
        customerMap1.put("ticketType", 1);
        customerArr.add(customerMap1);
        Map<String, Object> customerMap2 = new HashMap<String, Object>();
        customerMap2.put("name", "舒枫瑾");
        customerMap2.put("cardType", 1);
        customerMap2.put("cardNo", "511823198401103576");
        customerMap2.put("mobile", "17656175477");
        customerMap2.put("birthday", "1995-10-02");
        customerMap2.put("ticketType", 1);
        customerArr.add(customerMap2);
        Map<String, Object> customerMap3 = new HashMap<String, Object>();
        customerMap3.put("name", "康栋昆");
        customerMap3.put("cardType", 1);
        customerMap3.put("cardNo", "511823198401103218");
        customerMap3.put("mobile", "17656175477");
        customerMap3.put("birthday", "1995-10-02");
        customerMap3.put("ticketType", 1);
        customerArr.add(customerMap3);
        Map<String, Object> customerMap4 = new HashMap<String, Object>();
        customerMap4.put("name", "杨朝剑");
        customerMap4.put("cardType", 1);
        customerMap4.put("cardNo", "511823198401108334");
        customerMap4.put("mobile", "17656175477");
        customerMap4.put("birthday", "1995-10-02");
        customerMap4.put("ticketType", 1);
        customerArr.add(customerMap4);
        Map<String, Object> customerMap5 = new HashMap<String, Object>();
        customerMap5.put("name", "章留时");
        customerMap5.put("cardType", 1);
        customerMap5.put("cardNo", "511823198401109097");
        customerMap5.put("mobile", "17656175477");
        customerMap5.put("birthday", "1995-10-02");
        customerMap5.put("ticketType", 1);
        customerArr.add(customerMap5);
        Map<String, Object> customerMap6 = new HashMap<String, Object>();
        customerMap6.put("name", "于变承");
        customerMap6.put("cardType", 1);
        customerMap6.put("cardNo", "511823198401109871");
        customerMap6.put("mobile", "17656175477");
        customerMap6.put("birthday", "1995-10-02");
        customerMap6.put("ticketType", 1);
        customerArr.add(customerMap6);
        Map<String, Object> customerMap7 = new HashMap<String, Object>();
        customerMap7.put("name", "云义庚");
        customerMap7.put("cardType", 1);
        customerMap7.put("cardNo", "511823198401109150");
        customerMap7.put("mobile", "17656175477");
        customerMap7.put("birthday", "1995-10-02");
        customerMap7.put("ticketType", 1);
        customerArr.add(customerMap7);
        Map<String, Object> customerMap8 = new HashMap<String, Object>();
        customerMap8.put("name", "华效霏");
        customerMap8.put("cardType", 1);
        customerMap8.put("cardNo", "511823198401103891");
        customerMap8.put("mobile", "17656175477");
        customerMap8.put("birthday", "1995-10-02");
        customerMap8.put("ticketType", 1);
        customerArr.add(customerMap8);
        Map<String, Object> customerMap9 = new HashMap<String, Object>();
        customerMap9.put("name", "韩亮益");
        customerMap9.put("cardType", 1);
        customerMap9.put("cardNo", "511823198401106574");
        customerMap9.put("mobile", "17656175477");
        customerMap9.put("birthday", "1995-10-02");
        customerMap9.put("ticketType", 1);
        customerArr.add(customerMap9);
        Map<String, Object> customerMap10 = new HashMap<String, Object>();
        customerMap10.put("name", "傅慈堂");
        customerMap10.put("cardType", 1);
        customerMap10.put("cardNo", "511823198401109353");
        customerMap10.put("mobile", "17656175477");
        customerMap10.put("birthday", "1995-10-02");
        customerMap10.put("ticketType", 1);
        customerArr.add(customerMap10);
        Map<String, Object> customerMap11 = new HashMap<String, Object>();
        customerMap11.put("name", "魏图锵");
        customerMap11.put("cardType", 1);
        customerMap11.put("cardNo", "511823198401108350");
        customerMap11.put("mobile", "17656175477");
        customerMap11.put("birthday", "1995-10-02");
        customerMap11.put("ticketType", 1);
        customerArr.add(customerMap11);
        Map<String, Object> customerMap12 = new HashMap<String, Object>();
        customerMap12.put("name", "荣倍卫");
        customerMap12.put("cardType", 1);
        customerMap12.put("cardNo", "511823198401103939");
        customerMap12.put("mobile", "17656175477");
        customerMap12.put("birthday", "1995-10-02");
        customerMap12.put("ticketType", 1);
        customerArr.add(customerMap12);
        Map<String, Object> customerMap13 = new HashMap<String, Object>();
        customerMap13.put("name", "苏牡子");
        customerMap13.put("cardType", 1);
        customerMap13.put("cardNo", "511823198401101159");
        customerMap13.put("mobile", "17656175477");
        customerMap13.put("birthday", "1995-10-02");
        customerMap13.put("ticketType", 1);
        customerArr.add(customerMap13);
        Map<String, Object> customerMap14 = new HashMap<String, Object>();
        customerMap14.put("name", "祁贡策");
        customerMap14.put("cardType", 1);
        customerMap14.put("cardNo", "511823198401108553");
        customerMap14.put("mobile", "17656175477");
        customerMap14.put("birthday", "1995-10-02");
        customerMap14.put("ticketType", 1);
        customerArr.add(customerMap14);
        Map<String, Object> customerMap15 = new HashMap<String, Object>();
        customerMap15.put("name", "季翰毅");
        customerMap15.put("cardType", 1);
        customerMap15.put("cardNo", "511823198401102055");
        customerMap15.put("mobile", "17656175477");
        customerMap15.put("birthday", "1995-10-02");
        customerMap15.put("ticketType", 1);
        customerArr.add(customerMap15);
        Map<String, Object> customerMap16 = new HashMap<String, Object>();
        customerMap16.put("name", "赵农仲");
        customerMap16.put("cardType", 1);
        customerMap16.put("cardNo", "511823198401105096");
        customerMap16.put("mobile", "17656175477");
        customerMap16.put("birthday", "1995-10-02");
        customerMap16.put("ticketType", 1);
        customerArr.add(customerMap16);
        Map<String, Object> customerMap17 = new HashMap<String, Object>();
        customerMap17.put("name", "洪枫瑾");
        customerMap17.put("cardType", 1);
        customerMap17.put("cardNo", "511823198401106013");
        customerMap17.put("mobile", "17656175477");
        customerMap17.put("birthday", "1995-10-02");
        customerMap17.put("ticketType", 1);
        customerArr.add(customerMap17);
        Map<String, Object> customerMap18 = new HashMap<String, Object>();
        customerMap18.put("name", "彭凌可");
        customerMap18.put("cardType", 1);
        customerMap18.put("cardNo", "511823198401106339");
        customerMap18.put("mobile", "17656175477");
        customerMap18.put("birthday", "1995-10-02");
        customerMap18.put("ticketType", 1);
        customerArr.add(customerMap18);
        Map<String, Object> customerMap19 = new HashMap<String, Object>();
        customerMap19.put("name", "何政航");
        customerMap19.put("cardType", 1);
        customerMap19.put("cardNo", "511823198401108430");
        customerMap19.put("mobile", "17656175477");
        customerMap19.put("birthday", "1995-10-02");
        customerMap19.put("ticketType", 1);
        customerArr.add(customerMap19);
        Map<String, Object> customerMap20 = new HashMap<String, Object>();
        customerMap20.put("name", "余爽顺");
        customerMap20.put("cardType", 1);
        customerMap20.put("cardNo", "511823198401107518");
        customerMap20.put("mobile", "17656175477");
        customerMap20.put("birthday", "1995-10-02");
        customerMap20.put("ticketType", 1);
        customerArr.add(customerMap20);
        Map<String, Object> customerMap21 = new HashMap<String, Object>();
        customerMap21.put("name", "席悟营");
        customerMap21.put("cardType", 1);
        customerMap21.put("cardNo", "511823198401105571");
        customerMap21.put("mobile", "17656175477");
        customerMap21.put("birthday", "1995-10-02");
        customerMap21.put("ticketType", 1);
        customerArr.add(customerMap21);
        Map<String, Object> customerMap22 = new HashMap<String, Object>();
        customerMap22.put("name", "潘铮友");
        customerMap22.put("cardType", 1);
        customerMap22.put("cardNo", "511823198401101618");
        customerMap22.put("mobile", "17656175477");
        customerMap22.put("birthday", "1995-10-02");
        customerMap22.put("ticketType", 1);
        customerArr.add(customerMap22);
        Map<String, Object> customerMap23 = new HashMap<String, Object>();
        customerMap23.put("name", "茅胤鸣");
        customerMap23.put("cardType", 1);
        customerMap23.put("cardNo", "51182319840110689X");
        customerMap23.put("mobile", "17656175477");
        customerMap23.put("birthday", "1995-10-02");
        customerMap23.put("ticketType", 1);
        customerArr.add(customerMap23);
        Map<String, Object> customerMap24 = new HashMap<String, Object>();
        customerMap24.put("name", "金湃邦");
        customerMap24.put("cardType", 1);
        customerMap24.put("cardNo", "511823198401107155");
        customerMap24.put("mobile", "17656175477");
        customerMap24.put("birthday", "1995-10-02");
        customerMap24.put("ticketType", 1);
        customerArr.add(customerMap24);
        Map<String, Object> customerMap25 = new HashMap<String, Object>();
        customerMap25.put("name", "蓝初柳");
        customerMap25.put("cardType", 1);
        customerMap25.put("cardNo", "450311197509084501");
        customerMap25.put("mobile", "17656175477");
        customerMap25.put("birthday", "1995-10-02");
        customerMap25.put("ticketType", 1);
        customerArr.add(customerMap25);
        Map<String, Object> customerMap26 = new HashMap<String, Object>();
        customerMap26.put("name", "滕安青");
        customerMap26.put("cardType", 1);
        customerMap26.put("cardNo", "450311197509084181");
        customerMap26.put("mobile", "17656175477");
        customerMap26.put("birthday", "1995-10-02");
        customerMap26.put("ticketType", 1);
        customerArr.add(customerMap26);
        Map<String, Object> customerMap27 = new HashMap<String, Object>();
        customerMap27.put("name", "支冰蝶");
        customerMap27.put("cardType", 1);
        customerMap27.put("cardNo", "450311197509088609");
        customerMap27.put("mobile", "17656175477");
        customerMap27.put("birthday", "1995-10-02");
        customerMap27.put("ticketType", 1);
        customerArr.add(customerMap27);
        Map<String, Object> customerMap28 = new HashMap<String, Object>();
        customerMap28.put("name", "娄平安");
        customerMap28.put("cardType", 1);
        customerMap28.put("cardNo", "450311197509085264");
        customerMap28.put("mobile", "17656175477");
        customerMap28.put("birthday", "1995-10-02");
        customerMap28.put("ticketType", 1);
        customerArr.add(customerMap28);
        Map<String, Object> customerMap29 = new HashMap<String, Object>();
        customerMap29.put("name", "江幻梅");
        customerMap29.put("cardType", 1);
        customerMap29.put("cardNo", "450311197509086902");
        customerMap29.put("mobile", "17656175477");
        customerMap29.put("birthday", "1995-10-02");
        customerMap29.put("ticketType", 1);
        customerArr.add(customerMap29);
        Map<String, Object> customerMap30 = new HashMap<String, Object>();
        customerMap30.put("name", "邬念蕾");
        customerMap30.put("cardType", 1);
        customerMap30.put("cardNo", "45031119750908814X");
        customerMap30.put("mobile", "17656175477");
        customerMap30.put("birthday", "1995-10-02");
        customerMap30.put("ticketType", 1);
        customerArr.add(customerMap30);
        return customerArr;
	}
	
	public static ArrayList<Map<String, Object>> getAccountArr() {
		ArrayList<Map<String, Object>> accountArr = new ArrayList<Map<String, Object>>();
        Map<String, Object> accountMap1 = new HashMap<String, Object>();
        accountMap1.put("customerOrderNo", "17656175477");
        accountMap1.put("flightRangeType", "0");
        accountMap1.put("InsuranceCodes", null);
        accountMap1.put("isApplyReimbursement", false);
        accountMap1.put("contactName", "李伟");
        accountMap1.put("contactMobile", "17656175477");
        accountMap1.put("callBackUrl", "");
        accountArr.add(accountMap1);
        Map<String, Object> accountMap2 = new HashMap<String, Object>();
        accountMap2.put("customerOrderNo", "17351122661");
        accountMap2.put("flightRangeType", "0");
        accountMap2.put("InsuranceCodes", null);
        accountMap2.put("isApplyReimbursement", false);
        accountMap2.put("contactName", "李二伟");
        accountMap2.put("contactMobile", "17351122661");
        accountMap2.put("callBackUrl", "");
        accountArr.add(accountMap2);
        Map<String, Object> accountMap3 = new HashMap<String, Object>();
        accountMap3.put("customerOrderNo", "17656175477");
        accountMap3.put("flightRangeType", "0");
        accountMap3.put("InsuranceCodes", null);
        accountMap3.put("isApplyReimbursement", false);
        accountMap3.put("contactName", "李伟");
        accountMap3.put("contactMobile", "17656175477");
        accountMap3.put("callBackUrl", "");
        accountArr.add(accountMap3);
        Map<String, Object> accountMap4 = new HashMap<String, Object>();
        accountMap4.put("customerOrderNo", "17656175477");
        accountMap4.put("flightRangeType", "0");
        accountMap4.put("InsuranceCodes", null);
        accountMap4.put("isApplyReimbursement", false);
        accountMap4.put("contactName", "李伟");
        accountMap4.put("contactMobile", "17656175477");
        accountMap4.put("callBackUrl", "");
        accountArr.add(accountMap4);
        Map<String, Object> accountMap5 = new HashMap<String, Object>();
        accountMap5.put("customerOrderNo", "17656175477");
        accountMap5.put("flightRangeType", "0");
        accountMap5.put("InsuranceCodes", null);
        accountMap5.put("isApplyReimbursement", false);
        accountMap5.put("contactName", "李伟");
        accountMap5.put("contactMobile", "17656175477");
        accountMap5.put("callBackUrl", "");
        accountArr.add(accountMap5);
        Map<String, Object> accountMap6 = new HashMap<String, Object>();
        accountMap6.put("customerOrderNo", "17656175477");
        accountMap6.put("flightRangeType", "0");
        accountMap6.put("InsuranceCodes", null);
        accountMap6.put("isApplyReimbursement", false);
        accountMap6.put("contactName", "李伟");
        accountMap6.put("contactMobile", "17656175477");
        accountMap6.put("callBackUrl", "");
        accountArr.add(accountMap6);
        Map<String, Object> accountMap7 = new HashMap<String, Object>();
        accountMap7.put("customerOrderNo", "17656175477");
        accountMap7.put("flightRangeType", "0");
        accountMap7.put("InsuranceCodes", null);
        accountMap7.put("isApplyReimbursement", false);
        accountMap7.put("contactName", "李伟");
        accountMap7.put("contactMobile", "17656175477");
        accountMap7.put("callBackUrl", "");
        accountArr.add(accountMap7);
        Map<String, Object> accountMap8 = new HashMap<String, Object>();
        accountMap8.put("customerOrderNo", "17656175477");
        accountMap8.put("flightRangeType", "0");
        accountMap8.put("InsuranceCodes", null);
        accountMap8.put("isApplyReimbursement", false);
        accountMap8.put("contactName", "李伟");
        accountMap8.put("contactMobile", "17656175477");
        accountMap8.put("callBackUrl", "");
        accountArr.add(accountMap8);
        return accountArr;
	}
	
}
