package com.ticketsystem.async;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CompletableFuture;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ticketsystem.net.DemoNet;
import com.ticketsystem.service.DemoService;
import com.ticketsystem.util.DemoData;
import com.ticketsystem.util.SqlManager;
import com.ticketsystem.workflow.WorkFlowAction;


@Service
public class AsyncService2 {
	
	Logger log = LogManager.getLogger(AsyncService2.class);

	private static long CountdownMillis = (long) (30 * 1000);

	// 指定使用beanname为doSomethingExecutor的线程池
	@Async("doSomethingExecutor")
	public String doSomething(String message) {
		log.info("调用线程---" + message);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			log.info("调用线程出错----" + message);
		}
		return message;
	}

	@Async("doSomethingExecutor")
	public CompletableFuture<String> doSomething1(String message) throws InterruptedException {
		log.info("调用线程---" + message);
		Thread.sleep(1000);
		return CompletableFuture.completedFuture("do something1: " + message);
	}

	@Async("doSomethingExecutor")
	public CompletableFuture<String> doSomething2(String message) throws InterruptedException {
		log.info("调用线程---" + message);
		Thread.sleep(1000);
		return CompletableFuture.completedFuture("; do something2: " + message);
	}

	@Async("doSomethingExecutor")
	public CompletableFuture<String> doSomething3(String message) throws InterruptedException {
		log.info("调用线程---" + message);
		Thread.sleep(1000);
		return CompletableFuture.completedFuture("; do something3: " + message);
	}

	// 指定使用beanname为doSomethingExecutor的线程池
	@Async("doSomethingExecutor")
	public CompletableFuture<String> addAsync(String message) {
		try {
			addComp(message);
		} catch (InterruptedException e) {
			log.info("["+Thread.currentThread().getName()+"]订单线程，编号【" + message + "】----------出错！");
		}
		return CompletableFuture.completedFuture(Thread.currentThread().getName()+"----------结束");
	}
	private void addComp(String message) throws InterruptedException {
		log.info("["+Thread.currentThread().getName()+"]订单线程，编号【" + message + "】----------执行中");
		Thread.sleep(1000);
		long createTimeMillis = System.currentTimeMillis();
		String orderStr = String.valueOf(createTimeMillis);
		Date date1 = new Date();  //获取系统时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日"); //日期格式
		String createDate = sdf.format(date1);
		log.info("["+Thread.currentThread().getName()+"]订单线程，编号【" + message + "】----------创建订单号【"+orderStr+"】，订单创建的时间为【"+createDate+"】");
		WorkFlowAction.OrderNoMap.put(message, orderStr);
		long currentCountdownMillis = CountdownMillis;
		while ("true".equals(WorkFlowAction.ThreadNoMap.get(message))) {
			if (currentCountdownMillis > 0) {
				log.info("["+Thread.currentThread().getName()+"]订单线程，编号【" + message + "】----------执行中");
				Thread.sleep(1000);
				//倒计时
				currentCountdownMillis=currentCountdownMillis-(System.currentTimeMillis()-createTimeMillis);
			} else {
				log.info("["+Thread.currentThread().getName()+"]订单线程，编号【" + message + "】----------自动取消订单【"+WorkFlowAction.OrderNoMap.get(message)+"】");
				Thread.sleep(1000);

				//重新下单
				//递归调用自身，实现循环下单
				addComp(message);
			}
		}
		log.info("["+Thread.currentThread().getName()+"]订单线程，编号【" + message + "】----------结束");
	}


	/**
	 * 开启新线程<br>
	 * @param bookResultData
	 */
	@Async("doSomethingExecutor")
	public void bookLoop(JSONObject loopData) {
		log.info("线程创建");
		log.info("开始循环逻辑");

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
				log.info("线程提前结束");
				return;
			}
			currentTimeMillis = System.currentTimeMillis();
		}
		
		//每轮循环结束后，后续调用取消订单，重新下单
		//调用取消订单接口
		JSONObject cancelData = new JSONObject();
		cancelData.put("orderNo", loopData.getJSONObject("orderInfoData").getJSONObject("data").getString("orderNo"));
		//TODO 调用接口----------取消订单接口
		new DemoNet().cancelTicket(cancelData);
		
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
			AsyncService asyncService = ApplicationContextProvider.getBean(AsyncService.class);
			//启动线程
			asyncService.bookLoop(loopData2);
		}
		
		log.info("线程正常结束");
	}
	
	
}
