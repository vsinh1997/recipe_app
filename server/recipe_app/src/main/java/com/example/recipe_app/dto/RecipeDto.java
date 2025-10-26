package com.example.recipe_app.dto;

import lombok.Data;

import java.util.List;

@Data
public class RecipeDto {

    private Long id;
    private String name;
    private String description;
    private String cookingTime;
    private String difficulty;
    private Long categoryId;
    private String categoryName;

    private List<IngredientDto> ingredientList;

}
