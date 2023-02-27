package kr.co.javacafe.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import kr.co.javacafe.domain.Recipe;
import kr.co.javacafe.dto.PageRequestDTO;
import kr.co.javacafe.dto.PageResponseDTO;
import kr.co.javacafe.dto.RecipeDTO;
import kr.co.javacafe.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class RecipeServiceImpl implements RecipeService {

	private final ModelMapper modelMapper;
	private final RecipeRepository recipeRepository;
	
	@Override
	public Long register(RecipeDTO recipeDTO) {

		Recipe recipe = modelMapper.map(recipeDTO, Recipe.class);
		Long rno = recipeRepository.save(recipe).getRno();
		return rno;
	}

	@Override
	public RecipeDTO readOne(Long rno) {
		Optional<Recipe> result = recipeRepository.findById(rno);
		Recipe recipe = result.orElseThrow();
		RecipeDTO recipeDTO = modelMapper.map(recipe, RecipeDTO.class);
		return recipeDTO;
	}

	@Override
	public void modify(RecipeDTO recipeDTO) {
		Optional<Recipe> result = recipeRepository.findById(recipeDTO.getRno());
		Recipe recipe = result.orElseThrow();
		recipe.change(recipeDTO.getRname(), recipeDTO.getReng(), recipeDTO.getRdesc(), recipeDTO.getRtext());
		recipeRepository.save(recipe);
	}

	@Override
	public void remove(Long rno) {
		recipeRepository.deleteById(rno);
		
	}

	@Override
	public PageResponseDTO<RecipeDTO> list(PageRequestDTO pageRequestDTO) {
		String[] types = pageRequestDTO.getTypes();
		String keyword = pageRequestDTO.getKeyword();
		Pageable pageable = pageRequestDTO.getPageable("rno");
		Page<Recipe> result = recipeRepository.searchAll(types, keyword, pageable);
		List<RecipeDTO> dtoList = result.getContent().stream().map(recipe -> modelMapper.map(recipe, RecipeDTO.class)).collect(Collectors.toList());
		return PageResponseDTO.<RecipeDTO>withAll()
				.pageRequestDTO(pageRequestDTO)
				.dtoList(dtoList)
				.total((int)result.getTotalElements())
				.build();
	}


}
