package com.poc.sample.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class MyController {
	
	@RequestMapping(value = "/find", produces = "application/json", method = RequestMethod.GET)
	public ResponseEntity<Object> search( @RequestParam String[] ids) throws Exception{
		
		//System.out.println(ids.length);
		
		String respString ="";
		
		//magic: reversing sequence
		for (int i = ids.length-1; i >= 0 ; i--)
	    {		
			if(respString == "")
				respString += "{\"id\":\"" + ids[i] + "\", \"name\":\"" + "name" + ids[i] + "\"}";
			else
				respString += ", {\"id\":\"" + ids[i] + "\", \"name\":\"" + "name" + ids[i] + "\"}";
	    }
		respString = "{ \"dtos\": [" + respString + "] }";
		
		return new ResponseEntity<>(respString, HttpStatus.OK);
	}
	
	//@RequestMapping(value = "/sample/{id}", produces = "application/json", method = RequestMethod.POST)
	//public ResponseEntity<CustomDTO> searchByObject(@PathVariable int id, @RequestBody SearchModel searchModel) throws Exception{
	
}
