package com.wipro.oop;

public class ExceptionHandling {
	
	public static void main(String[] args) {
		try
		{
			String fname = null;
			fname.toUpperCase();
		}
		catch(NullPointerException e)
		{
			System.out.println("Null Pointer Exception");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
