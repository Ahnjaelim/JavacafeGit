package kr.co.javacafe.staff;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import kr.co.javacafe.domain.Staff;
import kr.co.javacafe.domain.Staffimage;
import kr.co.javacafe.repository.StaffRepository;
import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class StaffRepositoryTests {
	
	@Autowired
	private StaffRepository staffRepository;

	
	@Test
	public void testInsert() {
		IntStream.rangeClosed(1, 100).forEach(i -> {
			Staff staff = Staff.builder()
					.sname("name..." + i)
					.sphone(i)
					.saddr("add..." + i)
					.swork(true)
					.build();
			
			Staff result = staffRepository.save(staff);
			log.info("SNO: " + result.getSno());
		});
	}
	
	@Test
	public void testSelect() {
		Long sno = 50L;
		
		Optional<Staff> result = staffRepository.findById(sno);
		
		Staff staff = result.orElseThrow();
		
		log.info(staff);
	}
	
	@Test
	public void testUpdate() {
		Long sno = 50L;
		
		Optional<Staff> result = staffRepository.findById(sno);
		Staff staff = result.orElseThrow();
		
		staff.change("박성배", 010, "경기도", false);
		
		staffRepository.save(staff);
	}
	
	@Test
	public void testDelete() {
		Long sno = 1L;
		
		staffRepository.deleteById(sno);
	}
	
	@Test
	public void testPaging() {
		//1 page order by sno
		
		Pageable pageable = PageRequest.of(0, 10, Sort.by("sno").descending());
		
		Page<Staff> result = staffRepository.findAll(pageable);
		
		log.info("total count: " +result.getTotalElements());
		log.info("total pages: " +result.getTotalPages());
		log.info("page number: " +result.getNumber());
		log.info("page size: " +result.getSize());
		
		List<Staff> todoList = result.getContent();
		
		todoList.forEach(staff -> log.info(staff));
	}
	
	@Test
	public void testSearch1() {
		//2 page order by sno desc
		Pageable pageable = PageRequest.of(1, 10, Sort.by("sno").descending());
		
		staffRepository.search1(pageable);
	}
	
	@Test
	public void testSearchAll() {
		String[] types = {"t", "c", "w"};
		String keyword = "1";
		Pageable pageable = PageRequest.of(0, 10, Sort.by("sno").descending());
		Page<Staff> result = staffRepository.searcAll(types, keyword, pageable);
		
	}
	
	@Test
	public void testSearchAll2() {
		String[] types = {"t","c","w"};
		
		String keyword = "1";
		
		Pageable pageable = PageRequest.of(0, 10, Sort.by("sno").descending());
		
		Page<Staff> result = staffRepository.searcAll(types, keyword, pageable);
		
		//total pages
		log.info(result.getTotalPages());
		
		//page size
		log.info(result.getSize());
		
		//pageNumber
		log.info(result.getNumber());
		
		//prev next
		log.info(result.hasPrevious() + ": " +result.hasNext());
	}
	
	@Test
	public void testInsertWithImages() {
		Staff staff = Staff.builder()
				.sname("name...")
				.sphone(010)
				.saddr("파일테스트")
				.swork(true)
				.build();
		
		for(int i = 0; i<3; i++) {
			staff.addImage(UUID.randomUUID().toString(), "file"+i+".jpg");
		}
		staffRepository.save(staff);
	}
	@Test
	public void testReadWithImages() {
		Optional<Staff> result = staffRepository.findByIdWithImages(107L);
		
		Staff staff = result.orElseThrow();
		
		log.info(staff);
		log.info("----------");
		for(Staffimage staffimage : staff.getImageSet()) {
			log.info(staffimage);
		}
	} //수정필요
	
	

}
