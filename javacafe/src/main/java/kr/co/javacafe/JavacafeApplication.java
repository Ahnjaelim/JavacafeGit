package kr.co.javacafe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class JavacafeApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavacafeApplication.class, args);
	}

}
