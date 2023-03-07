package kr.co.javacafe.service;


import kr.co.javacafe.dto.FBoardDTO;
import kr.co.javacafe.dto.PageRequestDTO;
import kr.co.javacafe.dto.PageResponseDTO;

public interface FBoardService {

	Long register(FBoardDTO fboardDTO);

    FBoardDTO readOne(Long fno);

    void modify(FBoardDTO fboardDTO);

    void remove(Long fno);

    PageResponseDTO<FBoardDTO> list(PageRequestDTO pageRequestDTO);

}
