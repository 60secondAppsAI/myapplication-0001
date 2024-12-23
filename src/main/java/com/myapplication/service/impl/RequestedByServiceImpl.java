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
import com.myapplication.dao.RequestedByDAO;
import com.myapplication.domain.RequestedBy;
import com.myapplication.dto.RequestedByDTO;
import com.myapplication.dto.RequestedBySearchDTO;
import com.myapplication.dto.RequestedByPageDTO;
import com.myapplication.dto.RequestedByConvertCriteriaDTO;
import com.myapplication.dto.common.RequestDTO;
import com.myapplication.dto.common.ResultDTO;
import com.myapplication.service.RequestedByService;
import com.myapplication.util.ControllerUtils;





@Service
public class RequestedByServiceImpl extends GenericServiceImpl<RequestedBy, Integer> implements RequestedByService {

    private final static Logger logger = LoggerFactory.getLogger(RequestedByServiceImpl.class);

	@Autowired
	RequestedByDAO requestedByDao;

	


	@Override
	public GenericDAO<RequestedBy, Integer> getDAO() {
		return (GenericDAO<RequestedBy, Integer>) requestedByDao;
	}
	
	public List<RequestedBy> findAll () {
		List<RequestedBy> requestedBys = requestedByDao.findAll();
		
		return requestedBys;	
		
	}

	public ResultDTO addRequestedBy(RequestedByDTO requestedByDTO, RequestDTO requestDTO) {

		RequestedBy requestedBy = new RequestedBy();

		requestedBy.setRequestedById(requestedByDTO.getRequestedById());


		requestedBy.setRequestDate(requestedByDTO.getRequestDate());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		requestedBy = requestedByDao.save(requestedBy);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<RequestedBy> getAllRequestedBys(Pageable pageable) {
		return requestedByDao.findAll(pageable);
	}

	public Page<RequestedBy> getAllRequestedBys(Specification<RequestedBy> spec, Pageable pageable) {
		return requestedByDao.findAll(spec, pageable);
	}

	public ResponseEntity<RequestedByPageDTO> getRequestedBys(RequestedBySearchDTO requestedBySearchDTO) {
	
			Integer requestedById = requestedBySearchDTO.getRequestedById(); 
   			String sortBy = requestedBySearchDTO.getSortBy();
			String sortOrder = requestedBySearchDTO.getSortOrder();
			String searchQuery = requestedBySearchDTO.getSearchQuery();
			Integer page = requestedBySearchDTO.getPage();
			Integer size = requestedBySearchDTO.getSize();

	        Specification<RequestedBy> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, requestedById, "requestedById"); 
			
 			

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

		Page<RequestedBy> requestedBys = this.getAllRequestedBys(spec, pageable);
		
		//System.out.println(String.valueOf(requestedBys.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(requestedBys.getTotalPages()));
		
		List<RequestedBy> requestedBysList = requestedBys.getContent();
		
		RequestedByConvertCriteriaDTO convertCriteria = new RequestedByConvertCriteriaDTO();
		List<RequestedByDTO> requestedByDTOs = this.convertRequestedBysToRequestedByDTOs(requestedBysList,convertCriteria);
		
		RequestedByPageDTO requestedByPageDTO = new RequestedByPageDTO();
		requestedByPageDTO.setRequestedBys(requestedByDTOs);
		requestedByPageDTO.setTotalElements(requestedBys.getTotalElements());
		return ResponseEntity.ok(requestedByPageDTO);
	}

	public List<RequestedByDTO> convertRequestedBysToRequestedByDTOs(List<RequestedBy> requestedBys, RequestedByConvertCriteriaDTO convertCriteria) {
		
		List<RequestedByDTO> requestedByDTOs = new ArrayList<RequestedByDTO>();
		
		for (RequestedBy requestedBy : requestedBys) {
			requestedByDTOs.add(convertRequestedByToRequestedByDTO(requestedBy,convertCriteria));
		}
		
		return requestedByDTOs;

	}
	
	public RequestedByDTO convertRequestedByToRequestedByDTO(RequestedBy requestedBy, RequestedByConvertCriteriaDTO convertCriteria) {
		
		RequestedByDTO requestedByDTO = new RequestedByDTO();
		
		requestedByDTO.setRequestedById(requestedBy.getRequestedById());

	
		requestedByDTO.setRequestDate(requestedBy.getRequestDate());

	

		
		return requestedByDTO;
	}

	public ResultDTO updateRequestedBy(RequestedByDTO requestedByDTO, RequestDTO requestDTO) {
		
		RequestedBy requestedBy = requestedByDao.getById(requestedByDTO.getRequestedById());

		requestedBy.setRequestedById(ControllerUtils.setValue(requestedBy.getRequestedById(), requestedByDTO.getRequestedById()));

		requestedBy.setRequestDate(ControllerUtils.setValue(requestedBy.getRequestDate(), requestedByDTO.getRequestDate()));



        requestedBy = requestedByDao.save(requestedBy);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public RequestedByDTO getRequestedByDTOById(Integer requestedById) {
	
		RequestedBy requestedBy = requestedByDao.getById(requestedById);
			
		
		RequestedByConvertCriteriaDTO convertCriteria = new RequestedByConvertCriteriaDTO();
		return(this.convertRequestedByToRequestedByDTO(requestedBy,convertCriteria));
	}







}
