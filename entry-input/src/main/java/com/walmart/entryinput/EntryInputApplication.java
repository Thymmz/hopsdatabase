package com.walmart.entryinput;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class EntryInputApplication {

	public static void main(String[] args) {
		SpringApplication.run(EntryInputApplication.class, args);
	}

}
