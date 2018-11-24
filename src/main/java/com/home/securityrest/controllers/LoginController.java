package com.home.securityrest.controllers;

import com.home.securityrest.exceptions.UserNotFoundException;
import com.home.securityrest.model.User;
import com.home.securityrest.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@AllArgsConstructor
public class LoginController {
    private UserService userService;
    @PostMapping
    public String login(String login) {

//        User user = userService.findUserByLogin(login).orElseThrow(UserNotFoundException::new);

        return "qwerty123";
    }
}
