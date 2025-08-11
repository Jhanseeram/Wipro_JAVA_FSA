package com.wipro.userms.service;

import java.util.List;

import com.wipro.userms.dto.User;

public interface UserService {
	
	
	 User createUser(User user);
	    
	 User updateUser(int id, User user);
	 
	 void deleteUser(int id);
	 
	 User getUser(int id);
	 
	 List<User> getAllUsers();

}
