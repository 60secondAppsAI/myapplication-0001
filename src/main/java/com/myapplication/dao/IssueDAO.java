package com.myapplication.dao;

import java.util.List;

import com.myapplication.dao.GenericDAO;
import com.myapplication.domain.Issue;





public interface IssueDAO extends GenericDAO<Issue, Integer> {
  
	List<Issue> findAll();
	






}


