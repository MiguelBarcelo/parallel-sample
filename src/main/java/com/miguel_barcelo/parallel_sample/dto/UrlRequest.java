package com.miguel_barcelo.parallel_sample.dto;

import java.util.List;

public class UrlRequest {

	private List<String> urls;
	
	public UrlRequest() {
		
	}
	
	public UrlRequest(List<String> urls) {
		this.urls = urls;
	}
	
	public List<String> getUrls() { return urls; }
	public void setUrls(List<String> urls) { this.urls = urls; }
}
