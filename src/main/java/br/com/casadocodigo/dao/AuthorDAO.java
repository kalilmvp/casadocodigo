package br.com.casadocodigo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.casadocodigo.beans.Author;

public class AuthorDAO {

	@PersistenceContext
	EntityManager manager;

	public List<Author> list() {
		return manager.
				createQuery("SELECT author FROM Author author ORDER BY author.name ASC", Author.class).getResultList();
	}

	
}
