package com.walmart.transformationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class TransformationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransformationServiceApplication.class, args);
	}

}
