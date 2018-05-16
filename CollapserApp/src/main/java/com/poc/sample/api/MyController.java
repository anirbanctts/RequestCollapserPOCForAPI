package com.poc.sample.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.poc.sample.dtos.CustomDTO;
import com.poc.sample.model.SearchModel;
import com.poc.sample.services.MyService;


@RestController
public class MyController {
	
	@Autowired
	private MyService myService;
	
	@RequestMapping(value = "/sample/{id}", produces = "application/json", method = RequestMethod.POST)
	public ResponseEntity<CustomDTO> searchByObject(@PathVariable int id, @RequestBody SearchModel searchModel) throws Exception{
		
		CustomDTO val = myService.getValueBySearchParam(searchModel).get();
		return new ResponseEntity<CustomDTO>(val, HttpStatus.OK);
	}
}
