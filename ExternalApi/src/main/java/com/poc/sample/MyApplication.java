package com.poc.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

@SpringBootApplication
public class MyApplication {

	public static void main(String[] args) {
		

		SpringApplication.run(MyApplication.class, args);
	
		// Initialize Hystrix context
		//HystrixRequestContext context = HystrixRequestContext.initializeContext();
	
		// Shutdown Hystrix context
		//context.shutdown();

	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}
