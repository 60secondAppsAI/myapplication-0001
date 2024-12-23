package com.myapplication.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.myapplication.domain.RequestedBy;
import com.myapplication.dto.RequestedByDTO;
import com.myapplication.dto.RequestedBySearchDTO;
import com.myapplication.dto.RequestedByPageDTO;
import com.myapplication.dto.RequestedByConvertCriteriaDTO;
import com.myapplication.service.GenericService;
import com.myapplication.dto.common.RequestDTO;
import com.myapplication.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface RequestedByService extends GenericService<RequestedBy, Integer> {

	List<RequestedBy> findAll();

	ResultDTO addRequestedBy(RequestedByDTO requestedByDTO, RequestDTO requestDTO);

	ResultDTO updateRequestedBy(RequestedByDTO requestedByDTO, RequestDTO requestDTO);

    Page<RequestedBy> getAllRequestedBys(Pageable pageable);

    Page<RequestedBy> getAllRequestedBys(Specification<RequestedBy> spec, Pageable pageable);

	ResponseEntity<RequestedByPageDTO> getRequestedBys(RequestedBySearchDTO requestedBySearchDTO);
	
	List<RequestedByDTO> convertRequestedBysToRequestedByDTOs(List<RequestedBy> requestedBys, RequestedByConvertCriteriaDTO convertCriteria);

	RequestedByDTO getRequestedByDTOById(Integer requestedById);







}





