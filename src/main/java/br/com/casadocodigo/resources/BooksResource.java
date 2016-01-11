package br.com.casadocodigo.resources;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.providers.jaxb.Wrapped;

import br.com.casadocodigo.beans.Book;
import br.com.casadocodigo.dao.BookDAO;

@Path("/books")
@Stateful
public class BooksResource {
	
	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;
	private BookDAO bookDAO;
	
	@PostConstruct
	private void loadDAO() {
		this.bookDAO = new BookDAO(this.entityManager);
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Wrapped(element = "books")
	public List<Book> lastBooks() {
		return this.bookDAO.lastReleases();
	}
}