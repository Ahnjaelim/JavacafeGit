package kr.co.javacafe.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.javacafe.dto.FBoardDTO;
import kr.co.javacafe.dto.PageRequestDTO;
import kr.co.javacafe.dto.PageResponseDTO;
import kr.co.javacafe.service.FBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/fboard")
@Log4j2
@RequiredArgsConstructor
public class FBoardController {

	 private final FBoardService fboardService;

	    @GetMapping("/flist")
	    public void list(PageRequestDTO pageRequestDTO, Model model){

	        PageResponseDTO<FBoardDTO> responseDTO = fboardService.list(pageRequestDTO);

	        log.info(responseDTO);

	        model.addAttribute("responseDTO", responseDTO);

	    }

	    @GetMapping("/fregister")
	    public void registerGET(){

	    }

	    @PostMapping("/fregister")
	    public String registerPost(@Valid FBoardDTO fboardDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){

	        log.info("fboard POST register.......");

	        if(bindingResult.hasErrors()) {
	            log.info("has errors.......");
	            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors() );
	            return "redirect:/fboard/fregister";
	        }

	        log.info(fboardDTO);

	        Long fno  = fboardService.register(fboardDTO);

	        redirectAttributes.addFlashAttribute("result", fno);

	        return "redirect:/fboard/flist";
	    }

	    @GetMapping({"/fread", "/fmodify"})
	    public void read(Long fno, PageRequestDTO pageRequestDTO, Model model){

	        FBoardDTO fboardDTO = fboardService.readOne(fno);

	        log.info(fboardDTO);

	        model.addAttribute("dto", fboardDTO);

	    }

	    @PostMapping("/fmodify")
	    public String modify( PageRequestDTO pageRequestDTO,
	                          @Valid FBoardDTO fboardDTO,
	                          BindingResult bindingResult,
	                          RedirectAttributes redirectAttributes){

	        log.info("board modify post......." + fboardDTO);

	        if(bindingResult.hasErrors()) {
	            log.info("has errors.......");

	            String link = pageRequestDTO.getLink();

	            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors() );

	            redirectAttributes.addAttribute("fno", fboardDTO.getFno());

	            return "redirect:/fboard/fmodify?"+link;
	        }

	        fboardService.modify(fboardDTO);

	        redirectAttributes.addFlashAttribute("result", "modified");

	        redirectAttributes.addAttribute("fno", fboardDTO.getFno());

	        return "redirect:/fboard/fread";
	    }


	    @PostMapping("/fremove")
	    public String remove(Long fno, RedirectAttributes redirectAttributes) {

	        log.info("remove post.. " + fno);

	        fboardService.remove(fno);

	        redirectAttributes.addFlashAttribute("result", "removed");

	        return "redirect:/fboard/flist";

	    }

}
