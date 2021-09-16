package com.ticketsystem.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class TimerAction {
	
	Logger log = LogManager.getLogger(TimerAction.class);

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
		log.info("["+Thread.currentThread().getName()+"]线程编号【" + threadNo + "】----------倒计时开始");
		while (leftCountdownMillis > 0) {
			//倒计时
			leftCountdownMillis=leftCountdownMillis-(System.currentTimeMillis()-startTimeMillis);
			log.info("["+Thread.currentThread().getName()+"]线程编号【" + threadNo + "】----------倒计时还剩【"+leftCountdownMillis+"】毫秒");
		}
		result = false;
		log.info("["+Thread.currentThread().getName()+"]线程编号【" + threadNo + "】----------倒计时结束");
		return result;
	}

	/*
	public static void main(String[] args) {
		WorkFlowAction.ThreadNoMap.put("1", "true");
		new TimerAction().countDown("1", DemoData.COUNTDOWNMILLIS);
	}
	*/
}
