package com.example.demo.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.Student;
import com.example.demo.repository.FatherRepository;
import com.example.demo.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	public StudentRepository studentRepository;

	public Student registerStudent(Student student) {
		Student studentToDb = studentRepository.save(student);
		return studentToDb;
	}

	public Student getStudentById(Long id) {

		try {
			Student studentFromDb = studentRepository.studentById(id);
			return studentFromDb;
		} catch (final Exception e) {
			throw new HibernateException("Sorry No Student Found with id: " + id);
		}
	}
	
	public List<Student> getAllStudent(){
		try {
			List<Student> studentFromDb = studentRepository.getAllStudents();
			return studentFromDb;
		} catch (Exception e) {
			throw new HibernateException("Sorry No Student Found");
		}
	}
	
	public String deleteStudentById(Long studentId) {
		try {
			studentRepository.deleteById(studentId);
			return "Successfully Deleted";
		} catch (Exception e) {
			throw new HibernateException("Sorry, No Student To Delete");
		}
	}

}
