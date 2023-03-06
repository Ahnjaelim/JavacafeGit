package kr.co.javacafe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kr.co.javacafe.domain.Inventory;
import kr.co.javacafe.repository.search.InvenSearch;

public interface InventoryRepository extends JpaRepository<Inventory, Long>, InvenSearch {
	
	@Query(value= "select now()", nativeQuery = true)
	String getTime();

}
