package com.rahul.kafka.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExecutorServiceConfig {
	
	
	
	  @Bean
	  public ExecutorService customExecutorService ()
	  {
		  
		  final ExecutorService executorService = Executors.newFixedThreadPool(2 );
		  return executorService;
	 }


}
