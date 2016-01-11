package br.com.casadocodigo.services;

import java.math.BigDecimal;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;

import br.com.casadocodigo.beans.PaymentData;

public class PaymentGetaway {
	private static final String URI_TO_PAY = "http://book-payment.herokuapp.com/payment";
	
	public void pay(BigDecimal total) {
		Client client = ClientBuilder.newClient();
		
		PaymentData paymentData = new PaymentData(total);
		Entity<PaymentData> json = Entity.json(paymentData);
		System.out.println(client.target(URI_TO_PAY).request().post(json, String.class));
	}

}
