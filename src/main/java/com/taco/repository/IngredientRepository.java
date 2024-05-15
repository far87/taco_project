package com.taco.repository;

import com.taco.domain.Ingredient;

public interface IngredientRepository {

    public Iterable<Ingredient> findAll();
    public Ingredient findById(String id);
    public void save(Ingredient ingredient);

}
