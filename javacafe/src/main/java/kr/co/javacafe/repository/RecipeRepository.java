package kr.co.javacafe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kr.co.javacafe.domain.Recipe;
import kr.co.javacafe.repository.search.RecipeSearch;

public interface RecipeRepository extends JpaRepository<Recipe, Long>, RecipeSearch {

	@Query(value = "select now()", nativeQuery = true)
	String getTime();
}
