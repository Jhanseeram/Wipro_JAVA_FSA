package com.wipro.userms.dto1;

import com.wipro.userms.dto.User;

public class NotificationDTO {
	
	private User user;
    private String action;
    
    public NotificationDTO()
    {
    	super();
    }

	public NotificationDTO(User user, String action) {
		super();
		this.user = user;
		this.action = action;
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
		return "NotificationDTO [user=" + user + ", action=" + action + "]";
	}
    
    
}
