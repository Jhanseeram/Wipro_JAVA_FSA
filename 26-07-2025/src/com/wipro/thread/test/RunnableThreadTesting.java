package com.wipro.thread.test;

import java.util.Arrays;
import java.util.List;

public class RunnableThreadTesting {

    public static void main(String[] args) throws InterruptedException {
        List<String> names = Arrays.asList("Vedanti", "amit", "aasha", "Dante");
        System.out.println("Original List: " + names);

        //thread 1
        Runnable r = () -> {
            for (int i = 0; i < names.size(); i++) {
                String name = names.get(i);
                char letter = Character.toLowerCase(name.charAt(0));

                if (letter != 'a' && letter != 'e' && letter != 'i' &&
                    letter != 'o' && letter != 'u') {
                    names.set(i, name.toLowerCase());
                }
            }
//            System.out.println("Consonant Thread Output: " + names);
        };

        // Thread 2
        Runnable r2 = () -> {
            for (int i = 0; i < names.size(); i++) {
                String name = names.get(i);
                char letter = Character.toLowerCase(name.charAt(0));

                if (letter == 'a' || letter == 'e' || letter == 'i' ||
                    letter == 'o' || letter == 'u') {
                    names.set(i, name.toUpperCase());
                }
            }
//            System.out.println("Vowel Thread Output: " + names);
        };

        Thread t = new Thread(r);
        Thread t2 = new Thread(r2);

        t.start();
        Thread.sleep(1000);
        t2.start();

        t.join();
        t2.join();

        System.out.println("Final List: " + names);
    }
}
