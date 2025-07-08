package com.miguel_barcelo.parallel_sample.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AsyncWorkerService {
	
	@Async("taskExecutor")
	public CompletableFuture<String> simulateDownload(String url) {
		
		String thread = Thread.currentThread().getName();
		log.info("➡️ [{}] started in thread {}", url, thread);
		long t1 = System.currentTimeMillis();
		
		try {
			Thread.sleep(1000); // simulate it takes time			
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			return CompletableFuture.completedFuture("ERROR: Interrupted");
		}
		
		long t2 = System.currentTimeMillis();
		log.info("✅ [{}] completed in {} ms (thread {})", url, (t2 - t1), thread);
		
		String result = "Processed " + url + " in thread " + thread;
		
		return CompletableFuture.completedFuture(result);
	}
}
