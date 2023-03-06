package kr.co.javacafe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UploadResultDTO {

	private String uuid;
	private String fileName;
	private boolean img;
	
	public String getLink() { //getlink는 나중에 JSON으로 처리될때는 link라는 속성으로 자동처리됨
		if(img) {
			return "s_"+uuid+"_"+fileName; //이미지인경우 썸네일
		} else {
			return uuid+"_"+fileName;
		}
	}
	
}
