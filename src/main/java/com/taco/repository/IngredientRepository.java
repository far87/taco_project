package com.taco.repository;

import org.springframework.data.repository.CrudRepository;

import com.taco.domain.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}
