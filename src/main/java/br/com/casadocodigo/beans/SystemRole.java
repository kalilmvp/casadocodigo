package br.com.casadocodigo.beans;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SystemRole {

	@Id
	private String name;
	
	public SystemRole(){}
	
	public SystemRole(String name) {
		this.name = name;
	}
}
