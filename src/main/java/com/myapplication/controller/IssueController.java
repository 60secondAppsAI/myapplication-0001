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

import com.myapplication.domain.Issue;
import com.myapplication.dto.IssueDTO;
import com.myapplication.dto.IssueSearchDTO;
import com.myapplication.dto.IssuePageDTO;
import com.myapplication.service.IssueService;
import com.myapplication.dto.common.RequestDTO;
import com.myapplication.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/issue")
@RestController
public class IssueController {

	private final static Logger logger = LoggerFactory.getLogger(IssueController.class);

	@Autowired
	IssueService issueService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Issue> getAll() {

		List<Issue> issues = issueService.findAll();
		
		return issues;	
	}

	@GetMapping(value = "/{issueId}")
	@ResponseBody
	public IssueDTO getIssue(@PathVariable Integer issueId) {
		
		return (issueService.getIssueDTOById(issueId));
	}

 	@RequestMapping(value = "/addIssue", method = RequestMethod.POST)
	public ResponseEntity<?> addIssue(@RequestBody IssueDTO issueDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = issueService.addIssue(issueDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/issues")
	public ResponseEntity<IssuePageDTO> getIssues(IssueSearchDTO issueSearchDTO) {
 
		return issueService.getIssues(issueSearchDTO);
	}	

	@RequestMapping(value = "/updateIssue", method = RequestMethod.POST)
	public ResponseEntity<?> updateIssue(@RequestBody IssueDTO issueDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = issueService.updateIssue(issueDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
