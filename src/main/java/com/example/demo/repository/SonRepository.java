package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.bean.Son;

@Repository
public interface SonRepository extends CrudRepository<Son, Long> {
	
	@Query("Select Son from #{#entityName} Son where id = ?1")
	public Son sonbyidonly(Long sonid);
	
	@Query("Select Son from #{#entityName} Son where id = ?1 and sonName = ?1")
	public Son sonbyid(Long sonid, String sonName);
	
	@Query("Select Son from #{#entityName} Son")
	public List<Son> getallsons();
	
	@Query("Delete #{#entityName} Son where id = ?1")
	public void deletebyid(Long deleteid);
	
	

}
