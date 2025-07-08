package com.miguel_barcelo.parallel_sample.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;

import com.miguel_barcelo.parallel_sample.dto.UrlRequest;
import com.miguel_barcelo.parallel_sample.dto.UrlResponse;

@Service
public class UrlProcessingService {
	
	// We're gonna use @Async to delegate the concurrency to Spring Boot
	// we don't need to manually manage an ExecutorService
	private final AsyncWorkerService worker;
	
	public UrlProcessingService(AsyncWorkerService worker) {
		this.worker = worker;
	}
	
	public UrlResponse processUrlsConcurrentlyAsync(UrlRequest request) {
		long startTime = System.currentTimeMillis();
		
		List<CompletableFuture<String>> futures = new ArrayList<>();
		for (String url: request.getUrls()) {
			futures.add(worker.simulateDownload(url));
		}
		
		// wait for everyone to finish
		List<String> results = futures.stream()
				.map(CompletableFuture::join) // block until everyone is ready
				.toList();
		
		long endTime = System.currentTimeMillis();
		
		return new UrlResponse(results, endTime - startTime, Thread.currentThread().getName());
	}
}
