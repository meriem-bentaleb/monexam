package com.exemple.monexam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

@SpringBootApplication
public class MonexamApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonexamApplication.class, args);
    }
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("messages");  // will look for messages.properties files
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
