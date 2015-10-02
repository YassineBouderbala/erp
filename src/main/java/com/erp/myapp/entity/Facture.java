package com.erp.myapp.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

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

import org.hibernate.FetchMode;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.IndexColumn;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="factures")
public class Facture implements Serializable {
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
	private String created_by;
	
	@Column(nullable=false)
	private boolean payed;
	
	@Column(nullable=false)
	private String delay_paiement;
	
	@Column(nullable=false)
	private String mode_paiement;
	
	@Column(nullable=true)
	private String date_paiement;
	
	@Column(nullable=false)
	private boolean pdf_created;
	
	@ManyToOne
	@JoinColumn(name="fournisseur_id", nullable=true)
	private Fournisseur_Pro fournisseur;
	
	@ManyToOne
	@JoinColumn(name="customer_id", nullable=true)
	private Customer_Pro customer;
	
	@OneToOne
	@JoinColumn(name="devis_id", nullable=true)
	private Devis devis;
	
	@OneToMany(mappedBy="facture")
	private Collection<Line> line;
	
	@OneToMany(mappedBy="facture")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Collection<Avoir> avoir;
	
	@OneToOne(mappedBy="facture")
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

	public boolean isPayed() {
		return payed;
	}

	public void setPayed(boolean payed) {
		this.payed = payed;
	}

	public String getDelay_paiement() {
		return delay_paiement;
	}

	public void setDelay_paiement(String delay_paiement) {
		this.delay_paiement = delay_paiement;
	}

	public String getMode_paiement() {
		return mode_paiement;
	}

	public void setMode_paiement(String mode_paiement) {
		this.mode_paiement = mode_paiement;
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

	public Collection<Avoir> getAvoir() {
		return avoir;
	}

	public void setAvoir(Collection<Avoir> avoir) {
		this.avoir = avoir;
	}

	public String getDate_paiement() {
		return date_paiement;
	}

	public void setDate_paiement(String date_paiement) {
		this.date_paiement = date_paiement;
	}
}
