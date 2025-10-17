package com.example.recipe_app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.recipe_app.dto.RecipeCondDto;
import com.example.recipe_app.dto.RecipeDto;
import com.example.recipe_app.logic.RecipeLogic;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/recipe")
@RequiredArgsConstructor
@CrossOrigin("*")
public class RecipeController {

	private final RecipeLogic logic;

	@GetMapping("")
	public ResponseEntity<List<RecipeDto>> select(RecipeCondDto cond) {
		
		return new ResponseEntity<List<RecipeDto>>(logic.select(cond), HttpStatus.OK);

	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RecipeDto> selectOne(@PathVariable("id") Long id) {
		
		return new ResponseEntity<RecipeDto>(logic.selectOne(id), HttpStatus.OK);
		
	}

}
