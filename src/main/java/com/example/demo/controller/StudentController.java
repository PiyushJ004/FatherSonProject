package com.example.demo.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.Student;
import com.example.demo.service.StudentService;
import com.google.gson.Gson;

@RestController
@RequestMapping(path = "/api/object/student")
public class StudentController {
	
	@Autowired
	public StudentService studentService;
	
	@RequestMapping(path = "/create", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> createStudent(@Valid @RequestBody Student student, BindingResult bindingResult){
		if(bindingResult.hasErrors() == true) {
			Map<String, String> error = new LinkedHashMap<String, String>();
			for(FieldError fieldError : bindingResult.getFieldErrors()) {
				error.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return new ResponseEntity<Map<String, String>>(error, HttpStatus.BAD_REQUEST);
		}
		
		Student studentToDb = studentService.registerStudent(student);
		Gson gson = new Gson();
		String gsonString = gson.toJson(studentToDb);
		return new ResponseEntity<String>(gsonString, HttpStatus.CREATED);
	}
	
	@RequestMapping(path = "/get", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> getStudentById(@RequestParam(value ="id", required = true) Long id){
		Student studentFromDb = studentService.getStudentById(id);
		if(studentFromDb == null) {
			return new ResponseEntity<String>("Sorry, Couldn't retrieve data for id: " + id, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Student>(studentFromDb, HttpStatus.OK);
	}
	
	@RequestMapping(path = "/getAll")
	@ResponseBody
	public ResponseEntity<?> getAllStudents(){
		List<Student> studentList = studentService.getAllStudent();
		if(studentList.size() == 0 || studentList == null) {
			return new ResponseEntity<String>("Sorry, No Student found", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<Student>>(studentList, HttpStatus.OK);
	}
	
	@RequestMapping(path = "/delete")
	@ResponseBody
	public ResponseEntity<?> deleteById(@RequestParam(value = "id", required = true) Long id){
		String response = studentService.deleteStudentById(id);
		if(response == null) {
			return new ResponseEntity<String>("Sorry, No Student found to delete", HttpStatus.OK);
		}
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	

}
