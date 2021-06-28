package com.ticketsystem.workflow;

import java.util.TreeMap;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticketsystem.async.ApplicationContextProvider;
import com.ticketsystem.async.AsyncService2;

@Service
public class WorkFlowAction {

	@Autowired
	private AsyncService2 asyncService2;
	
	public static TreeMap<String, String> ThreadNoMap = new TreeMap<String, String>();
	
	public static TreeMap<String, String> OrderNoMap = new TreeMap<String, String>();

	public String add() {

		String result = "";
		this.asyncService2 = ApplicationContextProvider.getBean(AsyncService2.class);
		
		try {
			// 常见10个订单
			for (int i = 1; i <=3; i++) {
				Thread.sleep(300);
				ThreadNoMap.put(String.valueOf(i), "true");
				CompletableFuture<String> createOrder = asyncService2.addAsync(String.valueOf(i));
			}
			Thread.sleep(40000);
			ThreadNoMap.put("3", "false");
			Thread.sleep(40000);
			ThreadNoMap.put("1", "false");
			Thread.sleep(40000);
			ThreadNoMap.put("2", "false");

			// CompletableFuture<String> reduceAccount = asyncService2.doSomething2("reduce
			// account");
			// CompletableFuture<String> saveLog = asyncService2.doSomething3("save log");
			// 等待所有任务都执行完
			// CompletableFuture.allOf(createOrder, reduceAccount, saveLog).join();
			// 获取每个任务的返回结果
			// result = createOrder.get() + reduceAccount.get() + saveLog.get();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public String stop() {
		String result = "";

		try {
			CompletableFuture<String> createOrder = asyncService2.doSomething1("create order");
			CompletableFuture<String> reduceAccount = asyncService2.doSomething2("reduce account");
			CompletableFuture<String> saveLog = asyncService2.doSomething3("save log");

			// 等待所有任务都执行完
			CompletableFuture.allOf(createOrder, reduceAccount, saveLog).join();
			// 获取每个任务的返回结果
			result = createOrder.get() + reduceAccount.get() + saveLog.get();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public String restart() {
		String result = "";

		try {
			CompletableFuture<String> createOrder = asyncService2.doSomething1("create order");
			CompletableFuture<String> reduceAccount = asyncService2.doSomething2("reduce account");
			CompletableFuture<String> saveLog = asyncService2.doSomething3("save log");

			// 等待所有任务都执行完
			CompletableFuture.allOf(createOrder, reduceAccount, saveLog).join();
			// 获取每个任务的返回结果
			result = createOrder.get() + reduceAccount.get() + saveLog.get();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
