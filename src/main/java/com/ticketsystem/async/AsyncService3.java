package com.ticketsystem.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ticketsystem.service.FlightService2;
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
		System.out.println("["+Thread.currentThread().getName()+"]----------线程创建");
		System.out.println("["+Thread.currentThread().getName()+"]----------开始循环逻辑");

		KnSqlManager sqlManager = new KnSqlManager();
		FlightService2 flightService2 = new FlightService2();
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
			String oiId = loopData.getJSONObject("orderInfoData").getString("oiId");
			JSONObject orderData = sqlManager.getOrderInfo(oiId);
			if(!"1".equals(orderData.getString("orderStatus"))) {
				//如果订单状态不是 1 ，则直接跳出所有循环，结束循环订票逻辑，也就结束了此线程
				System.out.println("["+Thread.currentThread().getName()+"]----------线程提前结束");
				return;
			}
			currentTimeMillis = System.currentTimeMillis();
		}
		
		//每轮循环结束后，后续调用取消订单，重新下单
		//调用取消订单接口
    	//TODO 调用接口----------取消订单接口
		JSONObject cancelJson = new JSONObject();
		cancelJson.put("oiId", loopData.getJSONObject("orderInfoData").getString("oiId"));
		cancelJson.put("orderNo", loopData.getJSONObject("orderInfoData").getString("orderNo"));
		cancelJson.put("accountNo", loopData.getJSONObject("orderInfoData").getString("accountNo"));
		new FlightService2().cancelTicket(cancelJson);
		
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
		addData2.put("oiId", loopData.getJSONObject("orderInfoData").getString("oiId"));
		JSONObject bigData2 = flightService2.booking(addData2);
		
		//调用自身，递归，进行下一轮循环订票
		//组装新一轮的packageData
		JSONArray jsonArray2 = bigData2.getJSONArray("packageArrData");
		for (int i=0;i<jsonArray2.size();i++) {
			JSONObject loopData2 = new JSONObject();
			loopData2.put("addData", bigData2.getJSONObject("addData"));
			loopData2.put("customerArrData", jsonArray2.getJSONObject(i).getJSONArray("customerArrData"));
			loopData2.put("orderInfoData", jsonArray2.getJSONObject(i).getJSONObject("orderData"));
			//启动深层次循环
			deepLoop(loopData2);
		}
		
		System.out.println("["+Thread.currentThread().getName()+"]----------线程正常结束");
	}
	
	public void deepLoop(JSONObject loopData) {
		System.out.println("["+Thread.currentThread().getName()+"]----------开始深层次循环逻辑");

		KnSqlManager sqlManager = new KnSqlManager();
		FlightService2 flightService2 = new FlightService2();
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
			String oiId = loopData.getJSONObject("orderInfoData").getString("oiId");
			JSONObject orderData = sqlManager.getOrderInfo(oiId);
			if(!"1".equals(orderData.getString("orderStatus"))) {
				//TODO 深层次循环唯一出口
				//如果订单状态不是 1 ，则直接跳出所有循环，结束循环订票逻辑，也就结束了此线程
				System.out.println("["+Thread.currentThread().getName()+"]----------线程提前结束");
				return;
			}
			currentTimeMillis = System.currentTimeMillis();
		}
		
		//每轮循环结束后，后续调用取消订单，重新下单
		//调用取消订单接口
    	//TODO 调用接口----------取消订单接口
		JSONObject cancelJson = new JSONObject();
		cancelJson.put("oiId", loopData.getJSONObject("orderInfoData").getString("oiId"));
		cancelJson.put("orderNo", loopData.getJSONObject("orderInfoData").getString("orderNo"));
		cancelJson.put("accountNo", loopData.getJSONObject("orderInfoData").getString("accountNo"));
		new FlightService2().cancelTicket(cancelJson);
		
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
		addData2.put("oiId", loopData.getJSONObject("orderInfoData").getString("oiId"));
		JSONObject bigData2 = flightService2.booking(addData2);
		
		//调用自身，递归，进行下一轮循环订票
		//组装新一轮的packageData
		JSONArray jsonArray2 = bigData2.getJSONArray("packageArrData");
		for (int i=0;i<jsonArray2.size();i++) {
			JSONObject loopData2 = new JSONObject();
			loopData2.put("addData", bigData2.getJSONObject("addData"));
			loopData2.put("customerArrData", jsonArray2.getJSONObject(i).getJSONArray("customerArrData"));
			loopData2.put("orderInfoData", jsonArray2.getJSONObject(i).getJSONObject("orderData"));
			//一直循环自身
			deepLoop(loopData2);
		}
		
		System.out.println("["+Thread.currentThread().getName()+"]----------线程正常结束");
	}

}
