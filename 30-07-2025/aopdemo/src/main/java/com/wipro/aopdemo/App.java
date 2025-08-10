package com.wipro.aopdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.wipro.aopdemo.service.AirTravelProcess;
import com.wipro.aopdemo.exception.NoSeatAvailableException;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.scan("com.wipro.aopdemo");
        ctx.refresh();

        AirTravelProcess travel = ctx.getBean(AirTravelProcess.class);

        try {
            travel.checkIn(false);  
            travel.collectBoardingPass();
            travel.doSecurityCheck();
            travel.doBoarding();
        } catch (NoSeatAvailableException e) {
            System.out.println(e.getMessage());        }

        ctx.close();
    }
}
