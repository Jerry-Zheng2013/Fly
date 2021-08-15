package com.ticketsystem.async;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ticketsystem.comp.LoginComp;
import com.ticketsystem.service.FlightService3;
import com.ticketsystem.util.DemoData;
import com.ticketsystem.util.KnSqlManager;


@Service
public class AsyncService3 {
	
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
		Date intoDate = new Date();
		System.err.println("==="+format.format(intoDate)+"===["+Thread.currentThread().getName()+"]===开始循环，监听开始");

		KnSqlManager sqlManager = new KnSqlManager();
		FlightService3 FlightService3 = new FlightService3();
		JSONObject preOrderData = loopData.getJSONObject("orderInfoData");
		
		boolean accessFlag = true;
		//循环开始时间
		long startTimeMillis = System.currentTimeMillis();
		//当前时间
		long currentTimeMillis = System.currentTimeMillis();
		while(currentTimeMillis-startTimeMillis<DemoData.COUNTDOWNMILLIS) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//时刻监控，该订单是否被取消了
			String oiId = preOrderData.getString("oiId");
			JSONObject orderData = sqlManager.getOrderInfo(oiId);
			if(!"正常".equals(orderData.getString("orderStatus"))) {
				//TODO 深层次循环唯一出口
				//如果订单状态不是 1 ，则直接跳出所有循环，结束循环订票逻辑，也就结束了此线程
				Date intoDate2 = new Date();
				System.err.println("==="+format.format(intoDate2)+"===["+Thread.currentThread().getName()+"]===监听时段【提前】结束");
				return;
			}
			//经历的实际时间+两分钟 > 规定等待时间，去提前登录下一个账号
			
			if(currentTimeMillis-startTimeMillis+1000*60*2>DemoData.COUNTDOWNMILLIS && accessFlag) {
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
	    				System.out.println("==========登陆失败==========");
	    			} else {
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
		Date intoDate3 = new Date();
		System.err.println("==="+format.format(intoDate3)+"===["+Thread.currentThread().getName()+"]===监听时段结束");
		
		//每轮循环结束后，后续调用取消订单，重新下单
		//调用取消订单接口
    	//TODO 调用接口----------取消订单接口
		JSONObject cancelJson = new JSONObject();
		cancelJson.put("oiId", preOrderData.getString("oiId"));
		cancelJson.put("orderNo", preOrderData.getString("orderNo"));
		cancelJson.put("accountNo", preOrderData.getString("accountNo"));
		FlightService3.cancelTicket2(cancelJson);
		Date cancelDate = new Date();
		System.err.println("当前时间:==="+format.format(cancelDate)+"===循环放票成功===");
		
		//解锁客户
		JSONArray customerArrData = loopData.getJSONArray("customerArrData");
		for (int i=0;i<customerArrData.size();i++) {
			JSONObject customerData = new JSONObject();
			customerData.put("customerId", customerArrData.getJSONObject(i).getString("customerId"));
			customerData.put("customerStatus", "1");
			sqlManager.updateCustomerStatus(customerData);
		}
		
		//接着调用订票接口
		JSONObject addData2 = loopData.getJSONObject("addData");
		addData2.put("oiId", preOrderData.getString("oiId"));
		addData2.put("ticketNumber", "");
		addData2.put("preNumber", loopData.getString("preNumber"));
		JSONObject bigData2 = FlightService3.booking2(addData2);
		if(bigData2==null || !"true".equals(bigData2.getString("bookSucess"))) {
    		//压票失败，发出警报，更新订单状态为压票失败
			sqlManager.insertLost(preOrderData.getString("accountNo"), "failed");
	    	sqlManager.updateOrderStatus2(preOrderData.getString("oiId"), "压票失败");
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
		Date intoDate4 = new Date();
		System.out.println("==="+format.format(intoDate4)+"===["+Thread.currentThread().getName()+"]===线程正常结束");
	}

}
