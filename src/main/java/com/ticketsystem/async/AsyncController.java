package com.ticketsystem.async;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;

@RestController
public class AsyncController {

	@Autowired
	private AsyncService2 asyncService2;

	@GetMapping("/open/something")
	public String something() {
		int count = 10;
		for (int i = 0; i < count; i++) {
			asyncService2.doSomething("index = " + i);
		}
		return "success";
	}

	@SneakyThrows
	@ApiOperation("异步 有返回值")
	@GetMapping("/open/somethings")
	public String somethings2() {
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
