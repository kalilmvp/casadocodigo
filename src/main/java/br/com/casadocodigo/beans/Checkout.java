package br.com.casadocodigo.beans;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import br.com.casadocodigo.model.ShoppingCart;

@Entity
public class Checkout {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	private SystemUser buyer;
	
	private BigDecimal value;
	private String jsonCart;
	private String uuid;
	
	public Checkout(){}

	public Checkout(SystemUser user, ShoppingCart cart) {
		this.buyer = user;
		this.value = cart.getTotal();
		this.jsonCart = cart.toJson();
	}
	
	@PrePersist
	public void prePersist() {
		this.uuid = UUID.randomUUID().toString();
	}

	/**
	 * @return the buyer
	 */
	public SystemUser getBuyer() {
		return buyer;
	}

	/**
	 * @return the value
	 */
	public BigDecimal getValue() {
		return value;
	}

	/**
	 * @return the jsonCart
	 */
	public String getJsonCart() {
		return jsonCart;
	}

	public String getUuid() {
		return this.uuid;
	}
}