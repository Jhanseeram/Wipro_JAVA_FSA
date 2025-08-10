package com.wipro.test;

import com.wipro.oop.BankOps;
import com.wipro.oop.Citi;
import com.wipro.oop.HDFC;

public class BankTest {
    public static void main(String[] args) {
        BankOps hdfc = new HDFC();
        BankOps citi = new Citi();

        hdfc.deposit(500, "HD14623");
        hdfc.withdraw(200, "HD14623");

        citi.deposit(1000, "CI464456");
        citi.withdraw(200, "CI464456");
    }
}