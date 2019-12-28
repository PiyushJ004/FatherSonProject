package com.example.demo.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service
public class MapPGErrors {

	public ResponseEntity<?> mapStateToError(BindingResult result) {
		if (result.hasErrors()) {
			Map<String, String> map = new LinkedHashMap<String, String>();
			for (FieldError fieldError : result.getFieldErrors()) {
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.BAD_REQUEST);
		}
		return null;
	}

}
