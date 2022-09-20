package com.wolf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WolfApplication {

	public static void main(String[] args) {
		SpringApplication.run(WolfApplication.class, args);
	}
}
