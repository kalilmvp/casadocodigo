package br.com.casadocodigo.model;

import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import br.com.casadocodigo.beans.Checkout;
import br.com.casadocodigo.beans.SystemUser;
import br.com.casadocodigo.dao.CheckoutDAO;
import br.com.casadocodigo.dao.SystemUserDAO;

@Model
public class CheckoutBean {
	
	private SystemUser systemUser = new SystemUser();
	
	@Inject
	private SystemUserDAO systemUserDAO;
	
	@Inject
	private CheckoutDAO checkoutDAO;
	
	@Inject
	private ShoppingCart cart;
	
	@Inject
	private FacesContext facesContext;
	
	@Transactional
	public void checkout() {
		this.systemUserDAO.save(systemUser);
		
		Checkout checkout = new Checkout(this.systemUser, this.cart);
		this.checkoutDAO.save(checkout); 
		
		String contextName = this.facesContext.getExternalContext().getContextName();
		HttpServletResponse response = (HttpServletResponse) this.facesContext.getExternalContext().getResponse();
		//temporary redirect
		response.setStatus(307);
		response.setHeader("Location", "/" + contextName + "/services/payment?uuid=" + checkout.getUuid());
	}

	/**
	 * @return the systemUser
	 */
	public SystemUser getSystemUser() {
		return systemUser;
	}

	/**
	 * @param systemUser the systemUser to set
	 */
	public void setSystemUser(SystemUser systemUser) {
		this.systemUser = systemUser;
	}
}