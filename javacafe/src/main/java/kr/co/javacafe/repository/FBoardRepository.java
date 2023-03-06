package kr.co.javacafe.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import kr.co.javacafe.domain.FBoard;
import kr.co.javacafe.repository.search.FBoardSearch;

public interface FBoardRepository extends JpaRepository<FBoard, Long>, FBoardSearch {

}
