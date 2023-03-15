package kr.co.javacafe.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class ShopStateRepositoryTest {

	@Autowired
	private ShopStateRepository shopStateRepository;
	
	@Test
	public void countTodayOrderTest() {
		log.info(shopStateRepository.countTodayOrder("20230315"));
	}
}
