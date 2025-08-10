package com.wipro.thread;

import java.util.ArrayList;
import java.util.List;

public class Thread2 extends Thread{

	

		public void run() {
			List<String> list2 = new ArrayList<>();
			
			for(char c ='a'; c<='z';c++) {
				list2.add(String.valueOf(c));
			}
			System.out.println(list2);
		
		}

}
