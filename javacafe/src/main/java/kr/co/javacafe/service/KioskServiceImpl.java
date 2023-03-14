package kr.co.javacafe.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import kr.co.javacafe.domain.Kiosk;
import kr.co.javacafe.dto.KioskDTO;
import kr.co.javacafe.repository.KioskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class KioskServiceImpl implements KioskService {

	private final KioskRepository kioskRepository;
	private final ModelMapper modelMapper;
	
	@Override
	public Long register(KioskDTO kioskDTO) {
		Kiosk kiosk = modelMapper.map(kioskDTO, Kiosk.class);
		Long kno = kioskRepository.save(kiosk).getKno();
		return kno;
	}
	
}
