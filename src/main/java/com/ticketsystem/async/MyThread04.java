package com.ticketsystem.async;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyThread04 extends Thread {
	
	static Logger log = LogManager.getLogger(MyThread04.class);

	private boolean stop = false;

	public MyThread04(String threadName) {
		super(threadName);
	}

	@Override
	public void run() {

		for (int j = 0; j < 100; j++) {
			if (this.isInterrupted())
				break;
			log.info(Thread.currentThread().getName() + ":" + j);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public void setStop() {
		this.stop = true;
	}

	// 第一个线程
	public static void main(String[] args) {
		MyThread04 t = new MyThread04("辅线程");
		t.start();

		for (int i = 0; i < 10; i++) {
			log.info(Thread.currentThread().getName() + ":" + i);
		}
		log.info("....................");
		t.interrupt();

	}

}
