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

import com.example.demo.bean.Son;
import com.example.demo.service.MapPGErrors;
import com.example.demo.service.SonService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/api/object/son")
@Api(value = "This is son rest controller", description = "")
public class SonController {
	
	@Autowired
	public SonService sonservice;
	
	@Autowired
	public MapPGErrors mgerror;
	
	@PostMapping(path = "/create")
	@ApiOperation(value = "To store son in database")
	public ResponseEntity<?> sondb(@Valid @RequestBody Son son, BindingResult result){
		ResponseEntity<?> res = mgerror.mapStateToError(result);
		if(res != null) {
			return res;
		}
		Son sontodb = sonservice.registerson(son);
		return new ResponseEntity<Son>(sontodb, HttpStatus.CREATED);
	}
	
	@GetMapping(path = "/get/{id}")
	public ResponseEntity<?> getsonbyid(@PathVariable(value = "id") Long id, @RequestParam(value = "sonName") String sonName){
		Son son = sonservice.getsonbyid(id, sonName);
		return new ResponseEntity<Son>(son, HttpStatus.OK);
	}
	
	@GetMapping(path = "/get")
	public ResponseEntity<?> getallsons(){
		List<Son> son = sonservice.getAllSon();
		return new ResponseEntity<List<Son>>(son, HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<?> deletebyid(@PathVariable(value = "id") Long id){
		String res = sonservice.deletebyid(id);
		return new ResponseEntity<String>(res, HttpStatus.OK);
	}

}
