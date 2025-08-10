package com.wipro.thread.test;

import com.wipro.thread.Thread1;
import com.wipro.thread.Thread2;

public class ThreadTest1 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		Thread1  threadOne = new Thread1(null);
		threadOne.start();
		threadOne.sleep(1000);
		
		Thread2  threadTwo = new Thread2();
		threadTwo.start();
		try {
			
			
		threadOne.join();
//		threadOne.sleep(1000);
		threadTwo.join();
		}catch(InterruptedException ex) {
			ex.printStackTrace();
		}

//		System.out.println();

	}

}
