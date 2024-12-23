package com.myapplication.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.myapplication.domain.Issue;
import com.myapplication.dto.IssueDTO;
import com.myapplication.dto.IssueSearchDTO;
import com.myapplication.dto.IssuePageDTO;
import com.myapplication.dto.IssueConvertCriteriaDTO;
import com.myapplication.service.GenericService;
import com.myapplication.dto.common.RequestDTO;
import com.myapplication.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface IssueService extends GenericService<Issue, Integer> {

	List<Issue> findAll();

	ResultDTO addIssue(IssueDTO issueDTO, RequestDTO requestDTO);

	ResultDTO updateIssue(IssueDTO issueDTO, RequestDTO requestDTO);

    Page<Issue> getAllIssues(Pageable pageable);

    Page<Issue> getAllIssues(Specification<Issue> spec, Pageable pageable);

	ResponseEntity<IssuePageDTO> getIssues(IssueSearchDTO issueSearchDTO);
	
	List<IssueDTO> convertIssuesToIssueDTOs(List<Issue> issues, IssueConvertCriteriaDTO convertCriteria);

	IssueDTO getIssueDTOById(Integer issueId);







}





