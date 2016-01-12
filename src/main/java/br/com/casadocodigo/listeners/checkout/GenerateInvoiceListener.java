package br.com.casadocodigo.listeners.checkout;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.casadocodigo.beans.Checkout;
import br.com.casadocodigo.dao.CheckoutDAO;
import br.com.casadocodigo.services.InvoiceGenerator;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(
				propertyName = "destinationLookup",
				propertyValue = "java:/jms/topics/checkoutTopics"
				)
})
public class GenerateInvoiceListener implements MessageListener {
	
	private Logger LOGGER = LoggerFactory.getLogger(GenerateInvoiceListener.class);
	
	@Inject
	private CheckoutDAO checkoutDAO;
	
	@Inject
	private InvoiceGenerator invoiceGenerator;
	
	@Override
	public void onMessage(Message message) {
		TextMessage text = (TextMessage) message;
		
		try {
			Checkout checkout = this.checkoutDAO.findByUuid(text.getText());
			this.invoiceGenerator.invoiceFor(checkout);
		} catch (Exception e) {
			LOGGER.error("Problema na geração da nota fiscal", e);
		}
	}
}