package com.example.recipe_app.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
