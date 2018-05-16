package com.poc.sample.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.poc.sample.configuration.RequestCollapsingConfiguration;
import com.poc.sample.dtos.CustomDTO;
import com.poc.sample.gateways.MyGateway;
import com.poc.sample.model.SearchModel;

@Component
public class MyService {

	@Autowired
	private RequestCollapsingConfiguration requestCollapsingConfiguration;
	
	@Autowired
	private MyGateway myGateway;
		
	@HystrixCollapser(scope = com.netflix.hystrix.HystrixCollapser.Scope.GLOBAL, batchMethod = "getValue",
			collapserProperties = {
					@com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty(
							name = "maxRequestsInBatch", value = "4"),
					@com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty(
							name = "timerDelayInMilliseconds", value = "500")})
	public Future<CustomDTO> getValueBySearchParam(SearchModel model) {
		return null;	
	}
	
	@HystrixCommand(commandKey="getValue", fallbackMethod="fallbackMethod")
	public List<CustomDTO> getValue(List<SearchModel> models)
	{
		return myGateway.getValuesBySearchParam(models);
	}
	
	public List<CustomDTO> fallbackMethod(List<SearchModel> models) throws Exception
	{
		return null;
	}

}
