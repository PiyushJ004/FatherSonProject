package com.example.demo.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.Father;
import com.example.demo.repository.FatherRepository;

@Service
public class FatherService {
	private static final Logger logger = LoggerFactory.getLogger(FatherService.class);

	@Autowired
	public FatherRepository fatherRepository;

	public Father registerFather(Father father) {
		logger.info("**************Inside FaterService in registerFatehre method*****************");
		try {
			logger.info("**************Itry catch may hu ..fatehrr ka...*****************");
			Father fathertodb = fatherRepository.save(father);
			logger.info("Fatehr value:" + fathertodb);
			return fathertodb;
		} catch (Exception e) {
			logger.error("**************May exception may hu*****************");
			throw new HibernateException("Sorry, No father with id : ");
		}
	}

	public Father getfatherbyid(Long fatherId, String name) {
		Father father = fatherRepository.fatherbyid(fatherId, name);
		if (father == null) {
			throw new HibernateException("Sorry, No father with id : " + fatherId);
		}
		return father;
	}

	public List<Father> getallfather() {
		List<Father> fatherfromdb = fatherRepository.getAllFathers();
		if (fatherfromdb == null || fatherfromdb.size() == 0) {
			throw new HibernateException("Sorry, No father found");
		}

		return fatherfromdb;
	}

	public String deletefatherbyid(Long fatherId) {
		Father fatherfromdb = fatherRepository.fatherbyidonly(fatherId);
		if (fatherfromdb == null) {
			throw new HibernateException("Sorry, No father found");
		}

		fatherRepository.deletebyid(fatherId);
		return "Successfully Deleted";

	}
}
