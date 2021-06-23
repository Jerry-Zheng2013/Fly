package com.ticketsystem.async;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

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
	
	

}
