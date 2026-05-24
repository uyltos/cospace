package com.uyltos.cospace.service;

import com.uyltos.cospace.model.User;
import com.uyltos.cospace.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }
}