package com.example.demo.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.Son;
import com.example.demo.repository.SonRepository;

@Service
public class SonService {
	
	@Autowired
	public SonRepository sonrepository;
	
	public Son registerson(Son son) {
		
		try {
			Son sontodb = sonrepository.save(son);
			return sontodb;
		}
		catch (Exception e) {
			throw new HibernateException("Sorry, No son with id = ?1");
		}
	}
	
	public Son getsonbyid(Long sonId, String Name) {
		Son son = sonrepository.sonbyid(sonId, Name);
		if(son == null) {
			throw new HibernateException("Sorry, No son found with id = ?1 and sonName = ?2" +sonId + "sonName"+Name);
		}
		
		return son;
		
	}
	
	public List<Son> getAllSon(){
		List<Son> sonfromdb = sonrepository.getallsons();
		if(sonfromdb == null || sonfromdb.size() == 0) {
			throw new HibernateException("Sorry, No son data found");
		}
		
		return sonfromdb;
	}
	
	public String deletebyid(Long sonId) {
		Son sonfromdb = sonrepository.sonbyidonly(sonId);
		if(sonfromdb == null) {
			throw new HibernateException("Sorry, No son found");
		}
		
		sonrepository.deletebyid(sonId);
		return "Successfully Deleted";
		
		
	}
	
	

}
