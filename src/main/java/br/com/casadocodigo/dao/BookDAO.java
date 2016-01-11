package br.com.casadocodigo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.hibernate.jpa.QueryHints;

import br.com.casadocodigo.beans.Book;

public class BookDAO {
	
	@PersistenceContext
	EntityManager manager;

	public BookDAO(){}
	
	public BookDAO(EntityManager manager) {
		this.manager = manager;
	}

	public void save(Book product) {
		this.manager.persist(product);
		
	}

	public List<Book> list() {
		return 
			this.manager.createQuery("SELECT distinct(book) FROM Book book JOIN FETCH book.authors", Book.class).
				getResultList();
	}

	public List<Book> lastReleases() {
		TypedQuery<Book> query = this.manager.createQuery("SELECT book FROM Book book WHERE book.releaseDate <= now() "
				+ "order by book.id desc", 
				Book.class).
				setMaxResults(3);
		query.setHint(QueryHints.HINT_CACHEABLE, true);
		
		return query.getResultList();
	}

	public List<Book> last(int number) {
		TypedQuery<Book> query = manager
				.createQuery("select b from Book b join fetch b.authors",
						Book.class).setMaxResults(number);
		query.setHint(QueryHints.HINT_CACHEABLE, true);
		
		return query.getResultList();
	}

	public Book findById(Integer id) {
		return this.manager.find(Book.class, id);
	}
}
