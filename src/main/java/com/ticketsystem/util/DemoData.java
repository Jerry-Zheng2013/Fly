package com.ticketsystem.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.alibaba.fastjson.JSONObject;
import com.ticketsystem.model.DemoOrder;

public class DemoData {
	// 倒计时时长
	public static long COUNTDOWNMILLIS = (long) (30 * 1000);
	
	public static int ThreadNo = 0;
	
	public static TreeMap<String, String> OrderStatusMap = new TreeMap<String, String>();
	
	public static TreeMap<String, DemoOrder> OrderBeanMap = new TreeMap<String, DemoOrder>();
	
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
        dataMap1.put("tripStr", "AVH/PKXCTU/20210723/D/KN");
        dataMap1.put("flghtNo", "KN0000");
        dataMap1.put("cabinCode", "C");
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
		Map<String, Object> dataMap3 = new HashMap<String, Object>();
        dataMap3.put("officeNo", "0000000000");
        dataMap3.put("orderNo", "0000000000");
        JSONObject dataJson3 = new JSONObject(dataMap3);
		return dataJson3;
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
}
