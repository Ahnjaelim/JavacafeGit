package kr.co.javacafe.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import kr.co.javacafe.domain.Admin;
import kr.co.javacafe.domain.Inventory;
import kr.co.javacafe.domain.Sales;
import kr.co.javacafe.dto.AdminDTO;
import kr.co.javacafe.dto.InventoryDTO;
import kr.co.javacafe.dto.PageRequestDTO;
import kr.co.javacafe.dto.PageResponseDTO;
import kr.co.javacafe.dto.SalesDTO;
import kr.co.javacafe.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class AdminServiceImpl implements AdminService {
	
	private final ModelMapper modelMapper;
	private final AdminRepository adminRepository;
	
	//등록
	@Override
	public Long register(AdminDTO adminDTO) {
		Admin admin = modelMapper.map(adminDTO, Admin.class);
		Long ano = adminRepository.save(admin).getAno();
		return ano;
	}
	
	//조회 상세보기
	@Override
	public AdminDTO readOne(Long ano) {
		Optional<Admin> result = adminRepository.findById(ano);
		Admin admin = result.orElseThrow();
		AdminDTO adminDTO = modelMapper.map(admin, AdminDTO.class);
		return adminDTO;
	}

	@Override
	public void modify(AdminDTO adminDTO) {
		Optional<Admin> result = adminRepository.findById(adminDTO.getAno());
		Admin admin = result.orElseThrow();
		
		admin.change(adminDTO.getId(),
					 adminDTO.getPw());
					 
		adminRepository.save(admin);
	}

	@Override
	public void remove(Long ano) {
		adminRepository.deleteById(ano);
		
	}

	@Override
	public PageResponseDTO<AdminDTO> list(PageRequestDTO pageRequestDTO) {
        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("ano");

        Page<Admin> result = adminRepository.asearchAll(types, keyword, pageable);

        List<AdminDTO> dtoList = result.getContent().stream()
                .map(admin -> modelMapper.map(admin,AdminDTO.class)).collect(Collectors.toList());


        return PageResponseDTO.<AdminDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
	}


 



	

	

}
