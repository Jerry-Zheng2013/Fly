package com.ticketsystem.net;

import java.util.HashMap;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ticketsystem.net.query.QueryHttpClient;
import com.ticketsystem.util.DemoData;
import com.ticketsystem.util.MD5Util;

public class DemoNet {
	
	public static void main(String[] args) {
		JSONObject addData = DemoData.getData1();
		new DemoNet().queryTicket(addData);
	}
	
	/**
     * 查询
     *
     * @param user
     * @return
     */
    public JSONObject queryTicket(JSONObject queryData) {
    	JSONObject bookTicketData = new JSONObject(new HashMap<String, Object>());
    	String fromCity = queryData.getString("fromCityCode");
    	String toCity = queryData.getString("toCityCode");
    	String fromDate = queryData.getString("fromDate");
    	String fightNo = queryData.getString("fightNo");
    	String cabinCode = queryData.getString("cabinCode");
    	
    	String queryUrl = DemoData.getSysData().getString("queryUrl");
    	String appKey = DemoData.getSysData().getString("appKey");
    	String secretKey = DemoData.getSysData().getString("secretKey");
    	String sourceSign = secretKey+fromCity+toCity+fromDate+secretKey;
    	String descSign = MD5Util.getMD5(sourceSign);

    	String urlStr = queryUrl+"?appKey="+appKey+"&fromCityCode="+fromCity+"&toCityCode="+toCity+"&fromDate="+fromDate+"&sign="+descSign;
    	System.out.println(urlStr);
    	JSONObject queryResultData = new JSONObject(new HashMap<String, Object>());
    	queryResultData = QueryHttpClient.doPostOrGet2(urlStr, "GET", "");
    	//获取余票数量
    	String standbyCount = "";
    	//获取航班信息
    	JSONArray jsonArray = queryResultData.getJSONObject("data").getJSONObject("voyage").getJSONArray("flights");
    	for (int i=0;i<jsonArray.size();i++) {
    		String queryFlightResult = jsonArray.getJSONObject(i).getString("flightNo");
    		//锁定到指定航班
    		if (fightNo.equalsIgnoreCase(queryFlightResult)) {
    			//起飞时间
        		String fromDateTime = jsonArray.getJSONObject(i).getString("fromDateTime");
        		//到达时间
        		String toDateTime = jsonArray.getJSONObject(i).getString("toDateTime");
        		//起飞航站楼
        		String fromTerminal = jsonArray.getJSONObject(i).getString("fromTerminal");
        		//达到航站楼
        		String toTerminal = jsonArray.getJSONObject(i).getString("toTerminal");
    			//获取航班舱位信息
    			JSONArray cabinArr = jsonArray.getJSONObject(i).getJSONArray("cabins");
    			for (int j=0;j<cabinArr.size();j++) {
    				String queryCabin = cabinArr.getJSONObject(j).getString("cabinCode");
    				String seatLeftNum = cabinArr.getJSONObject(j).getString("seatLeftNum");
    				//锁定指定舱位号
    				if (cabinCode.equalsIgnoreCase(queryCabin)) {
    					//判断余票是否符合预定标准
    					if(DemoData.getSeatLeft().contains(seatLeftNum)) {
    						//锁定到余票数量
							standbyCount = seatLeftNum;
							System.out.println(standbyCount);
							bookTicketData.put("flightNo", fightNo);
							bookTicketData.put("fromAirportCode", fromCity);
							bookTicketData.put("toAirportCode", toCity);
							bookTicketData.put("fromDateTime", fromDateTime);
							bookTicketData.put("toDateTime", toDateTime);
							bookTicketData.put("fromTerminal", fromTerminal);
							bookTicketData.put("toTerminal", toTerminal);
							bookTicketData.put("isShareFlight", false);
							bookTicketData.put("shareFlightNo", null);
							bookTicketData.put("cabinCode", cabinCode);
							bookTicketData.put("cabinBookParms", "BF216356179765BE8FF5064BFCBA9DBD41A5D1357837CA9048AA4D986CF8D868279424D90C3324CBF9BA1FFD2B7923D77C64836E84037DAE203E807BD7BF5184128DA9A74913A9529A8FF9A36E7AFDEE5578C98E2BCFF6E1E73FD4ACD2E863A8B4334E54E4BAA1AEE9C05DFD96AF5C6BF91584613A792A3819280D50DBC9112C8AF41944FE5DF1EBBC023B1F63644D1EB733CA3D9158B7F676540444BB9E66320667B69EA456FA88E0B80AD378338A9EE244DDA8684CFBDAD55843B05A295CA987BEB8DA773E68AC4C1094150F1C74EFE6C7DCC339669B47EF9BA0803A4B581D1F178EC21E6AFEB4443FB8F466E7E6200DC54EF1F04A792B95306D08AD6D496AA0494953E49D8AFF604DAD5B05D2EC6C7223438B9564E1F361933AB3CFFBADB6A80155C070FE54273EF0F6F922FB3C7781AA28F2F39EC47EFCFB7D86E54F7BEDC5D867AFFDF63C6BE0280DC51F5FEBF0D48E050AB82C8C5D8444D8706261F4723C553D4187709A53D622226FED368085C6C486F4276F219CECEA8C26F4437B37FC6496012FC288693D105F27F87BF2E5F2ECCA95C1588D4D733084ED3EF7869981604ADA466CD01A09D372BCAEDAA618");
							bookTicketData.put("standbyCount", Integer.valueOf(standbyCount));
    					}
    				}
    			}
    		}
    	}
    	return bookTicketData;
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
    public JSONObject cancelTicket(String orderNo) {
    	String urlStr = DemoData.getSysData().getString("cancelUrl");
    	String appKey = DemoData.getSysData().getString("appKey");
    	String dataStr = "appKey="+appKey+"&orderNo="+orderNo;
    	urlStr = urlStr+"?"+dataStr;
    	JSONObject cancelResultData = QueryHttpClient.doPostOrGet2(urlStr, "GET", "");
    	return cancelResultData;
    }
    
}
