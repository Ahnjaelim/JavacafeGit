package kr.co.javacafe.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.javacafe.dto.InventoryDTO;
import kr.co.javacafe.dto.PageRequestDTO;
import kr.co.javacafe.dto.PageResponseDTO;
import kr.co.javacafe.service.InvenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/inven")
@Log4j2
@RequiredArgsConstructor
public class InventoryController {

	
	private final InvenService invenService;
	
	
	
	
	
	//검색 및 list 메소드
	@GetMapping("/list")
	public void list(PageRequestDTO pageRequestDTO, Model model) {
		 
		PageResponseDTO<InventoryDTO> responseDTO = invenService.list(pageRequestDTO);
				
		log.info(responseDTO);
				 
		model.addAttribute("responseDTO",responseDTO);
		//pageRequestDTO와 pageResponseDTO객체가 view로 전달됨
				
	}			
	
	//게시글 등록하기 get-post
		@GetMapping("/register")
		public void registerGET() {
			
		}
		
		@PostMapping("/register")
		public String registerPost(@Valid InventoryDTO inventoryDTO, BindingResult bindingResult, 
				RedirectAttributes redirectAttributes) {
			log.info("inven POST register==============");
			
			if(bindingResult.hasErrors()) { //오류 발생할경우
				log.info("has errors=================");
				redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
				//error가 발생할경우 error라는 이름으로 RedirectAtrributes에 추가해서 전송한다 라는 코드
				
				return "redirect:/inven/register";
			}
			
			//정상적으로 생성될경우
			log.info(inventoryDTO);
			long ino = invenService.register(inventoryDTO);
			redirectAttributes.addFlashAttribute("result",ino);
			return "redirect:/inven/list";
		}
	
	
}
