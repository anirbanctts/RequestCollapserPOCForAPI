package com.poc.sample.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RequestCollapsingConfiguration {
	
	public String getMaxRequestsInBatch() {
		return maxRequestsInBatch;
	}

	public void setMaxRequestsInBatch(String maxRequestsInBatch) {
		this.maxRequestsInBatch = maxRequestsInBatch;
	}

	public String getTimerDelayInMilliseconds() {
		return timerDelayInMilliseconds;
	}

	public void setTimerDelayInMilliseconds(String timerDelayInMilliseconds) {
		this.timerDelayInMilliseconds = timerDelayInMilliseconds;
	}

	@Value("${circuit.collapser.maxrequestsinbatch}")
	private String maxRequestsInBatch;
	
	@Value("${circuit.collapser.timerdelayinmilliseconds}")
	private String timerDelayInMilliseconds;
	
}
