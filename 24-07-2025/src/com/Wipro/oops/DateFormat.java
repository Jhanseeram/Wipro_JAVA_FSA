package com.Wipro.oops;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class DateFormat {
	
    public static void main(String[] args) {

        LocalDate date = LocalDate.of(2023, Month.NOVEMBER, 1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        String formattedDate = date.format(formatter);
        
        System.out.println(formattedDate);
    }
}
