package com.wipro.filedemo;

import java.util.Scanner;

public class PaymentTest {

    public static void main(String[] args) {
        Payment method = null;

        Scanner sc = new Scanner(System.in);
        System.out.println("Choose Payment Method: 1.GooglePay 2.PhonePe 3.Paytm");
        int option = sc.nextInt();

        switch(option) {
            case 1:
                method = new GoogplePay();  
                break;
            case 2:
                method = new PhonePe();
                break;
            case 3:
                method = new PayTM();
                break;
            default:
                System.out.println("Invalid option");
                sc.close();
                return;
        }

        method.pay();  
        sc.close();
    }
}
