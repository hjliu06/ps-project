package com.mybank.service;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mybank.controller.CreditCardController;
import com.mybank.repository.CreditCardRepository;

@Configuration
public class ComponentTestsConfig {
	
	@Bean
	public CreditCardController creditCardController() {
		return new CreditCardController();
	}
	
	@Bean
	public CreditCardService creditCardService() {
		return Mockito.spy(new CreditCardService());
	}
	
	@Bean
	public CreditCardRepository creditCardRepository() {
		return Mockito.mock(CreditCardRepository.class);
	}

}
