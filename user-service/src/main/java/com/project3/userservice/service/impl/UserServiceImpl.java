package com.project3.userservice.service.impl;

import com.project3.userservice.dto.User;
import com.project3.userservice.event.UserEvent;
import com.project3.userservice.exception.ResourceNotFoundException;
import com.project3.userservice.repository.UserRepository;
import com.project3.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    KafkaTemplate<String, UserEvent> kafkaTemplate;

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setId(randomUserId);
        kafkaTemplate.send("notificationTopic", new UserEvent(user.getId()));
        return userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found user with ID: " + userId));

    }
}
