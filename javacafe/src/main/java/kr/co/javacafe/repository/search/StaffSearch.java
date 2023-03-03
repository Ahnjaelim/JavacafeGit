package kr.co.javacafe.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import kr.co.javacafe.domain.Staff;

public interface StaffSearch {
	Page<Staff> search1(Pageable pageable);
	
	Page<Staff> searcAll(String[] types, String keyword, Pageable pageable);
}
