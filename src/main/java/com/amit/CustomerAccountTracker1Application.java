package com.amit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages="com.amit")
public class CustomerAccountTracker1Application {

	public static void main(String[] args) {
		SpringApplication.run(CustomerAccountTracker1Application.class, args);
	}

}
