package com.home.securityrest.service;

import com.home.securityrest.exceptions.UserNotFoundException;
import com.home.securityrest.model.User;
import com.home.securityrest.repository.UserRepository;
import com.home.securityrest.security.model.Role;
import com.home.securityrest.security.model.Status;
import com.home.securityrest.transfer.UserForm;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
    @Override
    public User save(UserForm userForm) {
        User user = new User();
        BeanUtils.copyProperties(userForm, user);
        user.setHashPassword(passwordEncoder.encode(userForm.getPassword()));
        user.setRoles(Collections.singleton(Role.USER));
        user.setStatus(Status.ACTIVE);
        userRepository.save(user);
        return user;
    }

    @Override
    public void remove(Long id) {
        User userCandidate = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        userRepository.delete(userCandidate);
    }

    @Override
    public Optional<User> findUserByLogin(String username) {
        return userRepository.findUserByLogin(username);
    }
}
