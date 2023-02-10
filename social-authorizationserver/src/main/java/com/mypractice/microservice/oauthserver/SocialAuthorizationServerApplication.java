package com.mypractice.microservice.oauthserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

public class SocialAuthorizationServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialAuthorizationServerApplication.class, args);
	}

}
