package com.taco.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taco.domain.Ingredient;
import com.taco.repository.IngredientRepository;

@RestController
@RequestMapping("/ingredients")
public class IngredientRestController {
	
	private IngredientRepository ingredientRepository;
	
	public IngredientRestController(IngredientRepository ingredientRepository) {
		super();
		this.ingredientRepository = ingredientRepository;
	}


	@GetMapping
	public Iterable<Ingredient> ingredients(){
		return ingredientRepository.findAll();
	}
}
