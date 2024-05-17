package com.taco.repository;

import org.springframework.data.repository.CrudRepository;

import com.taco.domain.TacoOrder;

public interface OrderRepository extends CrudRepository<TacoOrder, Long>{
    
}
