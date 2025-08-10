package com.wipro.aopdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AirTravelAspect {

    @Before("execution(* com.wipro.aopdemo.service.AirTravelProcess.checkIn(..))")
    public void showPhotoId(JoinPoint jp) {
        System.out.println("--Please show Photo ID--");
    }

    @Before("execution(* com.wipro.aopdemo.service.AirTravelProcess.doSecurityCheck()) || " +
            "execution(* com.wipro.aopdemo.service.AirTravelProcess.doBoarding())")
    public void showBoardingPass(JoinPoint jp) {
        System.out.println("--Please show Boarding Pass--");
    }

    @AfterThrowing(pointcut = "execution(* com.wipro.aopdemo.service.AirTravelProcess.checkIn(..))", throwing = "ex")
    public void handleNoSeat(JoinPoint jp, Throwable ex) {
        System.out.println("Please contact your airline manager ");
    }
}
