package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.Father;
import com.example.demo.service.FatherService;
import com.example.demo.service.MapPGErrors;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/api/object/father")
@Api(value = "This is father Rest Controller", description = "")
public class FatherController {

	// logger

	@Autowired
	private FatherService fatherservice;

	@Autowired
	private MapPGErrors mgerror;

	@PostMapping(path = "/create")
	@ApiOperation(value = "To store father details in the database")
	public ResponseEntity<?> fatherdb(@Valid @RequestBody Father father, BindingResult result) {

		ResponseEntity<?> res = mgerror.mapStateToError(result);
		if (res != null) {
			return res;
		}
		Father fathertodb = fatherservice.registerFather(father);
		return new ResponseEntity<Father>(fathertodb, HttpStatus.CREATED);
	}
	
	@GetMapping(path = "/get/{id}}")
	public ResponseEntity<?> getfatherbyid(@PathVariable(value="id") Long id, @RequestParam(value="fatherName") String fatherName) {
		Father father = fatherservice.getfatherbyid(id, fatherName);
		return new ResponseEntity<Father>(father, HttpStatus.OK);
	}
	
	@GetMapping(path = "/get")
	public ResponseEntity<?> getallfathers(){
		List<Father> father = fatherservice.getallfather();
		return new ResponseEntity<List<Father>>(father, HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<?> deletefatherbyid(@PathVariable(value = "id") Long id){
		String res = fatherservice.deletefatherbyid(id);
		return new ResponseEntity<String>(res, HttpStatus.OK);
	}

}
