package com.ticketsystem.async;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ticketsystem.comp.LoginComp;
import com.ticketsystem.service.FlightService3;
import com.ticketsystem.util.DemoData;
import com.ticketsystem.util.KnSqlManager;
import com.ticketsystem.util.StringX;


@Service
public class AsyncService3 {
	
	Logger log = LogManager.getLogger(AsyncService3.class);
	
	/**
	 * 开启新线程<br>
	 * @param bookResultData
	 */
	@Async("doSomethingExecutor")
	public void bookLoop(JSONObject loopData) {
		deepLoop(loopData);
	}
	
	public void deepLoop(JSONObject loopData) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		log.info("开始循环，监听开始");

		KnSqlManager sqlManager = new KnSqlManager();
		FlightService3 FlightService3 = new FlightService3();
		JSONObject preOrderData = loopData.getJSONObject("orderInfoData");
		JSONObject addData2 = loopData.getJSONObject("addData");
    	String tripStr = addData2.getString("tripCode");
    	String fightNo = addData2.getString("fightNo");
    	String cabinCode = addData2.getString("cabinCode");
		
		boolean accessFlag = true;
		//获取订单开始时间作为循环开始时间
		String oiId = preOrderData.getString("oiId");
		JSONObject orderData0 = sqlManager.getOrderInfo(oiId);
		long startTimeMillis = 0;
		String inputTime0 = orderData0.getString("inputTime");
		if(!StringX.empty(inputTime0)){
			try {
				startTimeMillis = format.parse(inputTime0).getTime();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else{
			startTimeMillis = System.currentTimeMillis();
		}
		String remark0 = orderData0.getString("remark");
		//当前时间
		long currentTimeMillis = System.currentTimeMillis();
		while(currentTimeMillis-startTimeMillis<DemoData.COUNTDOWNMILLIS) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//时刻监控，该订单是否被取消了
			JSONObject orderData = sqlManager.getOrderInfo(oiId);
			if(!"正常".equals(orderData.getString("orderStatus"))) {
				//TODO 深层次循环唯一出口
				//如果订单状态不是 1 ，则直接跳出所有循环，结束循环订票逻辑，也就结束了此线程
				log.info("监听时段【提前】结束");
				return;
			}
			//经历的实际时间+两分钟 > 规定等待时间，去提前登录下一个账号
			if(currentTimeMillis-startTimeMillis+1000*60*2>DemoData.COUNTDOWNMILLIS && accessFlag && !"second".equals(remark0)) {
				log.info("本轮循环剩余2分钟，开始登录");
				//获取官网账户信息
	    		JSONObject filterData = new JSONObject();
	    		Date currentDate = new Date();
	    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    		String currentDateStr = sdf.format(currentDate);
	    		filterData.put("useTime", currentDateStr);
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
	    		accessFlag = false;
			}
			currentTimeMillis = System.currentTimeMillis();
		}
		try {
			Date bookTimeDate = DemoData.PreBookTimeMap.get(tripStr+fightNo+cabinCode);
			if (bookTimeDate!=null) {
				log.info("循环存在上次预定时间");
				while(true) {
					Date currTime = new Date();
					if (currTime.before(new Date(bookTimeDate.getTime()+1000*15))) {
						Thread.sleep(1000);
					} else {
						break;
					}
				}
				log.info("增加间隔结束");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("监听时段结束");
		
		//每轮循环结束后，后续调用取消订单，重新下单
		if(!"second".equals(remark0)) {
			//调用取消订单接口
			//TODO 调用接口----------取消订单接口
			JSONObject cancelJson = new JSONObject();
			cancelJson.put("oiId", preOrderData.getString("oiId"));
			cancelJson.put("orderNo", preOrderData.getString("orderNo"));
			cancelJson.put("accountNo", preOrderData.getString("accountNo"));
			String cancelResult = FlightService3.cancelTicket2(cancelJson);
			if("success".equalsIgnoreCase(cancelResult)) {
				log.info("循环放票成功");
			}else {
				//取消失败后，已更新原订单开始时间，重新进入后续订单监听时段，不过不会再进入提前登录和取消订单步骤
				deepLoop(loopData);
			}
		} else {
			sqlManager.updateOrderStatus2(oiId, "正常结束", "");
			log.info("跳过取消阶段");
		}
		
		//解锁客户
		JSONArray customerArrData = loopData.getJSONArray("customerArrData");
		for (int i=0;i<customerArrData.size();i++) {
			JSONObject customerData = new JSONObject();
			customerData.put("customerId", customerArrData.getJSONObject(i).getString("customerId"));
			customerData.put("customerStatus", "1");
			sqlManager.updateCustomerStatus(customerData);
		}
		//TODO 接着调用订票接口
		addData2.put("oiId", preOrderData.getString("oiId"));
		addData2.put("ticketNumber", "");
		addData2.put("preNumber", loopData.getString("preNumber"));
		JSONObject bigData2 = FlightService3.booking2(addData2);
		if(bigData2==null || !"true".equals(bigData2.getString("bookSucess"))) {
			//循环压，第一次压失败了，在接下来两分钟内再次尝试压票
			long currentTimeMillis2 = System.currentTimeMillis();
			long endTimeMillis2 = (long) (currentTimeMillis2+1000*60*1.5);
			while(currentTimeMillis2<endTimeMillis2) {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//TODO 再次尝试循环压票失败
				bigData2 = FlightService3.booking2(addData2);
				if(bigData2==null || !"true".equals(bigData2.getString("bookSucess"))) {
					currentTimeMillis2 = System.currentTimeMillis();
					log.error("再次尝试循环压票失败！！！");
				} else {
					log.info("再次尝试循环压票成功");
					break;
				}
			}
    	}
		if(bigData2==null || !"true".equals(bigData2.getString("bookSucess"))) {
			log.error("最终循环压票失败，将发出警报！！！");
			//压票失败，发出警报，更新订单状态为压票失败
			sqlManager.insertLost(preOrderData.getString("accountNo"), "failed");
			sqlManager.updateOrderStatus2(preOrderData.getString("oiId"), "压票失败", "");
			return;			
		}
		
		
		//调用自身，递归，进行下一轮循环订票
		//组装新一轮的packageData
		JSONArray jsonArray2 = bigData2.getJSONArray("packageArrData");
		for (int i=0;i<jsonArray2.size();i++) {
			JSONObject loopData2 = new JSONObject();
			loopData2.put("addData", bigData2.getJSONObject("addData"));
			loopData2.put("customerArrData", jsonArray2.getJSONObject(i).getJSONArray("customerArrData"));
			loopData2.put("orderInfoData", jsonArray2.getJSONObject(i).getJSONObject("orderData"));
			loopData2.put("preNumber", bigData2.getString("preNumber"));
			//一直循环自身
			deepLoop(loopData2);
		}
		log.info("循环放票成功");
		log.info("线程正常结束");
	}

}
