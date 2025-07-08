package com.miguel_barcelo.parallel_sample.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;

import com.miguel_barcelo.parallel_sample.dto.UrlRequest;
import com.miguel_barcelo.parallel_sample.dto.UrlResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
		
		log.info("🔁 Starting ASYNC processing of {} URLs", request.getUrls().size());
		
		List<CompletableFuture<String>> futures = new ArrayList<>();
		for (String url: request.getUrls()) {
			futures.add(worker.simulateDownload(url));
		}
		
		// wait for everyone to finish
		List<String> results = futures.stream()
				.map(CompletableFuture::join) // block until everyone is ready
				.toList();
		
		long endTime = System.currentTimeMillis();
		
		log.info("🧮 ASYNC processing finished in {} ms", (endTime - startTime));
		
		return new UrlResponse(results, endTime - startTime, Thread.currentThread().getName());
	}
	
	public UrlResponse processUrlsSequentially(UrlRequest request) {
		long startTime = System.currentTimeMillis();
		
		log.info("🔁 Starting SEQUENTIAL processing of {} URLs", request.getUrls().size());
		
		List<String> results = new ArrayList<>();
		for (String url: request.getUrls()) {
			
			long t1 = System.currentTimeMillis();
			
			try {
				Thread.sleep(1000); // Simulate work
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			
			long t2 = System.currentTimeMillis();
			
			String thread = Thread.currentThread().getName();
			String result = "Processed " + url + " in thread " + thread;
			
			log.info("✅ [{}] completed in {} ms", url, (t2 - t1));
			
			results.add(result);
		}
		
		long endTime = System.currentTimeMillis();
		log.info("🧮 SEQUENTIAL processing finished in {} ms", (endTime - startTime));
		
		return new UrlResponse(results, endTime - startTime, Thread.currentThread().getName());
	}
}
