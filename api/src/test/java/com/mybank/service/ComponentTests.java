package com.mybank.service;

import static org.junit.Assert.assertEquals;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mybank.controller.CreditCardController;
import com.mybank.dto.CreditCardDTO;
import com.mybank.model.CreditCard;
import com.mybank.repository.CreditCardRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ComponentTestsConfig.class)
public class ComponentTests {

	private static final Long CARD_ID = ThreadLocalRandom.current().nextLong(1L, 1000L);
	private static final String OWNER_NAME = UUID.randomUUID().toString();
	private static final String CARD_NUMBER = UUID.randomUUID().toString();
	private static final Double BALANCE = ThreadLocalRandom.current().nextDouble(-1000, 1000);
	private static final Integer lIMIT = ThreadLocalRandom.current().nextInt(100, 3000);

	@Autowired
	private CreditCardController creditCardController;

	@Autowired
	private CreditCardRepository creditCardRepository;

	@Before
	public void setUp() {
		CreditCard entity = new CreditCard(CARD_ID, OWNER_NAME, CARD_NUMBER, BALANCE, lIMIT);
		org.mockito.BDDMockito.given(this.creditCardRepository.save(ArgumentMatchers.any(CreditCard.class)))
				.willReturn(entity);
	}

	@Test
	public void givenValidCardNumber_WhenAddCreditCard_thenOK() {
		CreditCardDTO dto = new CreditCardDTO(null, "test1", "4111 1111 1111 1111", 700.25, 2000);
		CreditCardDTO output = this.creditCardController.addCreditCard(dto);
		assertEquals(CARD_ID, output.getId());
		assertEquals(OWNER_NAME, output.getName());
		assertEquals(CARD_NUMBER, output.getCardNumber());
		assertEquals(BALANCE, output.getBalance());
		assertEquals(lIMIT, output.getLimit());
	}

	@Test(expected = IllegalArgumentException.class)
	public void givenNonValidCardNumber_WhenAddCreditCard_thenThrowException() {
		CreditCardDTO dto = new CreditCardDTO(null, "test1", "4111 1111 1111 1112", 700.25, 2000);
		this.creditCardController.addCreditCard(dto);
	}

}
