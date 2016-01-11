package br.com.casadocodigo.infra;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

public class MessageHelper {

	@Inject
	private FacesContext facesContext;
	
	public void addFlash(FacesMessage facesMessage) {
		this.facesContext.getExternalContext().getFlash().setKeepMessages(true);
		this.facesContext.addMessage(null, facesMessage);
	}
	
}
