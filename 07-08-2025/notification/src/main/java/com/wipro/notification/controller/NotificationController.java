package com.wipro.notification.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class NotificationController {

	 @PostMapping("/notify")
	    public void notify(@RequestBody String message) {
	        System.out.println("Notification: " + message);
	    }
}
