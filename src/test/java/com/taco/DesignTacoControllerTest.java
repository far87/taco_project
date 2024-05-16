package com.taco;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import com.taco.domain.Ingredient;
import com.taco.repository.IngredientRepository;
import com.taco.web.DesignTacoController;

@WebMvcTest(DesignTacoController.class)
public class DesignTacoControllerTest {

	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private IngredientRepository ingredientRepository;
	
	@Test
	public void unauthorizedDesignViewTest() throws Exception{
        UsernamePasswordAuthenticationToken principal =new UsernamePasswordAuthenticationToken("user", "password");
        SecurityContextHolder.getContext().setAuthentication(principal);       
		when(ingredientRepository.findAll()).thenReturn(new ArrayList<Ingredient>());
		mockMvc.perform(get("/design"))
		.andExpect(status().isUnauthorized());		
	}
	
	@Test
	@WithMockUser(username = "username",password = "password",roles = "USER")
	public void AuthorizedDesignViewTest() throws Exception{
		when(ingredientRepository.findAll()).thenReturn(new ArrayList<Ingredient>());
		mockMvc.perform(get("/design"))
		.andExpect(status().isOk());		
	}

}
