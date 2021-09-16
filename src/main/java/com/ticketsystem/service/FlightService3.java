package com.ticketsystem.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import com.ticketsystem.util.KnSqlManager;
import com.ticketsystem.util.StringX;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FlightService3 {

	@Autowired
	private AsyncService3 asyncService3;
	Logger log = LogManager.getLogger(FlightService3.class);
	
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
    	if(bigData==null) {
    		//压票失败，发出警报，更新订单状态为
    		//TODO 更新账户信息
    		return;
    	}
		
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
    public synchronized JSONObject booking(JSONObject addData) {
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	log.info("预定");

    	JSONObject bigData = new JSONObject();
    	bigData.put("addData", addData);
    	
		String ticketNumber = addData.getString("ticketNumber");
    	
    	ArrayList<JSONObject> packageArrData = new ArrayList<JSONObject>();
    	
    	//String oiId = addData.getString("oiId");
    	String fromDate = addData.getString("fromDate");
    	String tripStr = addData.getString("tripCode");
    	String fightNo = addData.getString("fightNo");
    	String cabinCode = addData.getString("cabinCode");

    	//TODO 调用接口----------查询航班余票信息
    	String standbyCountStr = new QueryComp().queryTicket(addData);
    	int standbyCount  = Integer.valueOf(standbyCountStr);
    	//standbyCount = 2;
    	
    	//预定数据组装
    	if (standbyCount>0) {
    		
    		int currStandBy = 0;
    		if(!StringX.empty(ticketNumber)) {
    			currStandBy = Integer.valueOf(ticketNumber);
    		} else {
    			currStandBy = standbyCount>DemoData.PERSONTICKETS?DemoData.PERSONTICKETS:standbyCount;
    		}
    		
    		bigData.put("standbyCount", currStandBy);
    		bigData.put("preNumber", currStandBy);
    		
    		KnSqlManager sqlManager = new KnSqlManager();
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
    		
			JSONObject packageData = new JSONObject();
			JSONObject accountData = new JSONObject();
			
			if (accountList.size()>0) {
				//预定信息
				BookData bookDataBiz = new BookData();
				
				//从数据库获取一条官网账户信息
    			accountData = accountList.get(0);
    			String accountNo = accountData.getString("accountNo");
    			String accountPas = accountData.getString("accountPas");
    			String encryptStr = accountData.getString("encryptStr");
    			String accountName = accountData.getString("name");
    			String accountContactMobile = accountData.getString("contactMobile");
    			String useTime = accountData.getString("useTime");
    			String accountTokenUUID = accountData.getString("tokenUUID");
    			String accountTokenId = accountData.getString("tokenId");
    			String accountSession = accountData.getString("session");
    			//String accountJsessionid = accountData.getString("jsessionid");
    			
    			//填充bookDataBiz
    			bookDataBiz.setFlightDate(fromDate);
    			bookDataBiz.setCustomer(accountName, accountContactMobile);
    			
    			// TODO 调用接口----------登录
    			JSONObject loginResult0 = new JSONObject();
    			String tokenUUID = "";
    			String tokenId = "";
    			String session ="";
    			//String JSESSIONID ="";
    			//判断账户是否已经登录
    			if(!StringX.empty(useTime) && "2000-01-01".equals(useTime)) {
    				//已经登录
    				loginResult0.put("tokenUUID", accountTokenUUID);
    				loginResult0.put("tokenId", accountTokenId);
    				loginResult0.put("session", accountSession);
        			tokenUUID = accountTokenUUID;
        			tokenId = accountTokenId;
        			session =accountSession;
        			//JSESSIONID =accountJsessionid;
        			//更新账户使用时间
            		Date currentDate2 = new Date();
            		String currentDateStr2 = sdf.format(currentDate2);
        			JSONObject updateTimeData = new JSONObject();
        			updateTimeData.put("accountNo", accountNo);
        			updateTimeData.put("useTime", currentDateStr2);
        			updateTimeData.put("session", accountSession);
        			sqlManager.updateAccountTime(updateTimeData);
    			} else {
    				//未登录
        			//更新账户使用时间
            		Date currentDate2 = new Date();
            		String currentDateStr2 = sdf.format(currentDate2);
        			JSONObject updateTimeData = new JSONObject();
        			updateTimeData.put("accountNo", accountNo);
        			updateTimeData.put("useTime", currentDateStr2);
        			updateTimeData.put("session", session);
        			sqlManager.updateAccountTime(updateTimeData);
        			
    				loginResult0 = new LoginComp().login2(accountNo, accountPas);
    				tokenUUID = loginResult0.getString("tokenUUID");
    				tokenId = loginResult0.getString("tokenId");
    				session = loginResult0.getString("session");
    				//JSESSIONID = loginResult.getString("JSESSIONID");
    				//再更新一次
        			updateTimeData.put("session", session);
        			sqlManager.updateAccountTime(updateTimeData);
    			}
    			if(session==null||session.length()<5) {
    				log.error("登陆失败！！！");
    				return null;
    			}
    			
    			//TODO 调用接口----------查询航班具体信息
    			addData.put("currStandBy", currStandBy);
    			addData.put("session", session);
    			JSONObject queryPost2 = new QueryComp().queryTicket2(addData);
    			
    			//填充bookDataBiz
    			String flightStr = queryPost2.getString("responseBody");
    			if (flightStr.length()<10) {
					log.error("查询航班具体信息失败！！！");
    				return null;
    			}
    			JSONObject flightData = JSONObject.parseObject(flightStr);
    			JSONObject processTripParam = bookDataBiz.processTripParam(flightData, fightNo, cabinCode);
    			//确认是否已经拿到正确的仓位价格信息，北京有点特殊，此处为特殊处理
    			String basecabinfareamount = processTripParam.getString("baseFare");
    			if(("null".equalsIgnoreCase(basecabinfareamount) || basecabinfareamount==null) && "PKX".equalsIgnoreCase(addData.getString("fromCityCode"))) {
        			JSONObject queryPost22 = new QueryComp().queryTicket3(addData);
        			//填充bookDataBiz
        			String flightStr22 = queryPost22.getString("responseBody");
        			if (flightStr22.length()<10) {
    					log.error("查询航班具体信息失败！！！");
        				return null;
        			}
        			addData.put("changeFlightFrom", "1");
        			JSONObject flightData22 = JSONObject.parseObject(flightStr22);
        			processTripParam = bookDataBiz.processTripParam(flightData22, fightNo, cabinCode);
    			}
    			bookDataBiz.addTripInfo(processTripParam);
    			
    			//TODO 调用接口----------加入购物车
    			JSONObject loginResult = new LoginComp().accountLogin3(addData, accountNo, accountPas, encryptStr, processTripParam, loginResult0);
    			if(loginResult==null) {
    				log.error("加入购物车失败！！！");
    				return null;
    			}
    			String uuid = loginResult.getString("uuid");
    			
    			//预定信息-客户信息
        		ArrayList<JSONObject> customerDataArr = new ArrayList<JSONObject>();
        		String eachAmount = "";
        		//判断乘机人人数是否已经超过当前轮次上线
    			for (int f = 0;f<currStandBy;f++) {
    				if(f<customerList.size()) {
    					//获取一条乘客信息
    					JSONObject customerData = customerList.get(f);
    					customerDataArr.add(customerData);
    					
    					//更新客户状态-锁定客户
    					JSONObject updateStatusData = new JSONObject();
    					updateStatusData.put("customerId", customerData.getString("customerId"));
    					updateStatusData.put("customerStatus", "2");
    					updateStatusData.put("accountNo", accountNo);
    					sqlManager.updateCustomerStatus(updateStatusData);
    					
    					//填充bookDataBiz
    					bookDataBiz.addPassenger(customerData.getString("name"), customerData.getString("cardNo"), customerData.getString("mobile"));
    					JSONObject passInfo = new JSONObject();
    					passInfo.put("flightDate", fromDate);
    					passInfo.put("amountOld", processTripParam.getString("baseFare"));
    					passInfo.put("amount", processTripParam.getString("amount"));
    					eachAmount = processTripParam.getString("amount");
    					passInfo.put("name", customerData.getString("name"));
    					passInfo.put("quantity", currStandBy);
    					bookDataBiz.addPassengersInfo2(passInfo);
    					
    					bookDataBiz.addTotal(processTripParam.getString("amount"));
    					
    				} else {
    					log.error("乘机人信息已不够用了！！！");
    				}
        		}
    			//bookDataBiz最后的组装
    			bookDataBiz.initPassengers2();
    			bookDataBiz.setUuid(uuid);
    			
    			String bookDataStr = bookDataBiz.toString().replaceAll(" ", "").replaceAll(" +","").replaceAll("\\s*", "");
    			//String bookCookie = CookieUtil.getBookCookie(tokenId, tokenUUID, session);
    			String bookCookie = CookieUtil.getBookCookie2(tokenId, tokenUUID, session);
    			
    			//TODO 调用接口----------机票预定接口
    			JSONObject bookResult = new BookComp().bookTicket(bookDataStr, bookCookie);
    			if(StringX.empty(bookResult.getString("orderNo"))) {
    				//新增压票失败，更新账户信息，更新客户信息
        			//更新账户信息-初始化使用时间
        			/*JSONObject updateTimeData2 = new JSONObject();
        			updateTimeData2.put("accountNo", accountNo);
        			updateTimeData2.put("useTime", "2021/07/11");
        			updateTimeData2.put("session", session);
        			sqlManager.updateAccountTime(updateTimeData2);
        			*/
        			//更新客户信息-解锁
        			for (int cc=0;cc<customerDataArr.size();cc++) {
        				JSONObject customerObject = customerDataArr.get(cc);
    					JSONObject updateStatusData2 = new JSONObject();
    					updateStatusData2.put("customerId", customerObject.getString("customerId"));
    					updateStatusData2.put("customerStatus", "1");
    					updateStatusData2.put("accountNo", accountNo);
    					sqlManager.updateCustomerStatus(updateStatusData2);
        			}
        			log.error("预定失败！！！");
    				return null;
    			}
    			Date bookDate = new Date();
    			DemoData.PreBookTimeMap.put(tripStr+fightNo+cabinCode, bookDate);

    			log.info("订票成功！！！");
    			
        		//新增订单信息
        		JSONObject orderInfoData = new JSONObject();
        		orderInfoData.put("accountNo", accountNo);
        		orderInfoData.put("orderNo", bookResult.getString("orderNo"));
        		orderInfoData.put("tripCode", addData.getString("tripCode"));
        		orderInfoData.put("flightNo", addData.getString("fightNo"));
        		orderInfoData.put("cabinCode", addData.getString("cabinCode"));
        		orderInfoData.put("standbyCount", currStandBy);
        		orderInfoData.put("price", eachAmount);
        		orderInfoData.put("orderStatus", "正常");
        		orderInfoData.put("round", "1");
        		orderInfoData.put("inputTime", format.format(new Date()));
        		orderInfoData.put("updateTime", format.format(new Date()));
        		orderInfoData.put("inputUser", "user");
        		
        		JSONObject insertOrderInfo = sqlManager.insertOrderInfo(orderInfoData);
    			addData.put("oiId", insertOrderInfo.getString("oiId"));
    			bookResult.put("oiId", insertOrderInfo.getString("oiId"));
    			bookResult.put("accountNo", accountNo);
        		
    			packageData.put("accountData", accountData);
    			packageData.put("customerArrData", customerDataArr);
    			packageData.put("bookDataStr", bookDataStr);
    			packageData.put("orderData", bookResult);
    			packageData.put("standbyCount", currStandBy);
    			packageArrData.add(packageData);
			} else {
				log.error("官网账号已不够用了！！！");
			}
    		
    		//返回信息
    		bigData.put("packageArrData", packageArrData);
    		
    	} else {
    		log.error("查询航班没有余票啦！！！");
    	}
    	return bigData;
    }



	
	/**
     * 预定(循环压票逻辑)<br/>
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
    public synchronized JSONObject booking2(JSONObject addData) {
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	log.info("开始预定(循环)");

    	JSONObject bigData = new JSONObject();
    	bigData.put("addData", addData);
    	
		String ticketNumber = addData.getString("ticketNumber");
		String preNumber = addData.getString("preNumber");
		
		KnSqlManager sqlManager = new KnSqlManager();
    	
    	ArrayList<JSONObject> packageArrData = new ArrayList<JSONObject>();
    	
    	//String oiId = addData.getString("oiId");
    	String fromDate = addData.getString("fromDate");
    	String tripStr = addData.getString("tripCode");
    	String fightNo = addData.getString("fightNo");
    	String cabinCode = addData.getString("cabinCode");

    	//TODO 调用接口----------查询航班余票信息
    	String standbyCountStr = new QueryComp().queryTicket(addData);
    	int standbyCount  = Integer.valueOf(standbyCountStr);
    	//standbyCount = 2;
    	
    	//预定数据组装
    	if (standbyCount>0) {
    		
    		int currStandBy = 0;
    		if(!StringX.empty(ticketNumber)) {
    			//判断是否票变少了,以当前剩余票数发起预定，后续会发出警报
    			if (standbyCount<Integer.valueOf(preNumber)) {
    				currStandBy = standbyCount;
    			}
    			currStandBy = Integer.valueOf(ticketNumber);
    		} else {
    			currStandBy = standbyCount>DemoData.PERSONTICKETS?DemoData.PERSONTICKETS:standbyCount;
    		}
    		
    		bigData.put("standbyCount", currStandBy);
    		bigData.put("preNumber", currStandBy);
    		
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
    		
			JSONObject packageData = new JSONObject();
			JSONObject accountData = new JSONObject();
			
			if (accountList.size()>0) {
				//预定信息
				BookData bookDataBiz = new BookData();
				
				//从数据库获取一条官网账户信息
    			accountData = accountList.get(0);
    			String accountNo = accountData.getString("accountNo");
    			String accountPas = accountData.getString("accountPas");
    			String encryptStr = accountData.getString("encryptStr");
    			String accountName = accountData.getString("name");
    			String accountContactMobile = accountData.getString("contactMobile");
    			String useTime = accountData.getString("useTime");
    			String accountTokenUUID = accountData.getString("tokenUUID");
    			String accountTokenId = accountData.getString("tokenId");
    			String accountSession = accountData.getString("session");
    			//String accountJsessionid = accountData.getString("jsessionid");
    			
    			//填充bookDataBiz
    			bookDataBiz.setFlightDate(fromDate);
    			bookDataBiz.setCustomer(accountName, accountContactMobile);
    			
    			// TODO 调用接口----------登录
    			JSONObject loginResult0 = new JSONObject();
    			String tokenUUID = "";
    			String tokenId = "";
    			String session ="";
    			//String JSESSIONID ="";
    			//判断账户是否已经登录
    			if(!StringX.empty(useTime) && "2000-01-01".equals(useTime)) {
    				//已经登录
    				loginResult0.put("tokenUUID", accountTokenUUID);
    				loginResult0.put("tokenId", accountTokenId);
    				loginResult0.put("session", accountSession);
        			tokenUUID = accountTokenUUID;
        			tokenId = accountTokenId;
        			session =accountSession;
        			//JSESSIONID =accountJsessionid;
        			//更新账户使用时间
            		Date currentDate2 = new Date();
            		String currentDateStr2 = sdf.format(currentDate2);
        			JSONObject updateTimeData = new JSONObject();
        			updateTimeData.put("accountNo", accountNo);
        			updateTimeData.put("useTime", currentDateStr2);
        			updateTimeData.put("session", accountSession);
        			sqlManager.updateAccountTime(updateTimeData);
    			} else {
    				//未登录
        			//更新账户使用时间
            		Date currentDate2 = new Date();
            		String currentDateStr2 = sdf.format(currentDate2);
        			JSONObject updateTimeData = new JSONObject();
        			updateTimeData.put("accountNo", accountNo);
        			updateTimeData.put("useTime", currentDateStr2);
        			updateTimeData.put("session", session);
        			sqlManager.updateAccountTime(updateTimeData);
        			
    				loginResult0 = new LoginComp().login2(accountNo, accountPas);
    				tokenUUID = loginResult0.getString("tokenUUID");
    				tokenId = loginResult0.getString("tokenId");
    				session = loginResult0.getString("session");
    				//JSESSIONID = loginResult.getString("JSESSIONID");
    				//再更新一次
        			updateTimeData.put("session", session);
        			sqlManager.updateAccountTime(updateTimeData);
    			}
    			if(session==null||session.length()<5) {
    				log.error("登陆失败！！！");
    				return null;
    			}    				
    			
    			//TODO 调用接口----------查询航班具体信息
    			addData.put("currStandBy", currStandBy);
    			addData.put("session", session);
    			JSONObject queryPost2 = new QueryComp().queryTicket2(addData);
    			
    			//填充bookDataBiz
    			String flightStr = queryPost2.getString("responseBody");
    			if (flightStr.length()<10) {
					log.error("查询航班具体信息失败！！！");
    				return null;
    			}
    			JSONObject flightData = JSONObject.parseObject(flightStr);
    			JSONObject processTripParam = bookDataBiz.processTripParam(flightData, fightNo, cabinCode);
    			//确认是否已经拿到正确的仓位价格信息，北京有点特殊，此处为特殊处理
    			String basecabinfareamount = processTripParam.getString("baseFare");
    			if(("null".equalsIgnoreCase(basecabinfareamount) || basecabinfareamount==null) && "PKX".equalsIgnoreCase(addData.getString("fromCityCode"))) {
        			JSONObject queryPost22 = new QueryComp().queryTicket3(addData);
        			//填充bookDataBiz
        			String flightStr22 = queryPost22.getString("responseBody");
        			if (flightStr22.length()<10) {
    					log.error("查询航班具体信息失败！！！");
        				return null;
        			}
        			addData.put("changeFlightFrom", "1");
        			JSONObject flightData22 = JSONObject.parseObject(flightStr22);
        			processTripParam = bookDataBiz.processTripParam(flightData22, fightNo, cabinCode);
    			}
    			bookDataBiz.addTripInfo(processTripParam);
    			
    			//TODO 调用接口----------加入购物车
    			JSONObject loginResult = new LoginComp().accountLogin3(addData, accountNo, accountPas, encryptStr, processTripParam, loginResult0);
    			if(loginResult==null) {
					log.error("加入购物车失败！！！");
    				return null;
    			}
    			String uuid = loginResult.getString("uuid");
    			
    			//预定信息-客户信息
        		ArrayList<JSONObject> customerDataArr = new ArrayList<JSONObject>();
        		String eachAmount = "";
        		//判断乘机人人数是否已经超过当前轮次上线
    			for (int f = 0;f<currStandBy;f++) {
    				if(f<customerList.size()) {
    					//获取一条乘客信息
    					JSONObject customerData = customerList.get(f);
    					customerDataArr.add(customerData);
    					
    					//更新客户状态-锁定客户
    					JSONObject updateStatusData = new JSONObject();
    					updateStatusData.put("customerId", customerData.getString("customerId"));
    					updateStatusData.put("customerStatus", "2");
    					updateStatusData.put("accountNo", accountNo);
    					sqlManager.updateCustomerStatus(updateStatusData);
    					
    					//填充bookDataBiz
    					bookDataBiz.addPassenger(customerData.getString("name"), customerData.getString("cardNo"), customerData.getString("mobile"));
    					JSONObject passInfo = new JSONObject();
    					passInfo.put("flightDate", fromDate);
    					passInfo.put("amountOld", processTripParam.getString("baseFare"));
    					passInfo.put("amount", processTripParam.getString("amount"));
    					eachAmount = processTripParam.getString("amount");
    					passInfo.put("name", customerData.getString("name"));
    					passInfo.put("quantity", currStandBy);
    					bookDataBiz.addPassengersInfo2(passInfo);
    					
    					bookDataBiz.addTotal(processTripParam.getString("amount"));
    					
    				} else {
    					log.error("乘机人信息已不够用了！！！");
    					return null;
    				}
        		}
    			//bookDataBiz最后的组装
    			bookDataBiz.initPassengers2();
    			bookDataBiz.setUuid(uuid);
    			
    			String bookDataStr = bookDataBiz.toString().replaceAll(" ", "").replaceAll(" +","").replaceAll("\\s*", "");
    			//String bookCookie = CookieUtil.getBookCookie(tokenId, tokenUUID, session);
    			String bookCookie = CookieUtil.getBookCookie2(tokenId, tokenUUID, session);
    			
    			//TODO 调用接口----------机票预定接口
    			JSONObject bookResult = new BookComp().bookTicket(bookDataStr, bookCookie);
    			if(StringX.empty(bookResult.getString("orderNo"))) {
    				//新增压票失败，更新账户信息，更新客户信息
        			//更新账户信息-初始化使用时间
        			/*JSONObject updateTimeData2 = new JSONObject();
        			updateTimeData2.put("accountNo", accountNo);
        			updateTimeData2.put("useTime", "2021/07/11");
        			updateTimeData2.put("session", session);
        			sqlManager.updateAccountTime(updateTimeData2);
        			*/
        			//更新客户信息-解锁
        			for (int cc=0;cc<customerDataArr.size();cc++) {
        				JSONObject customerObject = customerDataArr.get(cc);
    					JSONObject updateStatusData2 = new JSONObject();
    					updateStatusData2.put("customerId", customerObject.getString("customerId"));
    					updateStatusData2.put("customerStatus", "1");
    					updateStatusData2.put("accountNo", accountNo);
    					sqlManager.updateCustomerStatus(updateStatusData2);
        			}
					log.error("在此新增订单，压票失败！！！");
    				return null;
    			}
    			Date bookDate = new Date();
    			DemoData.PreBookTimeMap.put(tripStr+fightNo+cabinCode, bookDate);

    			log.info("订票成功！！！");
    	    	bigData.put("bookSucess", "true");
    			
    	    	//订票成功之后，检查是否当前订票张数 小于 前一轮订票张数
    			if (currStandBy<Integer.valueOf(preNumber)) {
    				sqlManager.insertLost(accountNo, "less");
    			}
    			
        		//新增订单信息
        		JSONObject orderInfoData = new JSONObject();
        		orderInfoData.put("accountNo", accountNo);
        		orderInfoData.put("orderNo", bookResult.getString("orderNo"));
        		orderInfoData.put("tripCode", addData.getString("tripCode"));
        		orderInfoData.put("flightNo", addData.getString("fightNo"));
        		orderInfoData.put("cabinCode", addData.getString("cabinCode"));
        		orderInfoData.put("standbyCount", currStandBy);
        		orderInfoData.put("price", eachAmount);
        		orderInfoData.put("orderStatus", "正常");
        		orderInfoData.put("round", "1");
        		orderInfoData.put("inputTime", format.format(new Date()));
        		orderInfoData.put("updateTime", format.format(new Date()));
        		orderInfoData.put("inputUser", "user");
        		
        		JSONObject insertOrderInfo = sqlManager.insertOrderInfo(orderInfoData);
    			addData.put("oiId", insertOrderInfo.getString("oiId"));
    			bookResult.put("oiId", insertOrderInfo.getString("oiId"));
    			bookResult.put("accountNo", accountNo);
        		
    			packageData.put("accountData", accountData);
    			packageData.put("customerArrData", customerDataArr);
    			packageData.put("bookDataStr", bookDataStr);
    			packageData.put("orderData", bookResult);
    			packageData.put("standbyCount", currStandBy);
    			packageArrData.add(packageData);
			} else {
				log.error("官网账号已不够用了！！！");
				return null;
			}
    		
    		//返回信息
    		bigData.put("packageArrData", packageArrData);
    		
    	} else {
    		log.error("查询航班没有余票啦！！！");
			return null;
    	}
    	return bigData;
    }
    

    /**
     * 取消订单(循环逻辑中)<br/>
     * <br/>
     * @param cancelData{oiId, accountNo}<br/>
     * @return void<br/>
     */
    public synchronized String cancelTicket2(JSONObject cancelData) {
    	String result = "";
    	KnSqlManager sqlManager = new KnSqlManager();
    	String oiId = cancelData.getString("oiId");
    	String orderNo = cancelData.getString("orderNo");
    	String accountNo = cancelData.getString("accountNo");

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	
    	//TODO 调用接口----------取消订单接口
    	try {
			new CancelComp().cancelTicket(orderNo, accountNo);
			log.info("取消成功之后，更新订单信息");
			//更新订单信息
			sqlManager.updateOrderStatus2(oiId, "正常结束", "");
			result="success";
		} catch (Exception e) {
			log.error("取消订单失败！！！");
			e.printStackTrace();
			//取消失败后，等待两分零10秒后，直接开始下轮订票循环
			JSONObject orderData0 = sqlManager.getOrderInfo(oiId);
			String inputTime0 = orderData0.getString("inputTime");
			try {
				long waitTime = (1000*60*2+1000*10);
				long startTime = format.parse(inputTime0).getTime();
				long secondTime = startTime+waitTime;
				String secondTimeStr = format.format(new Date(secondTime));
				sqlManager.updateOrderInputTime(oiId,secondTimeStr,"second");
			} catch (ParseException e2) {
				e2.printStackTrace();
			}
			result="failed";
			/*
    		log.error("取消失败之后，更新订单信息！！！");
    		//发出警报
    		sqlManager.insertLost(accountNo, "failed");
    		//取消失败，更新订单状态为取消失败
    		sqlManager.updateOrderStatus2(oiId, "取消失败");
			 */
		}
    	
    	//更新账户使用时间
		Date currentDate2 = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String currentDateStr2 = sdf.format(currentDate2);
		JSONObject updateTimeData = new JSONObject();
		updateTimeData.put("accountNo", accountNo);
		updateTimeData.put("useTime", currentDateStr2);
		updateTimeData.put("session", "");
		sqlManager.updateAccountTime(updateTimeData);
    	
    	//更新客户信息
    	sqlManager.updateCustomerByOrder2(accountNo, "1");
    	return result;
    }

    /**
     * 暂停订单<br/>
     *<br/>
     * @param cancelData{oiId, accountNo}<br/>
     * @return void<br/>
     */
    public synchronized void suspendTicket(JSONObject cancelData) {
    	KnSqlManager sqlManager = new KnSqlManager();
    	String oiId = cancelData.getString("oiId");
    	String orderNo = cancelData.getString("orderNo");
    	String accountNo = cancelData.getString("accountNo");
    	
    	//TODO 调用接口----------取消订单接口
    	boolean cancelFlag = true;
    	try {
			new CancelComp().cancelTicket(orderNo, accountNo);
		} catch (Exception e) {
			cancelFlag = false;
			e.printStackTrace();
		}
    	
    	if (cancelFlag) {
    		log.info("取消订单成功");
    		//更新订单信息
    		sqlManager.updateOrderStatus2(oiId, "订单暂停", "");
    	} else {
    		log.error("取消订单失败！！！");
    		//取消失败，更新订单状态为取消失败
    		sqlManager.updateOrderStatus2(oiId, "暂停失败", "");
    	}
    	
    	//更新账户使用时间
		Date currentDate2 = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String currentDateStr2 = sdf.format(currentDate2);
		JSONObject updateTimeData = new JSONObject();
		updateTimeData.put("accountNo", accountNo);
		updateTimeData.put("useTime", currentDateStr2);
		updateTimeData.put("session", "");
		sqlManager.updateAccountTime(updateTimeData);
    	
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
    			loopData.put("preNumber", bigData.getString("preNumber"));
    			//获取线程池服务
    			this.asyncService3 = ApplicationContextProvider.getBean(AsyncService3.class);
    			//启动线程
    			asyncService3.bookLoop(loopData);
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    
    /**
     * 官网账号登录<br/>
     * @param oiId 订单流水号
     */
	public void loginAccount() {
		log.info("官网账号登录");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		KnSqlManager sqlManager = new KnSqlManager();
		
		JSONObject filterData = new JSONObject();
		filterData.put("useTime", sdf.format(new Date()));
		//获取官网账户信息
		ArrayList<JSONObject> accountList = sqlManager.getAccountList(filterData);
		
		if (accountList.size()>0) {
			//从数据库获取一条官网账户信息
			JSONObject accountData = accountList.get(0);
			String accountNo = accountData.getString("accountNo");
			String accountPas = accountData.getString("accountPas");
			// TODO 调用接口----------登录
			JSONObject logInResult = new LoginComp().login2(accountNo, accountPas);
			String tokenUUID = logInResult.getString("tokenUUID");
			String tokenId = logInResult.getString("tokenId");
			String session = logInResult.getString("session");
			//String JSESSIONID = loginResult.getString("JSESSIONID");
			if(session==null||session.length()<5) {
				log.error("登陆失败！！！");
			} else {
				log.info("登录成功");
				//登录成功之后，更新当前账户的session以及useTime=2000-01-01
    			JSONObject updateSessionData = new JSONObject();
    			updateSessionData.put("accountNo", accountNo);
    			updateSessionData.put("useTime", "2000-01-01");
    			updateSessionData.put("tokenUUID", tokenUUID);
    			updateSessionData.put("tokenId", tokenId);
    			updateSessionData.put("session", session);
    			sqlManager.updateAccountSession(updateSessionData);
			}
		}
	}

    /**
     * 删除订单<br/>
     * @param oiId 订单流水号
     */
	public void deleteOrder(String oiId) {
		KnSqlManager sqlManager = new KnSqlManager();
		sqlManager.deleteOrder(oiId);
	}


    /**
     * 删除丢票信息<br/>
     */
	public void deleteLost() {
		KnSqlManager sqlManager = new KnSqlManager();
		sqlManager.deleteLost();
	}
	


    /**
     * 删除订单<br/>
     * @param oiId 订单流水号
     */
	public JSONObject getLost() {
		KnSqlManager sqlManager = new KnSqlManager();
		JSONObject lostJson = sqlManager.getLost();
		return lostJson;
	}
	
	
}

