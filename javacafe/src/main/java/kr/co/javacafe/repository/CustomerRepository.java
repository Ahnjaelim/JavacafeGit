package kr.co.javacafe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kr.co.javacafe.domain.Customer;

import kr.co.javacafe.repository.search.CustomerSearch;

public interface CustomerRepository extends JpaRepository<Customer, Long>, CustomerSearch{
	
}
