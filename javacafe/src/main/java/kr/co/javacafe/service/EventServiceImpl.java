package kr.co.javacafe.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import kr.co.javacafe.domain.Event;
import kr.co.javacafe.dto.PageRequestDTO;
import kr.co.javacafe.dto.PageResponseDTO;
import kr.co.javacafe.dto.EventDTO;
import kr.co.javacafe.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class EventServiceImpl implements EventService {

	private final ModelMapper modelMapper;
	private final EventRepository eventRepository;
		
	// 작성
	@Override
	public Long register(EventDTO eventDTO) {

		Event event = modelMapper.map(eventDTO, Event.class);
		Long eno = eventRepository.save(event).getEno();
		return eno;
	}
	
	// 조회
	@Override
	public EventDTO readOne(Long eno) {
		
		Optional<Event> result = eventRepository.findById(eno);
		
		Event event = result.orElseThrow();
		
		EventDTO eventDTO = modelMapper.map(event, EventDTO.class);
		
		return eventDTO;
	}
	
	// 수정
	@Override
	public void modify(EventDTO eventDTO) {
		
		Optional<Event> result = eventRepository.findById(eventDTO.getEno());
		Event event = result.orElseThrow();
		
		// 제목 & 내용만 수정 가능
		event.change(eventDTO.getEtitle(), eventDTO.getEcontent());
		eventRepository.save(event);
	}

	// 삭제
	@Override
	public void remove(Long eno) {
		
		eventRepository.deleteById(eno);
		
	}

	// 목록 & 검색 기능
	@Override
	public PageResponseDTO<EventDTO> list(PageRequestDTO pageRequestDTO) {
		
		String[] types = pageRequestDTO.getTypes();
		String keyword = pageRequestDTO.getKeyword();
		Pageable pageable = pageRequestDTO.getPageable("eno");
		
		Page<Event> result = eventRepository.searchAll(types, keyword, pageable);
		
		List<EventDTO> dtoList = result.getContent().stream()
				.map(event -> modelMapper.map(event, EventDTO.class))
				.collect(Collectors.toList());
		
		return PageResponseDTO.<EventDTO>withAll()
				.pageRequestDTO(pageRequestDTO)
				.dtoList(dtoList)
				.total((int)result.getTotalElements())
				.build();
	}
	
//	// 이미지 업로드
//	public String fileUpload(MultipartFile file, HttpServletRequest request) {
//		Long unixtime = System.currentTimeMillis();
//		String fileName = unixtime + "_" + file.getOriginalFilename();
//		String path = "C:\\upload\\"; // 파일 업로드되는 경로
//		try {
//			File folder = new File(path);
//			if (!folder.exists()) folder.mkdirs();
//			File destination = new File(path + File.separator + fileName);
//			file.transferTo(destination);
//			System.out.println(destination);
//			System.out.println("업로드 성공");
//		}catch (Exception e) {
//			System.out.println("업로드 실패");
//		}
//		return fileName;
//	}
//	

}
