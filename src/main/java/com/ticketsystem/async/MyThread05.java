package com.ticketsystem.async;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyThread05 extends Thread {
	
	static Logger log = LogManager.getLogger(MyThread05.class);

	private boolean stop = false;

	public MyThread05(String threadName) {
		super(threadName);
	}

	@Override
	public void run() {

		for (int j = 0; j < 100; j++) {
			if (stop)
				break;

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
		MyThread05 t = new MyThread05("辅线程");
		t.start();

		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			log.info(Thread.currentThread().getName() + ":" + i);
		}
		log.info("....................");
		t.setStop();
		for (int i = 0; i < 1000; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			log.info(Thread.currentThread().getName() + ":" + i);
		}
	}

}
