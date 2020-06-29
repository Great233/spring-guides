package com.example.authenticatingldap.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Great
 */
@RestController
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "Welcome to the home page!";
    }
}
