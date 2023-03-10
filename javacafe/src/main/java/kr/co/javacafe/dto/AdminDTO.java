package kr.co.javacafe.dto;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminDTO {
	
	private Long ano;
	
	@NotEmpty
	private String id;
	
	@NotEmpty
	private String pw;
	
}
