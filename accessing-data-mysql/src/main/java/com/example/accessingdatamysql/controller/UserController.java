package com.example.accessingdatamysql.controller;

import com.example.accessingdatamysql.entity.User;
import com.example.accessingdatamysql.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Great
 */
@RestController
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    @ResponseBody
    public Iterable<User> getAll() {
        return userRepository.findAll();
    }
}
