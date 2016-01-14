package br.com.casadocodigo.model;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.casadocodigo.beans.Book;
import br.com.casadocodigo.beans.Sale;
import br.com.casadocodigo.websockets.ConnectedUsers;

@Model
public class AdminSalesBean {
	private Sale sale = new Sale();
	
	@Inject
	private ConnectedUsers remoteUsers;
	
	@PostConstruct
	private void configure() {
		this.sale.setBook(new Book());
	}
	
	public String save() {
		this.remoteUsers.send(this.sale.toJson());
		return "/promocoes/form.xhtml?faces-redirect=true";
	}

	/**
	 * @return the sale
	 */
	public Sale getSale() {
		return sale;
	}

	/**
	 * @param sale the sale to set
	 */
	public void setSale(Sale sale) {
		this.sale = sale;
	}	
}