package com.myapplication.dao;

import java.util.List;

import com.myapplication.dao.GenericDAO;
import com.myapplication.domain.BusAssignment;





public interface BusAssignmentDAO extends GenericDAO<BusAssignment, Integer> {
  
	List<BusAssignment> findAll();
	






}


