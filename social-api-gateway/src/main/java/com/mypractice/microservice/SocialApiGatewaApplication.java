package com.mypractice.microservice;

import com.mypractice.microservice.filter.LoggingGatewayFilterFactory;
import com.mypractice.microservice.socialcore.isc.IscSyncClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Map;

@EnableAspectJAutoProxy
@ComponentScan({"com.mypractice.microservice.usermgtsvc", "com.mypractice.microservice.socialcore"})
@SpringBootApplication
public class SocialApiGatewaApplication implements CommandLineRunner {
	@Autowired
	private IscSyncClient iscSyncClient;
	public static void main(String[] args) {
		SpringApplication.run(SocialApiGatewaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("start");
		iscSyncClient.getObject("http://localhost:8090/actuator/health", Map.of(), String.class)
				.subscribe(v -> System.out.println(v));
		System.out.println("end");

	}

	@Bean
	public AbstractGatewayFilterFactory requestFilter(){
		return new LoggingGatewayFilterFactory();
	}
}
