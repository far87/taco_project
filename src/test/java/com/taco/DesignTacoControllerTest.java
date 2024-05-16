package com.taco;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;

import com.taco.domain.Ingredient;
import com.taco.repository.IngredientRepository;
import com.taco.web.DesignTacoController;

@WebMvcTest(DesignTacoController.class)
public class DesignTacoControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private IngredientRepository ingredientRepository;
	
	@Test 
	public void getDesignViewTest() throws Exception{
		when(ingredientRepository.findAll()).thenReturn(new ArrayList<Ingredient>());
		mockMvc.perform(get("/design"))
		.andExpect(status().isUnauthorized());		
	}
}
