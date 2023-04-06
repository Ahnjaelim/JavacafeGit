package kr.co.javacafe.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.javacafe.domain.Recipe;
import kr.co.javacafe.domain.Shop;
import kr.co.javacafe.dto.PageRequestDTO;
import kr.co.javacafe.dto.PageResponseDTO;
import kr.co.javacafe.dto.ShopJoinDTO;
import kr.co.javacafe.dto.ShopStateDTO;
import kr.co.javacafe.service.RecipeService;
import kr.co.javacafe.service.ShopService;
import kr.co.javacafe.service.ShopStateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/shop")
@Log4j2
@RequiredArgsConstructor
public class ShopController {
	
	private final ShopService shopService;
	private final ShopStateService shopStateService;
	private final RecipeService recipeService;

	@GetMapping("/order")
	public void orderGET() {	
	}	
	@GetMapping("/list")
	public void listGET(PageRequestDTO pageRequestDTO, Model model) {
        PageResponseDTO<ShopStateDTO> responseDTO = shopStateService.list(pageRequestDTO);
        log.info(responseDTO);
        model.addAttribute("responseDTO", responseDTO);		
        
	}
	
	@GetMapping("/demand")
	public void testGET(Model model) {
		model.addAttribute("bestlist", shopService.getBest());
		
	}
	
	
	//ajax recipelist받기
	@PostMapping("recipelist")
	@ResponseBody
	public List<Recipe> recipelist(Model model){
		List<Recipe> recipelist = recipeService.recipeList();
		return recipelist;
	}
	
	@GetMapping("/read")
	public void readGET(Long ssno, String sid, Model model){
		log.info("sid : "+sid);
		// List<ShopDTO> dtolist = shopService.getByKid(sid);
		model.addAttribute("dto", shopStateService.readOne(ssno));
		model.addAttribute("dtolist", shopService.getByKidJoin(sid));
	}
	
	@GetMapping("/dashboard")
	public void dashboard(Model model) {
		model.addAttribute("bestlist", shopService.getBest());
		
	}
}