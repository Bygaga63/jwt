package com.home.securityrest.security.details;

import com.home.securityrest.exceptions.UserNotFoundException;
import com.home.securityrest.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        return new UserDetailsImpl(userRepository.findUserByLogin(userName)
                .orElseThrow(UserNotFoundException::new));
    }
}
