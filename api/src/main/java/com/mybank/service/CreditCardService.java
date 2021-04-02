package com.mybank.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mybank.dto.CreditCardDTO;
import com.mybank.model.CreditCard;
import com.mybank.repository.CreditCardRepository;

@Service
public class CreditCardService {

	@Autowired
	private CreditCardRepository creditCardRepository;

	/**
	 * Find all new credit cards
	 * 
	 * @return
	 */
	@Transactional
	public List<CreditCardDTO> findAllCreditCards() {

		List<CreditCard> entities = this.creditCardRepository.findAll();

		List<CreditCardDTO> dtos = new ArrayList<>();

		for (CreditCard entity : entities) {
			CreditCardDTO dto = convertEnityToDTO(entity);
			dtos.add(dto);
		}

		return dtos;
	}

	/**
	 * Create a new credit card
	 * 
	 * @param dto
	 * @return
	 */
	@Transactional
	public CreditCardDTO addCreditCard(CreditCardDTO dto) {
		if (!this.checkLuhn(dto.getCardNumber())) {
			throw new IllegalArgumentException("bad card number");
		}
		CreditCard entity = convertDTOToEntity(dto);
		return convertEnityToDTO(this.creditCardRepository.save(entity));

	}

	private CreditCardDTO convertEnityToDTO(CreditCard entity) {
		CreditCardDTO dto = new CreditCardDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setCardNumber(entity.getCardNumber());
		dto.setBalance(entity.getBalance());
		dto.setLimit(entity.getLimit());
		return dto;
	}

	private CreditCard convertDTOToEntity(CreditCardDTO dto) {
		CreditCard entity = new CreditCard();
		entity.setName(dto.getName());
		entity.setCardNumber(dto.getCardNumber().replaceAll("\\s+", ""));
		entity.setBalance(dto.getBalance());
		entity.setLimit(dto.getLimit());
		return entity;
	}

	protected boolean checkLuhn(String number) {
		String cardNumber = number.replaceAll("\\s+", "");
		int len = cardNumber.length();
		if (!cardNumber.matches("[0-9]+") || len < 10 || len > 19 || cardNumber.startsWith("0")) {
			return false;
		}

		int nSum = 0;
		boolean isSecond = false;
		for (int i = len - 1; i >= 0; i--) {

			int d = cardNumber.charAt(i) - '0';

			// begin from the rightmost digit, every second digit should multiply by 2
			if (isSecond == true)
				d = d * 2;

			// if number is two digits, add the digits
			nSum += d / 10;
			nSum += d % 10;

			isSecond = !isSecond;
		}

		// the modulo of 10 should be 0
		return (nSum % 10 == 0);

	}

}
