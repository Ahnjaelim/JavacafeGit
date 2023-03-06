package kr.co.javacafe.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import kr.co.javacafe.domain.Inventory;
import kr.co.javacafe.dto.InventoryDTO;
import kr.co.javacafe.dto.PageRequestDTO;
import kr.co.javacafe.dto.PageResponseDTO;

import kr.co.javacafe.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class InvenServiceImpl implements InvenService{

    private final ModelMapper modelMapper;
    private final InventoryRepository inventoryRepository;
    

    @Override//등록
    public Long register(InventoryDTO inventoryDTO) {

         Inventory inventory = modelMapper.map(inventoryDTO, Inventory.class);

        Long ino = inventoryRepository.save(inventory).getIno();

        return ino;
    }

    
    
    @Override // 조회/상세보기
    public InventoryDTO readOne(Long ino) {

        Optional<Inventory> result = inventoryRepository.findById(ino);

        Inventory inventory = result.orElseThrow();

        InventoryDTO inventoryDTO = modelMapper.map(inventory, InventoryDTO.class);

        return inventoryDTO;
    }
    
    //수정
    @Override
    public void modify(InventoryDTO inventoryDTO) {

        Optional<Inventory> result = inventoryRepository.findById(inventoryDTO.getIno());

        Inventory inventory = result.orElseThrow();
        
        inventory.change(
        inventoryDTO.getIname(),inventoryDTO.getIprice(),inventoryDTO.getIclass(),
		inventoryDTO.getIcontent(),inventoryDTO.getIcount(),inventoryDTO.getIstate());
        
        inventoryRepository.save(inventory);

    }

    @Override
    public void remove(Long ino) {

       inventoryRepository.deleteById(ino);

    }

//    @Override
//    public PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO) {
//
//        String[] types = pageRequestDTO.getTypes();
//        String keyword = pageRequestDTO.getKeyword();
//        Pageable pageable = pageRequestDTO.getPageable("bno");
//
//        Page<Board> result = boardRepository.searchAll(types, keyword, pageable);
//
//        return null;
//    }

    @Override
    public PageResponseDTO<InventoryDTO> list(PageRequestDTO pageRequestDTO) {

        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("ino");

        Page<Inventory> result = inventoryRepository.isearchAll(types, keyword, pageable);

        List<InventoryDTO> dtoList = result.getContent().stream()
                .map(inventory -> modelMapper.map(inventory,InventoryDTO.class)).collect(Collectors.toList());


        return PageResponseDTO.<InventoryDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();

    }


}