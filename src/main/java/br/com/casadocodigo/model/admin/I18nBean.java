package br.com.casadocodigo.model.admin;

import java.io.Serializable;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class I18nBean implements Serializable {
	private static final long serialVersionUID = 5460492062510617349L;
	
	private Locale locale;
	
	@Inject
	private FacesContext facesContext;
	
	@PostConstruct
	public void loadDefaultLocale() {
		this.locale = this.facesContext.getApplication().getDefaultLocale();
	}
	
	public String changeLocale(String language) {
		this.locale = new Locale(language);
		return "site/index.xhtml?faces-redirect=true";
	}
	
	public Locale getLocale() {
		return this.locale;
	}
}