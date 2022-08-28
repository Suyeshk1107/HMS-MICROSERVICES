package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(scanBasePackages = "com")
@EnableEurekaClient
public class HmsClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(HmsClientApplication.class, args);
	}

}
