package com.erp.myapp.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="entreprise")
public class Entreprise implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	@Size(min=1,max=80, message="Ce champs doit être compris entre {min} et {max} caractères")
	private String name;
	
	@Column(nullable=false)
	@Size(min=1,max=80, message="Ce champs doit être compris entre {min} et {max} caractères")
	private String address;
	
	@Column(nullable=false)
	@Size(min=1,max=20, message="Ce champs doit être compris entre {min} et {max} caractères")
	private String code_postal;
	
	@Column(nullable=false)
	@Size(min=1,max=60, message="Ce champs doit être compris entre {min} et {max} caractères")
	private String city;
	
	@Column(nullable=false)
	@Size(min=1,max=60, message="Ce champs doit être compris entre {min} et {max} caractères")
	private String country;
	
	@Column(nullable=true)
	private String logo;
	
	@Column(nullable=false)
	@Size(min=1,max=20, message="Ce champs doit être compris entre {min} et {max} caractères")
	private String phone;
	
	@Column(nullable=true)
	private String fax;
	
	@Column(nullable=false)
	@Size(min=1,max=50, message="Ce champs doit être compris entre {min} et {max} caractères")
	private String email;
	
	@Column(nullable=false)
	@Size(min=1,max=20, message="Ce champs doit être compris entre {min} et {max} caractères")
	private String capital;
	
	@Column(nullable=false)
	@Size(min=1,max=20, message="Ce champs doit être compris entre {min} et {max} caractères")
	private String forme_juridique;
	
	@Column(nullable=false)
	@Size(min=1,max=30, message="Ce champs doit être compris entre {min} et {max} caractères")
	private String siren_number;
	
	@Column(nullable=false)
	@Size(min=1,max=30, message="Ce champs doit être compris entre {min} et {max} caractères")
	private String tv_number;
	
	@Column(nullable=false)
	@Size(min=1,max=20, message="Ce champs doit être compris entre {min} et {max} caractères")
	private String money_type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCode_postal() {
		return code_postal;
	}

	public void setCode_postal(String code_postal) {
		this.code_postal = code_postal;
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

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public String getForme_juridique() {
		return forme_juridique;
	}

	public void setForme_juridique(String forme_juridique) {
		this.forme_juridique = forme_juridique;
	}

	public String getSiren_number() {
		return siren_number;
	}

	public void setSiren_number(String siren_number) {
		this.siren_number = siren_number;
	}

	public String getTv_number() {
		return tv_number;
	}

	public void setTv_number(String tv_number) {
		this.tv_number = tv_number;
	}

	public String getMoney_type() {
		return money_type;
	}

	public void setMoney_type(String money_type) {
		this.money_type = money_type;
	}

}
