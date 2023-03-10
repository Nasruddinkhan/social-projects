package com.mypractice.microservice.oauthserver.security;

import com.mypractice.microservice.oauthserver.service.OidcUserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.oidc.endpoint.OidcParameterNames;
import org.springframework.security.oauth2.server.authorization.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	private final UserDetailsService userDetailsService;
	@Bean
	SecurityFilterChain configureSecurityFilterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests(authorizeRequests ->
				authorizeRequests.antMatchers("/assets/**", "/webjars/**", "/login").permitAll()
				.anyRequest()
						.authenticated())
				.formLogin()
				.loginPage("/login.html")
				.loginProcessingUrl("/login_process")
				.failureUrl("/login.html?error=true")
				.failureHandler(authenticationFailureHandler())
				.and()
				.userDetailsService(userDetailsService);
		return http.build();

	}
	@Bean
	public AuthenticationFailureHandler authenticationFailureHandler() {
		return new CustomAuthenticationFailureHandler();
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


	@Bean
	public OAuth2TokenCustomizer<JwtEncodingContext> tokenCustomizer(
			OidcUserInfoService userInfoService) {
		return context -> {
			if (OidcParameterNames.ID_TOKEN.equals(context.getTokenType().getValue())) {
				context.getClaims().claims(claims -> claims.putAll(userInfoService.loadUser(context.getPrincipal().getName())));
			}
		};
	}
}
