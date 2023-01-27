package com.mypractice.microservice.socialcore.config;

import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.aop.ObservedAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class ObservationRegistryConfig {

    @Bean
    ObservedAspect observedAspect(ObservationRegistry observationRegistry) {
        System.out.println("ObservationRegistry.observedAspect");
        return new ObservedAspect(observationRegistry);
    }


}
