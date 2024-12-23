package com.myapplication.dao;

import java.util.List;

import com.myapplication.dao.GenericDAO;
import com.myapplication.domain.RequestedBy;





public interface RequestedByDAO extends GenericDAO<RequestedBy, Integer> {
  
	List<RequestedBy> findAll();
	






}


