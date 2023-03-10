package kr.co.javacafe.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.javacafe.domain.Sales;
import kr.co.javacafe.dto.AdminDTO;
import kr.co.javacafe.dto.InventoryDTO;
import kr.co.javacafe.dto.PageRequestDTO;
import kr.co.javacafe.dto.PageResponseDTO;
import kr.co.javacafe.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/admin")
@Log4j2
@RequiredArgsConstructor
public class AdminController {
	
	@Autowired
	private final AdminService adminService;
	
	
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	};
	
	//LoginController의 이하 부분 삭제! 필요없어짐.

	@PostMapping("/login")
	public String login(AdminDTO adminDTO) {
	    boolean isValidMember = adminService.isValidMember(adminDTO.getPw());
		    if (isValidMember)
		        return "/home";
	    return "login";
	}
	
		
	
			//관리자 메인
			@GetMapping({"/main","/read","/modify"})
			public void read(long ano, PageRequestDTO pageRequestDTO, Model model) {
				
				
				AdminDTO adminDTO = adminService.readOne(ano);
				log.info(adminDTO);
				model.addAttribute("dto1",adminDTO);
				
			}
	 
	
	
	
		//관리자 리스트불러오기	
		@GetMapping( "/list" )
		public void list(PageRequestDTO pageRequestDTO, Model model) {
			
			PageResponseDTO<AdminDTO> responseDTO = adminService.list(pageRequestDTO);
			model.addAttribute("dto",responseDTO);
		}			
		
		//관리자 정보 수정 post
		@PostMapping("/modify")
		public String modify(PageRequestDTO pageRequestDTO,
							@Valid AdminDTO adminDTO,
							BindingResult bindingResult,
							RedirectAttributes redirectAttributes,
							HttpServletRequest request) {
			log.info("admin modify post================>" + adminDTO);
			
			//오류발생시
			if(bindingResult.hasErrors()) {
				log.info("has errors===============");
				String link = pageRequestDTO.getLink(); //url고정
				//에러발생시 모든 에러는 errors라는 이름으로 수정페이지로 이동시킴
				
				redirectAttributes.addFlashAttribute("errors",bindingResult.getAllErrors());
				redirectAttributes.addAttribute("ano", adminDTO.getAno());
				
				//link를 통해 기존의 모든 조건(url)을 붙여서 redirect
				return "redirect:/admin/main";
				
			} //errors if end
				
				
			//수정메소드
			adminService.modify(adminDTO);
			redirectAttributes.addFlashAttribute("result2", "modified");
			redirectAttributes.addAttribute("Ano",adminDTO.getAno());
			
			return "redirect:/admin/main";
		}

			
					

			//게시글 등록하기 get-post
			@GetMapping("/register")
			public void registerGET() {
				
			}
			
			@PostMapping("/register")
			public String registerPost(@Valid AdminDTO adminDTO, BindingResult bindingResult, 
					RedirectAttributes redirectAttributes, HttpServletRequest request) {
				log.info("admin POST register==============");
				
				if(bindingResult.hasErrors()) { //오류 발생할경우
					log.info("has errors=================");
					redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
					//error가 발생할경우 error라는 이름으로 RedirectAtrributes에 추가해서 전송한다 라는 코드
					
					return "redirect:/admin/main";
				}
				
				//정상적으로 생성될경우
				log.info(adminDTO);
				long ano = adminService.register(adminDTO);
				redirectAttributes.addFlashAttribute("result1",ano);
				return "redirect:/admin/main";
			}
			
			//삭제 처리
			@PostMapping("/remove")
			public String remove(long ano, RedirectAttributes redirectAttributes) {
				log.info("remove post================== " + ano);
				
				adminService.remove(ano);
				redirectAttributes.addFlashAttribute("result3","removed");
				return "redirect:/admin/main";
			}

		
	
	
	
}
