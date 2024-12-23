package com.myapplication.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;


import com.myapplication.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.myapplication.domain.RequestedBy;
import com.myapplication.dto.RequestedByDTO;
import com.myapplication.dto.RequestedBySearchDTO;
import com.myapplication.dto.RequestedByPageDTO;
import com.myapplication.service.RequestedByService;
import com.myapplication.dto.common.RequestDTO;
import com.myapplication.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/requestedBy")
@RestController
public class RequestedByController {

	private final static Logger logger = LoggerFactory.getLogger(RequestedByController.class);

	@Autowired
	RequestedByService requestedByService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<RequestedBy> getAll() {

		List<RequestedBy> requestedBys = requestedByService.findAll();
		
		return requestedBys;	
	}

	@GetMapping(value = "/{requestedById}")
	@ResponseBody
	public RequestedByDTO getRequestedBy(@PathVariable Integer requestedById) {
		
		return (requestedByService.getRequestedByDTOById(requestedById));
	}

 	@RequestMapping(value = "/addRequestedBy", method = RequestMethod.POST)
	public ResponseEntity<?> addRequestedBy(@RequestBody RequestedByDTO requestedByDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = requestedByService.addRequestedBy(requestedByDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/requestedBys")
	public ResponseEntity<RequestedByPageDTO> getRequestedBys(RequestedBySearchDTO requestedBySearchDTO) {
 
		return requestedByService.getRequestedBys(requestedBySearchDTO);
	}	

	@RequestMapping(value = "/updateRequestedBy", method = RequestMethod.POST)
	public ResponseEntity<?> updateRequestedBy(@RequestBody RequestedByDTO requestedByDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = requestedByService.updateRequestedBy(requestedByDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
