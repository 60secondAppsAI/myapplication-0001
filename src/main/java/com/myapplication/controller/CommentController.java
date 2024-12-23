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

import com.myapplication.domain.Comment;
import com.myapplication.dto.CommentDTO;
import com.myapplication.dto.CommentSearchDTO;
import com.myapplication.dto.CommentPageDTO;
import com.myapplication.service.CommentService;
import com.myapplication.dto.common.RequestDTO;
import com.myapplication.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/comment")
@RestController
public class CommentController {

	private final static Logger logger = LoggerFactory.getLogger(CommentController.class);

	@Autowired
	CommentService commentService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Comment> getAll() {

		List<Comment> comments = commentService.findAll();
		
		return comments;	
	}

	@GetMapping(value = "/{commentId}")
	@ResponseBody
	public CommentDTO getComment(@PathVariable Integer commentId) {
		
		return (commentService.getCommentDTOById(commentId));
	}

 	@RequestMapping(value = "/addComment", method = RequestMethod.POST)
	public ResponseEntity<?> addComment(@RequestBody CommentDTO commentDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = commentService.addComment(commentDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/comments")
	public ResponseEntity<CommentPageDTO> getComments(CommentSearchDTO commentSearchDTO) {
 
		return commentService.getComments(commentSearchDTO);
	}	

	@RequestMapping(value = "/updateComment", method = RequestMethod.POST)
	public ResponseEntity<?> updateComment(@RequestBody CommentDTO commentDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = commentService.updateComment(commentDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
