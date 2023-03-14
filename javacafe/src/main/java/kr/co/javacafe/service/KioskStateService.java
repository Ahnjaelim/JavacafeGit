package kr.co.javacafe.service;

import kr.co.javacafe.dto.BoardDTO;
import kr.co.javacafe.dto.KioskStateDTO;
import kr.co.javacafe.dto.PageRequestDTO;
import kr.co.javacafe.dto.PageResponseDTO;

public interface KioskStateService {

	Long register(KioskStateDTO kioskStateDTO);
	
	PageResponseDTO<KioskStateDTO> list(PageRequestDTO pageRequestDTO);
}
