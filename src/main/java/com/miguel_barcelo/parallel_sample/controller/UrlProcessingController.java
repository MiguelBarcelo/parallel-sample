package com.miguel_barcelo.parallel_sample.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miguel_barcelo.parallel_sample.dto.UrlRequest;
import com.miguel_barcelo.parallel_sample.dto.UrlResponse;
import com.miguel_barcelo.parallel_sample.service.UrlProcessingService;

@RestController
@RequestMapping("/api/urls")
public class UrlProcessingController {

	private final UrlProcessingService service;
	
	public UrlProcessingController(UrlProcessingService service) {
		this.service = service;
	}
	
	@PostMapping("/process-async")
	public UrlResponse processUrlsAsync(@RequestBody UrlRequest request) {
		return service.processUrlsConcurrentlyAsync(request);
	}
	
	@PostMapping("/benchmark/sync")
	public UrlResponse processSync(@RequestBody UrlRequest request) {
		return service.processUrlsSequentially(request);
	}
	
	@PostMapping("/benchmark/async")
	public UrlResponse processAsync(@RequestBody UrlRequest request) {
		return service.processUrlsConcurrentlyAsync(request);
	}
}
