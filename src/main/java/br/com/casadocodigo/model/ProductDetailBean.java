package br.com.casadocodigo.model;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.inject.Model;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import br.com.casadocodigo.beans.Book;
import br.com.casadocodigo.dao.BookDAO;

@Model
@Stateful
public class ProductDetailBean {
	
	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager manager;
	
	private BookDAO bookDAO;
	private Book book;
	private Integer id;
	
	@PostConstruct
	private void loadManager() {
		this.bookDAO = new BookDAO(this.manager);
	}
	
	public void loadBook() {
		this.book = this.bookDAO.findById(id);
	}

	/**
	 * @return the book
	 */
	public Book getBook() {
		return book;
	}

	/**
	 * @param book the book to set
	 */
	public void setBook(Book book) {
		this.book = book;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
}