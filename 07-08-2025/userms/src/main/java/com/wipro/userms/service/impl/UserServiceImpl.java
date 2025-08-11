package com.wipro.userms.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wipro.userms.dto.User;
import com.wipro.userms.dto1.NotificationDTO;
import com.wipro.userms.repo.UserRepo;
import com.wipro.userms.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
    private UserRepo userRepository;

    private final RestTemplate restTemplate = new RestTemplate();

    private void notify(String message) {
        restTemplate.postForObject("http://localhost:9001/notify", message, String.class);
    }

    @Override
    public User createUser(User user) {
        User saved = userRepository.save(user);
        notify(saved.getUsername() + " is created");
        return saved;
    }

    @Override
    public User updateUser(int id, User user) {
        user.setId(id);
        User updated = userRepository.save(user);
        notify(updated.getUsername() + " is updated");
        return updated;
    }

    @Override
    public void deleteUser(int id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            userRepository.deleteById(id);
            notify(user.getUsername() + " is deleted");
        }
    }
    
    @Override
    public User getUser(int id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}

	


