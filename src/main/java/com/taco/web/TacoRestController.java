package com.taco.web;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taco.domain.Taco;
import com.taco.repository.TacoRepository;

@RestController
@RequestMapping(path="/api/tacos",produces="application/json")
@CrossOrigin(origins="localhost:8080")
public class TacoRestController {
	
	private TacoRepository tacoRepository;
	
	public TacoRestController(TacoRepository tacoRepository) {
		super();
		this.tacoRepository = tacoRepository;
	}

	@GetMapping(params="recent")
	public Iterable<Taco> recentTacos(){
		return tacoRepository.findAll();
	}

}
