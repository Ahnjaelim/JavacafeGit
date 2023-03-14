package kr.co.javacafe.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import kr.co.javacafe.domain.Board;
import kr.co.javacafe.domain.Kiosk;
import kr.co.javacafe.domain.KioskState;
import kr.co.javacafe.dto.BoardDTO;
import kr.co.javacafe.dto.KioskStateDTO;
import kr.co.javacafe.dto.PageRequestDTO;
import kr.co.javacafe.dto.PageResponseDTO;
import kr.co.javacafe.repository.KioskStateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class KioskStateServiceImpl implements KioskStateService {

	private final KioskStateRepository kioskStateRepository;
	private final ModelMapper modelMapper;

	@Override
	public Long register(KioskStateDTO kioskStateDTO) {
		KioskState kioskState = modelMapper.map(kioskStateDTO, KioskState.class);
		Long ksno = kioskStateRepository.save(kioskState).getKsno();
		return ksno;
	}

	@Override
	public PageResponseDTO<KioskStateDTO> list(PageRequestDTO pageRequestDTO) {
        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("ksno");

        Page<KioskState> result = kioskStateRepository.searchAll(types, keyword, pageable);

        List<KioskStateDTO> dtoList = result.getContent().stream()
                .map(entity -> modelMapper.map(entity,KioskStateDTO.class)).collect(Collectors.toList());


        return PageResponseDTO.<KioskStateDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
	}
	
}
