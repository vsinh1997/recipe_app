package com.example.recipe_app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.recipe_app.dto.RecipeCondDto;
import com.example.recipe_app.dto.RecipeDto;

@Mapper
public interface RecipeMapper {

	List<RecipeDto> select(RecipeCondDto cond);
	
	RecipeDto selectOne(RecipeDto dto);
	
	int insert(RecipeDto dto);
	
	int update(RecipeDto dto);
	
	int delete(RecipeDto dto);
	

}
