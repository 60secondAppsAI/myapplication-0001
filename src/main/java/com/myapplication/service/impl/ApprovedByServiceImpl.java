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
import com.myapplication.dao.ApprovedByDAO;
import com.myapplication.domain.ApprovedBy;
import com.myapplication.dto.ApprovedByDTO;
import com.myapplication.dto.ApprovedBySearchDTO;
import com.myapplication.dto.ApprovedByPageDTO;
import com.myapplication.dto.ApprovedByConvertCriteriaDTO;
import com.myapplication.dto.common.RequestDTO;
import com.myapplication.dto.common.ResultDTO;
import com.myapplication.service.ApprovedByService;
import com.myapplication.util.ControllerUtils;





@Service
public class ApprovedByServiceImpl extends GenericServiceImpl<ApprovedBy, Integer> implements ApprovedByService {

    private final static Logger logger = LoggerFactory.getLogger(ApprovedByServiceImpl.class);

	@Autowired
	ApprovedByDAO approvedByDao;

	


	@Override
	public GenericDAO<ApprovedBy, Integer> getDAO() {
		return (GenericDAO<ApprovedBy, Integer>) approvedByDao;
	}
	
	public List<ApprovedBy> findAll () {
		List<ApprovedBy> approvedBys = approvedByDao.findAll();
		
		return approvedBys;	
		
	}

	public ResultDTO addApprovedBy(ApprovedByDTO approvedByDTO, RequestDTO requestDTO) {

		ApprovedBy approvedBy = new ApprovedBy();

		approvedBy.setApprovedById(approvedByDTO.getApprovedById());


		approvedBy.setApprovalDate(approvedByDTO.getApprovalDate());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		approvedBy = approvedByDao.save(approvedBy);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<ApprovedBy> getAllApprovedBys(Pageable pageable) {
		return approvedByDao.findAll(pageable);
	}

	public Page<ApprovedBy> getAllApprovedBys(Specification<ApprovedBy> spec, Pageable pageable) {
		return approvedByDao.findAll(spec, pageable);
	}

	public ResponseEntity<ApprovedByPageDTO> getApprovedBys(ApprovedBySearchDTO approvedBySearchDTO) {
	
			Integer approvedById = approvedBySearchDTO.getApprovedById(); 
   			String sortBy = approvedBySearchDTO.getSortBy();
			String sortOrder = approvedBySearchDTO.getSortOrder();
			String searchQuery = approvedBySearchDTO.getSearchQuery();
			Integer page = approvedBySearchDTO.getPage();
			Integer size = approvedBySearchDTO.getSize();

	        Specification<ApprovedBy> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, approvedById, "approvedById"); 
			
 			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

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

		Page<ApprovedBy> approvedBys = this.getAllApprovedBys(spec, pageable);
		
		//System.out.println(String.valueOf(approvedBys.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(approvedBys.getTotalPages()));
		
		List<ApprovedBy> approvedBysList = approvedBys.getContent();
		
		ApprovedByConvertCriteriaDTO convertCriteria = new ApprovedByConvertCriteriaDTO();
		List<ApprovedByDTO> approvedByDTOs = this.convertApprovedBysToApprovedByDTOs(approvedBysList,convertCriteria);
		
		ApprovedByPageDTO approvedByPageDTO = new ApprovedByPageDTO();
		approvedByPageDTO.setApprovedBys(approvedByDTOs);
		approvedByPageDTO.setTotalElements(approvedBys.getTotalElements());
		return ResponseEntity.ok(approvedByPageDTO);
	}

	public List<ApprovedByDTO> convertApprovedBysToApprovedByDTOs(List<ApprovedBy> approvedBys, ApprovedByConvertCriteriaDTO convertCriteria) {
		
		List<ApprovedByDTO> approvedByDTOs = new ArrayList<ApprovedByDTO>();
		
		for (ApprovedBy approvedBy : approvedBys) {
			approvedByDTOs.add(convertApprovedByToApprovedByDTO(approvedBy,convertCriteria));
		}
		
		return approvedByDTOs;

	}
	
	public ApprovedByDTO convertApprovedByToApprovedByDTO(ApprovedBy approvedBy, ApprovedByConvertCriteriaDTO convertCriteria) {
		
		ApprovedByDTO approvedByDTO = new ApprovedByDTO();
		
		approvedByDTO.setApprovedById(approvedBy.getApprovedById());

	
		approvedByDTO.setApprovalDate(approvedBy.getApprovalDate());

	

		
		return approvedByDTO;
	}

	public ResultDTO updateApprovedBy(ApprovedByDTO approvedByDTO, RequestDTO requestDTO) {
		
		ApprovedBy approvedBy = approvedByDao.getById(approvedByDTO.getApprovedById());

		approvedBy.setApprovedById(ControllerUtils.setValue(approvedBy.getApprovedById(), approvedByDTO.getApprovedById()));

		approvedBy.setApprovalDate(ControllerUtils.setValue(approvedBy.getApprovalDate(), approvedByDTO.getApprovalDate()));



        approvedBy = approvedByDao.save(approvedBy);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public ApprovedByDTO getApprovedByDTOById(Integer approvedById) {
	
		ApprovedBy approvedBy = approvedByDao.getById(approvedById);
			
		
		ApprovedByConvertCriteriaDTO convertCriteria = new ApprovedByConvertCriteriaDTO();
		return(this.convertApprovedByToApprovedByDTO(approvedBy,convertCriteria));
	}







}
