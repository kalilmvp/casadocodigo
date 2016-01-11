package br.com.casadocodigo.model;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.casadocodigo.beans.Book;
import br.com.casadocodigo.dao.BookDAO;

@Model
public class HomeBean {
	
	@Inject
	private BookDAO bookDAO;
	
	public List<Book> lastReleases() {
		return this.bookDAO.lastReleases();
	}
	
	public List<Book> olderBooks() {
		return this.bookDAO.last(20);
	}
}