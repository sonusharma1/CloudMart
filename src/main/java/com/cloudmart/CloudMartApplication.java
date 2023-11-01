package com.cloudmart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CloudMartApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudMartApplication.class, args);
		System.out.println("CloudMart App is Running...");
	}

}
