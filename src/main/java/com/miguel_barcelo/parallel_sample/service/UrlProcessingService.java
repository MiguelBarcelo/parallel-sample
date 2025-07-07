package com.miguel_barcelo.parallel_sample.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.stereotype.Service;

import com.miguel_barcelo.parallel_sample.dto.UrlRequest;
import com.miguel_barcelo.parallel_sample.dto.UrlResponse;

@Service
public class UrlProcessingService {
	
	// We're gonna use ExecutorService to simulate parallel work for each URL
	private final ExecutorService executor = Executors.newFixedThreadPool(4); // fixed pool of 4 threads
	
	public UrlResponse processUrlsConcurrently(UrlRequest request) {
		long startTime = System.currentTimeMillis();
		
		List<Future<String>> futures = new ArrayList<>();
		for (String url: request.getUrls()) {
			futures.add(executor.submit(() -> simulateDownload(url)));
		}
		
		List<String> results = new ArrayList<>();
		for (Future<String> future: futures) {
			try {
				results.add(future.get()); // Block until ready
			} catch (InterruptedException | ExecutionException e) {
				results.add("ERROR:" + e.getMessage());
			}
		}
		
		long endTime = System.currentTimeMillis();
		
		return new UrlResponse(results, endTime - startTime, Thread.currentThread().getName());
	}
	
	private String simulateDownload(String url) throws InterruptedException {
		Thread.sleep(1000); // simulate it takes time
		
		return "Processed " + url + " in thread " + Thread.currentThread().getName();
	}
}
