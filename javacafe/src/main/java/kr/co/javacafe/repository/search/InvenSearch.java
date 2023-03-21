package kr.co.javacafe.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

 
import kr.co.javacafe.domain.Inventory;
 

public interface InvenSearch {

    Page<Inventory> isearch(Pageable pageable);

    Page<Inventory> isearchAll(String[] types, String keyword, Pageable pageable);

 
}