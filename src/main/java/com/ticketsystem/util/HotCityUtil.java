package com.ticketsystem.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ticketsystem.net.GetSender;
import com.ticketsystem.util.DemoData;

public class HotCityUtil {
	public static JSONArray hotCityArr = new JSONArray();
	public static String[] letterArr = new String[] {"A","B","C","D","E","F","G","H","J","K","L","M","N","O","P","Q","R","S","T","W","X","Y","Z"};
	public static int letterIndex = 0;
	
	public static String getCityCode(String iata) {
		String cityCode = "";
		if (hotCityArr.size()<=0) {
			String hotCityUrl = DemoData.hotCityUrl;
			JSONObject hotCityJson = new GetSender().sendHttpGet(hotCityUrl, String.valueOf(Math.random()).substring(2, 15));
			if(hotCityJson.size()>0) {
				String cityStr = hotCityJson.getString("responseBody");
				if (cityStr.length() >0) {
					letterIndex = 0;
					JSONObject originJson = JSONObject.parseObject(cityStr);
					JSONArray abcd = originJson.getJSONArray("ABCD");
					for (int i=0;i<abcd.size();i++) {
						String l = letterArr[letterIndex];
						JSONArray letterArr = abcd.getJSONObject(i).getJSONArray(l);
						if (23 == letterIndex) {
							letterIndex=0;
						} else {
							letterIndex++;
						}
						for (int j=0;j<letterArr.size();j++) {
							JSONObject letterJson = letterArr.getJSONObject(j);
							JSONObject item = new JSONObject();
							item.put("K", letterJson.getString("airCode")+"_"+letterJson.getString("Name"));
							item.put("V", letterJson.getString("cityCode"));
							hotCityArr.add(item);
							
							if (letterJson.getString("airCode").startsWith("AIR_"+iata)
									|| letterJson.getString("airCode").startsWith("AirCn"+iata)) {
								cityCode = letterJson.getString("cityCode");
							}
						}
					}
				}
			}
		} else {
			for (int i=0;i<hotCityArr.size();i++) {
				if (hotCityArr.getJSONObject(i).getString("K").startsWith("AIR_"+iata)
						|| hotCityArr.getJSONObject(i).getString("K").startsWith("AirCn"+iata)) {
					cityCode = hotCityArr.getJSONObject(i).getString("V");
				}
			}
		}
		
		return cityCode;
	}
	
	public static String getCityCodeByName(String cityName) {
		String cityCode = "";
		if (hotCityArr.size()<=0) {
			String hotCityUrl = DemoData.hotCityUrl;
			JSONObject hotCityJson = new GetSender().sendHttpGet(hotCityUrl, String.valueOf(Math.random()).substring(2, 15));
			if(hotCityJson.size()>0) {
				String cityStr = hotCityJson.getString("responseBody");
				if (cityStr.length() >0) {
					letterIndex = 0;
					JSONObject originJson = JSONObject.parseObject(cityStr);
					JSONArray abcd = originJson.getJSONArray("ABCD");
					for (int i=0;i<abcd.size();i++) {
						String l = letterArr[letterIndex];
						JSONArray letterArr = abcd.getJSONObject(i).getJSONArray(l);
						if (23 == letterIndex) {
							letterIndex=0;
						} else {
							letterIndex++;
						}
						for (int j=0;j<letterArr.size();j++) {
							JSONObject letterJson = letterArr.getJSONObject(j);
							JSONObject item = new JSONObject();
							item.put("K", letterJson.getString("airCode")+"_"+letterJson.getString("Name"));
							item.put("V", letterJson.getString("cityCode"));
							hotCityArr.add(item);
							
							if (letterJson.getString("Name").contains(cityName)) {
								cityCode = letterJson.getString("cityCode");
							}
						}
					}
					//EFGHJ
					JSONArray EFGHJ = originJson.getJSONArray("EFGHJ");
					for (int i=0;i<EFGHJ.size();i++) {
						String l = letterArr[letterIndex];
						JSONArray letterArr = EFGHJ.getJSONObject(i).getJSONArray(l);
						if (23 == letterIndex) {
							letterIndex=0;
						} else {
							letterIndex++;
						}
						for (int j=0;j<letterArr.size();j++) {
							JSONObject letterJson = letterArr.getJSONObject(j);
							JSONObject item = new JSONObject();
							item.put("K", letterJson.getString("airCode")+"_"+letterJson.getString("Name"));
							item.put("V", letterJson.getString("cityCode"));
							hotCityArr.add(item);
							
							if (letterJson.getString("Name").contains(cityName)) {
								cityCode = letterJson.getString("cityCode");
							}
						}
					}
					//KLMNOP
					JSONArray KLMNOP = originJson.getJSONArray("KLMNOP");
					for (int i=0;i<KLMNOP.size();i++) {
						String l = letterArr[letterIndex];
						JSONArray letterArr = KLMNOP.getJSONObject(i).getJSONArray(l);
						if (23 == letterIndex) {
							letterIndex=0;
						} else {
							letterIndex++;
						}
						for (int j=0;j<letterArr.size();j++) {
							JSONObject letterJson = letterArr.getJSONObject(j);
							JSONObject item = new JSONObject();
							item.put("K", letterJson.getString("airCode")+"_"+letterJson.getString("Name"));
							item.put("V", letterJson.getString("cityCode"));
							hotCityArr.add(item);
							
							if (letterJson.getString("Name").contains(cityName)) {
								cityCode = letterJson.getString("cityCode");
							}
						}
					}
					//QRSTW
					JSONArray QRSTW = originJson.getJSONArray("QRSTW");
					for (int i=0;i<QRSTW.size();i++) {
						String l = letterArr[letterIndex];
						JSONArray letterArr = QRSTW.getJSONObject(i).getJSONArray(l);
						if (23 == letterIndex) {
							letterIndex=0;
						} else {
							letterIndex++;
						}
						for (int j=0;j<letterArr.size();j++) {
							JSONObject letterJson = letterArr.getJSONObject(j);
							JSONObject item = new JSONObject();
							item.put("K", letterJson.getString("airCode")+"_"+letterJson.getString("Name"));
							item.put("V", letterJson.getString("cityCode"));
							hotCityArr.add(item);
							
							if (letterJson.getString("Name").contains(cityName)) {
								cityCode = letterJson.getString("cityCode");
							}
						}
					}
					//XYZ
					JSONArray XYZ = originJson.getJSONArray("XYZ");
					for (int i=0;i<XYZ.size();i++) {
						String l = letterArr[letterIndex];
						JSONArray letterArr = XYZ.getJSONObject(i).getJSONArray(l);
						if (23 == letterIndex) {
							letterIndex=0;
						} else {
							letterIndex++;
						}
						for (int j=0;j<letterArr.size();j++) {
							JSONObject letterJson = letterArr.getJSONObject(j);
							JSONObject item = new JSONObject();
							item.put("K", letterJson.getString("airCode")+"_"+letterJson.getString("Name"));
							item.put("V", letterJson.getString("cityCode"));
							hotCityArr.add(item);
							
							if (letterJson.getString("Name").contains(cityName)) {
								cityCode = letterJson.getString("cityCode");
							}
						}
					}
					
				}
			}
		} else {
			for (int i=0;i<hotCityArr.size();i++) {
				if (hotCityArr.getJSONObject(i).getString("K").contains(cityName)) {
					cityCode = hotCityArr.getJSONObject(i).getString("V");
				}
			}
		}
		return cityCode;
	}
	
	public static String getCityCodeByAVH(String avh) {
		String cityCode = "";
		String cityName = "";
		switch(avh) {
		case "PKX": cityName = "北京"; break;
		case "SHA": cityName = "上海"; break;
		default : cityName="北京";
		}
		cityCode = getCityCodeByName(cityName);
		return cityCode;
	}
}
