package com.myapplication.dao;

import java.util.List;

import com.myapplication.dao.GenericDAO;
import com.myapplication.domain.Quote;





public interface QuoteDAO extends GenericDAO<Quote, Integer> {
  
	List<Quote> findAll();
	






}


