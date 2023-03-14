package kr.co.javacafe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.javacafe.dto.KioskStateDTO;
import kr.co.javacafe.dto.PageRequestDTO;
import kr.co.javacafe.dto.PageResponseDTO;
import kr.co.javacafe.service.KioskService;
import kr.co.javacafe.service.KioskStateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/kiosk")
@Log4j2
@RequiredArgsConstructor
public class KioskController {
	
	private final KioskService kioskService;
	private final KioskStateService kioskStateService;
	
	@GetMapping("/list")
	public void listGET() {	
	}
	
	@GetMapping("/statelist")
	public void statelistGET(PageRequestDTO pageRequestDTO, Model model) {
        PageResponseDTO<KioskStateDTO> responseDTO = kioskStateService.list(pageRequestDTO);
        log.info(responseDTO);
        model.addAttribute("responseDTO", responseDTO);		
	}
	
	@GetMapping("/stateread")
	public void statereadGET(Model model){
		
	}
}