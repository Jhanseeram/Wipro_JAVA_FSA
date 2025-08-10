package com.wipro.anonymous;

import java.util.function.Predicate;

public class Predicate {

	public static void main(String[] args) {
		String str = "JHANSEE";

        Predicate<String> upperCase = s -> s.equals(s.toUpperCase());
        Predicate<String> lowerCase = s -> s.equals(s.toLowerCase());

        System.out.println( str + " uppercase - " + upperCase.test(str));
        System.out.println( str + " lowercase - " + lowerCase.test(str));

	}

}
