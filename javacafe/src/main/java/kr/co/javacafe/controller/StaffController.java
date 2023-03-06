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
import kr.co.javacafe.dto.StaffDTO;
import kr.co.javacafe.service.StaffService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/staff")
@Log4j2
@RequiredArgsConstructor
public class StaffController {

	private final StaffService staffService;
	
	@GetMapping("/list")
	public void list(PageRequestDTO pageRequestDTO, Model model) {
		PageResponseDTO<StaffDTO> responseDTO = staffService.list(pageRequestDTO);
		
		
		log.info(responseDTO);
		
		model.addAttribute("responseDTO" ,responseDTO);
	}
	@GetMapping("/listin")
	public void listin(PageRequestDTO pageRequestDTO, Model model) {
		PageResponseDTO<StaffDTO> responseDTO = staffService.list(pageRequestDTO);
		log.info(responseDTO);
		
		model.addAttribute("responseDTO" ,responseDTO);
	}
	@GetMapping("/listout")
	public void listout(PageRequestDTO pageRequestDTO, Model model) {
		PageResponseDTO<StaffDTO> responseDTO = staffService.list(pageRequestDTO);
		log.info(responseDTO);
		
		model.addAttribute("responseDTO" ,responseDTO);
	}
	
	
	@GetMapping("/register")
		public void registerGET() {
			
		}
	
	
	@PostMapping("/register")
	public String regiserPost(@Valid StaffDTO staffDTO, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		log.info("staff POST.... register");
		
		if(bindingResult.hasErrors()) {
			log.info("has errors....");
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			
			return "redirect:/staff/register";
		}
		
		log.info(staffDTO);
		
		Long sno = staffService.register(staffDTO);
		redirectAttributes.addFlashAttribute("result", sno);
		
		return "redirect:/staff/list";
	}
	
	@GetMapping({"/read", "/modify"})
	public void read(Long sno, PageRequestDTO pageRequestDTO, Model model) {
		StaffDTO staffDTO = staffService.readOne(sno);
		
		log.info(staffDTO);
		
		model.addAttribute("dto",staffDTO);
	}
	
	
	
	@PostMapping("/modify")
	public String modify(PageRequestDTO pageRequestDTO,
						@Valid StaffDTO staffDTO,
						BindingResult bindingResult,
						RedirectAttributes redirectAttributes) {
		log.info("staff modify post...." + staffDTO);
		if(bindingResult.hasErrors()) {
			log.info("has errors...");
			
			String link = pageRequestDTO.getLink();
			
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			
			redirectAttributes.addAttribute("sno", staffDTO.getSno());
			return"redirect:/staff/modify?"+link;			
		}
		staffService.modify(staffDTO);
		
		redirectAttributes.addFlashAttribute("result", "modified");
		redirectAttributes.addAttribute("sno", staffDTO.getSno());
		
		return "redirect:/staff/read";
	}
	@PostMapping("/remove")
	public String remove(Long sno, RedirectAttributes redirectAttributes) {
		log.info("remove post..." + sno);
		
		staffService.remove(sno);
		
		redirectAttributes.addFlashAttribute("result", "removed");
		
		return "redirect:/board/list";
	}
}
