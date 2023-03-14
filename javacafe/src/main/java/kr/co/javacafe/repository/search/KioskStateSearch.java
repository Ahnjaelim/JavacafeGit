package kr.co.javacafe.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import kr.co.javacafe.domain.KioskState;

public interface KioskStateSearch {

	Page<KioskState> searchAll(String[] types, String keyword, Pageable pageable);
}
