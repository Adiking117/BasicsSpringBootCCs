package com.adi.prop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class PropApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(PropApplication.class, args);
		context.close();
	}

}
