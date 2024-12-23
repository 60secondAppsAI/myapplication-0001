package com.myapplication.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.myapplication.domain.BusAssignment;
import com.myapplication.dto.BusAssignmentDTO;
import com.myapplication.dto.BusAssignmentSearchDTO;
import com.myapplication.dto.BusAssignmentPageDTO;
import com.myapplication.dto.BusAssignmentConvertCriteriaDTO;
import com.myapplication.service.GenericService;
import com.myapplication.dto.common.RequestDTO;
import com.myapplication.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface BusAssignmentService extends GenericService<BusAssignment, Integer> {

	List<BusAssignment> findAll();

	ResultDTO addBusAssignment(BusAssignmentDTO busAssignmentDTO, RequestDTO requestDTO);

	ResultDTO updateBusAssignment(BusAssignmentDTO busAssignmentDTO, RequestDTO requestDTO);

    Page<BusAssignment> getAllBusAssignments(Pageable pageable);

    Page<BusAssignment> getAllBusAssignments(Specification<BusAssignment> spec, Pageable pageable);

	ResponseEntity<BusAssignmentPageDTO> getBusAssignments(BusAssignmentSearchDTO busAssignmentSearchDTO);
	
	List<BusAssignmentDTO> convertBusAssignmentsToBusAssignmentDTOs(List<BusAssignment> busAssignments, BusAssignmentConvertCriteriaDTO convertCriteria);

	BusAssignmentDTO getBusAssignmentDTOById(Integer busAssignmentId);







}





