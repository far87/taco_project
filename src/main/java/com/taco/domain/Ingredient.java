package com.taco.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE,force = true)
@AllArgsConstructor
@Entity
public class Ingredient {

	@Id
    private String id;
    
	private String name;
    
	@Enumerated(EnumType.STRING)
    private Type type;

    public enum Type{CHEESE,PROTEIN,SAUCE,WRAP,VEGGIES}
    
}
