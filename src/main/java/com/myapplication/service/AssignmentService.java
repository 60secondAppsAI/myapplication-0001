package com.myapplication.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.myapplication.domain.Assignment;
import com.myapplication.dto.AssignmentDTO;
import com.myapplication.dto.AssignmentSearchDTO;
import com.myapplication.dto.AssignmentPageDTO;
import com.myapplication.dto.AssignmentConvertCriteriaDTO;
import com.myapplication.service.GenericService;
import com.myapplication.dto.common.RequestDTO;
import com.myapplication.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface AssignmentService extends GenericService<Assignment, Integer> {

	List<Assignment> findAll();

	ResultDTO addAssignment(AssignmentDTO assignmentDTO, RequestDTO requestDTO);

	ResultDTO updateAssignment(AssignmentDTO assignmentDTO, RequestDTO requestDTO);

    Page<Assignment> getAllAssignments(Pageable pageable);

    Page<Assignment> getAllAssignments(Specification<Assignment> spec, Pageable pageable);

	ResponseEntity<AssignmentPageDTO> getAssignments(AssignmentSearchDTO assignmentSearchDTO);
	
	List<AssignmentDTO> convertAssignmentsToAssignmentDTOs(List<Assignment> assignments, AssignmentConvertCriteriaDTO convertCriteria);

	AssignmentDTO getAssignmentDTOById(Integer assignmentId);







}





