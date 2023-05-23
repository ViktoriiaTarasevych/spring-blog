package com.example.springblog.service;

import com.example.springblog.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    User save(User user);
}