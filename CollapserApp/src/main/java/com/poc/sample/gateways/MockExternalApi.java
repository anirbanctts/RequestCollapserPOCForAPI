package com.poc.sample.gateways;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MockExternalApi {

	public ResponseEntity<Object> callExternalApi(String ids) {
		
		String[] idArr = ids.split(",");
		
		String respString ="";
		
		//magic: reversing sequence
		for (int i = idArr.length-1; i >= 0 ; i--)
	    {		
			if(respString == "")
				respString += "{\"id\":\"" + idArr[i] + "\", \"name\":\"" + "name" + idArr[i] + "\"}";
			else
				respString += ", {\"id\":\"" + idArr[i] + "\", \"name\":\"" + "name" + idArr[i] + "\"}";
	    }
		respString = "{ \"dtos\": [" + respString + "] }";
		
		return new ResponseEntity<>(respString, HttpStatus.OK); 
	}
}
