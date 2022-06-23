package com.walmart.transformationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableJms
public class TransformationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransformationServiceApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

}
