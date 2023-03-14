package kr.co.javacafe.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KioskDTO {

	private Long kno;
	private String kid;
	private Long rno;
	private Long kccount;
	private Long kprice;
	private String cphone;
	private LocalDateTime regDate;
	private LocalDateTime modDate;	
}
