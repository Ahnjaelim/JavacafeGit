package kr.co.javacafe.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import kr.co.javacafe.dto.StaffUploadResultDTO;
import kr.co.javacafe.dto.StaffuploadFileDTO;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@Log4j2
public class StaffUpDownController {
	
	@Value("${upload.path}")// import 시에 springframework로 시작하는 value
	private String uploadPath;

	@ApiOperation(value="Upload POST", notes = "POST 방식으로 파일 등록")
	@PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public List<StaffUploadResultDTO> upload(StaffuploadFileDTO staffuploadFileDTO) {
		log.info(staffuploadFileDTO);
		
		if(staffuploadFileDTO.getFiles() != null) {
			
			final List<StaffUploadResultDTO> list = new ArrayList<>();
			
			staffuploadFileDTO.getFiles().forEach(multipartFile->{
				
				String orginalName = multipartFile.getOriginalFilename();
				log.info(orginalName);
				
				String uuid = UUID.randomUUID().toString();
				
				Path savePath = Paths.get(uploadPath, uuid+"_"+orginalName);
				
				boolean image = false;
				
				try {
					multipartFile.transferTo(savePath); //실제 파일 저장
					
					//이미지 파일의 종류
					if(Files.probeContentType(savePath).startsWith("image")) {
						
						image = true;
						
						File thuFile = new File(uploadPath, "s_" + uuid + "_"+ orginalName);
						
						Thumbnailator.createThumbnail(savePath.toFile(), thuFile, 200,200);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				list.add(StaffUploadResultDTO.builder()
						.uuid(uuid)
						.fileName(orginalName)
						.img(image).build()
						);
				 
			});//end each
			
			return list;
		} //end if
		return null;
	}
	
//	@Value("${upload.path}")// import 시에 springframework로 시작하는 value
//	private String staffuploadPath;
//	@ApiOperation(value = "view 파일", notes = "GET방식으로 첨부파일 조회")
//	@GetMapping("/staff/view/{fileName}")
//	public ResponseEntity<Resource> viewFileGET(@PathVariable String fileName){
//		Resource resource = new FileSystemResource(staffuploadPath+File.separator + fileName);
//		
//		String resourceName = resource.getFilename();
//		HttpHeaders headers = new HttpHeaders();
//		  
//		try {
//			headers.add("Content-Type", Files.probeContentType(resource.getFile().toPath()));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			return ResponseEntity.internalServerError().build();
//		}
//		return ResponseEntity.ok().headers(headers).body(resource);
//		
//		
//	}
	
	@ApiOperation(value="remove 파일", notes = "DELETE 방식으로 파일 삭제")
	@DeleteMapping("/remove/{fileName}")
	public Map<String, Boolean> removeFile(@PathVariable String fileName){
		
	Resource resource = new FileSystemResource(uploadPath+File.separator+fileName);
	String resourceName = resource.getFilename();

	Map<String, Boolean> resultMap = new HashMap<>();
	
	boolean removed = false;
	
	try {
		String contentType = Files.probeContentType(resource.getFile().toPath());
		removed = resource.getFile().delete();
		
		//섬네일이 존재 한다면
		if(contentType.startsWith("image")) {
			File thumbnailFil = new File(uploadPath+File.separator + "s_"+fileName);
			
			thumbnailFil.delete();
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		log.info(e.getMessage());
	}
	resultMap.put("result", removed);
	
	return resultMap;
}
}