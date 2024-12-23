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
import com.myapplication.dao.QuoteDAO;
import com.myapplication.domain.Quote;
import com.myapplication.dto.QuoteDTO;
import com.myapplication.dto.QuoteSearchDTO;
import com.myapplication.dto.QuotePageDTO;
import com.myapplication.dto.QuoteConvertCriteriaDTO;
import com.myapplication.dto.common.RequestDTO;
import com.myapplication.dto.common.ResultDTO;
import com.myapplication.service.QuoteService;
import com.myapplication.util.ControllerUtils;





@Service
public class QuoteServiceImpl extends GenericServiceImpl<Quote, Integer> implements QuoteService {

    private final static Logger logger = LoggerFactory.getLogger(QuoteServiceImpl.class);

	@Autowired
	QuoteDAO quoteDao;

	


	@Override
	public GenericDAO<Quote, Integer> getDAO() {
		return (GenericDAO<Quote, Integer>) quoteDao;
	}
	
	public List<Quote> findAll () {
		List<Quote> quotes = quoteDao.findAll();
		
		return quotes;	
		
	}

	public ResultDTO addQuote(QuoteDTO quoteDTO, RequestDTO requestDTO) {

		Quote quote = new Quote();

		quote.setQuoteId(quoteDTO.getQuoteId());


		quote.setDepartureDate(quoteDTO.getDepartureDate());


		quote.setPrice(quoteDTO.getPrice());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		quote = quoteDao.save(quote);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Quote> getAllQuotes(Pageable pageable) {
		return quoteDao.findAll(pageable);
	}

	public Page<Quote> getAllQuotes(Specification<Quote> spec, Pageable pageable) {
		return quoteDao.findAll(spec, pageable);
	}

	public ResponseEntity<QuotePageDTO> getQuotes(QuoteSearchDTO quoteSearchDTO) {
	
			Integer quoteId = quoteSearchDTO.getQuoteId(); 
    			String sortBy = quoteSearchDTO.getSortBy();
			String sortOrder = quoteSearchDTO.getSortOrder();
			String searchQuery = quoteSearchDTO.getSearchQuery();
			Integer page = quoteSearchDTO.getPage();
			Integer size = quoteSearchDTO.getSize();

	        Specification<Quote> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, quoteId, "quoteId"); 
			
 			
			

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

		Page<Quote> quotes = this.getAllQuotes(spec, pageable);
		
		//System.out.println(String.valueOf(quotes.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(quotes.getTotalPages()));
		
		List<Quote> quotesList = quotes.getContent();
		
		QuoteConvertCriteriaDTO convertCriteria = new QuoteConvertCriteriaDTO();
		List<QuoteDTO> quoteDTOs = this.convertQuotesToQuoteDTOs(quotesList,convertCriteria);
		
		QuotePageDTO quotePageDTO = new QuotePageDTO();
		quotePageDTO.setQuotes(quoteDTOs);
		quotePageDTO.setTotalElements(quotes.getTotalElements());
		return ResponseEntity.ok(quotePageDTO);
	}

	public List<QuoteDTO> convertQuotesToQuoteDTOs(List<Quote> quotes, QuoteConvertCriteriaDTO convertCriteria) {
		
		List<QuoteDTO> quoteDTOs = new ArrayList<QuoteDTO>();
		
		for (Quote quote : quotes) {
			quoteDTOs.add(convertQuoteToQuoteDTO(quote,convertCriteria));
		}
		
		return quoteDTOs;

	}
	
	public QuoteDTO convertQuoteToQuoteDTO(Quote quote, QuoteConvertCriteriaDTO convertCriteria) {
		
		QuoteDTO quoteDTO = new QuoteDTO();
		
		quoteDTO.setQuoteId(quote.getQuoteId());

	
		quoteDTO.setDepartureDate(quote.getDepartureDate());

	
		quoteDTO.setPrice(quote.getPrice());

	

		
		return quoteDTO;
	}

	public ResultDTO updateQuote(QuoteDTO quoteDTO, RequestDTO requestDTO) {
		
		Quote quote = quoteDao.getById(quoteDTO.getQuoteId());

		quote.setQuoteId(ControllerUtils.setValue(quote.getQuoteId(), quoteDTO.getQuoteId()));

		quote.setDepartureDate(ControllerUtils.setValue(quote.getDepartureDate(), quoteDTO.getDepartureDate()));

		quote.setPrice(ControllerUtils.setValue(quote.getPrice(), quoteDTO.getPrice()));



        quote = quoteDao.save(quote);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public QuoteDTO getQuoteDTOById(Integer quoteId) {
	
		Quote quote = quoteDao.getById(quoteId);
			
		
		QuoteConvertCriteriaDTO convertCriteria = new QuoteConvertCriteriaDTO();
		return(this.convertQuoteToQuoteDTO(quote,convertCriteria));
	}







}
