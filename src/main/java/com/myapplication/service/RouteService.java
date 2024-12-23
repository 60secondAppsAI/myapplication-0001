package com.myapplication.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.myapplication.domain.Route;
import com.myapplication.dto.RouteDTO;
import com.myapplication.dto.RouteSearchDTO;
import com.myapplication.dto.RoutePageDTO;
import com.myapplication.dto.RouteConvertCriteriaDTO;
import com.myapplication.service.GenericService;
import com.myapplication.dto.common.RequestDTO;
import com.myapplication.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface RouteService extends GenericService<Route, Integer> {

	List<Route> findAll();

	ResultDTO addRoute(RouteDTO routeDTO, RequestDTO requestDTO);

	ResultDTO updateRoute(RouteDTO routeDTO, RequestDTO requestDTO);

    Page<Route> getAllRoutes(Pageable pageable);

    Page<Route> getAllRoutes(Specification<Route> spec, Pageable pageable);

	ResponseEntity<RoutePageDTO> getRoutes(RouteSearchDTO routeSearchDTO);
	
	List<RouteDTO> convertRoutesToRouteDTOs(List<Route> routes, RouteConvertCriteriaDTO convertCriteria);

	RouteDTO getRouteDTOById(Integer routeId);







}





