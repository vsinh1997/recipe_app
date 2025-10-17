package com.example.recipe_app.dto;

import lombok.Data;

@Data
public class IngredientDto {
	
	private Long id;
	private Long recipeId;
	private String name;
	private String amount;	
	
}
