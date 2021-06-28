package com.ticketsystem.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.ticketsystem.async.ApplicationContextProvider;
import com.ticketsystem.async.AsyncService;
import com.ticketsystem.model.DemoOrder;
import com.ticketsystem.net.DemoNet;
import com.ticketsystem.util.DemoData;
import com.ticketsystem.util.SqlManager;

@Service
public class DemoService {

	@Autowired
	private AsyncService asyncService;
	
	/**
     * 预定<br/>
     * 传入fromCityCode，toCityCode，fromDate，<br/>
     * fightNo，cabinCode
     * @param 预定信息
     * @return
     */
	public void bookTicket(JSONObject addData) {
		//
		JSONObject bookResultTicket = this.add(addData);
    	//获取预定成功后的 订单号
    	//新增订单信息
    	SqlManager sqlManager = new SqlManager();
    	JSONObject orderInfoData = new JSONObject();
    	orderInfoData.put("accountNo", bookResultTicket.getJSONObject("bookData").getString("customerOrderNo"));
    	orderInfoData.put("orderNo", bookResultTicket.getJSONObject("resultData").getJSONObject("data").getString("orderNo"));
    	orderInfoData.put("tripCode", addData.getString("tripCode"));
    	orderInfoData.put("flightNo", addData.getString("fightNo"));
    	orderInfoData.put("cabinCode", addData.getString("cabinCode"));
    	String standbyCount = bookResultTicket.getJSONObject("resultData").getString("standbyCount");
    	orderInfoData.put("standbyCount", standbyCount);
    	double totalAmount = bookResultTicket.getJSONObject("resultData").getJSONObject("data").getDoubleValue("orderAmount");
    	double price = totalAmount/Double.valueOf(standbyCount);
    	orderInfoData.put("price", String.valueOf(price));
    	orderInfoData.put("orderStatus", "1");
    	orderInfoData.put("round", "1");
    	Date currentTime = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
    	orderInfoData.put("inputTime", sdf.format(currentTime));
    	orderInfoData.put("updateTime", sdf.format(currentTime));
    	orderInfoData.put("inputUser", "user");
    	sqlManager.insertOrderInfo(orderInfoData);
    	
    	//调用循环逻辑，开始循环预定
    	this.bookLoop(bookResultTicket);
	}
	
	/**
     * 预定<br/>
     * 传入fromCityCode，toCityCode，fromDate，<br/>
     * fightNo，cabinCode
     * @param 预定信息
     * @return
     */
    public JSONObject add(JSONObject addData) {
    	JSONObject bookResultTicket = new JSONObject();
    	bookResultTicket.put("addData", addData);
    	//查询
    	JSONObject standbyData = new DemoNet().queryTicket(addData);
    	int standbyCount  = standbyData.getIntValue("standbyCount");
    	
    	//预定数据组装
    	//standbyCount = 0;
    	if (standbyCount>0) {
    		//向上取整，决定用多少个账号进行预定
    		double accountCount = Math.ceil(Double.valueOf(
    				new BigDecimal(String.valueOf(standbyCount))
    				.divide(
    						new BigDecimal(String.valueOf(DemoData.PERSONTICKETS))).toPlainString()));
    		SqlManager sqlManager = new SqlManager();
    		//获取官网账户信息
    		JSONObject filterData = new JSONObject();
    		Date currentDate = new Date();
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    		String currentDateStr = sdf.format(currentDate);
    		filterData.put("useTime", currentDateStr);
    		ArrayList<JSONObject> accountList = sqlManager.getAccountList(filterData);
    		
    		//获取乘机客户信息
    		JSONObject filterData2 = new JSONObject();
    		filterData2.put("customerStatus", "1");
    		ArrayList<JSONObject> customerList = sqlManager.getCustomerList(filterData2);
    		
    		int f=0;
    		for (int d=0;d<accountCount;d++) {
    			if (d<accountList.size()) {
    				//预定信息
    				JSONObject bookMap = new JSONObject();
        			JSONObject bookAccountData = accountList.get(d);
        			//更新账户使用时间
            		Date currentDate2 = new Date();
            		String currentDateStr2 = sdf.format(currentDate2);
        			JSONObject updateTimeData = new JSONObject();
        			String accountNo = bookAccountData.getString("customerOrderNo");
        			updateTimeData.put("accountNo", accountNo);
        			updateTimeData.put("useTime", currentDateStr2);
        			sqlManager.updateAccountTime(updateTimeData);
        			
        			//预定信息-官网账户信息
        			bookMap=bookAccountData;
        			bookMap.put("appKey", DemoData.getSysData().getString("appKey"));
        			//预定信息-航班信息
        			ArrayList<Object> flightArr = new ArrayList<Object>();
        			flightArr.add(standbyData);
        			bookMap.put("flights", flightArr);
        			//预定信息-客户信息
            		ArrayList<JSONObject> bookCustomerDataArr = new ArrayList<JSONObject>();
        			for (;f<DemoData.PERSONTICKETS;f++) {
        				if(f<customerList.size()) {
        					JSONObject bookCustomerData = customerList.get(f);
            				bookCustomerDataArr.add(bookCustomerData);
                			//更新客户状态-锁定客户
                    		JSONObject updateStatusData = new JSONObject();
                    		updateStatusData.put("customerId", bookCustomerData.getString("customerId"));
                    		updateStatusData.put("customerStatus", "2");
                			sqlManager.updateCustomerStatus(updateStatusData);
        				} else {
        					System.out.println("乘机人信息已不够用了！！！");
        				}
        			}
        			bookMap.put("passengers", bookCustomerDataArr);
        			JSONObject bookData = new JSONObject(bookMap);
            		// 预定
        			bookResultTicket.put("bookData", bookData);
            		JSONObject resultData = new DemoNet().bookTicket(bookData);
            		resultData.put("standbyCount", standbyCount);
        			bookResultTicket.put("resultData", resultData);
    			} else {
    				System.out.println("官网账号已不够用了！！！");
    			}
    		}
    	} else {
    		System.out.println("查询航班没有余票啦！");
    	}
    	return bookResultTicket;
    }
    
    
    /**
     * 查询
     *
     * @param user
     * @return
     */
    public void bookLoop(JSONObject bookResultTicket) {
    	// 预定(循环)
    	try {
    		this.asyncService = ApplicationContextProvider.getBean(AsyncService.class);
			asyncService.bookAsync(bookResultTicket);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
}
