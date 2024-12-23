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
import com.myapplication.dao.BusDAO;
import com.myapplication.domain.Bus;
import com.myapplication.dto.BusDTO;
import com.myapplication.dto.BusSearchDTO;
import com.myapplication.dto.BusPageDTO;
import com.myapplication.dto.BusConvertCriteriaDTO;
import com.myapplication.dto.common.RequestDTO;
import com.myapplication.dto.common.ResultDTO;
import com.myapplication.service.BusService;
import com.myapplication.util.ControllerUtils;





@Service
public class BusServiceImpl extends GenericServiceImpl<Bus, Integer> implements BusService {

    private final static Logger logger = LoggerFactory.getLogger(BusServiceImpl.class);

	@Autowired
	BusDAO busDao;

	


	@Override
	public GenericDAO<Bus, Integer> getDAO() {
		return (GenericDAO<Bus, Integer>) busDao;
	}
	
	public List<Bus> findAll () {
		List<Bus> buss = busDao.findAll();
		
		return buss;	
		
	}

	public ResultDTO addBus(BusDTO busDTO, RequestDTO requestDTO) {

		Bus bus = new Bus();

		bus.setBusId(busDTO.getBusId());


		bus.setLicensePlate(busDTO.getLicensePlate());


		bus.setModel(busDTO.getModel());


		bus.setSeats(busDTO.getSeats());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		bus = busDao.save(bus);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Bus> getAllBuss(Pageable pageable) {
		return busDao.findAll(pageable);
	}

	public Page<Bus> getAllBuss(Specification<Bus> spec, Pageable pageable) {
		return busDao.findAll(spec, pageable);
	}

	public ResponseEntity<BusPageDTO> getBuss(BusSearchDTO busSearchDTO) {
	
			Integer busId = busSearchDTO.getBusId(); 
 			String licensePlate = busSearchDTO.getLicensePlate(); 
 			String model = busSearchDTO.getModel(); 
  			String sortBy = busSearchDTO.getSortBy();
			String sortOrder = busSearchDTO.getSortOrder();
			String searchQuery = busSearchDTO.getSearchQuery();
			Integer page = busSearchDTO.getPage();
			Integer size = busSearchDTO.getSize();

	        Specification<Bus> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, busId, "busId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, licensePlate, "licensePlate"); 
			
			spec = ControllerUtils.andIfNecessary(spec, model, "model"); 
			
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("licensePlate")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("model")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Bus> buss = this.getAllBuss(spec, pageable);
		
		//System.out.println(String.valueOf(buss.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(buss.getTotalPages()));
		
		List<Bus> bussList = buss.getContent();
		
		BusConvertCriteriaDTO convertCriteria = new BusConvertCriteriaDTO();
		List<BusDTO> busDTOs = this.convertBussToBusDTOs(bussList,convertCriteria);
		
		BusPageDTO busPageDTO = new BusPageDTO();
		busPageDTO.setBuss(busDTOs);
		busPageDTO.setTotalElements(buss.getTotalElements());
		return ResponseEntity.ok(busPageDTO);
	}

	public List<BusDTO> convertBussToBusDTOs(List<Bus> buss, BusConvertCriteriaDTO convertCriteria) {
		
		List<BusDTO> busDTOs = new ArrayList<BusDTO>();
		
		for (Bus bus : buss) {
			busDTOs.add(convertBusToBusDTO(bus,convertCriteria));
		}
		
		return busDTOs;

	}
	
	public BusDTO convertBusToBusDTO(Bus bus, BusConvertCriteriaDTO convertCriteria) {
		
		BusDTO busDTO = new BusDTO();
		
		busDTO.setBusId(bus.getBusId());

	
		busDTO.setLicensePlate(bus.getLicensePlate());

	
		busDTO.setModel(bus.getModel());

	
		busDTO.setSeats(bus.getSeats());

	

		
		return busDTO;
	}

	public ResultDTO updateBus(BusDTO busDTO, RequestDTO requestDTO) {
		
		Bus bus = busDao.getById(busDTO.getBusId());

		bus.setBusId(ControllerUtils.setValue(bus.getBusId(), busDTO.getBusId()));

		bus.setLicensePlate(ControllerUtils.setValue(bus.getLicensePlate(), busDTO.getLicensePlate()));

		bus.setModel(ControllerUtils.setValue(bus.getModel(), busDTO.getModel()));

		bus.setSeats(ControllerUtils.setValue(bus.getSeats(), busDTO.getSeats()));



        bus = busDao.save(bus);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public BusDTO getBusDTOById(Integer busId) {
	
		Bus bus = busDao.getById(busId);
			
		
		BusConvertCriteriaDTO convertCriteria = new BusConvertCriteriaDTO();
		return(this.convertBusToBusDTO(bus,convertCriteria));
	}







}
