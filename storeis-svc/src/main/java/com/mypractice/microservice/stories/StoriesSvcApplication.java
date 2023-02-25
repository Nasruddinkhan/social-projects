package com.mypractice.microservice.stories;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.mypractice.microservice.stories", "com.mypractice.microservice.socialcore"})
public class StoriesSvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoriesSvcApplication.class, args);
	}

}
