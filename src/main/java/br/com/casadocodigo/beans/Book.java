package br.com.casadocodigo.beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Cacheable
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank
	private String title;
	
	@NotBlank
	@Length(min = 10)
	private String description;
	
	@Min(50)
	private int numberOfPages;
	
	@DecimalMin("20")
	private BigDecimal price;
	
	@ManyToMany
	@NotNull
	@Size(min = 1)
	@XmlElement(name = "author")
	@XmlElementWrapper(name = "authors")
	private List<Author> authors = new ArrayList<Author>();
	
	@NotNull
	@Future
	private Calendar releaseDate;
	
	private String summaryPath;
	private String coverPath;
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the numberOfPages
	 */
	public int getNumberOfPages() {
		return numberOfPages;
	}
	/**
	 * @param numberOfPages the numberOfPages to set
	 */
	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}
	/**
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	/**
	 * @return the authors
	 */
	public List<Author> getAuthors() {
		return authors;
	}
	/**
	 * @param authors the authors to set
	 */
	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}	
	
	public void add(Author author) {
		this.authors.add(author);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", description=" + description + ", numberOfPages="
				+ numberOfPages + ", price=" + price + "]";
	}
	/**
	 * @return the releaseDate
	 */
	public Calendar getReleaseDate() {
		return releaseDate;
	}
	/**
	 * @param releaseDate the releaseDate to set
	 */
	public void setReleaseDate(Calendar releaseDate) {
		this.releaseDate = releaseDate;
	}
	/**
	 * @return the summaryPath
	 */
	public String getSummaryPath() {
		return summaryPath;
	}
	/**
	 * @param summaryPath the summaryPath to set
	 */
	public void setSummaryPath(String summaryPath) {
		this.summaryPath = summaryPath;
	}
	/**
	 * @return the coverPath
	 */
	public String getCoverPath() {
		return coverPath;
	}
	/**
	 * @param coverPath the coverPath to set
	 */
	public void setCoverPath(String coverPath) {
		this.coverPath = coverPath;
	}
}