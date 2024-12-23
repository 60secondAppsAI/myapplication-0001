package com.myapplication.dao;

import java.util.List;

import com.myapplication.dao.GenericDAO;
import com.myapplication.domain.ApprovedBy;





public interface ApprovedByDAO extends GenericDAO<ApprovedBy, Integer> {
  
	List<ApprovedBy> findAll();
	






}


