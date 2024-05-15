package com.taco.web;

import com.taco.domain.Ingredient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class IngredientConverter implements Converter<String, Ingredient> {

    private Map<String,Ingredient> mappa=new HashMap<>();

    public IngredientConverter(){
        mappa.put("FLTO",new Ingredient("FLTO","Flour Tortilla", Ingredient.Type.WRAP));
        mappa.put("COTO",new Ingredient("COTO","Corn Tortilla", Ingredient.Type.WRAP));
        mappa.put("GRBF",new Ingredient("GRBF","Ground Beef", Ingredient.Type.PROTEIN));
        mappa.put("CARN",new Ingredient("CARN","Carnitas", Ingredient.Type.PROTEIN));
        mappa.put("TMTO",new Ingredient("TMTO","Diced Tomatoes", Ingredient.Type.VEGGIES));
        mappa.put("LETC",new Ingredient("LETC","Lettuce", Ingredient.Type.VEGGIES));
        mappa.put("CHED",new Ingredient("CHED","Cheddar", Ingredient.Type.CHEESE));
        mappa.put("JACK",new Ingredient("JACK","Monterrey Jack", Ingredient.Type.CHEESE));
        mappa.put("SLSA",new Ingredient("SLSA","Salsa", Ingredient.Type.SAUCE));
        mappa.put("SRCR",new Ingredient("SRCR","Sour Cream", Ingredient.Type.SAUCE));
    }

    @Override
    public Ingredient convert(String source) {
        return mappa.get(source);
    }
}
