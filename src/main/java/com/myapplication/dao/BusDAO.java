package com.myapplication.dao;

import java.util.List;

import com.myapplication.dao.GenericDAO;
import com.myapplication.domain.Bus;





public interface BusDAO extends GenericDAO<Bus, Integer> {
  
	List<Bus> findAll();
	






}


