package kr.co.javacafe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StaffUploadResultDTO {

	private String uuid;
	
	private String fileName;
	
	private boolean img;
	
	public String getLink(){
		if(img) {
			return "s_"+uuid+"_" + fileName; //이미지인 경우 섬네임
		}else {
			return uuid+"_"+fileName;
		}
	}
	
}
