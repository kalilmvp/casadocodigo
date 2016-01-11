package br.com.casadocodigo.model;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.casadocodigo.beans.Book;
import br.com.casadocodigo.dao.BookDAO;

@Model
public class AdminListBooksBean {
private List<Book> books = new ArrayList<>();
	
	@Inject
	private BookDAO bookDAO;
	
	@PostConstruct
	private void loadObjects() {
		this.books = this.bookDAO.list();
	}

	/**
	 * @return the books
	 */
	public List<Book> getBooks() {
		return books;
	}
}
