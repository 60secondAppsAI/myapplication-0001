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
import com.myapplication.dao.DriverAssignmentDAO;
import com.myapplication.domain.DriverAssignment;
import com.myapplication.dto.DriverAssignmentDTO;
import com.myapplication.dto.DriverAssignmentSearchDTO;
import com.myapplication.dto.DriverAssignmentPageDTO;
import com.myapplication.dto.DriverAssignmentConvertCriteriaDTO;
import com.myapplication.dto.common.RequestDTO;
import com.myapplication.dto.common.ResultDTO;
import com.myapplication.service.DriverAssignmentService;
import com.myapplication.util.ControllerUtils;





@Service
public class DriverAssignmentServiceImpl extends GenericServiceImpl<DriverAssignment, Integer> implements DriverAssignmentService {

    private final static Logger logger = LoggerFactory.getLogger(DriverAssignmentServiceImpl.class);

	@Autowired
	DriverAssignmentDAO driverAssignmentDao;

	


	@Override
	public GenericDAO<DriverAssignment, Integer> getDAO() {
		return (GenericDAO<DriverAssignment, Integer>) driverAssignmentDao;
	}
	
	public List<DriverAssignment> findAll () {
		List<DriverAssignment> driverAssignments = driverAssignmentDao.findAll();
		
		return driverAssignments;	
		
	}

	public ResultDTO addDriverAssignment(DriverAssignmentDTO driverAssignmentDTO, RequestDTO requestDTO) {

		DriverAssignment driverAssignment = new DriverAssignment();

		driverAssignment.setDriverAssignmentId(driverAssignmentDTO.getDriverAssignmentId());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		driverAssignment = driverAssignmentDao.save(driverAssignment);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<DriverAssignment> getAllDriverAssignments(Pageable pageable) {
		return driverAssignmentDao.findAll(pageable);
	}

	public Page<DriverAssignment> getAllDriverAssignments(Specification<DriverAssignment> spec, Pageable pageable) {
		return driverAssignmentDao.findAll(spec, pageable);
	}

	public ResponseEntity<DriverAssignmentPageDTO> getDriverAssignments(DriverAssignmentSearchDTO driverAssignmentSearchDTO) {
	
			Integer driverAssignmentId = driverAssignmentSearchDTO.getDriverAssignmentId(); 
 			String sortBy = driverAssignmentSearchDTO.getSortBy();
			String sortOrder = driverAssignmentSearchDTO.getSortOrder();
			String searchQuery = driverAssignmentSearchDTO.getSearchQuery();
			Integer page = driverAssignmentSearchDTO.getPage();
			Integer size = driverAssignmentSearchDTO.getSize();

	        Specification<DriverAssignment> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, driverAssignmentId, "driverAssignmentId"); 
			

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

		Page<DriverAssignment> driverAssignments = this.getAllDriverAssignments(spec, pageable);
		
		//System.out.println(String.valueOf(driverAssignments.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(driverAssignments.getTotalPages()));
		
		List<DriverAssignment> driverAssignmentsList = driverAssignments.getContent();
		
		DriverAssignmentConvertCriteriaDTO convertCriteria = new DriverAssignmentConvertCriteriaDTO();
		List<DriverAssignmentDTO> driverAssignmentDTOs = this.convertDriverAssignmentsToDriverAssignmentDTOs(driverAssignmentsList,convertCriteria);
		
		DriverAssignmentPageDTO driverAssignmentPageDTO = new DriverAssignmentPageDTO();
		driverAssignmentPageDTO.setDriverAssignments(driverAssignmentDTOs);
		driverAssignmentPageDTO.setTotalElements(driverAssignments.getTotalElements());
		return ResponseEntity.ok(driverAssignmentPageDTO);
	}

	public List<DriverAssignmentDTO> convertDriverAssignmentsToDriverAssignmentDTOs(List<DriverAssignment> driverAssignments, DriverAssignmentConvertCriteriaDTO convertCriteria) {
		
		List<DriverAssignmentDTO> driverAssignmentDTOs = new ArrayList<DriverAssignmentDTO>();
		
		for (DriverAssignment driverAssignment : driverAssignments) {
			driverAssignmentDTOs.add(convertDriverAssignmentToDriverAssignmentDTO(driverAssignment,convertCriteria));
		}
		
		return driverAssignmentDTOs;

	}
	
	public DriverAssignmentDTO convertDriverAssignmentToDriverAssignmentDTO(DriverAssignment driverAssignment, DriverAssignmentConvertCriteriaDTO convertCriteria) {
		
		DriverAssignmentDTO driverAssignmentDTO = new DriverAssignmentDTO();
		
		driverAssignmentDTO.setDriverAssignmentId(driverAssignment.getDriverAssignmentId());

	

		
		return driverAssignmentDTO;
	}

	public ResultDTO updateDriverAssignment(DriverAssignmentDTO driverAssignmentDTO, RequestDTO requestDTO) {
		
		DriverAssignment driverAssignment = driverAssignmentDao.getById(driverAssignmentDTO.getDriverAssignmentId());

		driverAssignment.setDriverAssignmentId(ControllerUtils.setValue(driverAssignment.getDriverAssignmentId(), driverAssignmentDTO.getDriverAssignmentId()));



        driverAssignment = driverAssignmentDao.save(driverAssignment);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public DriverAssignmentDTO getDriverAssignmentDTOById(Integer driverAssignmentId) {
	
		DriverAssignment driverAssignment = driverAssignmentDao.getById(driverAssignmentId);
			
		
		DriverAssignmentConvertCriteriaDTO convertCriteria = new DriverAssignmentConvertCriteriaDTO();
		return(this.convertDriverAssignmentToDriverAssignmentDTO(driverAssignment,convertCriteria));
	}







}
