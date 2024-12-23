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

import com.myapplication.domain.Assignment;
import com.myapplication.dto.AssignmentDTO;
import com.myapplication.dto.AssignmentSearchDTO;
import com.myapplication.dto.AssignmentPageDTO;
import com.myapplication.service.AssignmentService;
import com.myapplication.dto.common.RequestDTO;
import com.myapplication.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/assignment")
@RestController
public class AssignmentController {

	private final static Logger logger = LoggerFactory.getLogger(AssignmentController.class);

	@Autowired
	AssignmentService assignmentService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Assignment> getAll() {

		List<Assignment> assignments = assignmentService.findAll();
		
		return assignments;	
	}

	@GetMapping(value = "/{assignmentId}")
	@ResponseBody
	public AssignmentDTO getAssignment(@PathVariable Integer assignmentId) {
		
		return (assignmentService.getAssignmentDTOById(assignmentId));
	}

 	@RequestMapping(value = "/addAssignment", method = RequestMethod.POST)
	public ResponseEntity<?> addAssignment(@RequestBody AssignmentDTO assignmentDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = assignmentService.addAssignment(assignmentDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/assignments")
	public ResponseEntity<AssignmentPageDTO> getAssignments(AssignmentSearchDTO assignmentSearchDTO) {
 
		return assignmentService.getAssignments(assignmentSearchDTO);
	}	

	@RequestMapping(value = "/updateAssignment", method = RequestMethod.POST)
	public ResponseEntity<?> updateAssignment(@RequestBody AssignmentDTO assignmentDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = assignmentService.updateAssignment(assignmentDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
