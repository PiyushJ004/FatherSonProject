package com.example.demo.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.Father;
import com.example.demo.repository.FatherRepository;

@Service
public class FatherService {
	
	@Autowired
	public FatherRepository fatherRepository;
	
	public Father registerFather(Father father) {
		try {
			Father fathertodb = fatherRepository.save(father);
			return fathertodb;
		}
		catch (Exception e) {
			throw new HibernateException("Sorry, No father with id : ");
		}
	}
	
	public Father getfatherbyid(Long fatherId, String name) {
		Father father = fatherRepository.fatherbyid(fatherId, name);
		if(father == null) {
			throw new HibernateException("Sorry, No father with id : " + fatherId);
		}
		return father;
	}
	
	public List<Father> getallfather(){
		List<Father> fatherfromdb = fatherRepository.getAllFathers();
		if(fatherfromdb == null || fatherfromdb.size() == 0) {
			throw new HibernateException("Sorry, No father found");
		}
		
		return fatherfromdb;
	}
	
	public String deletefatherbyid(Long fatherId) {
		Father fatherfromdb = fatherRepository.fatherbyidonly(fatherId);
		if(fatherfromdb == null) {
			throw new HibernateException("Sorry, No father found");
		}
		
		fatherRepository.deletebyid(fatherId);
		return "Successfully Deleted";
		
		
		
	}
}
