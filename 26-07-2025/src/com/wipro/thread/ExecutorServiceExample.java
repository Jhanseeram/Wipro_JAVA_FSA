package com.wipro.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ExecutorServiceExample {
	
	public static void main(String[] args) {
		
		ExecutorService ex = Executors.newFixedThreadPool(3);
		
		ex.submit(()->{
			for(int i=1; i<=5;i++) {
				System.out.println(i);
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("from ex1");
			
		});
		ex.submit(()->{
			for(int i=1; i<=5;i++) {
				System.out.println(i);
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("from ex2");
			
		});
		ex.submit(()->{
			for(int i=1; i<=5;i++) {
				System.out.println(i);
			}
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("from ex3");
			
		});
		ex.submit(()->{
			for(int i=1; i<=5;i++) {
				System.out.println(i);
			}
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("from ex4");
			
		});
		ex.submit(()->{
			for(int i=1; i<=5;i++) {
				System.out.println(i);
			}
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("from ex5");
			
		});
		ex.shutdown();


	}

}
