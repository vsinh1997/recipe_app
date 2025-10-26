package com.example.recipe_app.mapper;

import com.example.recipe_app.dto.IngredientCondDto;
import com.example.recipe_app.dto.IngredientDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IngredientMapper {

    List<IngredientDto> select(IngredientCondDto cond);

    IngredientDto selectOne(IngredientDto dto);

    void insert(IngredientDto dto);

    void update(IngredientDto dto);

    void delete(IngredientDto dto);

}
