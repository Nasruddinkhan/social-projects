//package com.mypractice.microservice.usermgtsvc.config.validation;
//
//import org.springframework.context.MessageSource;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.support.ResourceBundleMessageSource;
//import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
//
//@Configuration
//public class ValidationConfig {
//    @Bean
//    public MessageSource messageSource() {
//        final var source = new ResourceBundleMessageSource();
//        source.setBasename("messages");
//        return source;
//    }
//
//    @Bean
//    public LocalValidatorFactoryBean getValidator(MessageSource messageSource) {
//        final var bean = new LocalValidatorFactoryBean();
//        bean.setValidationMessageSource(messageSource);
//        return bean;
//    }
//}
