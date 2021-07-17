package com.ticketsystem.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ticketsystem.async.ApplicationContextProvider;
import com.ticketsystem.async.AsyncService3;
import com.ticketsystem.comp.BookComp;
import com.ticketsystem.comp.CancelComp;
import com.ticketsystem.comp.LoginComp;
import com.ticketsystem.comp.QueryComp;
import com.ticketsystem.model.BookData;
import com.ticketsystem.net.CookieUtil;
import com.ticketsystem.util.DemoData;
import com.ticketsystem.util.SqlManager;

@Service
public class FlightService2 {

	@Autowired
	private AsyncService3 asyncService3;
	
	/**
     * 预定<br/>
     * 传入fromCityCode，toCityCode，fromDate，<br/>
     * fightNo，cabinCode
     * @param 预定信息
     * @return
     */
	public void addTicket(JSONObject addData) {
		//开始新增订票逻辑
		JSONObject bigData = this.booking(addData);
    	if(bigData==null) {return;}
		
    	//调用循环逻辑，开始循环预定
		int standbyCount = bigData.getIntValue("standbyCount");
		if (standbyCount > 0) {
			this.bookLoop(bigData);
		}
	}
	
	/**
     * 预定<br/>
     * @param 预定信息addData{oiId, fromCityCode，toCityCode，fromDate，fightNo，cabinCode}<br/>
     * @return <br/>
     * 		bigData{
     * 			addData,
     * 			packageArrData[
     * 				accountData,customerArrData,bookData,orderData
     * 			],
     * 			otherData
     * 		}<br/>
     */
    public JSONObject booking(JSONObject addData) {
    	JSONObject bigData = new JSONObject();
    	bigData.put("addData", addData);
    	
    	ArrayList<JSONObject> packageArrData = new ArrayList<JSONObject>();
    	
    	String oiId = addData.getString("oiId");
    	String fromDate = addData.getString("fromDate");
    	String fightNo = addData.getString("fightNo");
    	String cabinCode = addData.getString("cabinCode");

    	//TODO 调用接口----------查询航班余票信息
    	String standbyCountStr = new QueryComp().queryTicket(addData);
    	int standbyCount  = Integer.valueOf(standbyCountStr);
    	
    	//standbyCount = 0;
    	
    	bigData.put("standbyCount", standbyCount);
    	
    	//预定数据组装
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
    			JSONObject packageData = new JSONObject();
    			JSONObject accountData = new JSONObject();
    			
    			if (d<accountList.size()) {
    				//预定信息
    				BookData bookDataBiz = new BookData();
    				
    				//从数据库获取一条官网账户信息
        			accountData = accountList.get(d);
        			//更新账户使用时间
            		Date currentDate2 = new Date();
            		String currentDateStr2 = sdf.format(currentDate2);
        			JSONObject updateTimeData = new JSONObject();
        			String accountNo = accountData.getString("accountNo");
        			updateTimeData.put("accountNo", accountNo);
        			updateTimeData.put("useTime", currentDateStr2);
        			sqlManager.updateAccountTime(updateTimeData);
        			
        			//填充bookDataBiz
        			bookDataBiz.setFlightDate(fromDate);
        			bookDataBiz.setCustomer(accountData.getString("name"), accountData.getString("contactMobile"));
        			
        			//TODO 调用接口----------查询航班具体信息
        			JSONObject queryPost2 = new QueryComp().queryTicket2(addData);
        			//填充bookDataBiz
        			String flightStr = queryPost2.getString("responseBody");
        			if (flightStr.length()<10) {return null;}
        			JSONObject flightData = JSONObject.parseObject(flightStr);
        			JSONObject processTripParam = bookDataBiz.processTripParam(flightData, fightNo, cabinCode);
        			bookDataBiz.addTripInfo(processTripParam);
        			
        			//TODO 调用接口----------账户登录
        			String encryptStr = accountData.getString("encryptStr");
        			String session = accountData.getString("session");
        			JSONObject loginResult = new LoginComp().accountLogin3(addData, encryptStr, session, processTripParam);
        			if(loginResult==null) {return null;}
        			String JSESSIONID = loginResult.getString("JSESSIONID");
        			String tokenUUID = loginResult.getString("tokenUUID");
        			String tokenId = loginResult.getString("tokenId");
        			String uuid = loginResult.getString("uuid");
        			
        			//预定信息-客户信息
            		ArrayList<JSONObject> customerDataArr = new ArrayList<JSONObject>();
            		String eachAmount = "";
        			for (;f<DemoData.PERSONTICKETS*(d+1);f++) {
        				if (f<standbyCount) {
            				if(f<customerList.size()) {
            					//获取一条乘客信息
            					JSONObject customerData = customerList.get(f);
            					customerDataArr.add(customerData);
            					
            					//更新客户状态-锁定客户
            					JSONObject updateStatusData = new JSONObject();
            					updateStatusData.put("customerId", customerData.getString("customerId"));
            					updateStatusData.put("customerStatus", "2");
            					updateStatusData.put("accountNo", accountData.getString("accountNo"));
            					sqlManager.updateCustomerStatus(updateStatusData);
            					
            					//填充bookDataBiz
            					bookDataBiz.addPassenger(customerData.getString("name"), customerData.getString("cardNo"), customerData.getString("mobile"));
            					JSONObject passInfo = new JSONObject();
            					passInfo.put("flightDate", fromDate);
            					passInfo.put("amountOld", processTripParam.getString("baseFare"));
            					passInfo.put("amount", processTripParam.getString("amount"));
            					eachAmount = processTripParam.getString("amount");
            					passInfo.put("name", customerData.getString("name"));
            					bookDataBiz.addPassengersInfo2(passInfo);
            					
            					bookDataBiz.addTotal(processTripParam.getString("amount"));
            					
            				} else {
            					System.out.println("乘机人信息已不够用了！！！");
            				}
            			}
            		}
        			//bookDataBiz最后的组装
        			bookDataBiz.initPassengers2();
        			bookDataBiz.setUuid(uuid);
        			
        	    	//TODO 调用接口----------机票预定接口
        			String bookDataStr = bookDataBiz.toString().replace(" ", "");
        			String bookCookie = CookieUtil.getBookCookie(tokenId, tokenUUID, session);
        			
        			JSONObject bookResult = new BookComp().bookTicket(bookDataStr, bookCookie);
        			if(bookResult.getString("orderNo").length()<7) {return null;}
        			
        			//JSONObject bookResult = new JSONObject();
        			//bookResult.put("orderNo", "temp"+String.valueOf(Math.random()).substring(2, 15)); //临时代码，假装上一行代码预定成功
        			
            		//新增或者更新订单信息
            		JSONObject orderInfoData = new JSONObject();
            		orderInfoData.put("accountNo", accountData.getString("accountNo"));
            		orderInfoData.put("orderNo", bookResult.getString("orderNo"));
            		orderInfoData.put("tripCode", addData.getString("tripCode"));
            		orderInfoData.put("flightNo", addData.getString("fightNo"));
            		orderInfoData.put("cabinCode", addData.getString("cabinCode"));
            		orderInfoData.put("standbyCount", standbyCount);
            		orderInfoData.put("price", eachAmount);
            		orderInfoData.put("orderStatus", "1");
            		orderInfoData.put("round", "1");
            		Date currentTime = new Date();
            		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
            		orderInfoData.put("inputTime", sdf2.format(currentTime));
            		orderInfoData.put("updateTime", sdf2.format(currentTime));
            		orderInfoData.put("inputUser", "user");

            		if (oiId == null || oiId.length()<1) {
            			//新增订单信息
            			JSONObject insertOrderInfo = sqlManager.insertOrderInfo(orderInfoData);
            			addData.put("oiId", insertOrderInfo.getString("oiId"));
            			bookResult.put("oiId", insertOrderInfo.getString("oiId"));
            			bookResult.put("accountNo", accountNo);
            		} else {
            			//更新订单信息
            			JSONObject orderInfo2 = sqlManager.getOrderInfo(oiId);
            			String round2= orderInfo2.getString("round");
            			orderInfoData.put("oiId", oiId);
            			orderInfoData.put("round", String.valueOf(Integer.valueOf(round2)+1));
            			sqlManager.updateOrder(orderInfoData);
            			addData.put("oiId", oiId);
            			bookResult.put("oiId", oiId);
            			bookResult.put("accountNo", accountNo);
            		}
            		
        			packageData.put("accountData", accountData);
        			packageData.put("customerArrData", customerDataArr);
        			packageData.put("bookDataStr", bookDataStr);
        			packageData.put("orderData", bookResult);
        			packageData.put("standbyCount", standbyCount);
        			packageArrData.add(packageData);
    			} else {
    				System.out.println("官网账号已不够用了！！！");
    			}
    		}
    		
    		//返回信息
    		bigData.put("packageArrData", packageArrData);
    		
    	} else {
    		System.out.println("查询航班没有余票啦！");
    	}
    	return bigData;
    }


    /**
     * 取消订单<br/>
     *<br/>
     * @param cancelData{oiId, accountNo}<br/>
     * @return void<br/>
     */
    public void cancelTicket(JSONObject cancelData) {
    	SqlManager sqlManager = new SqlManager();
    	String oiId = cancelData.getString("oiId");
    	String orderNo = cancelData.getString("orderNo");
    	String accountNo = cancelData.getString("accountNo");
    	
    	//更新订单信息
    	sqlManager.updateOrderStatus2(oiId, "2");
    	//TODO 调用接口----------取消订单接口
    	new CancelComp().cancelTicket(orderNo, accountNo);
    	
    	//更新客户信息
    	sqlManager.updateCustomerByOrder2(accountNo, "1");
    }
    
    
    /**
     * 调用循环订票程序
     *
     * @param user
     * @return
     */
    private void bookLoop(JSONObject bigData) {
    	// 预定(循环)
    	try {
    		JSONArray jsonArray = bigData.getJSONArray("packageArrData");
    		for (int i=0;i<jsonArray.size();i++) {
    			JSONObject loopData = new JSONObject();
    			loopData.put("addData", bigData.getJSONObject("addData"));
    			loopData.put("customerArrData", jsonArray.getJSONObject(i).getJSONArray("customerArrData"));
    			loopData.put("orderInfoData", jsonArray.getJSONObject(i).getJSONObject("orderData"));
    			//获取线程池服务
    			this.asyncService3 = ApplicationContextProvider.getBean(AsyncService3.class);
    			//启动线程
    			asyncService3.bookLoop(loopData);
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    
}
