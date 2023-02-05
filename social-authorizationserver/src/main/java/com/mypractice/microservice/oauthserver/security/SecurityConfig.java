package com.mypractice.microservice.oauthserver.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
	private final UserDetailsService userDetailsService;
	@Bean
	SecurityFilterChain configureSecurityFilterChain(HttpSecurity http) throws Exception {

		http
				.authorizeHttpRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated())
				.userDetailsService(userDetailsService)

				.formLogin(Customizer.withDefaults());
		return http.build();

	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
//	@Bean
//	public UserDetailsService users() {
//
//		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//
//		UserDetails user = User.withUsername("admin")
//				.password(encoder.encode("admin"))
//				.roles("USER")
//				.build();
//
//		return new InMemoryUserDetailsManager(user);
//
//	}

}
