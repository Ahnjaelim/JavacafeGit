package kr.co.javacafe.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kr.co.javacafe.domain.Staff;
import kr.co.javacafe.repository.search.StaffSearch;

public interface StaffRepository extends JpaRepository<Staff, Long>, StaffSearch{
	
	@Query(value = "select now()", nativeQuery = true)
	String getTime();
	
	@EntityGraph(attributePaths = {"imageSet"})
	@Query("select b from Staff b where b.sno =:sno")
	Optional<Staff> findByIdWithImages(Long sno);
}
