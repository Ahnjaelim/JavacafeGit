package kr.co.javacafe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.javacafe.domain.Board;
import kr.co.javacafe.repository.search.BoardSearch;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {


}