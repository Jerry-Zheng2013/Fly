package com.ticketsystem.comp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ticketsystem.net.GetSender;
import com.ticketsystem.net.PostSender;
import com.ticketsystem.util.DemoData;
import com.ticketsystem.util.KnSqlManager;
import com.ticketsystem.util.Xml2JsonUtil;

public class QueryComp {
	
	Logger log = LogManager.getLogger(QueryComp.class);

	public String queryTicket(JSONObject queryData) {
		String standbyCount = "0";
    	String fromCity = queryData.getString("fromCityCode");
    	String toCity = queryData.getString("toCityCode");
    	String fromDate = queryData.getString("fromDate");
    	String fightNo = queryData.getString("fightNo");
    	String cabinCode = queryData.getString("cabinCode");
    	
    	String queryUrl = DemoData.queryUrl;
    	String sHashCode = DemoData.sHashCode;
    	String postDataStr = 
    			   "sHashcode="+sHashCode
    			+ "&orgCity="+fromCity
    			+ "&dstCity="+toCity 
    			+ "&FlightDate="+fromDate
    			+ "&Airline=KN&direct=true&eticket=true&limittime=0000&outstyle=0";

		log.info("=====黑屏查询余票=====url["+queryUrl+"]=====param["+postDataStr+"]");
    	System.out.println("=====黑屏查询余票=====url["+queryUrl+"]=====param["+postDataStr+"]");
    	JSONObject queryPost = new PostSender().queryPost(queryUrl, postDataStr);
    	if (queryPost.size()>0) {
    		String queryBody = queryPost.getString("responseBody");
    		String AVResult = queryBody.substring(queryBody.indexOf("<AVResult>"), queryBody.indexOf("</AVResult>")+11);
    		String queryBodyStr = Xml2JsonUtil.xml2JSON(AVResult);
    		JSONObject queryBodyJson = JSONObject.parseObject(queryBodyStr);
    		if (queryBodyJson.size()>0) {
    			JSONObject avJson = queryBodyJson.getJSONObject("AVResult");
    			JSONArray flightGroup = avJson.getJSONArray("IBE_FlightGroup");
    			for (int i=0;i<flightGroup.size();i++) {
    				JSONArray flightssArr = flightGroup.getJSONObject(i).getJSONArray("IBE_Flights");
    				if (flightssArr.size()>0) {
    					JSONArray flightArr = flightssArr.getJSONObject(0).getJSONArray("IBE_Flight");
    					if (flightArr.size() >0) {
    						JSONObject flightJson = flightArr.getJSONObject(0);
    						String flightNo2 = flightJson.getJSONArray("FlightNO").getString(0);
    						if (fightNo.equalsIgnoreCase(flightNo2)) {
    							JSONObject cabinJson = flightJson.getJSONArray("Cabin").getJSONObject(0);
    							JSONArray ibeCabinArr = cabinJson.getJSONArray("IBE_Cabin");
    							for (int j=0;j<ibeCabinArr.size();j++) {
    								JSONObject ibeJson = ibeCabinArr.getJSONObject(j);
    								String CabinName = ibeJson.getJSONArray("CabinName").getString(0);
    								String seatLeftNum = ibeJson.getJSONArray("CabinInfo").getString(0);
    								if (cabinCode.equalsIgnoreCase(CabinName)) {
    									if("A".equalsIgnoreCase(seatLeftNum)) {
    										standbyCount = "10";
    										return standbyCount;
    									}
    	    							if(DemoData.getSeatLeft().contains(seatLeftNum)) {
    	    								standbyCount = seatLeftNum;
    	    								//standbyCount = "1";
    	    								//break;
    	    								return standbyCount;
    	    							}
    	    							
    	    						}
    							}
    	    				}
    					}
    				}
    			}
    		}
    	}
		return standbyCount;
	}
	
	public JSONObject queryTicket2(JSONObject queryData) {
    	String fromCity = queryData.getString("fromCityCode");
    	String toCity = queryData.getString("toCityCode");
    	String fromDate = queryData.getString("fromDate");
    	String currStandBy = queryData.getString("currStandBy");
    	String session = queryData.getString("session");
    	
    	KnSqlManager sqlmanager = new KnSqlManager();
    	String fromCityCode = sqlmanager.getCityInfo(fromCity).getString("cityCode");
    	String toCityCode = sqlmanager.getCityInfo(toCity).getString("cityCode");
    	
    	GetSender getSender = new GetSender();
    	String queryCookie = "session="+session;
    	
    	String queryUrl4 = DemoData.queryUrl3;
    	String param4 = "_="+String.valueOf(Math.random()).substring(2, 15)+"&org="+fromCityCode+"&des="+toCityCode+"&type=oneway&depd="+fromDate+"&cals=false&adt="+currStandBy+"&chd=0&gm=0&jc=0";
		log.info("---查询具体航班详情URL4="+queryUrl4+"---PARAM4="+param4+"---COOKIE4="+queryCookie);
    	System.out.println("---查询具体航班详情URL4="+queryUrl4+"---PARAM4="+param4+"---COOKIE4="+queryCookie);
    	JSONObject queryPost = getSender.queryGet4(queryUrl4, param4, queryCookie);
		return queryPost;
	}

}
