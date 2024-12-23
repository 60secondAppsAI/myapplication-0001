package com.myapplication.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.myapplication.domain.AssignedTo;
import com.myapplication.dto.AssignedToDTO;
import com.myapplication.dto.AssignedToSearchDTO;
import com.myapplication.dto.AssignedToPageDTO;
import com.myapplication.dto.AssignedToConvertCriteriaDTO;
import com.myapplication.service.GenericService;
import com.myapplication.dto.common.RequestDTO;
import com.myapplication.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface AssignedToService extends GenericService<AssignedTo, Integer> {

	List<AssignedTo> findAll();

	ResultDTO addAssignedTo(AssignedToDTO assignedToDTO, RequestDTO requestDTO);

	ResultDTO updateAssignedTo(AssignedToDTO assignedToDTO, RequestDTO requestDTO);

    Page<AssignedTo> getAllAssignedTos(Pageable pageable);

    Page<AssignedTo> getAllAssignedTos(Specification<AssignedTo> spec, Pageable pageable);

	ResponseEntity<AssignedToPageDTO> getAssignedTos(AssignedToSearchDTO assignedToSearchDTO);
	
	List<AssignedToDTO> convertAssignedTosToAssignedToDTOs(List<AssignedTo> assignedTos, AssignedToConvertCriteriaDTO convertCriteria);

	AssignedToDTO getAssignedToDTOById(Integer assignedToId);







}





