package kr.co.javacafe.controller;

import java.io.File;
<<<<<<< HEAD
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

import kr.co.javacafe.dto.UploadFileDTO;
import kr.co.javacafe.dto.UploadResultDTO;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
=======
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;
>>>>>>> 89534e67b43b8b2c56f1135dec38447f06977ae2

@RestController
@Log4j2
public class UpDownController {

<<<<<<< HEAD
	@Value("${com.example.upload.path}")
	private String uploadPath;
	
	@ApiOperation(value = "Upload POST", notes = "POST방식으로 파일등록")
												//springframwork/http/ import
	@PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public List<UploadResultDTO> upload(UploadFileDTO UploadFileDTO) {
		
		log.info(UploadFileDTO);
		
		if(UploadFileDTO.getFiles() != null) {
			
			final List<UploadResultDTO> list = new ArrayList<>();
			
			UploadFileDTO.getFiles().forEach(multipartFile -> {
				
				String originalName = multipartFile.getOriginalFilename();
				log.info(originalName);
				
				String uuid = UUID.randomUUID().toString();
				
				//nio.Path import
				Path savePath = Paths.get(uploadPath, uuid+"_"+originalName);
				
				boolean image = false;
				
				try {
					multipartFile.transferTo(savePath); // 실제 파일저장
					
					//이미지 파일의 종류라면 (썸네일만들기) tumbnailator라이브러리 사용
					if(Files.probeContentType(savePath).startsWith("image")) {
						File thumbFile = new File(uploadPath, "s_" + uuid+"_"+originalName);
						Thumbnailator.createThumbnail(savePath.toFile(), thumbFile, 200,200);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}//tryend
				
				list.add(UploadResultDTO.builder()
						.uuid(uuid)
						.fileName(originalName)
						.img(image).build()
						);
				
			}); //foreach end
			
			return list;
		} // if end
			
		
		return null;
	}
=======
	@Value("${upload.path}")
	private String uploadPath;
	
	@GetMapping("/view/{fileName}")
	public ResponseEntity<Resource> viewFileGET(@PathVariable String fileName){
		Resource resource = new FileSystemResource(uploadPath+File.separator+fileName);
		String resourceName = resource.getFilename();
		HttpHeaders headers = new HttpHeaders();
		try {
			headers.add("Content-Type", Files.probeContentType(resource.getFile().toPath()));
		} catch(Exception e) {
			return ResponseEntity.internalServerError().build();
		}
		return ResponseEntity.ok().headers(headers).body(resource);
	}	
>>>>>>> 89534e67b43b8b2c56f1135dec38447f06977ae2
}
