package com.wipro.thread;

import java.util.ArrayList;
import java.util.List;

public class Thread1 extends Thread {
	
	public Thread1(Runnable r) {
		// TODO Auto-generated constructor stub
	}

	public void run() {
List<Integer> list = new ArrayList<>();
		
		for(int i=1;i<=10; i++) {
			list.add(i);
	}

		System.out.println(list);
	
	}
}


