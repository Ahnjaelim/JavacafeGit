package kr.co.javacafe.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kr.co.javacafe.domain.Admin;
 
import kr.co.javacafe.repository.search.AdminSearch;
 

public interface AdminRepository extends JpaRepository<Admin, Long>, AdminSearch {

	
}

