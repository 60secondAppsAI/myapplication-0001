package com.myapplication.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.myapplication.dao.GenericDAO;
import com.myapplication.service.GenericService;
import com.myapplication.service.impl.GenericServiceImpl;
import com.myapplication.dao.CommentDAO;
import com.myapplication.domain.Comment;
import com.myapplication.dto.CommentDTO;
import com.myapplication.dto.CommentSearchDTO;
import com.myapplication.dto.CommentPageDTO;
import com.myapplication.dto.CommentConvertCriteriaDTO;
import com.myapplication.dto.common.RequestDTO;
import com.myapplication.dto.common.ResultDTO;
import com.myapplication.service.CommentService;
import com.myapplication.util.ControllerUtils;





@Service
public class CommentServiceImpl extends GenericServiceImpl<Comment, Integer> implements CommentService {

    private final static Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);

	@Autowired
	CommentDAO commentDao;

	


	@Override
	public GenericDAO<Comment, Integer> getDAO() {
		return (GenericDAO<Comment, Integer>) commentDao;
	}
	
	public List<Comment> findAll () {
		List<Comment> comments = commentDao.findAll();
		
		return comments;	
		
	}

	public ResultDTO addComment(CommentDTO commentDTO, RequestDTO requestDTO) {

		Comment comment = new Comment();

		comment.setCommentId(commentDTO.getCommentId());


		comment.setContent(commentDTO.getContent());


		comment.setCreatedDate(commentDTO.getCreatedDate());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		comment = commentDao.save(comment);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Comment> getAllComments(Pageable pageable) {
		return commentDao.findAll(pageable);
	}

	public Page<Comment> getAllComments(Specification<Comment> spec, Pageable pageable) {
		return commentDao.findAll(spec, pageable);
	}

	public ResponseEntity<CommentPageDTO> getComments(CommentSearchDTO commentSearchDTO) {
	
			Integer commentId = commentSearchDTO.getCommentId(); 
 			String content = commentSearchDTO.getContent(); 
   			String sortBy = commentSearchDTO.getSortBy();
			String sortOrder = commentSearchDTO.getSortOrder();
			String searchQuery = commentSearchDTO.getSearchQuery();
			Integer page = commentSearchDTO.getPage();
			Integer size = commentSearchDTO.getSize();

	        Specification<Comment> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, commentId, "commentId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, content, "content"); 
			
 			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("content")), "%" + searchQuery.toLowerCase() + "%") 
		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<Comment> comments = this.getAllComments(spec, pageable);
		
		//System.out.println(String.valueOf(comments.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(comments.getTotalPages()));
		
		List<Comment> commentsList = comments.getContent();
		
		CommentConvertCriteriaDTO convertCriteria = new CommentConvertCriteriaDTO();
		List<CommentDTO> commentDTOs = this.convertCommentsToCommentDTOs(commentsList,convertCriteria);
		
		CommentPageDTO commentPageDTO = new CommentPageDTO();
		commentPageDTO.setComments(commentDTOs);
		commentPageDTO.setTotalElements(comments.getTotalElements());
		return ResponseEntity.ok(commentPageDTO);
	}

	public List<CommentDTO> convertCommentsToCommentDTOs(List<Comment> comments, CommentConvertCriteriaDTO convertCriteria) {
		
		List<CommentDTO> commentDTOs = new ArrayList<CommentDTO>();
		
		for (Comment comment : comments) {
			commentDTOs.add(convertCommentToCommentDTO(comment,convertCriteria));
		}
		
		return commentDTOs;

	}
	
	public CommentDTO convertCommentToCommentDTO(Comment comment, CommentConvertCriteriaDTO convertCriteria) {
		
		CommentDTO commentDTO = new CommentDTO();
		
		commentDTO.setCommentId(comment.getCommentId());

	
		commentDTO.setContent(comment.getContent());

	
		commentDTO.setCreatedDate(comment.getCreatedDate());

	

		
		return commentDTO;
	}

	public ResultDTO updateComment(CommentDTO commentDTO, RequestDTO requestDTO) {
		
		Comment comment = commentDao.getById(commentDTO.getCommentId());

		comment.setCommentId(ControllerUtils.setValue(comment.getCommentId(), commentDTO.getCommentId()));

		comment.setContent(ControllerUtils.setValue(comment.getContent(), commentDTO.getContent()));

		comment.setCreatedDate(ControllerUtils.setValue(comment.getCreatedDate(), commentDTO.getCreatedDate()));



        comment = commentDao.save(comment);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public CommentDTO getCommentDTOById(Integer commentId) {
	
		Comment comment = commentDao.getById(commentId);
			
		
		CommentConvertCriteriaDTO convertCriteria = new CommentConvertCriteriaDTO();
		return(this.convertCommentToCommentDTO(comment,convertCriteria));
	}







}
