package com.miguel_barcelo.parallel_sample.dto;

import java.util.List;

public class UrlResponse {
	
	private List<String> processed;
	private Long elapsedTimeMillis;
	private String threadName;
	
	public UrlResponse() {
		
	}
	
	public UrlResponse(List<String> processed, Long elapsedTimeMillis, String threadName) {
		this.processed = processed;
		this.elapsedTimeMillis = elapsedTimeMillis;
		this.threadName = threadName;
	}
	
	public List<String> getProcessed() { return processed; }
	public Long getElapsedTimeMillis() { return elapsedTimeMillis; }
	public String getThreadName() { return threadName; }
}
