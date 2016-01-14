package br.com.casadocodigo.beans;

import javax.json.Json;
import javax.json.JsonObjectBuilder;

public class Sale {
	
	private String title;
	private Book book;
	
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the book
	 */
	public Book getBook() {
		return book;
	}
	/**
	 * @param book the book to set
	 */
	public void setBook(Book book) {
		this.book = book;
	}
	public String toJson() {
		JsonObjectBuilder builder = Json.createObjectBuilder();
		builder
			.add("title", this.title)
			.add("bookId", this.book.getId());
		
		return builder.build().toString();
	}
}