package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.bean.Student;

public interface StudentRepository extends CrudRepository<Student, Long>{
	
	@Query("Select Student from #{#entityName} Student where id = ?1")
	public Student studentById(Long id);
	
	@Query("Select Student from #{#entityName} Student")
	public List<Student> getAllStudents();
	
	@Modifying
	@Transactional
	@Query("Delete #{#entityName} Student where id = ?1")
	public void deleteById(Long deleteId);
	
	
	
	
	
	

}
