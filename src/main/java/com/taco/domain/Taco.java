package com.taco.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
public class Taco {

	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
    private Long id;
    
    private Date createdAt;
    
    @NotNull
    @Size(min=5,message = "Il nome del Taco deve avere una lunghezza minima di 5 caratteri")
    private String name;
    
    
    @Size(min=1,message = "Devi scegliere almeno un ingrediente")
    @ManyToMany
    private List<Ingredient> ingredients;

    public Taco(){}
    public Taco(String name, List<Ingredient> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
