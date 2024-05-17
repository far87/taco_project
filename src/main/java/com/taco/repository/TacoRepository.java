package com.taco.repository;

import org.springframework.data.repository.CrudRepository;

import com.taco.domain.Ingredient;
import com.taco.domain.Taco;

public interface TacoRepository extends CrudRepository<Taco, Long>{
	public Iterable<Taco> findAll();
}
