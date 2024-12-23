package com.myapplication.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.myapplication.domain.Bus;
import com.myapplication.dto.BusDTO;
import com.myapplication.dto.BusSearchDTO;
import com.myapplication.dto.BusPageDTO;
import com.myapplication.dto.BusConvertCriteriaDTO;
import com.myapplication.service.GenericService;
import com.myapplication.dto.common.RequestDTO;
import com.myapplication.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface BusService extends GenericService<Bus, Integer> {

	List<Bus> findAll();

	ResultDTO addBus(BusDTO busDTO, RequestDTO requestDTO);

	ResultDTO updateBus(BusDTO busDTO, RequestDTO requestDTO);

    Page<Bus> getAllBuss(Pageable pageable);

    Page<Bus> getAllBuss(Specification<Bus> spec, Pageable pageable);

	ResponseEntity<BusPageDTO> getBuss(BusSearchDTO busSearchDTO);
	
	List<BusDTO> convertBussToBusDTOs(List<Bus> buss, BusConvertCriteriaDTO convertCriteria);

	BusDTO getBusDTOById(Integer busId);







}





