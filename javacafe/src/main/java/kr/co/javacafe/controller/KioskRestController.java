package kr.co.javacafe.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import kr.co.javacafe.dto.KioskDTO;
import kr.co.javacafe.dto.KioskStateDTO;
import kr.co.javacafe.service.KioskService;
import kr.co.javacafe.service.KioskStateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/kioskrest")
@Log4j2
@RequiredArgsConstructor
public class KioskRestController {

	private final KioskService kioskService;
	private final KioskStateService kioskStateService;
	
	@ApiOperation(value = "Kiosk Register", notes = "POST 방식으로 레코드 등록")
	@PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Long> register(@Valid @RequestBody KioskDTO kioskDTO, BindingResult bindingResult)throws BindException{
		log.info(kioskDTO);
		if(bindingResult.hasErrors()) {
			throw new BindException(bindingResult);
		}
		Map<String, Long> resultMap = new HashMap<>();
		Long kno = kioskService.register(kioskDTO);
		resultMap.put("kno", kno);
		return resultMap;
	}
	@ApiOperation(value = "Kiosk State Register", notes = "POST 방식으로 레코드 등록")
	@PostMapping(value = "/state/", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Long> register(@Valid @RequestBody KioskStateDTO kioskStateDTO, BindingResult bindingResult)throws BindException{
		log.info(kioskStateDTO);
		if(bindingResult.hasErrors()) {
			throw new BindException(bindingResult);
		}
		Map<String, Long> resultMap = new HashMap<>();
		Long ksno = kioskStateService.register(kioskStateDTO);
		resultMap.put("ksno", ksno);
		return resultMap;
	}	
}
