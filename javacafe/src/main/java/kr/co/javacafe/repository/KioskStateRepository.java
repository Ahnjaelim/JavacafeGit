package kr.co.javacafe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.javacafe.domain.KioskState;
import kr.co.javacafe.repository.search.KioskStateSearch;

public interface KioskStateRepository extends JpaRepository<KioskState, Long>, KioskStateSearch {

}
