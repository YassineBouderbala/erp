package com.erp.myapp.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="avoirs")
public class Avoir implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private String guid;
	
	@Column(nullable=false)
	@Size(min=1,max=30, message="Minimum: 1, Maximum : 60")
	private String reference;
	
	@Column(nullable=false)
	private String date;
		
	@Column(nullable=false)
	private boolean pdf_created;
	
	@OneToMany(mappedBy="avoir")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Collection<Line> line;
	
	@ManyToOne
	@JoinColumn(name="facture_id", nullable=false)
	private Facture facture;
	
	@ManyToOne
	@JoinColumn(name="customer_id", nullable=true)
	private Customer_Pro customer;
	
	@OneToOne(mappedBy="avoir")
	private Totals total;

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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public boolean isPdf_created() {
		return pdf_created;
	}

	public void setPdf_created(boolean pdf_created) {
		this.pdf_created = pdf_created;
	}

	public Collection<Line> getLine() {
		return line;
	}

	public void setLine(Collection<Line> line) {
		this.line = line;
	}

	public Facture getFacture() {
		return facture;
	}

	public void setFacture(Facture facture) {
		this.facture = facture;
	}

	public Customer_Pro getCustomer() {
		return customer;
	}

	public void setCustomer(Customer_Pro customer) {
		this.customer = customer;
	}

	public Totals getTotal() {
		return total;
	}

	public void setTotal(Totals total) {
		this.total = total;
	}

}
