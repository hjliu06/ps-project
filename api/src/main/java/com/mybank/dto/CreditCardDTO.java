package com.mybank.dto;

public class CreditCardDTO {

	private Long id;
	private String name;
	private String cardNumber;
	private Double balance;
	private Integer limit;

	public CreditCardDTO() {
		super();
	}

	public CreditCardDTO(Long id, String name, String cardNumber, Double balance, Integer limit) {
		super();
		this.id = id;
		this.name = name;
		this.cardNumber = cardNumber;
		this.balance = balance;
		this.limit = limit;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

}
