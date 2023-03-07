package kr.co.javacafe.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class StaffuploadFileDTO {

	private List<MultipartFile> files;
}
