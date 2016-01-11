package br.com.casadocodigo.resources;

import java.math.BigDecimal;
import java.net.URI;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.inject.Inject;
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
import br.com.casadocodigo.infra.MailSender;
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
	private MailSender mailSender;
	
	@Resource(name = "java:comp/DefaultManagedExecutorService")
	private ManagedExecutorService managedExecutorService;
	
	@POST
	public void pay(@Suspended final AsyncResponse ar, @QueryParam("uuid") String uuid) {
		String contextPath = this.context.getContextPath();
		Checkout checkout = this.checkoutDAO.findByUuid(uuid);
		
		this.managedExecutorService.submit(() -> {
			BigDecimal total = checkout.getValue();
			
			try {
				this.paymentGetaway.pay(total);
				
				String mailBody = "Nova compra, seu código de acompanhante é" + checkout.getUuid();
				
				this.mailSender.send(
						"compras@casadocodigo.com.br", 
						checkout.getBuyer().getEmail(), 
						"Nova compra", 
						mailBody);
				
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
