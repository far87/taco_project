package com.taco.repository;

import com.taco.domain.Ingredient;
import com.taco.domain.Taco;
import com.taco.domain.TacoOrder;
import org.springframework.asm.Type;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Repository
public class JdbcOrderRepository implements OrderRepository{

    private JdbcTemplate jdbcTemplate;

    public JdbcOrderRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public TacoOrder save(TacoOrder tacoOrder) {
        PreparedStatementCreatorFactory pscf=new PreparedStatementCreatorFactory("INSERT INTO TACO_ORDER (DELIVERY_NAME,DELIVERY_STREET,DELIVERY_CITY,DELIVERY_STATE," +
                "DELIVERY_ZIP,CC_NUMBER,CC_EXPIRATION,CC_CVV,PLACED_AT) VALUES(?,?,?,?,?,?,?,?,?)",
                Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.TIMESTAMP);
        pscf.setReturnGeneratedKeys(true);
        tacoOrder.setPlacedAt(new Date());
        PreparedStatementCreator psc=pscf.newPreparedStatementCreator(Arrays.asList(
                tacoOrder.getDeliveryName(),tacoOrder.getDeliveryStreet(),
                tacoOrder.getDeliveryCity(), tacoOrder.getDeliveryState(),
                tacoOrder.getDeliveryZip(), tacoOrder.getCcNumber(),tacoOrder.getCcExpiration(),
                tacoOrder.getCcCvv(),tacoOrder.getPlacedAt())
        );
        GeneratedKeyHolder generatedKeyHolder=new GeneratedKeyHolder();
        jdbcTemplate.update(psc,generatedKeyHolder);
        long orderId=generatedKeyHolder.getKey().longValue();
        tacoOrder.setId(orderId);

        List<Taco> tacos=tacoOrder.getTacos();
        long orderKey=0;
        for(Taco taco: tacos){
            saveTaco(taco,tacoOrder.getId(),++orderKey);
        }

    return tacoOrder;
    }

    private Taco saveTaco(Taco taco, long orderId,long orderKey){
        PreparedStatementCreatorFactory pscf=new PreparedStatementCreatorFactory("INSERT INTO TACO(NAME,TACO_ORDER,TACO_ORDER_KEY,CREATED_AT) VALUES(?,?,?,?)",
                Types.VARCHAR, Type.LONG,Type.LONG,Types.TIMESTAMP);
        pscf.setReturnGeneratedKeys(true);
        taco.setCreatedAt(new Date());
        PreparedStatementCreator psc=pscf.newPreparedStatementCreator(Arrays.asList(taco.getName(),orderId,orderKey,taco.getCreatedAt()));
        GeneratedKeyHolder generatedKeyHolder=new GeneratedKeyHolder();
        jdbcTemplate.update(psc,generatedKeyHolder);
        long tacoId=generatedKeyHolder.getKey().longValue();
        taco.setId(tacoId);
        saveIngredientRef(taco.getIngredients(),taco.getId());
        return taco;

    }

    private void saveIngredientRef(List<Ingredient> ingredients, long tacoId){
        int tacoKey=0;
        for(Ingredient ingredient: ingredients){
            jdbcTemplate.update("INSERT INTO INGREDIENT_REF VALUES(?,?,?)",ingredient.getId(),tacoId,++tacoKey);
        }
    }
}
