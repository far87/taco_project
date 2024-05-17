package com.taco.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Ingredient {

	@Id
    private String id;
    private String name;
    private Type type;

    public enum Type{CHEESE,PROTEIN,SAUCE,WRAP,VEGGIES}

    public Ingredient(){}

    public Ingredient(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public Ingredient(String id,String name, Type type) {
        this.id=id;
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
