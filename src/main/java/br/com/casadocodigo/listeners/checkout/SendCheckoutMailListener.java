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
import br.com.casadocodigo.infra.MailSender;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(
				propertyName = "destinationLookup",
				propertyValue = "java:/jms/topics/checkoutTopics"
				)
})
public class SendCheckoutMailListener implements MessageListener {
	
	private Logger LOGGER = LoggerFactory.getLogger(SendCheckoutMailListener.class);
	
	@Inject
	private MailSender mailSender;
	
	@Inject
	private CheckoutDAO checkoutDAO;

	@Override
	public void onMessage(Message message) {
		TextMessage text = (TextMessage) message;
		
		try {
			Checkout checkout = this.checkoutDAO.findByUuid(text.getText());
			
			StringBuilder mailBody = new StringBuilder();
			mailBody.append("<html><body>Compra realizada");
			mailBody.append("com sucesso. O código de acompanhamento é");
			mailBody.append(checkout.getUuid());
			mailBody.append("</body></html>");
			
			this.mailSender.send(
					"compras@casadocodigo.com.br", 
					checkout.getBuyer().getEmail(), 
					"Nova compra", 
					mailBody.toString());
			
		} catch (Exception e) {
			LOGGER.error("Erro ao enviar e-mail", e);
		}
	}
}