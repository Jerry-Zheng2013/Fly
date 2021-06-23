package com.ticketsystem.util;

import org.springframework.stereotype.Service;

import com.ticketsystem.workflow.WorkFlowAction;

@Service
public class TimerAction {

	/**
	 * 倒计时
	 * @param threadNo 线程号
	 * @param countdownMillis 倒计时时长(毫秒)
	 * @return 倒计时结果
	 */
	public boolean countDown(String threadNo, long countdownMillis) {
		boolean result = true;
		long leftCountdownMillis = countdownMillis;
		long startTimeMillis = System.currentTimeMillis();
		System.out.println("["+Thread.currentThread().getName()+"]线程编号【" + threadNo + "】----------倒计时开始");
		while ("true".equals(WorkFlowAction.ThreadNoMap.get(threadNo))
				&& leftCountdownMillis > 0) {
			//倒计时
			leftCountdownMillis=leftCountdownMillis-(System.currentTimeMillis()-startTimeMillis);
			System.out.println("["+Thread.currentThread().getName()+"]线程编号【" + threadNo + "】----------倒计时还剩【"+leftCountdownMillis+"】毫秒");
		}
		result = false;
		System.out.println("["+Thread.currentThread().getName()+"]线程编号【" + threadNo + "】----------倒计时结束");
		return result;
	}
	
	

	/*
	public static void main(String[] args) {
		WorkFlowAction.ThreadNoMap.put("1", "true");
		new TimerAction().countDown("1", DemoData.COUNTDOWNMILLIS);
	}
	*/
}
