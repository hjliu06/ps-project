package com.mybank.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.mybank.dto.CreditCardDTO;
import com.mybank.model.CreditCard;
import com.mybank.repository.CreditCardRepository;

@RunWith(MockitoJUnitRunner.class)
public class CreditCardServiceTests {

	@InjectMocks
	private CreditCardService service;

	@Mock
	private CreditCardRepository creditCardRepository;

	@Test
	public void givenNumberContainsLetter_WhenCheckLune_thenNotValid() {
		String cardNumber = "ab45 4644 5404 5454 54";
		assertFalse(this.service.checkLuhn(cardNumber));
	}

	@Test
	public void givenNumberLengthLessThan10_WhenCheckLune_thenNotValid() {
		String cardNumber = "4745 4644";
		assertFalse(this.service.checkLuhn(cardNumber));
	}

	@Test
	public void givenNumberLengthGreaterThan19_WhenCheckLune_thenNotValid() {
		String cardNumber = "4745 4644 5404 5457 7741";
		assertFalse(this.service.checkLuhn(cardNumber));
	}

	@Test
	public void givenNumberNotValidLuhn_WhenCheckLune_thenReturnFalse() {
		String cardNumber = "4745 4644 5404 545";
		assertFalse(this.service.checkLuhn(cardNumber));
	}

	@Test
	public void givenNumberValidLuhn_WhenCheckLune_thenReturnTrue() {
		String cardNumber = "4111 1111 1111 1111";
		assertTrue(this.service.checkLuhn(cardNumber));
	}

	@Test
	public void givenRepositoryFind2_WhenFindAll_ThenReturn2Elements() {
		CreditCard card1 = Mockito.mock(CreditCard.class);
		CreditCard card2 = Mockito.mock(CreditCard.class);
		List<CreditCard> entities = List.of(card1, card2);
		org.mockito.BDDMockito.given(this.creditCardRepository.findAll()).willReturn(entities);
		List<CreditCardDTO> dtos = this.service.findAllCreditCards();
		assertEquals(2, dtos.size());
	}

}
