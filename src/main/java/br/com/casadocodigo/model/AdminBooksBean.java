package br.com.casadocodigo.model;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.servlet.http.Part;
import javax.transaction.Transactional;

import br.com.casadocodigo.beans.Author;
import br.com.casadocodigo.beans.Book;
import br.com.casadocodigo.dao.AuthorDAO;
import br.com.casadocodigo.dao.BookDAO;
import br.com.casadocodigo.infra.FileSaver;
import br.com.casadocodigo.infra.MessageHelper;

@Model
public class AdminBooksBean {
private Book product = new Book();
	
	private List<Author> authors = new ArrayList<>();
	private List<Integer> selectedAuthors = new ArrayList<Integer>();
	
	private Part summary;
	
	@Inject
	private BookDAO bookDAO;
	@Inject
	private AuthorDAO authorDAO;
	@Inject
	private MessageHelper messageHelper;
	@Inject
	private FileSaver fileSaver;
	
	@PostConstruct
	private void loadObjects() {
		this.authors = this.authorDAO.list();
	}
	
	@Transactional
	public String save() {
		String summaryPath = this.fileSaver.write("summaries", this.summary);
		
		product.setSummaryPath(summaryPath);
		
		bookDAO.save(product);
		this.clearObjects();
		
		this.messageHelper.addFlash(new FacesMessage("Livro gravado com sucesso!"));
		
		return "/livros/lista?faces-redirect=true";
	}

	private void clearObjects() {
		this.product = new Book();
		this.selectedAuthors.clear();
		
	}

	/**
	 * @return the product
	 */
	public Book getProduct() {
		return product;
	}

	/**
	 * @return the selectedAuthors
	 */
	public List<Integer> getSelectedAuthors() {
		return selectedAuthors;
	}

	/**
	 * @return the authors
	 */
	public List<Author> getAuthors() {
		return authors;
	}

	/**
	 * @param selectedAuthors the selectedAuthors to set
	 */
	public void setSelectedAuthors(List<Integer> selectedAuthors) {
		this.selectedAuthors = selectedAuthors;
	}

	/**
	 * @return the summary
	 */
	public Part getSummary() {
		return summary;
	}

	/**
	 * @param summary the summary to set
	 */
	public void setSummary(Part summary) {
		this.summary = summary;
	}	
}