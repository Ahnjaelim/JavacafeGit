package kr.co.javacafe.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import kr.co.javacafe.domain.Customer;

public interface CustomerSearch {

    Page<Customer> search(Pageable pageable);

    Page<Customer> searchAll(String[] types, String keyword, Pageable pageable);
}
