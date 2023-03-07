package kr.co.javacafe.service;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.co.javacafe.dto.FBoardDTO;
import kr.co.javacafe.dto.PageRequestDTO;
import kr.co.javacafe.dto.PageResponseDTO;
import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class FBoardServiceTest {

	@Autowired
	private FBoardService fboardService;
	
	@Test
	public void testRegister() {
		
		log.info(fboardService.getClass().getName());
		
		FBoardDTO fboardDTO = FBoardDTO.builder()
				.ftitle("Sample title...")
				.fcontent("Sample content....")
				.fwriter("Sample writer....")
				.build();
		
		Long fno = fboardService.register(fboardDTO);
		
		log.info("bno: " + fno);				
	}
	
	@Test
	public void testModify() {
		
		FBoardDTO fboardDTO = FBoardDTO.builder()
				.fno(101L)
				.ftitle("updated....101")
				.fcontent("Updated content 101...")
				.build();
		
		fboardService.modify(fboardDTO);
	}
	
	@Test
	public void testList() {
		
		PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
				.type("tcw")
				.keyword("1")
				.page(1)
				.size(10)
				.build();
		
		PageResponseDTO<FBoardDTO> responseDTO = fboardService.list(pageRequestDTO);
		
		log.info(responseDTO);
	}
}
