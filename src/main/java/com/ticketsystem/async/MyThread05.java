package com.ticketsystem.async;

public class MyThread05 extends Thread {

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
			System.out.println(Thread.currentThread().getName() + ":" + j);
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
			System.out.println(Thread.currentThread().getName() + ":" + i);
		}
		System.out.println("....................");
		t.setStop();
		for (int i = 0; i < 1000; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + ":" + i);
		}
	}

}
