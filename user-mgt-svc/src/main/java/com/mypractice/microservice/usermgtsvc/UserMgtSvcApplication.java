package com.mypractice.microservice.usermgtsvc;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.reactive.config.EnableWebFlux;

@ComponentScan({"com.mypractice.microservice.usermgtsvc", "com.mypractice.microservice.socialcore"})
@OpenAPIDefinition(info = @Info(title = "APIs", version = "1.0", description = "Documentation APIs v1.0"))
@SpringBootApplication

public class UserMgtSvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserMgtSvcApplication.class, args);
	}

}
