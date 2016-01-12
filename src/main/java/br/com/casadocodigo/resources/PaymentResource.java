package br.com.casadocodigo.resources;

import java.math.BigDecimal;
import java.net.URI;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.servlet.ServletContext;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import br.com.casadocodigo.beans.Checkout;
import br.com.casadocodigo.dao.CheckoutDAO;
import br.com.casadocodigo.services.PaymentGetaway;

@Path("/payment")
public class PaymentResource {
	
	@Context
	private ServletContext context;
	
	@Inject
	private CheckoutDAO checkoutDAO;
	
	@Inject
	private PaymentGetaway paymentGetaway;
	
	@Inject
	private JMSContext jmsContext;
	
	@Resource(lookup = "java:/jms/topics/checkoutTopics")
	private Destination checkoutTopics;
	
	@Resource(name = "java:comp/DefaultManagedExecutorService")
	private ManagedExecutorService managedExecutorService;
	
	@POST
	public void pay(@Suspended final AsyncResponse ar, @QueryParam("uuid") String uuid) {
		String contextPath = this.context.getContextPath();
		Checkout checkout = this.checkoutDAO.findByUuid(uuid);
		JMSProducer producer = this.jmsContext.createProducer();
		
		this.managedExecutorService.submit(() -> {
			BigDecimal total = checkout.getValue();
			
			try {
				this.paymentGetaway.pay(total);
				
				producer.send(checkoutTopics, checkout.getUuid());
				
				URI redirectURI = 
						UriBuilder.fromUri(contextPath + "/site/index.xhtml")
						.queryParam("msg", "Compra realizada com sucesso!").build();
				
				Response response = Response.seeOther(redirectURI).build();
				ar.resume(response);
			} catch (Exception e) {
				ar.resume(e);
			}
		});
	}
}
