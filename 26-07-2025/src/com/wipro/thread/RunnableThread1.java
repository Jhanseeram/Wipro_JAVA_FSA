//package com.wipro.thread;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class RunnableThread1 implements Runnable{
//	List<String> names;
//
//	public RunnableThread1(List<String> names) {
//		// TODO Auto-generated constructor stub
//		this.names = names;
//	}
//
//	@Override
//	public void run() {
//		// TODO Auto-generated method stub
//		List<String> names = new ArrayList<>();
//		
//		for(String name : names) {
//			 char letter = Character.toLowerCase(name.charAt(0)); 
//			 if(letter == 'a' || letter == 'e' || letter == 'i' ||
//			            letter == 'o' || letter == 'u') {
//				 name=name.toUpperCase();			 }
//			
//		}
//		System.out.println(names);
//		
//	}
//
//}
