package br.com.casadocodigo.security;

import java.security.Principal;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import br.com.casadocodigo.beans.SystemUser;
import br.com.casadocodigo.dao.SecurityDAO;

@Model
public class CurrentUser {
	
	@Inject
	private HttpServletRequest request;

	@Inject
	private SecurityDAO securityDAO;
	
	private SystemUser user;
	
	public SystemUser get() {
		return this.user;
	}
	
	@PostConstruct
	private void loadUser(){
		Principal principal = request.getUserPrincipal();
		
		if (principal != null) {
			this.user = this.securityDAO.loadUserByUserName(principal.getName());
		}
	}
	
	public boolean hasRole(String role) {
		return this.request.isUserInRole(role);
	}
}