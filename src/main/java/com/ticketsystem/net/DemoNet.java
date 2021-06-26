package com.ticketsystem.net;

import com.alibaba.fastjson.JSONObject;
import com.ticketsystem.net.query.QueryHttpClient;
import com.ticketsystem.util.DemoData;

public class DemoNet {
	/**
     * 查询
     *
     * @param user
     * @return
     */
    public JSONObject queryTicket(JSONObject queryData) {
    	String fromCity = queryData.getString("tripStr").substring(5, 8);
    	String toCity = queryData.getString("tripStr").substring(8, 11);
    	String goTime = queryData.getString("tripStr").substring(12, 20);
    	String fightNo = queryData.getString("fightNo");
    	String cabinCode = queryData.getString("cabinCode");
    	
    	String urlStr = DemoData.getSysData().getString("queryUrl");
    	String dataStr = "appKey=f46a96420331ea3be28eaf1036af4252&fromCityCode=CTU&toCityCode=PKX&fromDate=2021-07-24&sign=7771b2a86c85429ed98d4be2b83d64ae";
    	urlStr = urlStr+"?"+dataStr;
    	JSONObject queryResultData = QueryHttpClient.doPostOrGet2(urlStr, "GET", "");    	
    	return queryResultData;
    }
    
    /**
     * 订票
     *
     * @param user
     * @return
     */
    public JSONObject bookTicket(JSONObject bookData) {
    	

        String urlStr = DemoData.getSysData().getString("createUrl");
        JSONObject bookResultData = QueryHttpClient.doPostOrGet2(urlStr, "POST", bookData.toJSONString());
    	
    	return bookResultData;
    }
    
    /**
     * 取消订单
     *
     * @param user
     * @return
     */
    public JSONObject cancelTicket(JSONObject cancelData) {
    	String urlStr = DemoData.getSysData().getString("cancelUrl");
    	String orderNo  = cancelData.getJSONObject("data").getString("orderNo");
    	String dataStr = "appKey=f46a96420331ea3be28eaf1036af4252&orderNo="+orderNo;
    	urlStr = urlStr+"?"+dataStr;
    	JSONObject cancelResultData = QueryHttpClient.doPostOrGet2(urlStr, "GET", "");
    	return cancelResultData;
    }
    
}
