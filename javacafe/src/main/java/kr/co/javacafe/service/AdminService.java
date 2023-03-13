package kr.co.javacafe.service;

import kr.co.javacafe.dto.AdminJoinDTO;

public interface AdminService {
	static class MidExistException extends Exception{
	}
	void join(AdminJoinDTO joinDTO)throws MidExistException;
	
	
    
    
    
}
