package com.myapplication.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.myapplication.domain.Driver;
import com.myapplication.dto.DriverDTO;
import com.myapplication.dto.DriverSearchDTO;
import com.myapplication.dto.DriverPageDTO;
import com.myapplication.dto.DriverConvertCriteriaDTO;
import com.myapplication.service.GenericService;
import com.myapplication.dto.common.RequestDTO;
import com.myapplication.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface DriverService extends GenericService<Driver, Integer> {

	List<Driver> findAll();

	ResultDTO addDriver(DriverDTO driverDTO, RequestDTO requestDTO);

	ResultDTO updateDriver(DriverDTO driverDTO, RequestDTO requestDTO);

    Page<Driver> getAllDrivers(Pageable pageable);

    Page<Driver> getAllDrivers(Specification<Driver> spec, Pageable pageable);

	ResponseEntity<DriverPageDTO> getDrivers(DriverSearchDTO driverSearchDTO);
	
	List<DriverDTO> convertDriversToDriverDTOs(List<Driver> drivers, DriverConvertCriteriaDTO convertCriteria);

	DriverDTO getDriverDTOById(Integer driverId);







}





