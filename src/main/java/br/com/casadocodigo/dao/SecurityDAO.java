package br.com.casadocodigo.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.casadocodigo.beans.SystemUser;

public class SecurityDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	public SystemUser loadUserByUserName(String userName) {
		String jpql = "SELECT u FROM SystemUser u WHERE u.email = :username";
		return this.em.createQuery(jpql, SystemUser.class).setParameter("username", userName).getSingleResult();
	}
}
