package com.adi.aop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AopApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(AopApplication.class, args);
		// context.close();
	}

}
