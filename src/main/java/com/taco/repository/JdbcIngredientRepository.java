package com.taco.repository;

import com.taco.domain.Ingredient;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcIngredientRepository implements IngredientRepository{

    private JdbcTemplate jdbcTemplate;

    public JdbcIngredientRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }

    @Override
    public Iterable<Ingredient> findAll() {
       return jdbcTemplate.query("SELECT * FROM INGREDIENT",
                (rs, rowNum) -> {
                    return new Ingredient(rs.getString("ID"),rs.getString("NAME"),Ingredient.Type.valueOf(rs.getString("TYPE")));
        });
    }

    @Override
    public Ingredient findById(String id) {
        List<Ingredient> lista = jdbcTemplate.query("SELECT * FROM INGREDIENT WHERE ID=?", (rs, rowNum) -> {
            return new Ingredient(rs.getString("ID"), rs.getString("NAME"), Ingredient.Type.valueOf(rs.getString("TYPE")));
        }, id);
        return lista.get(0);
    }

    @Override
    public void save(Ingredient ingredient) {
        jdbcTemplate.update("INSERT INTO INGREDIENT VALUES(?,?,?)",ingredient.getId(),ingredient.getName(),ingredient.getType());
    }
}
