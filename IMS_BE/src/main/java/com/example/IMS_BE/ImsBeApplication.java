package com.example.IMS_BE;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.IMS_BE")
public class ImsBeApplication {
	public static void main(String[] args) {
		SpringApplication.run(ImsBeApplication.class, args);
	}
}
