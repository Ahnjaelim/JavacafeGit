package kr.co.javacafe.staff;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.co.javacafe.dto.PageRequestDTO;
import kr.co.javacafe.dto.PageResponseDTO;
import kr.co.javacafe.dto.StaffDTO;
import kr.co.javacafe.service.StaffService;
import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class StaffServiceTests {
	@Autowired
	private StaffService staffService;
	
	@Test
	public void testRegister() {
		log.info(staffService.getClass().getName());
		
		StaffDTO staffDTO = StaffDTO.builder()
				.sname("name...")
				.sphone(123)
				.saddr("add...")
				.swork(true)
				.build();
		Long sno = staffService.register(staffDTO);
		
		log.info("sno : " + sno);
	}
	
	@Test
	public void testModify() {
		//필요한 값만 변경.
		StaffDTO staffDTO = StaffDTO.builder()
				.sno(101L)
				.sname("name...101")
				.sphone(101101)
				.saddr("add...101")
				.swork(false)
				.build();
		staffService.modify(staffDTO);
	}
	
	@Test
	public void testList() {
		PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
				.type("tcw")
				.keyword("1")
				.page(1)
				.size(10)
				.build();
		PageResponseDTO<StaffDTO> responseDTO = staffService.list(pageRequestDTO);
		
		log.info(responseDTO);
	}
	
	
}
