package com.poc.sample.gateways;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.sample.dtos.CustomDTO;
import com.poc.sample.dtos.CustomDTOs;
import com.poc.sample.model.SearchModel;

@Service
public class MyGateway {

	@Autowired
	public MockExternalApi mockExternalApi;

	@Autowired
	private RestTemplate restTemplate;
	
	public List<CustomDTO> getValues(List<Integer> ids) throws Exception {
		System.out.println("Hitting main method" + ids);

		String idsCommaSeparated = "";
		for(int i=0; i<ids.size();i++)
		{
			if(idsCommaSeparated == "")
				idsCommaSeparated = "ids=" + ids.get(i);
			else
				idsCommaSeparated += "&ids=" + ids.get(i);
		}		
		ResponseEntity<String> response = findAll("http://localhost:8081/find?" + idsCommaSeparated);
		
		String responseJson = response.getBody().toString();
		
		
		List<CustomDTO> responseObjectsInReversedOrder = mapResponse(responseJson);
		//return responseObjectsInReversedOrder;
		
		// Fixing order in which requests arrived
		List<CustomDTO> responseObjectsInCorrectOrder = fixOrder(responseObjectsInReversedOrder, ids);
		return responseObjectsInCorrectOrder;
	}
	


	public ResponseEntity<String> findAll(String url) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
		HttpEntity<String> productHeaderEntity = new HttpEntity<String>("parameters", headers);
		return restTemplate.exchange(url, HttpMethod.GET, productHeaderEntity, String.class);
	}

	private List<CustomDTO> fixOrder(List<CustomDTO> responseObjectsInReversedOrder, List<Integer> ids) {
		List<CustomDTO> listCorrectOrder = new ArrayList<>();
		ids.forEach(id->
				{
					listCorrectOrder.add(responseObjectsInReversedOrder.stream().filter(item -> item.getId()==id).findFirst().get());
				});
		
		
		return listCorrectOrder;
	}

	private List<CustomDTO> mapResponse(String responseJson) {
		
		List<CustomDTO> values = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();
		try {
			CustomDTOs dtos  = mapper.readValue(responseJson, CustomDTOs.class);			
			return dtos.getDtos();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	public List<CustomDTO> getValuesBySearchParam(List<SearchModel> models) {
		List<Integer> ids = models.stream().map(SearchModel::getSearchId).collect(Collectors.toList());
		try {
			return getValues(ids);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
