package com.mypractice.microservice.oauthserver.service;

import com.mypractice.microservice.oauthserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class OidcUserInfoService {

    private final UserRepository userRepository;


    public Map<String, Object> loadUser(String username) {
        final var  user = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("Record not found"));

        return Map.of(
                "username", user.getUsername(),
                "email", user.getEmail(),
                "role", user.getRoles(),
                "account_locked", user.isAccountNonLocked(),
                "permission", user.getAuthorities(),
                "credentials_expired", user.isCredentialsNonExpired(),
                "account_expired", user.isAccountNonExpired()
        );
    }

}
