package com.mypractice.microservice.oauthserver.service;

import com.mypractice.microservice.oauthserver.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        var users = userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("User not found with email : " + email));
        return new User(users.getEmail(), users.getPassword(), users.getAuthorities() );
    }


}