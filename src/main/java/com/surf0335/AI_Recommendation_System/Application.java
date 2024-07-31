package com.surf0335.AI_Recommendation_System;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	/*@PostConstruct
	public void init() {
		System.setProperty("BAIDU_ACCESS_KEY", "<your_access_key>");
		System.setProperty("BAIDU_SECRET_KEY", "<your_secret_key>");
	}*/


}
