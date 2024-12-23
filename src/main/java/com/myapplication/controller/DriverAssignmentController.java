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

import com.myapplication.domain.DriverAssignment;
import com.myapplication.dto.DriverAssignmentDTO;
import com.myapplication.dto.DriverAssignmentSearchDTO;
import com.myapplication.dto.DriverAssignmentPageDTO;
import com.myapplication.service.DriverAssignmentService;
import com.myapplication.dto.common.RequestDTO;
import com.myapplication.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/driverAssignment")
@RestController
public class DriverAssignmentController {

	private final static Logger logger = LoggerFactory.getLogger(DriverAssignmentController.class);

	@Autowired
	DriverAssignmentService driverAssignmentService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<DriverAssignment> getAll() {

		List<DriverAssignment> driverAssignments = driverAssignmentService.findAll();
		
		return driverAssignments;	
	}

	@GetMapping(value = "/{driverAssignmentId}")
	@ResponseBody
	public DriverAssignmentDTO getDriverAssignment(@PathVariable Integer driverAssignmentId) {
		
		return (driverAssignmentService.getDriverAssignmentDTOById(driverAssignmentId));
	}

 	@RequestMapping(value = "/addDriverAssignment", method = RequestMethod.POST)
	public ResponseEntity<?> addDriverAssignment(@RequestBody DriverAssignmentDTO driverAssignmentDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = driverAssignmentService.addDriverAssignment(driverAssignmentDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/driverAssignments")
	public ResponseEntity<DriverAssignmentPageDTO> getDriverAssignments(DriverAssignmentSearchDTO driverAssignmentSearchDTO) {
 
		return driverAssignmentService.getDriverAssignments(driverAssignmentSearchDTO);
	}	

	@RequestMapping(value = "/updateDriverAssignment", method = RequestMethod.POST)
	public ResponseEntity<?> updateDriverAssignment(@RequestBody DriverAssignmentDTO driverAssignmentDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = driverAssignmentService.updateDriverAssignment(driverAssignmentDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
