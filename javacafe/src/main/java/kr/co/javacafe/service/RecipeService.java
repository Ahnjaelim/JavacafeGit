package kr.co.javacafe.service;

import kr.co.javacafe.dto.PageRequestDTO;
import kr.co.javacafe.dto.PageResponseDTO;
import kr.co.javacafe.dto.RecipeDTO;

public interface RecipeService {

	Long register(RecipeDTO recipeDTO);
	RecipeDTO readOne(Long rno);
	void modify(RecipeDTO recipeDTO);
	void remove(Long rno);
	PageResponseDTO<RecipeDTO> list(PageRequestDTO pageRequestDTO);
}
