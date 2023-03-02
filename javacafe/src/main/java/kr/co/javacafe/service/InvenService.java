package kr.co.javacafe.service;


import kr.co.javacafe.dto.InventoryDTO;
import kr.co.javacafe.dto.PageRequestDTO;
import kr.co.javacafe.dto.PageResponseDTO;

public interface InvenService {

    Long register(InventoryDTO inventoryDTO);

    InventoryDTO readOne(Long ino);

    void modify(InventoryDTO inventoryDTODTO);

    void remove(Long ino);

    PageResponseDTO<InventoryDTO> list(PageRequestDTO pageRequestDTO);
}