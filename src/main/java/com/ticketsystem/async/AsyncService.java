package com.ticketsystem.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ticketsystem.net.DemoNet;
import com.ticketsystem.service.DemoService;
import com.ticketsystem.util.DemoData;
import com.ticketsystem.util.SqlManager;


@Service
public class AsyncService {
	
	/**
	 * 开启新线程<br>
	 * @param bookResultData
	 */
	@Async("doSomethingExecutor")
	public void bookLoop(JSONObject loopData) {
		System.out.println("["+Thread.currentThread().getName()+"]----------线程创建");
		System.out.println("["+Thread.currentThread().getName()+"]----------开始循环逻辑");

		SqlManager sqlManager = new SqlManager();
		DemoService demoService = new DemoService();
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
		new DemoNet().cancelTicket(loopData.getJSONObject("orderInfoData").getJSONObject("data").getString("orderNo"));
		
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
		JSONObject bigData2 = demoService.booking(addData2);
		
		//调用自身，递归，进行下一轮循环订票
		//组装新一轮的packageData
		JSONArray jsonArray2 = bigData2.getJSONArray("packageArrData");
		for (int i=0;i<jsonArray2.size();i++) {
			JSONObject loopData2 = new JSONObject();
			loopData2.put("addData", bigData2.getJSONObject("addData"));
			loopData2.put("customerArrData", jsonArray2.getJSONObject(i).getJSONArray("customerArrData"));
			loopData2.put("orderInfoData", jsonArray2.getJSONObject(i).getJSONObject("orderData"));
			//获取线程池服务
			AsyncService2 asyncService2 = ApplicationContextProvider.getBean(AsyncService2.class);
			//启动线程
			asyncService2.bookLoop(loopData2);
		}
		
		System.out.println("["+Thread.currentThread().getName()+"]----------线程正常结束");
	}

}
