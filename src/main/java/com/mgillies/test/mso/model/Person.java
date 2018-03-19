package com.mgillies.test.mso.model;

//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(value = {"created"}, allowGetters=true)
public class Person {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@NotBlank
	private String forename;
	@NotBlank
	private String surname;
	private String email="";
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	//private final LocalDateTime created;
	private Date created;
	
	/*
	 * Once class is created, you wouldn't want to modify id or timestamp.
	 * dont allow to construct without person details.  
	 */
	
	private Person()
	{
		super();
		//created = LocalDateTime.now();
		created = Calendar.getInstance().getTime();
	}
	public Person(String surname, String forename, String email){
		this();
		this.setForename(forename);
		this.setSurname(surname);
		this.setEmail(email);
	}
	public long getId() {
		return id;
	}
	public String getForename() {
		return forename;
	}
	public void setForename(String forename) {
		this.forename = forename;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@JsonIgnore
	public Date getCreatedAsDate() {
		return created;
	}
	public String getCreated() {
		return created.toString();
	}
	
}