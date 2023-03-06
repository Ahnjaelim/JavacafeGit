package kr.co.javacafe.service;


import kr.co.javacafe.dto.InventoryDTO;
import kr.co.javacafe.dto.PageRequestDTO;
import kr.co.javacafe.dto.PageResponseDTO;

public interface InvenService {
	//등록
    Long register(InventoryDTO inventoryDTO);
    
    //조회
    InventoryDTO readOne(Long ino);
    
    //수정
    void modify(InventoryDTO inventoryDTODTO);
    
    //삭제
    void remove(Long ino);
    
    //검색 / 리스트처리
    PageResponseDTO<InventoryDTO> list(PageRequestDTO pageRequestDTO);
}