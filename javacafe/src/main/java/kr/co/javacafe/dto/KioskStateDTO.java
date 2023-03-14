package kr.co.javacafe.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KioskStateDTO {

	private Long ksno;
	private String kid;
	private String cphone;
	private Long ksstate;
	private Long kstotal;
	private LocalDateTime regDate;
	private LocalDateTime modDate;		
}
