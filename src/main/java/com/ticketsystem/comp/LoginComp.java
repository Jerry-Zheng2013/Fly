package com.ticketsystem.comp;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSONObject;
import com.ticketsystem.net.CookieUtil;
import com.ticketsystem.net.PostSender;
import com.ticketsystem.test.FlightPackager2;
import com.ticketsystem.util.DemoData;

public class LoginComp {
	public JSONObject accountLogin(JSONObject addData, String encryptStr, String session) {
		JSONObject logInResult = new JSONObject();
		String fromCity = addData.getString("fromCityCode");
    	String toCity = addData.getString("toCityCode");
    	String fromDate = addData.getString("fromDate");
    	String fightNo = addData.getString("fightNo");
    	String cabinCode = addData.getString("cabinCode");
    	FlightPackager2 flightPackager2 = new FlightPackager2();
		
		//NrcZ9YVVM/N5PuZaHJfqltN6wPGOIfrHwwFNJ4DTKCHb/yyON/GL4rvJVQxNcX1J9Ui22MPCTWHxcaNCrUiOmdhfbhpKhRZPxIegcZRKs7OCN1CCNH1QMiZZxgP5bdFD
		
		try {
			//TODO 调用接口----------查询订单接口
			JSONObject queryPost2 = new QueryComp().queryTicket2(addData);
			//组装flight
			String flightStr = queryPost2.getString("responseBody");
			JSONObject flightData = JSONObject.parseObject(flightStr);
			flightPackager2.setBookData(flightData, fightNo, cabinCode);
			
			//TODO 调用接口----------登录接口
			PostSender postSender = new PostSender();
			String loginUrl = DemoData.loginUrl;
			JSONObject logInPost = postSender.loginPost(loginUrl, encryptStr);
			if (logInPost.get("responseHead") != null) {
				Map<String, List<String>> headMap = (Map<String, List<String>>)logInPost.get("responseHead");
				for (Entry<String, List<String>> entry : headMap.entrySet()) {
					System.out.println(entry.getKey());
					System.out.println(entry.getValue());	
					if ("set-cookie".equalsIgnoreCase(entry.getKey())) {
						for (String entryValue: entry.getValue()) {
							if (entryValue != null) {
								if (entryValue.toLowerCase().contains("jsessionid")) {
									String xlbValue = entryValue.substring(11, entryValue.indexOf(";"));
									System.out.println("JSESSIONID="+xlbValue);
									logInResult.put("JSESSIONID", xlbValue);
								} else if (entryValue.toLowerCase().contains("session")) {
									String sessionValue = entryValue.substring(8, entryValue.indexOf(";"));
									System.out.println("sessionValue="+sessionValue);
									logInResult.put("session", sessionValue);
								} else if (entryValue.toLowerCase().contains("tokenuuid")) {
									String sessionValue = entryValue.substring(10, entryValue.indexOf(";"));
									System.out.println("tokenUUID="+sessionValue);
									logInResult.put("tokenUUID", sessionValue);
								}else if (entryValue.toLowerCase().contains("tokenid")) {
									String xlbValue = entryValue.substring(8, entryValue.indexOf(";"));
									System.out.println("tokenId="+xlbValue);
									logInResult.put("tokenId", xlbValue);
								}
							}
						}
					}
				}
			}
			
			String tokenUUID = logInResult.getString("tokenUUID");
			String tokenId = logInResult.getString("tokenId");
			String bookCookie = CookieUtil.getBookCookie(tokenId, tokenUUID, session);
			
			//确认航班
			String pointsUrl = DemoData.pointsInfoUrl +"?_="+String.valueOf(Math.random()).substring(2, 15);
			String pointsDataStr = "number="+fightNo.substring(2, fightNo.length())+"&cabin="+cabinCode+"&org="+fromCity+"&dst="+toCity+"&basecabinfareamount="+flightPackager2.baseFare
					+ "&flightdate="+fromDate+"T23%3A15%3A00.000%2B0000"
					+ "&segment="+fromCity+"%7C"+toCity+"%2C1%2CCNY%2C1630";
			JSONObject pointsPost = postSender.pointsPost(pointsUrl, pointsDataStr, bookCookie);
			if (pointsPost.size()>0) {
				String chooseBody = pointsPost.getString("responseBody");
				if (chooseBody.contains("uuid")) {
					String uuidStr1 = chooseBody.substring(chooseBody.indexOf("\"uuid\":\"")+8, chooseBody.indexOf("\"}"));
					System.out.println(uuidStr1);					
				}
			}
			
			//选择航班
			String chooseUrl = DemoData.chooseUrl +"?_="+String.valueOf(Math.random()).substring(2, 15);
			String chooseDataStr = "farefamilycode="+flightPackager2.farefamily+"&seqNum="+flightPackager2.priceInfoSeq+"&type=1&airline="+flightPackager2.airline+"&validate="+flightPackager2.priceInfoSeq+"#"+flightPackager2.priceInfoSeq2+"#"+flightPackager2.airline+"#1#"+flightPackager2.farefamily+"#"+flightPackager2.cabinClass+"#"+flightPackager2.flightNumber+"#ONE_WAY#"+flightPackager2.baseFare+"&memberType=COMMON&pricetype=COMMON";
			JSONObject choosePost = postSender.choosePost(chooseUrl, chooseDataStr, bookCookie);
			if (choosePost.size()>0) {
				String chooseBody = choosePost.getString("responseBody");
				if (chooseBody.contains("uuid")) {
					String uuidStr1 = chooseBody.substring(chooseBody.indexOf("\"uuid\":\"")+8, chooseBody.indexOf("\"}"));
					System.out.println(uuidStr1);					
				}
			}
			
			//加入购物车
			String addUrl = DemoData.add2CartUrl + "?_="+String.valueOf(Math.random()).substring(2, 15);
			String postData = "";
			JSONObject addToCartPost = postSender.sendHttpPost(addUrl, postData, bookCookie);
			String uuid2 = "";
			if (choosePost.size()>0) {
				String addBody = addToCartPost.getString("responseBody");
				uuid2 = addBody.substring(addBody.indexOf("\"uuid\":\"")+8, addBody.indexOf("\"}"));
				System.out.println(uuid2);
			}
			logInResult.put("uuid", uuid2);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return logInResult;
	}
	
	
	public JSONObject accountLogin2(String encryptStr, String session) {
		JSONObject logInResult = new JSONObject();
		try {
			//TODO 调用接口----------登录接口
			PostSender postSender = new PostSender();
			String loginUrl = DemoData.loginUrl;
			JSONObject logInPost = postSender.loginPost(loginUrl, encryptStr);
			if (logInPost.get("responseHead") != null) {
				Map<String, List<String>> headMap = (Map<String, List<String>>)logInPost.get("responseHead");
				for (Entry<String, List<String>> entry : headMap.entrySet()) {
					System.out.println(entry.getKey());
					System.out.println(entry.getValue());	
					if ("set-cookie".equalsIgnoreCase(entry.getKey())) {
						for (String entryValue: entry.getValue()) {
							if (entryValue != null) {
								if (entryValue.toLowerCase().contains("jsessionid")) {
									String xlbValue = entryValue.substring(11, entryValue.indexOf(";"));
									System.out.println("JSESSIONID="+xlbValue);
									logInResult.put("JSESSIONID", xlbValue);
								} else if (entryValue.toLowerCase().contains("session")) {
									String sessionValue = entryValue.substring(8, entryValue.indexOf(";"));
									System.out.println("sessionValue="+sessionValue);
									logInResult.put("session", sessionValue);
								} else if (entryValue.toLowerCase().contains("tokenuuid")) {
									String sessionValue = entryValue.substring(10, entryValue.indexOf(";"));
									System.out.println("tokenUUID="+sessionValue);
									logInResult.put("tokenUUID", sessionValue);
								}else if (entryValue.toLowerCase().contains("tokenid")) {
									String xlbValue = entryValue.substring(8, entryValue.indexOf(";"));
									System.out.println("tokenId="+xlbValue);
									logInResult.put("tokenId", xlbValue);
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return logInResult;
	}
	
	public JSONObject accountLogin3(JSONObject addData, String encryptStr, String session, JSONObject tripParam) {
		JSONObject logInResult = new JSONObject();
		String fromCity = addData.getString("fromCityCode");
    	String toCity = addData.getString("toCityCode");
    	String fromDate = addData.getString("fromDate");
    	String fightNo = addData.getString("fightNo");
    	String cabinCode = addData.getString("cabinCode");
		
		try {
			//TODO 调用接口----------登录接口
			PostSender postSender = new PostSender();
			String loginUrl = DemoData.loginUrl;
			JSONObject logInPost = postSender.loginPost(loginUrl, encryptStr);
			if (logInPost.get("responseHead") != null) {
				Map<String, List<String>> headMap = (Map<String, List<String>>)logInPost.get("responseHead");
				for (Entry<String, List<String>> entry : headMap.entrySet()) {
					System.out.println(entry.getKey());
					System.out.println(entry.getValue());	
					if ("set-cookie".equalsIgnoreCase(entry.getKey())) {
						for (String entryValue: entry.getValue()) {
							if (entryValue != null) {
								if (entryValue.toLowerCase().contains("jsessionid")) {
									String xlbValue = entryValue.substring(11, entryValue.indexOf(";"));
									System.out.println("JSESSIONID="+xlbValue);
									logInResult.put("JSESSIONID", xlbValue);
								} else if (entryValue.toLowerCase().contains("session")) {
									String sessionValue = entryValue.substring(8, entryValue.indexOf(";"));
									System.out.println("sessionValue="+sessionValue);
									logInResult.put("session", sessionValue);
								} else if (entryValue.toLowerCase().contains("tokenuuid")) {
									String sessionValue = entryValue.substring(10, entryValue.indexOf(";"));
									System.out.println("tokenUUID="+sessionValue);
									logInResult.put("tokenUUID", sessionValue);
								}else if (entryValue.toLowerCase().contains("tokenid")) {
									String xlbValue = entryValue.substring(8, entryValue.indexOf(";"));
									System.out.println("tokenId="+xlbValue);
									logInResult.put("tokenId", xlbValue);
								}
							}
						}
					}
				}
			}
			
			String tokenUUID = logInResult.getString("tokenUUID");
			String tokenId = logInResult.getString("tokenId");
			String bookCookie = CookieUtil.getBookCookie(tokenId, tokenUUID, session);
			
			//确认航班
			String pointsUrl = DemoData.pointsInfoUrl +"?_="+String.valueOf(Math.random()).substring(2, 15);
			String pointsDataStr = "number="+fightNo.substring(2, fightNo.length())
					+"&cabin="+cabinCode+"&org="+fromCity+"&dst="+toCity
					+"&basecabinfareamount="+tripParam.getString("baseFare")
					+ "&flightdate="+fromDate+"T23%3A15%3A00.000%2B0000"
					+ "&segment="+fromCity+"%7C"+toCity+"%2C1%2CCNY%2C1630";
			JSONObject pointsPost = postSender.pointsPost(pointsUrl, pointsDataStr, bookCookie);
			if (pointsPost.size()>0) {
				String chooseBody = pointsPost.getString("responseBody");
				System.out.println(chooseBody);
			}
			
			//选择航班
			String chooseUrl = DemoData.chooseUrl +"?_="+String.valueOf(Math.random()).substring(2, 15);
			String chooseDataStr = "farefamilycode="+tripParam.getString("fareFamily")
				+"&seqNum="+tripParam.getString("priceInfoSeq")
				+"&type=1"
				+"&airline="+tripParam.getString("airline")
				+"&validate="+tripParam.getString("priceInfoSeq")+"%23"+tripParam.getString("priceInfoSeq2")+"%23"+tripParam.getString("airline")+"%231%23"+tripParam.getString("fareFamily")+"%23"+cabinCode+"%23"+tripParam.getString("flightNumber")+"%23ONE_WAY%23"+tripParam.getString("baseFare")
				+"&memberType=COMMON&pricetype=COMMON&suppliercode=";
			
			JSONObject choosePost = postSender.choosePost(chooseUrl, chooseDataStr, bookCookie);
			if (choosePost.size()>0) {
				String chooseBody = choosePost.getString("responseBody");
				System.out.println(chooseBody);
			}
			
			//加入购物车
			String addUrl = DemoData.add2CartUrl + "?_="+String.valueOf(Math.random()).substring(2, 15);
			String postData = "";
			JSONObject addToCartPost = postSender.sendHttpPost(addUrl, postData, bookCookie);
			String uuid2 = "";
			if (choosePost.size()>0) {
				String addBody = addToCartPost.getString("responseBody");
				if(addBody.contains("\"uuid\":\"")) {
					uuid2 = addBody.substring(addBody.indexOf("\"uuid\":\"")+8, addBody.indexOf("\"}"));
					System.out.println(uuid2);
				} else {
					return null;
				}
			}
			logInResult.put("uuid", uuid2);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return logInResult;
	}
}
