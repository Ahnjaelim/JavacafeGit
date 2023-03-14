package kr.co.javacafe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.javacafe.domain.Kiosk;

public interface KioskRepository extends JpaRepository<Kiosk, Long> {

}
