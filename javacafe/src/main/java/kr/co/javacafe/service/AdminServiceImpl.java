package kr.co.javacafe.service;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.javacafe.domain.Admin;
import kr.co.javacafe.domain.AdminRole;
import kr.co.javacafe.dto.AdminJoinDTO;
import kr.co.javacafe.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
	
	private final ModelMapper modelMapper;
	private final LoginRepository loginRepository;
	private final PasswordEncoder passwordEncoder;
	
	
	
	@Override
	public void join(AdminJoinDTO joinDTO) throws MidExistException{
		String id = joinDTO.getId();
		boolean exist = loginRepository.existsById(id);
		if(exist) {
			throw new MidExistException();
		}
		
		Admin admin = modelMapper.map(joinDTO,Admin.class);
		admin.change(passwordEncoder.encode(joinDTO.getPw()));
		admin.addRole(AdminRole.ADMIN);
		log.info("==========================");
		log.info(admin);
		log.info(admin.getRoleSet());
		
		loginRepository.save(admin);
		
	}
	

}
