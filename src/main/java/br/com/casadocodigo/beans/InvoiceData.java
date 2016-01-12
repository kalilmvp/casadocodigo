package br.com.casadocodigo.beans;

import java.math.BigDecimal;

public class InvoiceData {
	
	private BigDecimal value;
	private String buyerMail;
	
	public InvoiceData(Checkout checkout) {
		this.value = checkout.getValue();
		this.buyerMail = checkout.getBuyer().getEmail();
	}
	
	public BigDecimal getValue() {
		return value;
	}
	
	public String getBuyerMail() {
		return buyerMail;
	}
}
