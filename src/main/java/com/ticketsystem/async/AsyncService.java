package com.ticketsystem.async;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.ticketsystem.model.DemoOrder;
import com.ticketsystem.net.DemoNet;
import com.ticketsystem.util.DemoData;
import com.ticketsystem.util.SqlManager;
import com.ticketsystem.util.TimerAction;
import com.ticketsystem.workflow.WorkFlowAction;


@Service
public class AsyncService {

	private static long CountdownMillis = (long) (30 * 1000);

	// 指定使用beanname为doSomethingExecutor的线程池
	@Async("doSomethingExecutor")
	public String doSomething(String message) {
		System.out.println("调用线程---" + message);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println("调用线程出错----" + message);
		}
		return message;
	}

	@Async("doSomethingExecutor")
	public CompletableFuture<String> doSomething1(String message) throws InterruptedException {
		System.out.println("调用线程---" + message);
		Thread.sleep(1000);
		return CompletableFuture.completedFuture("do something1: " + message);
	}

	@Async("doSomethingExecutor")
	public CompletableFuture<String> doSomething2(String message) throws InterruptedException {
		System.out.println("调用线程---" + message);
		Thread.sleep(1000);
		return CompletableFuture.completedFuture("; do something2: " + message);
	}

	@Async("doSomethingExecutor")
	public CompletableFuture<String> doSomething3(String message) throws InterruptedException {
		System.out.println("调用线程---" + message);
		Thread.sleep(1000);
		return CompletableFuture.completedFuture("; do something3: " + message);
	}

	// 指定使用beanname为doSomethingExecutor的线程池
	@Async("doSomethingExecutor")
	public CompletableFuture<String> addAsync(String message) {
		try {
			addComp(message);
		} catch (InterruptedException e) {
			System.out.println("["+Thread.currentThread().getName()+"]订单线程，编号【" + message + "】----------出错！");
		}
		return CompletableFuture.completedFuture(Thread.currentThread().getName()+"----------结束");
	}
	private void addComp(String message) throws InterruptedException {
		System.out.println("["+Thread.currentThread().getName()+"]订单线程，编号【" + message + "】----------执行中");
		Thread.sleep(1000);
		long createTimeMillis = System.currentTimeMillis();
		String orderStr = String.valueOf(createTimeMillis);
		Date date1 = new Date();  //获取系统时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日"); //日期格式
		String createDate = sdf.format(date1);
		System.out.println("["+Thread.currentThread().getName()+"]订单线程，编号【" + message + "】----------创建订单号【"+orderStr+"】，订单创建的时间为【"+createDate+"】");
		WorkFlowAction.OrderNoMap.put(message, orderStr);
		long currentCountdownMillis = CountdownMillis;
		while ("true".equals(WorkFlowAction.ThreadNoMap.get(message))) {
			if (currentCountdownMillis > 0) {
				System.out.println("["+Thread.currentThread().getName()+"]订单线程，编号【" + message + "】----------执行中");
				Thread.sleep(1000);
				//倒计时
				currentCountdownMillis=currentCountdownMillis-(System.currentTimeMillis()-createTimeMillis);
			} else {
				System.out.println("["+Thread.currentThread().getName()+"]订单线程，编号【" + message + "】----------自动取消订单【"+WorkFlowAction.OrderNoMap.get(message)+"】");
				Thread.sleep(1000);

				//重新下单
				//递归调用自身，实现循环下单
				addComp(message);
			}
		}
		System.out.println("["+Thread.currentThread().getName()+"]订单线程，编号【" + message + "】----------结束");
	}
	
	@Async("doSomethingExecutor")
	public void bookAsync(JSONObject bookResultData) {
		System.out.println("["+Thread.currentThread().getName()+"]----------开始循环逻辑");
		//循环开始时间
		long startTimeMillis = System.currentTimeMillis();
		//当前
		long currentTimeMillis = System.currentTimeMillis();
		
		try {
			while(currentTimeMillis-startTimeMillis<DemoData.COUNTDOWNMILLIS) {
					//时刻监控，该订单是否被取消了
					String oiId = bookResultData.getString("oiId");
					SqlManager sqlAction = new SqlManager();
					JSONObject orderData = sqlAction.getOrderInfo(oiId);
					if(!"true".equals(orderData.getString("status"))) {
						//如果订单状态不是是true，则直接跳出所有循环，结束循环订票逻辑
						return;
					}
			}
			
			//一轮循环结束后，后续调用取消订单，重新下单
			//Thread.sleep(DemoData.COUNTDOWNMILLIS);
			//倒计时结束，调用取消订单接口
			DemoNet demoNet = new DemoNet();
			JSONObject cancelData = new JSONObject();
			cancelData.put("orderNo", bookResultData.getString("orderNo"));
			demoNet.cancelTicket(cancelData);
			//接着调用订票接口
			/**
			JSONObject bookResultData2 = demoNet.bookTicket(bookData);
			//调用订票接口成功后，更新订单状态及下单时间
			Date currentDate = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			String currentDateStr = sdf.format(currentDate);

			JSONObject updateData = new JSONObject();
			updateData.put("oiId", bookResultData.getString("oiId"));
			updateData.put("accountNo", bookResultData2.getString("accountNo"));
			updateData.put("orderNo", bookResultData2.getString("accountNo"));
			//updateData.put("standbyCount", aaa);
			//updateData.put("status", aaa);
			//updateData.put("updateTime", aaa);
			//updateData.put("round", aaa);
			
			
			JSONObject bookResultData3 = demoNet.bookTicket(bookData);
			String orderNo = bookResultData2.getJSONObject("data").getString("orderNo");
			DemoData.OrderBeanMap.remove(bookResultData.getJSONObject("data").getString("orderNo"));
	    	DemoOrder demoOrder = new DemoOrder(orderNo, "true");
	    	DemoData.OrderBeanMap.put(orderNo, demoOrder);
	    	bookResultData = bookResultData2;
	    	*/

		} catch (Exception e) {
			System.out.println("["+Thread.currentThread().getName()+"]----------订票出错");
			e.printStackTrace();
		}
		System.out.println("["+Thread.currentThread().getName()+"]----------结束订票");
	}
	

}
