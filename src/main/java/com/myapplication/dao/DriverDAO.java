package com.myapplication.dao;

import java.util.List;

import com.myapplication.dao.GenericDAO;
import com.myapplication.domain.Driver;





public interface DriverDAO extends GenericDAO<Driver, Integer> {
  
	List<Driver> findAll();
	






}


