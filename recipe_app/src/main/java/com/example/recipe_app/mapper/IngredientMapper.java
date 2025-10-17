package com.example.recipe_app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.recipe_app.dto.IngredientCondDto;
import com.example.recipe_app.dto.IngredientDto;

@Mapper
public interface IngredientMapper {

	List<IngredientDto> select(IngredientCondDto cond);
	
	IngredientDto selectOne(IngredientDto dto);
	
	int insert(IngredientDto dto);
	
	int update(IngredientDto dto);
	
	int delete(IngredientDto dto);
	

}
