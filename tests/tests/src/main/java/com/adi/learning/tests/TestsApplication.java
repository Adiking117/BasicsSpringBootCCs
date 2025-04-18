package com.adi.learning.tests;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TestsApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(TestsApplication.class, args);
		//context.close();
	}

}
