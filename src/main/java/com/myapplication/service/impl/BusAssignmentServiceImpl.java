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
import com.myapplication.dao.BusAssignmentDAO;
import com.myapplication.domain.BusAssignment;
import com.myapplication.dto.BusAssignmentDTO;
import com.myapplication.dto.BusAssignmentSearchDTO;
import com.myapplication.dto.BusAssignmentPageDTO;
import com.myapplication.dto.BusAssignmentConvertCriteriaDTO;
import com.myapplication.dto.common.RequestDTO;
import com.myapplication.dto.common.ResultDTO;
import com.myapplication.service.BusAssignmentService;
import com.myapplication.util.ControllerUtils;





@Service
public class BusAssignmentServiceImpl extends GenericServiceImpl<BusAssignment, Integer> implements BusAssignmentService {

    private final static Logger logger = LoggerFactory.getLogger(BusAssignmentServiceImpl.class);

	@Autowired
	BusAssignmentDAO busAssignmentDao;

	


	@Override
	public GenericDAO<BusAssignment, Integer> getDAO() {
		return (GenericDAO<BusAssignment, Integer>) busAssignmentDao;
	}
	
	public List<BusAssignment> findAll () {
		List<BusAssignment> busAssignments = busAssignmentDao.findAll();
		
		return busAssignments;	
		
	}

	public ResultDTO addBusAssignment(BusAssignmentDTO busAssignmentDTO, RequestDTO requestDTO) {

		BusAssignment busAssignment = new BusAssignment();

		busAssignment.setBusAssignmentId(busAssignmentDTO.getBusAssignmentId());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		busAssignment = busAssignmentDao.save(busAssignment);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<BusAssignment> getAllBusAssignments(Pageable pageable) {
		return busAssignmentDao.findAll(pageable);
	}

	public Page<BusAssignment> getAllBusAssignments(Specification<BusAssignment> spec, Pageable pageable) {
		return busAssignmentDao.findAll(spec, pageable);
	}

	public ResponseEntity<BusAssignmentPageDTO> getBusAssignments(BusAssignmentSearchDTO busAssignmentSearchDTO) {
	
			Integer busAssignmentId = busAssignmentSearchDTO.getBusAssignmentId(); 
 			String sortBy = busAssignmentSearchDTO.getSortBy();
			String sortOrder = busAssignmentSearchDTO.getSortOrder();
			String searchQuery = busAssignmentSearchDTO.getSearchQuery();
			Integer page = busAssignmentSearchDTO.getPage();
			Integer size = busAssignmentSearchDTO.getSize();

	        Specification<BusAssignment> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, busAssignmentId, "busAssignmentId"); 
			

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

		Page<BusAssignment> busAssignments = this.getAllBusAssignments(spec, pageable);
		
		//System.out.println(String.valueOf(busAssignments.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(busAssignments.getTotalPages()));
		
		List<BusAssignment> busAssignmentsList = busAssignments.getContent();
		
		BusAssignmentConvertCriteriaDTO convertCriteria = new BusAssignmentConvertCriteriaDTO();
		List<BusAssignmentDTO> busAssignmentDTOs = this.convertBusAssignmentsToBusAssignmentDTOs(busAssignmentsList,convertCriteria);
		
		BusAssignmentPageDTO busAssignmentPageDTO = new BusAssignmentPageDTO();
		busAssignmentPageDTO.setBusAssignments(busAssignmentDTOs);
		busAssignmentPageDTO.setTotalElements(busAssignments.getTotalElements());
		return ResponseEntity.ok(busAssignmentPageDTO);
	}

	public List<BusAssignmentDTO> convertBusAssignmentsToBusAssignmentDTOs(List<BusAssignment> busAssignments, BusAssignmentConvertCriteriaDTO convertCriteria) {
		
		List<BusAssignmentDTO> busAssignmentDTOs = new ArrayList<BusAssignmentDTO>();
		
		for (BusAssignment busAssignment : busAssignments) {
			busAssignmentDTOs.add(convertBusAssignmentToBusAssignmentDTO(busAssignment,convertCriteria));
		}
		
		return busAssignmentDTOs;

	}
	
	public BusAssignmentDTO convertBusAssignmentToBusAssignmentDTO(BusAssignment busAssignment, BusAssignmentConvertCriteriaDTO convertCriteria) {
		
		BusAssignmentDTO busAssignmentDTO = new BusAssignmentDTO();
		
		busAssignmentDTO.setBusAssignmentId(busAssignment.getBusAssignmentId());

	

		
		return busAssignmentDTO;
	}

	public ResultDTO updateBusAssignment(BusAssignmentDTO busAssignmentDTO, RequestDTO requestDTO) {
		
		BusAssignment busAssignment = busAssignmentDao.getById(busAssignmentDTO.getBusAssignmentId());

		busAssignment.setBusAssignmentId(ControllerUtils.setValue(busAssignment.getBusAssignmentId(), busAssignmentDTO.getBusAssignmentId()));



        busAssignment = busAssignmentDao.save(busAssignment);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public BusAssignmentDTO getBusAssignmentDTOById(Integer busAssignmentId) {
	
		BusAssignment busAssignment = busAssignmentDao.getById(busAssignmentId);
			
		
		BusAssignmentConvertCriteriaDTO convertCriteria = new BusAssignmentConvertCriteriaDTO();
		return(this.convertBusAssignmentToBusAssignmentDTO(busAssignment,convertCriteria));
	}







}
