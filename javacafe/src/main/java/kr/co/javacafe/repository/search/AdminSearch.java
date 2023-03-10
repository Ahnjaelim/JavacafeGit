package kr.co.javacafe.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import kr.co.javacafe.domain.Admin;



public interface AdminSearch {

    Page<Admin> asearch(Pageable pageable);

    Page<Admin> asearchAll(String[] types, String keyword, Pageable pageable);

}