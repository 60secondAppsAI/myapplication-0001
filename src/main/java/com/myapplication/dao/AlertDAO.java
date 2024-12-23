package com.myapplication.dao;

import java.util.List;

import com.myapplication.dao.GenericDAO;
import com.myapplication.domain.Alert;





public interface AlertDAO extends GenericDAO<Alert, Integer> {
  
	List<Alert> findAll();
	






}


