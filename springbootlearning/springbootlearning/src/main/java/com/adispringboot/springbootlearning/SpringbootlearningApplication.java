package com.adispringboot.springbootlearning;

import com.adispringboot.springbootlearning.Entity.PaymentEntity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringbootlearningApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringbootlearningApplication.class, args);
		PaymentEntity payment = context.getBean(PaymentEntity.class); // Only Payment bean is created
		payment.accessOrder(); // Order bean is created here



		context.close();

	}

}
