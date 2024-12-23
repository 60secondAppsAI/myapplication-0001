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

import com.myapplication.domain.ApprovedBy;
import com.myapplication.dto.ApprovedByDTO;
import com.myapplication.dto.ApprovedBySearchDTO;
import com.myapplication.dto.ApprovedByPageDTO;
import com.myapplication.service.ApprovedByService;
import com.myapplication.dto.common.RequestDTO;
import com.myapplication.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/approvedBy")
@RestController
public class ApprovedByController {

	private final static Logger logger = LoggerFactory.getLogger(ApprovedByController.class);

	@Autowired
	ApprovedByService approvedByService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<ApprovedBy> getAll() {

		List<ApprovedBy> approvedBys = approvedByService.findAll();
		
		return approvedBys;	
	}

	@GetMapping(value = "/{approvedById}")
	@ResponseBody
	public ApprovedByDTO getApprovedBy(@PathVariable Integer approvedById) {
		
		return (approvedByService.getApprovedByDTOById(approvedById));
	}

 	@RequestMapping(value = "/addApprovedBy", method = RequestMethod.POST)
	public ResponseEntity<?> addApprovedBy(@RequestBody ApprovedByDTO approvedByDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = approvedByService.addApprovedBy(approvedByDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/approvedBys")
	public ResponseEntity<ApprovedByPageDTO> getApprovedBys(ApprovedBySearchDTO approvedBySearchDTO) {
 
		return approvedByService.getApprovedBys(approvedBySearchDTO);
	}	

	@RequestMapping(value = "/updateApprovedBy", method = RequestMethod.POST)
	public ResponseEntity<?> updateApprovedBy(@RequestBody ApprovedByDTO approvedByDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = approvedByService.updateApprovedBy(approvedByDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
