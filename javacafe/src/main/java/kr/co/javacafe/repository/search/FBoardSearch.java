package kr.co.javacafe.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import kr.co.javacafe.domain.Board;
import kr.co.javacafe.domain.FBoard;

public interface FBoardSearch {

	Page<FBoard> search1(Pageable pageable);

    Page<FBoard> searchAll(String[] types, String keyword, Pageable pageable);
}
