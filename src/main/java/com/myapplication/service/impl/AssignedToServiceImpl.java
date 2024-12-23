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
import com.myapplication.dao.AssignedToDAO;
import com.myapplication.domain.AssignedTo;
import com.myapplication.dto.AssignedToDTO;
import com.myapplication.dto.AssignedToSearchDTO;
import com.myapplication.dto.AssignedToPageDTO;
import com.myapplication.dto.AssignedToConvertCriteriaDTO;
import com.myapplication.dto.common.RequestDTO;
import com.myapplication.dto.common.ResultDTO;
import com.myapplication.service.AssignedToService;
import com.myapplication.util.ControllerUtils;





@Service
public class AssignedToServiceImpl extends GenericServiceImpl<AssignedTo, Integer> implements AssignedToService {

    private final static Logger logger = LoggerFactory.getLogger(AssignedToServiceImpl.class);

	@Autowired
	AssignedToDAO assignedToDao;

	


	@Override
	public GenericDAO<AssignedTo, Integer> getDAO() {
		return (GenericDAO<AssignedTo, Integer>) assignedToDao;
	}
	
	public List<AssignedTo> findAll () {
		List<AssignedTo> assignedTos = assignedToDao.findAll();
		
		return assignedTos;	
		
	}

	public ResultDTO addAssignedTo(AssignedToDTO assignedToDTO, RequestDTO requestDTO) {

		AssignedTo assignedTo = new AssignedTo();

		assignedTo.setAssignedToId(assignedToDTO.getAssignedToId());


		assignedTo.setAssignmentDate(assignedToDTO.getAssignmentDate());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		assignedTo = assignedToDao.save(assignedTo);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<AssignedTo> getAllAssignedTos(Pageable pageable) {
		return assignedToDao.findAll(pageable);
	}

	public Page<AssignedTo> getAllAssignedTos(Specification<AssignedTo> spec, Pageable pageable) {
		return assignedToDao.findAll(spec, pageable);
	}

	public ResponseEntity<AssignedToPageDTO> getAssignedTos(AssignedToSearchDTO assignedToSearchDTO) {
	
			Integer assignedToId = assignedToSearchDTO.getAssignedToId(); 
   			String sortBy = assignedToSearchDTO.getSortBy();
			String sortOrder = assignedToSearchDTO.getSortOrder();
			String searchQuery = assignedToSearchDTO.getSearchQuery();
			Integer page = assignedToSearchDTO.getPage();
			Integer size = assignedToSearchDTO.getSize();

	        Specification<AssignedTo> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, assignedToId, "assignedToId"); 
			
 			

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

		Page<AssignedTo> assignedTos = this.getAllAssignedTos(spec, pageable);
		
		//System.out.println(String.valueOf(assignedTos.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(assignedTos.getTotalPages()));
		
		List<AssignedTo> assignedTosList = assignedTos.getContent();
		
		AssignedToConvertCriteriaDTO convertCriteria = new AssignedToConvertCriteriaDTO();
		List<AssignedToDTO> assignedToDTOs = this.convertAssignedTosToAssignedToDTOs(assignedTosList,convertCriteria);
		
		AssignedToPageDTO assignedToPageDTO = new AssignedToPageDTO();
		assignedToPageDTO.setAssignedTos(assignedToDTOs);
		assignedToPageDTO.setTotalElements(assignedTos.getTotalElements());
		return ResponseEntity.ok(assignedToPageDTO);
	}

	public List<AssignedToDTO> convertAssignedTosToAssignedToDTOs(List<AssignedTo> assignedTos, AssignedToConvertCriteriaDTO convertCriteria) {
		
		List<AssignedToDTO> assignedToDTOs = new ArrayList<AssignedToDTO>();
		
		for (AssignedTo assignedTo : assignedTos) {
			assignedToDTOs.add(convertAssignedToToAssignedToDTO(assignedTo,convertCriteria));
		}
		
		return assignedToDTOs;

	}
	
	public AssignedToDTO convertAssignedToToAssignedToDTO(AssignedTo assignedTo, AssignedToConvertCriteriaDTO convertCriteria) {
		
		AssignedToDTO assignedToDTO = new AssignedToDTO();
		
		assignedToDTO.setAssignedToId(assignedTo.getAssignedToId());

	
		assignedToDTO.setAssignmentDate(assignedTo.getAssignmentDate());

	

		
		return assignedToDTO;
	}

	public ResultDTO updateAssignedTo(AssignedToDTO assignedToDTO, RequestDTO requestDTO) {
		
		AssignedTo assignedTo = assignedToDao.getById(assignedToDTO.getAssignedToId());

		assignedTo.setAssignedToId(ControllerUtils.setValue(assignedTo.getAssignedToId(), assignedToDTO.getAssignedToId()));

		assignedTo.setAssignmentDate(ControllerUtils.setValue(assignedTo.getAssignmentDate(), assignedToDTO.getAssignmentDate()));



        assignedTo = assignedToDao.save(assignedTo);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public AssignedToDTO getAssignedToDTOById(Integer assignedToId) {
	
		AssignedTo assignedTo = assignedToDao.getById(assignedToId);
			
		
		AssignedToConvertCriteriaDTO convertCriteria = new AssignedToConvertCriteriaDTO();
		return(this.convertAssignedToToAssignedToDTO(assignedTo,convertCriteria));
	}







}
