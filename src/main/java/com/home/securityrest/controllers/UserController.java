package com.home.securityrest.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.home.securityrest.exceptions.UserNotFoundException;
import com.home.securityrest.model.User;
import com.home.securityrest.service.UserService;
import com.home.securityrest.transfer.UserForm;
import com.home.securityrest.views.UserView;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @GetMapping
    @JsonView(UserView.AdminResponse.class)
    public List<User> userList(){
        return userService.findAll();
    }

    @GetMapping("{id}")
    @JsonView(UserView.UserResponse.class)
    public User userGetOne(@PathVariable Long id){
        return userService.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    @PostMapping
    @JsonView(UserView.UserResponse.class)
    public ResponseEntity<User> userSave(UserForm userRequest){
        return ResponseEntity.ok(userService.save(userRequest));
    }

    @DeleteMapping("{id}")
    public ResponseEntity userRemove(@PathVariable Long id){
        userService.remove(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
