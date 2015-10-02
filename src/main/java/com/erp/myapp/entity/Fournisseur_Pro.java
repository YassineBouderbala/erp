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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="fournisseurs")
public class Fournisseur_Pro implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	@Size(min=1,max=30, message="Minimum: 1, Maximum : 30")
	private String reference;
	
	@Column(nullable=false)
	@Size(min=1,max=30, message="Minimum: 1, Maximum : 30")
	private String name;
	
	@Column(nullable=false)
	private String guid;
	
	@Column(nullable=true)
	@Size(min=0,max=30, message="Minimum: 1, Maximum : 30")
	private String phone1;
	
	@Column(nullable=true)
	@Size(min=0,max=30, message="Minimum: 1, Maximum : 30")
	private String phone2;
	
	@Column(nullable=true)
	@Size(min=0,max=30, message="Minimum: 1, Maximum : 30")
	private String fax1;
	
	@Column(nullable=true)
	@Size(min=0,max=30, message="Minimum: 1, Maximum : 30")
	private String fax2;
	
	@Column(nullable=false)
	@Size(min=1,max=30, message="Minimum: 1, Maximum : 30")
	private String code_postal;
	
	@Column(nullable=false)
	@Size(min=1,max=90, message="Minimum: 1, Maximum : 90")
	private String address;
	
	@Column(nullable=false)
	@Size(min=1,max=40, message="Minimum: 1, Maximum : 40")
	private String city;
	
	@Column(nullable=false)
	@Size(min=1,max=40, message="Minimum: 1, Maximum : 40")
	private String country;
	
	@Column(unique=true,nullable=false)
	@Size(min=1,max=40, message="Minimum: 1, Maximum : 40")
	private String email;
	
	@OneToMany(mappedBy="fournisseur",cascade=CascadeType.ALL)
	private Collection<BonDeLivraison> bonDeLivraison;
	
	@OneToMany(mappedBy="fournisseur",cascade=CascadeType.ALL)
	private Collection<Facture> facture;
	
	@OneToMany(mappedBy="fournisseur",cascade=CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private Collection<Article> article;
	
	@OneToMany(mappedBy="fournisseur",cascade=CascadeType.ALL)
	private Collection<Employee> employee;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
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

	public String getCode_postal() {
		return code_postal;
	}

	public void setCode_postal(String code_postal) {
		this.code_postal = code_postal;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Collection<Article> getArticle() {
		return article;
	}

	public void setArticle(Collection<Article> article) {
		this.article = article;
	}

	public Collection<Employee> getEmployee() {
		return employee;
	}

	public void setEmployee(Collection<Employee> employee) {
		this.employee = employee;
	}
}
