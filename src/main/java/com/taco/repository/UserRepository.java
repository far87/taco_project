package com.taco.repository;

import com.taco.domain.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User,Long> {
    User findByUsername(String username);
}
