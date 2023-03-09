package kr.co.javacafe.controller;

 
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.javacafe.domain.Recipe;
import kr.co.javacafe.domain.Sales;
import kr.co.javacafe.dto.EventDTO;
import kr.co.javacafe.dto.FBoardDTO;
import kr.co.javacafe.dto.PageRequestDTO;
import kr.co.javacafe.dto.HomePageRequestDTO;
import kr.co.javacafe.dto.PageResponseDTO;
import kr.co.javacafe.dto.RecipeDTO;
import kr.co.javacafe.dto.HomePageResponseDTO;
import kr.co.javacafe.dto.SalesDTO;
import kr.co.javacafe.service.EventService;
import kr.co.javacafe.service.FBoardService;
import kr.co.javacafe.service.RecipeService;
import kr.co.javacafe.service.SalesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequiredArgsConstructor
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private final SalesService salesService;
	private final FBoardService fBoardService;
	private final EventService eventService;
	private final RecipeService recipeService;
	
	@GetMapping(value={"/",""})
	public String home(Locale locale, Model model,
						PageRequestDTO pageRequestDTO,
						HomePageRequestDTO HomePageRequestDTO) {
		logger.info("Welcome home! The client locale is {}.", locale);	
			//카페규정 리스트불러오기
			HomePageResponseDTO<FBoardDTO> fbresponseDTO = fBoardService.list2(HomePageRequestDTO);
			model.addAttribute("fboardDTO",fbresponseDTO);
			log.info("카페규정 리스트 불러오기 성공=======================");
			//이벤트 불러오기
			HomePageResponseDTO<EventDTO> eventresponseDTO = eventService.list2(HomePageRequestDTO);
			model.addAttribute("eventDTO", eventresponseDTO);
			log.info("이벤트 리스트 불러오기 성공=======================");
		
		return "/home";
	}
	//ajax saleslist받기
	@PostMapping("saleslist")
	@ResponseBody
	public List<Sales> saleslist(Model model) {		
		List<Sales> saleslist = salesService.list();
		return saleslist;		
	}
	//ajax recipelist받기
	@PostMapping("recipelist")
	@ResponseBody
	public List<Recipe> recipelist(Model model){
		List<Recipe> recipelist = recipeService.recipeList();
		return recipelist;
	}
	
 }
