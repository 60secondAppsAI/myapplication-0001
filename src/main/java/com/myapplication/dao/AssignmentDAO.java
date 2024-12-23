package com.myapplication.dao;

import java.util.List;

import com.myapplication.dao.GenericDAO;
import com.myapplication.domain.Assignment;





public interface AssignmentDAO extends GenericDAO<Assignment, Integer> {
  
	List<Assignment> findAll();
	






}


