package kr.co.javacafe.service;

import javax.servlet.http.HttpServletRequest;

import kr.co.javacafe.dto.PageRequestDTO;
import kr.co.javacafe.dto.PageResponseDTO;
import kr.co.javacafe.dto.RecipeDTO;


public interface RecipeService {

	Long register(RecipeDTO recipeDTO, HttpServletRequest request);
	RecipeDTO readOne(Long rno);
	void modify(RecipeDTO recipeDTO, HttpServletRequest request);
	void remove(Long rno);
	PageResponseDTO<RecipeDTO> list(PageRequestDTO pageRequestDTO);

}
