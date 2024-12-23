package com.myapplication.dao;

import java.util.List;

import com.myapplication.dao.GenericDAO;
import com.myapplication.domain.User;

import java.util.Optional;




public interface UserDAO extends GenericDAO<User, Integer> {
  
	List<User> findAll();
	






}


