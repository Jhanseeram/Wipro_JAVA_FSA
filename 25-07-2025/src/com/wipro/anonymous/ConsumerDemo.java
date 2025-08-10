package com.wipro.anonymous;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Consumer;

public class ConsumerDemo {

	public static void main(String[] args) {
		
		Consumer<ZoneId> currTime = zone -> {
            ZonedDateTime time = ZonedDateTime.now(zone);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            System.out.println("Current time in " + zone + " is: " + time.format(formatter));
        };

        currTime.accept(ZoneId.of("Europe/London"));
	}

}
