package kr.co.javacafe.service;

import kr.co.javacafe.dto.AdminDTO;
import kr.co.javacafe.dto.PageRequestDTO;
import kr.co.javacafe.dto.PageResponseDTO;


public interface AdminService {
	//등록
    Long register(AdminDTO adminDTO);    
    //조회
    AdminDTO readOne(Long ano);    
    //수정
    void modify(AdminDTO adminDTO);    
    //삭제
    void remove(Long ano);
    //검색 / 리스트처리
    PageResponseDTO<AdminDTO> list(PageRequestDTO pageRequestDTO);
}
