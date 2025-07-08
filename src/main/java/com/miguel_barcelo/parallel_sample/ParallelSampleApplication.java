package com.miguel_barcelo.parallel_sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ParallelSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParallelSampleApplication.class, args);
	}

}
