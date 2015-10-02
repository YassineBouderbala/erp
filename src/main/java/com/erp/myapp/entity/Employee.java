package com.erp.myapp.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="employees")
public class Employee implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=true)
	@Size(min=0,max=30, message="Maximum de caractères 30")
	private String lastName;
	
	@Column(nullable=true)
	@Size(min=0,max=30, message="Maximum de caractères 30")
	private String firstName;
	
	@Column(nullable=true)
	@Size(min=0,max=30, message="Maximum de caractères 30")
	private String phone1;
	
	@Column(nullable=true)
	@Size(min=0,max=30, message="Maximum de caractères 30")
	private String phone2;
	
	@Column(nullable=true)
	@Size(min=0,max=30, message="Maximum de caractères 30")
	private String fax1;
	
	@Column(nullable=true)
	@Size(min=0,max=30, message="Maximum de caractères 30")
	private String fax2;
	
	@Column(nullable=true)
	@Size(min=0,max=40, message="Maximum de caractères 40")
	private String email;
	
	@Column(nullable=true)
	@Size(min=0,max=60, message="Maximum de caractères 60")
	private String commentaire;
		
	@ManyToOne
	@JoinColumn(name="fournisseur_id", nullable=true)
	private Fournisseur_Pro fournisseur;
	
	@ManyToOne
	@JoinColumn(name="customer_id", nullable=true)
	private Customer_Pro customer;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getFax1() {
		return fax1;
	}

	public void setFax1(String fax1) {
		this.fax1 = fax1;
	}

	public String getFax2() {
		return fax2;
	}

	public void setFax2(String fax2) {
		this.fax2 = fax2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public Fournisseur_Pro getFournisseur() {
		return fournisseur;
	}

	public void setFournisseur(Fournisseur_Pro fournisseur) {
		this.fournisseur = fournisseur;
	}

	public Customer_Pro getCustomer() {
		return customer;
	}

	public void setCustomer(Customer_Pro customer) {
		this.customer = customer;
	}

}
