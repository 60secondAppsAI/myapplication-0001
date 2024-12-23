package com.myapplication.dao;

import java.util.List;

import com.myapplication.dao.GenericDAO;
import com.myapplication.domain.Comment;





public interface CommentDAO extends GenericDAO<Comment, Integer> {
  
	List<Comment> findAll();
	






}


