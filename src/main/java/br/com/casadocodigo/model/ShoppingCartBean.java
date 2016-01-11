package br.com.casadocodigo.model;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.casadocodigo.beans.Book;
import br.com.casadocodigo.dao.BookDAO;

@Model
public class ShoppingCartBean {
	
	@Inject
	private ShoppingCart shoppingCart;
	
	@Inject
	private BookDAO bookDAO;
	
	public String add(Integer id) {
		Book book = this.bookDAO.findById(id);
		ShoppingItem item = new ShoppingItem(book);
		shoppingCart.add(item);
		return "/site/carrinho?faces-redirect=true";
	}
	
	public String remove(Integer id) {
		Book book = this.bookDAO.findById(id);
		ShoppingItem item = new ShoppingItem(book);
		shoppingCart.remove(item);
		return "/site/carrinho?faces-redirect=true";
	}
}