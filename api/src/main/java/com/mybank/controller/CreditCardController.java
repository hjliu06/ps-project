package com.mybank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mybank.dto.CreditCardDTO;
import com.mybank.service.CreditCardService;

@RestController
public class CreditCardController {

	@Autowired
	private CreditCardService creditCardService;

	/**
	 * Fetch all credit cards
	 * 
	 * @return
	 */
	@CrossOrigin(origins = { "http://localhost:3000" })
	@GetMapping("/cards")
	public List<CreditCardDTO> findAllCreditCards() {
		return this.creditCardService.findAllCreditCards();
	}

	/**
	 * Create a new credit card
	 * 
	 * @param creditCardDTO
	 * @return
	 */
	@CrossOrigin(origins = { "http://localhost:3000" })
	@PostMapping("/cards")
	public CreditCardDTO addCreditCard(@RequestBody CreditCardDTO creditCardDTO) {
		return this.creditCardService.addCreditCard(creditCardDTO);
	}

}
