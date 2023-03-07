package kr.co.javacafe.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StaffListAllDTO {

	private Long sno;
	
	private String sname; //스탭이름
	private int sphone; // 전화번호
	private String saddr; // 주소
	private Boolean swork; // 출근여부
	private LocalDateTime regDate;
	
	private List<StaffImageDTO> staffImages;
}
