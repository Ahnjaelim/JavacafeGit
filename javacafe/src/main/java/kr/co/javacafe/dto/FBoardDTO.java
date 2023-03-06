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
public class FBoardDTO {
	
	private Long fno;
	private String ftitle;
	private String fcontent;
	private String fwriter;
	private LocalDateTime regDate;
	private LocalDateTime modDate;

}
