package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.bean.Father;

@Repository
public interface FatherRepository extends CrudRepository<Father, Long> {
	
	@Query("Select Father from #{#entityName} Father where id = ?1")
	public Father fatherbyidonly(Long fatherid);
	
	@Query("Select Father from #{#entityName} Father where id = ?1 and fatherName = ?1")
	public Father fatherbyid(Long fatherid, String fatherName);
	
	@Query("Select Father from #{#entityName} Father")
	public List<Father> getAllFathers();
	
	@Query("Delete #{#entityName} Father where id = ?1")
	public void  deletebyid(Long deleteid);
	
	@Modifying
	@Transactional
	@Query("Update Father from #{#entityName} Father where id = ?1")
	public Father updatebyid(String updateid);
	
	
	
	

}
