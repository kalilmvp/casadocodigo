package br.com.casadocodigo.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.casadocodigo.beans.Checkout;

public class CheckoutDAO {
	

	@PersistenceContext
	private EntityManager manager;
	
	public void save(Checkout checkout) {
		this.manager.persist(checkout);
	}
	
	public Checkout findByUuid(String uuid) {
		return this.manager.createQuery(
						"SELECT c FROM Checkout c WHERE c.uuid = :uuid",
						Checkout.class).setParameter("uuid", uuid)
				.getSingleResult();
	}
}
