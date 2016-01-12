package br.com.casadocodigo.services;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;

import br.com.casadocodigo.beans.Checkout;
import br.com.casadocodigo.beans.InvoiceData;

public class InvoiceGenerator {

	private static final String URI_TO_PAY = "http://book-payment.herokuapp.com/invoice";
	
	public void invoiceFor(Checkout checkout) {
		Client client = ClientBuilder.newClient();
		InvoiceData invoiceData = new InvoiceData(checkout);
		Entity<InvoiceData> json = Entity.json(invoiceData);
		System.out.println(client.target(URI_TO_PAY).request().post(json, String.class));
	}
}
