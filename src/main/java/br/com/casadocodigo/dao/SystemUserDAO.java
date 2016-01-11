package br.com.casadocodigo.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.casadocodigo.beans.SystemUser;

public class SystemUserDAO {
	
	@PersistenceContext
	private EntityManager manager;
	
	public void save(SystemUser user) {
		this.manager.persist(user);
	}
}
