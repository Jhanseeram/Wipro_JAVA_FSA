package com.wipro.notification.dto;

import com.wipro.notification.dto1.User;

public class NotificationDTO {

	 User user;
     String action;
	
    
    public NotificationDTO(User user, String action) {
		super();
		this.user = user;
		this.action = action;
	}
	public NotificationDTO() {
		
		super();
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	 
	@Override
	    public String toString() {
	        return user.getUsername() + " is " + action;
	    }
	
	
    
    
}
