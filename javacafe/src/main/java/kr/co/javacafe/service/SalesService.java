package kr.co.javacafe.service;


import kr.co.javacafe.dto.PageRequestDTO;
import kr.co.javacafe.dto.SalesPageRequestDTO;
import kr.co.javacafe.dto.PageResponseDTO;
import kr.co.javacafe.dto.SalesPageResponseDTO;
import kr.co.javacafe.dto.SalesDTO;


public interface SalesService {
	//등록
    Long register(SalesDTO salesDTO);    
    //조회
    SalesDTO readOne(Long sno);    
    //수정
    void modify(SalesDTO salesDTO);    
    //삭제
    void remove(Long sno);    
    
    //검색 / 리스트처리
    PageResponseDTO<SalesDTO> list(PageRequestDTO pageRequestDTO);
    
    //홈페이지용
    SalesPageResponseDTO<SalesDTO> list2(SalesPageRequestDTO pageRequestDTO2);

}
