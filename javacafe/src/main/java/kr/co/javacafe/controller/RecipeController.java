package kr.co.javacafe.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.javacafe.dto.PageRequestDTO;
import kr.co.javacafe.dto.PageResponseDTO;
import kr.co.javacafe.dto.RecipeDTO;
import kr.co.javacafe.service.RecipeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/recipe")
@Log4j2
@RequiredArgsConstructor
public class RecipeController {
	
	private final RecipeService recipeService;
	
	@GetMapping("/list")
	public void list(PageRequestDTO pageRequestDTO, Model model) {
		log.info(pageRequestDTO);
		PageResponseDTO<RecipeDTO> responseDTO = recipeService.list(pageRequestDTO);
		log.info(responseDTO);
		model.addAttribute("responseDTO", responseDTO);
	}
	
	@GetMapping("/register")
	public void registerGET() {
		
	}
	
	@PostMapping("/register")
	public String registerPost(@Valid RecipeDTO recipeDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		log.info("<Recipe Controller> recipe register POST");
		if(bindingResult.hasErrors()) {
			log.info("has errors......");
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			return "redirect:/recipe/register";
		}
		log.info(recipeDTO);
		Long rno = recipeService.register(recipeDTO);
		redirectAttributes.addFlashAttribute("result", rno);
		return "redirect:/recipe/list";
	}
}
