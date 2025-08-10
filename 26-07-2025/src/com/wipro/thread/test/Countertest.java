package com.wipro.thread.test;

import com.wipro.thread.Counter;
import com.wipro.thread.CounterThread;

public class Countertest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		Counter counter = new Counter();

        CounterThread t1 = new CounterThread(counter);
        CounterThread t2 = new CounterThread(counter);

        t1.start();
//        t1.sleep(1000);
//        System.out.println(counter.getCounter()+" counter thread one");
        t2.start();
//        t1.sleep(1000);
//        System.out.println(counter.getCounter()+" counter thread two");

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final Count: " + counter.getCounter());
    }
	}

