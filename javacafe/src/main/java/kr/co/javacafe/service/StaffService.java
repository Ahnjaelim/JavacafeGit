package kr.co.javacafe.service;

import org.springframework.data.domain.PageRequest;

import kr.co.javacafe.dto.PageRequestDTO;
import kr.co.javacafe.dto.PageResponseDTO;
import kr.co.javacafe.dto.StaffDTO;

public interface StaffService {
	Long register(StaffDTO staffDTO);
	
	StaffDTO readOne(Long sno);
	
	void modify(StaffDTO staffDTO);
	
	void remove(Long sno);
	
	PageResponseDTO<StaffDTO> list(PageRequestDTO pageRequestDTO);
}
