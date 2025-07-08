package com.miguel_barcelo.parallel_sample.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig {

	@Bean(name = "taskExecutor")
	Executor taskExecutor() {
		//return Executors.newFixedThreadPool(4);
		
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(4); // Number of simultaneous threads
		executor.setMaxPoolSize(8); // Maximum total threads if saturated
		executor.setQueueCapacity(100); // Jobs able to queue
		executor.setThreadNamePrefix("worker-thread-"); // Names visibles in logs
		executor.initialize();
		
		return executor;
	}
}
