package com.miguel_barcelo.parallel_sample.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncWorkerService {
	
	@Async("taskExecutor")
	public CompletableFuture<String> simulateDownload(String url) {
		try {
			Thread.sleep(1000); // simulate it takes time			
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			return CompletableFuture.completedFuture("ERROR: Interrupted");
		}
		
		String result = "Processed " + url + " in thread " + Thread.currentThread().getName();
		
		return CompletableFuture.completedFuture(result);
	}
}
