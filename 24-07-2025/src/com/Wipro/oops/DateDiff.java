package com.Wipro.oops;

import java.time.LocalDate;

public class DateDiff {
	
	public static void getDateDiff(LocalDate dt1, LocalDate dt2) {
		    
		int years = dt2.getYear() - dt1.getYear();
        int months = dt2.getMonthValue() - dt1.getMonthValue();
        int days = dt2.getDayOfMonth() - dt1.getDayOfMonth();

        if (days < 0) {
            months--;
            days += dt1.plusMonths(months + 1).lengthOfMonth();
        }

        if (months < 0) {
            years--;
            months += 12;
        }

        System.out.println("Years: " + years);
        System.out.println("Months: " + months);
        System.out.println("Days: " + days);
    }

    public static void main(String[] args) {
    	
        LocalDate dt1 = LocalDate.of(2024, 12, 25);
        LocalDate dt2 = LocalDate.of(2025, 1, 6);
        
        getDateDiff(dt1, dt2);
    }
}
