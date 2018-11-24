package com.home.securityrest.service;

import com.home.securityrest.transfer.UserForm;
import com.home.securityrest.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();

    User save(UserForm userForm);

    void remove(Long id);
     Optional<User> findById(Long id);

    Optional<User> findUserByLogin(String username);

}
