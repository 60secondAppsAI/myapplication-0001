package com.myapplication.dao;

import java.util.List;

import com.myapplication.dao.GenericDAO;
import com.myapplication.domain.AssignedTo;





public interface AssignedToDAO extends GenericDAO<AssignedTo, Integer> {
  
	List<AssignedTo> findAll();
	






}


