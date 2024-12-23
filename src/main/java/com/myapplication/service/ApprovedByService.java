package com.myapplication.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.myapplication.domain.ApprovedBy;
import com.myapplication.dto.ApprovedByDTO;
import com.myapplication.dto.ApprovedBySearchDTO;
import com.myapplication.dto.ApprovedByPageDTO;
import com.myapplication.dto.ApprovedByConvertCriteriaDTO;
import com.myapplication.service.GenericService;
import com.myapplication.dto.common.RequestDTO;
import com.myapplication.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface ApprovedByService extends GenericService<ApprovedBy, Integer> {

	List<ApprovedBy> findAll();

	ResultDTO addApprovedBy(ApprovedByDTO approvedByDTO, RequestDTO requestDTO);

	ResultDTO updateApprovedBy(ApprovedByDTO approvedByDTO, RequestDTO requestDTO);

    Page<ApprovedBy> getAllApprovedBys(Pageable pageable);

    Page<ApprovedBy> getAllApprovedBys(Specification<ApprovedBy> spec, Pageable pageable);

	ResponseEntity<ApprovedByPageDTO> getApprovedBys(ApprovedBySearchDTO approvedBySearchDTO);
	
	List<ApprovedByDTO> convertApprovedBysToApprovedByDTOs(List<ApprovedBy> approvedBys, ApprovedByConvertCriteriaDTO convertCriteria);

	ApprovedByDTO getApprovedByDTOById(Integer approvedById);







}





