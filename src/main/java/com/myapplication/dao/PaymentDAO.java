package com.myapplication.dao;

import java.util.List;

import com.myapplication.dao.GenericDAO;
import com.myapplication.domain.Payment;





public interface PaymentDAO extends GenericDAO<Payment, Integer> {
  
	List<Payment> findAll();
	






}


