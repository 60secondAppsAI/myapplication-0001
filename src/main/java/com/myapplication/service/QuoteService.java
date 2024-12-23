package com.myapplication.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.myapplication.domain.Quote;
import com.myapplication.dto.QuoteDTO;
import com.myapplication.dto.QuoteSearchDTO;
import com.myapplication.dto.QuotePageDTO;
import com.myapplication.dto.QuoteConvertCriteriaDTO;
import com.myapplication.service.GenericService;
import com.myapplication.dto.common.RequestDTO;
import com.myapplication.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface QuoteService extends GenericService<Quote, Integer> {

	List<Quote> findAll();

	ResultDTO addQuote(QuoteDTO quoteDTO, RequestDTO requestDTO);

	ResultDTO updateQuote(QuoteDTO quoteDTO, RequestDTO requestDTO);

    Page<Quote> getAllQuotes(Pageable pageable);

    Page<Quote> getAllQuotes(Specification<Quote> spec, Pageable pageable);

	ResponseEntity<QuotePageDTO> getQuotes(QuoteSearchDTO quoteSearchDTO);
	
	List<QuoteDTO> convertQuotesToQuoteDTOs(List<Quote> quotes, QuoteConvertCriteriaDTO convertCriteria);

	QuoteDTO getQuoteDTOById(Integer quoteId);







}





