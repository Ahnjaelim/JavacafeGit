package kr.co.javacafe.service;


import kr.co.javacafe.dto.EventDTO;
import kr.co.javacafe.dto.PageRequestDTO;
import kr.co.javacafe.dto.PageResponseDTO;

public interface EventService {
	// 작성
	Long register(EventDTO eventDTO);
	// 조회
	EventDTO readOne(Long eno);
	// 수정
	void modify(EventDTO eventDTO);
	// 삭제
	void remove(Long eno);
	// 목록 & 검색 기능
	PageResponseDTO<EventDTO> list(PageRequestDTO pageRequestDTO);
}
