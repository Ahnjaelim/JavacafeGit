package kr.co.javacafe.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.javacafe.domain.Sales;
import kr.co.javacafe.dto.PageRequestDTO;
import kr.co.javacafe.dto.SalesPageRequestDTO;
import kr.co.javacafe.dto.PageResponseDTO;
import kr.co.javacafe.dto.SalesPageResponseDTO;
import kr.co.javacafe.dto.SalesDTO;

import kr.co.javacafe.service.SalesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequiredArgsConstructor
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private final SalesService salesService;
	
	@GetMapping(value={"/",""})
	public String home(Locale locale, Model model,
						PageRequestDTO pageRequestDTO,
						SalesPageRequestDTO salesPageRequestDTO) {
		logger.info("Welcome home! The client locale is {}.", locale);
		//매출 데이터 가져오기 (차트 만들기용도)
			PageResponseDTO<SalesDTO> pageResponseDTO = salesService.list(pageRequestDTO);
			model.addAttribute("salesDTO",pageResponseDTO);
			log.info("매출 데이터 불러오기 성공==============");
		
		
			//매출 데이터 가져오기 (게시판용도)
			SalesPageResponseDTO<SalesDTO> salesPageResponseDTO = salesService.list2(salesPageRequestDTO);
			model.addAttribute("dto", salesPageResponseDTO);
			log.info("매출데이터 불러오기(게시판용) ============");
			
		
		
		return "/home";
	}
	

	
	
}
