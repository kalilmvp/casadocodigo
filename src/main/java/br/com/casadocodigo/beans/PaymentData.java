package br.com.casadocodigo.beans;

import java.math.BigDecimal;

public class PaymentData {
	
	private BigDecimal value;
	
	public PaymentData(BigDecimal value) {
		super();
		this.value = value;
	}

	/**
	 * @return the value
	 */
	public BigDecimal getValue() {
		return value;
	}	
}
