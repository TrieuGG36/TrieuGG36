package com.example.smartphone_web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SmartphoneWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartphoneWebApplication.class, args);
	}

}
