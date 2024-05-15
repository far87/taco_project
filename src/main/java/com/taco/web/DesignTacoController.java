package com.taco.web;

import com.taco.domain.Ingredient;
import com.taco.domain.Taco;
import com.taco.repository.IngredientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/design")
@SessionAttributes("addedTacos")
public class DesignTacoController {

	private IngredientRepository ingredientRepository;

	public DesignTacoController(IngredientRepository ingredientRepository) {
		this.ingredientRepository = ingredientRepository;
	}

	@ModelAttribute(name="addedTacos")
	public List<Taco> ingredients(){
		return new ArrayList<Taco>();
	}

	@ModelAttribute(name="taco")
	public Taco taco(){
		return new Taco();
	}

	@ModelAttribute(name="ingredients")
	public Iterable<Ingredient> loadIngredient(){
		return ingredientRepository.findAll();
	}

	@GetMapping
	public String getDesignView(Model model) {
		return "design";
	}


	@PostMapping
	public String tacoProcessor(@Valid Taco taco, Errors errors, @ModelAttribute("addedTacos") List<Taco> addedTacos) {
		if(errors.hasErrors())
			return "design";

		addedTacos.add(taco);
		return "redirect:/design";
	}

}
