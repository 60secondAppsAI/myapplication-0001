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

import com.myapplication.domain.AssignedTo;
import com.myapplication.dto.AssignedToDTO;
import com.myapplication.dto.AssignedToSearchDTO;
import com.myapplication.dto.AssignedToPageDTO;
import com.myapplication.service.AssignedToService;
import com.myapplication.dto.common.RequestDTO;
import com.myapplication.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/assignedTo")
@RestController
public class AssignedToController {

	private final static Logger logger = LoggerFactory.getLogger(AssignedToController.class);

	@Autowired
	AssignedToService assignedToService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<AssignedTo> getAll() {

		List<AssignedTo> assignedTos = assignedToService.findAll();
		
		return assignedTos;	
	}

	@GetMapping(value = "/{assignedToId}")
	@ResponseBody
	public AssignedToDTO getAssignedTo(@PathVariable Integer assignedToId) {
		
		return (assignedToService.getAssignedToDTOById(assignedToId));
	}

 	@RequestMapping(value = "/addAssignedTo", method = RequestMethod.POST)
	public ResponseEntity<?> addAssignedTo(@RequestBody AssignedToDTO assignedToDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = assignedToService.addAssignedTo(assignedToDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/assignedTos")
	public ResponseEntity<AssignedToPageDTO> getAssignedTos(AssignedToSearchDTO assignedToSearchDTO) {
 
		return assignedToService.getAssignedTos(assignedToSearchDTO);
	}	

	@RequestMapping(value = "/updateAssignedTo", method = RequestMethod.POST)
	public ResponseEntity<?> updateAssignedTo(@RequestBody AssignedToDTO assignedToDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = assignedToService.updateAssignedTo(assignedToDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
