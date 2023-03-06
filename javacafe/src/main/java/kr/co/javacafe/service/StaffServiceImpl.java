package kr.co.javacafe.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import kr.co.javacafe.domain.Staff;
import kr.co.javacafe.dto.PageRequestDTO;
import kr.co.javacafe.dto.PageResponseDTO;
import kr.co.javacafe.dto.StaffDTO;
import kr.co.javacafe.dto.StaffImageDTO;
import kr.co.javacafe.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor

@Transactional
public class StaffServiceImpl implements StaffService{

	private final ModelMapper modelMapper;
	
	private final StaffRepository staffRepository;
	
	@Override
	public Long register(StaffDTO staffDTO) {
		Staff staff = modelMapper.map(staffDTO, Staff.class);
		
		Long sno = staffRepository.save(staff).getSno();
		
		return sno;
	}

	@Override
	public StaffDTO readOne(Long sno) {
		Optional<Staff> result = staffRepository.findById(sno);
		
		Staff staff = result.orElseThrow();
		
		StaffDTO staffDTO = modelMapper.map(staff, StaffDTO.class);
		return staffDTO;
	}

	@Override
	public void modify(StaffDTO staffDTO) {
		Optional<Staff> result = staffRepository.findById(staffDTO.getSno());
		
		Staff staff = result.orElseThrow();
		
		staff.change(staffDTO.getSaddr(), staffDTO.getSphone(), staffDTO.getSname(), staffDTO.getSwork());

		staffRepository.save(staff);
	}

	@Override
	public void remove(Long sno) {
		// TODO Auto-generated method stub
		staffRepository.deleteById(sno);
	}

	@Override
	public PageResponseDTO<StaffDTO> list(PageRequestDTO pageRequestDTO) {
		String[] types = pageRequestDTO.getTypes();
		String keyword = pageRequestDTO.getKeyword();
		Pageable pageable = pageRequestDTO.getPageable("sno");
		
		Page<Staff> result = staffRepository.searcAll(types, keyword, pageable);
		
		List<StaffDTO> dtoList = result.getContent().stream()
				.map(staff -> modelMapper.map(staff, StaffDTO.class)).
				collect(Collectors.toList());
		
		return PageResponseDTO.<StaffDTO>withAll()
				.pageRequestDTO(pageRequestDTO)
				.dtoList(dtoList)
				.total((int)result.getTotalElements())
				.build();
	}

	@Override
	public PageResponseDTO<StaffImageDTO> listWithAll(PageRequestDTO pageRequestDTO) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
