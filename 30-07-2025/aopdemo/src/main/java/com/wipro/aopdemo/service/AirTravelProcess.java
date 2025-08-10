package com.wipro.aopdemo.service;

import org.springframework.stereotype.Component;
import com.wipro.aopdemo.exception.NoSeatAvailableException;

@Component
public class AirTravelProcess {

    public void checkIn(Boolean flag) throws NoSeatAvailableException {
        if (flag) {
            System.out.println("--Check-in Successful--");
        } else {
            throw new NoSeatAvailableException("No seats available!");
        }
    }

    public void collectBoardingPass() {
        System.out.println("--Boarding Pass Collected--");
    }

    public void doSecurityCheck() {
        System.out.println("--Security Check Done--");
    }

    public void doBoarding() {
        System.out.println("--Boarding Completed--");
    }
}
