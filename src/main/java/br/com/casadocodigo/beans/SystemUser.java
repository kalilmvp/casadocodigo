package br.com.casadocodigo.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import br.com.casadocodigo.beans.validation.groups.BuyerGroup;

@Entity
public class SystemUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Email
	@NotBlank
	@Column(unique = true)
	private String email;
	
	@NotBlank(groups = BuyerGroup.class)
	private String firstName;
	
	@NotBlank(groups = BuyerGroup.class)
	private String lastName;
	
	@NotBlank(groups = BuyerGroup.class)
	@Column(unique = true)
	private String socialId;
	
	@NotBlank(groups = BuyerGroup.class)
	private String address;
	
	@NotBlank(groups = BuyerGroup.class)
	private String city;
	
	@NotBlank(groups = BuyerGroup.class)
	private String state;
	
	@NotBlank(groups = BuyerGroup.class)
	private String postalCode;
	
	@NotBlank(groups = BuyerGroup.class)
	private String phone;
	
	@NotBlank(groups = BuyerGroup.class)
	private String country;
	
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<SystemRole> roles = new ArrayList<>();

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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the socialId
	 */
	public String getSocialId() {
		return socialId;
	}

	/**
	 * @param socialId the socialId to set
	 */
	public void setSocialId(String socialId) {
		this.socialId = socialId;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}