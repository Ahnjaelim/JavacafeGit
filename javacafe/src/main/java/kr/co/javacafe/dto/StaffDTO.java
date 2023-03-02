package kr.co.javacafe.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StaffDTO {
	private Long sno; //스탭 번호
	
	@NotEmpty
	@Size(min = 3, max = 100)
	private String sname; //스탭이름
	@NotNull
	private int sphone; // 전화번호
	
	@NotEmpty
	private String saddr; // 주소
	@NotNull
	private Boolean swork; // 출근여부
	private LocalDateTime regDate;
	private LocalDateTime modDate;
}
