package com.myapplication.dao;

import java.util.List;

import com.myapplication.dao.GenericDAO;
import com.myapplication.domain.DriverAssignment;





public interface DriverAssignmentDAO extends GenericDAO<DriverAssignment, Integer> {
  
	List<DriverAssignment> findAll();
	






}


