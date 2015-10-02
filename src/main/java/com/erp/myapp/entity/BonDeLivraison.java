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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="bons_de_livraison")
public class BonDeLivraison implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private String guid;
	
	@Column(nullable=false)
	@Size(min=1,max=30, message="Minimum: 1, Maximum : 60")
	private String reference;
	
	private boolean delivred;
	
	@Column(nullable=false)
	private String date;
	
	@Column(nullable=false)
	private String created_by;
	
	@Column(nullable=false)
	private String date_livraison;
	
	@Column(nullable=false)
	private boolean pdf_created;
	
	@Column(nullable=true)
	private String date_delivred;
		
	@ManyToOne
	@JoinColumn(name="fournisseur_id", nullable=true)
	private Fournisseur_Pro fournisseur;
	
	@ManyToOne
	@JoinColumn(name="customer_id", nullable=true)
	private Customer_Pro customer;
	
	@OneToOne
	@JoinColumn(name="devis_id", nullable=true)
	private Devis devis;
	
	@OneToMany(mappedBy="bonDeLivraison")
	private Collection<Line> line;

	
	@OneToOne(mappedBy="bonDeLivraison")
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

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public String getDate_livraison() {
		return date_livraison;
	}

	public void setDate_livraison(String date_livraison) {
		this.date_livraison = date_livraison;
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

	public Devis getDevis() {
		return devis;
	}

	public void setDevis(Devis devis) {
		this.devis = devis;
	}

	public Collection<Line> getLine() {
		return line;
	}

	public void setLine(Collection<Line> line) {
		this.line = line;
	}

	public Totals getTotal() {
		return total;
	}

	public void setTotal(Totals total) {
		this.total = total;
	}

	public boolean isPdf_created() {
		return pdf_created;
	}

	public void setPdf_created(boolean pdf_created) {
		this.pdf_created = pdf_created;
	}

	public boolean isDelivred() {
		return delivred;
	}

	public void setDelivred(boolean delivred) {
		this.delivred = delivred;
	}

	public String getDate_delivred() {
		return date_delivred;
	}

	public void setDate_delivred(String date_delivred) {
		this.date_delivred = date_delivred;
	}	
}
