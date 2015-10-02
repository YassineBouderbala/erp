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
@Table(name="customers")
public class Customer_Pro implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false,unique = true)
	private String guid;

	@Column(nullable=false,unique = true)
	private String reference;

	@Column(unique=true,nullable=false)
	@Size(min=1,max=60, message="Champs obligatoire, maximum de caractères 30")
	private String name;
	
	@Column(nullable=false)
	@Size(min=1,max=20, message="Champs obligatoire, maximum de caractères 20")
	private String code_postal;
	
	@Column(nullable=true)
	@Size(min=0,max=30, message="Maximum de caractères 30")
	private String phone1;
	
	@Column(nullable=true)
	@Size(min=0,max=30, message="Maximum de caractères 30")
	private String phone2;

	@Column(nullable=true)
	@Size(min=0,max=30, message="Maximum de caractères 30")
	private String fax;

	@Column(nullable=false) 
	@Size(min=1,max=60, message="Champs obligatoire, maximum de caractères 60")
	private String address;
	
	@Column(nullable=false) 
	@Size(min=1,max=40, message="Champs obligatoire, maximum de caractères 40")
	private String country;
	
	@Column(nullable=false)
	@Size(min=1,max=40, message="Champs obligatoire, maximum de caractères 40")
	private String city;
	
	@Column(unique=false, nullable=false)
	@Size(min=0,max=40, message="Champs obligatoire, maximum de caractères 40")
	private String email;
	
	@OneToMany(mappedBy="customer",cascade=CascadeType.ALL)
	private Collection<Devis> devis;
	
	@OneToMany(mappedBy="customer",cascade=CascadeType.ALL)
	private Collection<BonDeLivraison> bonDeLivraison;
	
	@OneToMany(mappedBy="customer",cascade=CascadeType.ALL)
	private Collection<Facture> facture;
	
	@OneToMany(mappedBy="customer",cascade=CascadeType.ALL)
	private Collection<Employee> employee;
	
	@OneToMany(mappedBy="customer",cascade=CascadeType.ALL)
	private Collection<Avoir> avoir;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode_postal() {
		return code_postal;
	}

	public void setCode_postal(String code_postal) {
		this.code_postal = code_postal;
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

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Collection<Devis> getDevis() {
		return devis;
	}

	public void setDevis(Collection<Devis> devis) {
		this.devis = devis;
	}

	public Collection<BonDeLivraison> getBonDeLivraison() {
		return bonDeLivraison;
	}

	public void setBonDeLivraison(Collection<BonDeLivraison> bonDeLivraison) {
		this.bonDeLivraison = bonDeLivraison;
	}

	public Collection<Facture> getFacture() {
		return facture;
	}

	public void setFacture(Collection<Facture> facture) {
		this.facture = facture;
	}

	public Collection<Employee> getEmployee() {
		return employee;
	}

	public void setEmployee(Collection<Employee> employee) {
		this.employee = employee;
	}

	public Collection<Avoir> getAvoir() {
		return avoir;
	}

	public void setAvoir(Collection<Avoir> avoir) {
		this.avoir = avoir;
	}
	
}
