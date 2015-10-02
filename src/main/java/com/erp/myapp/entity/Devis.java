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
@Table(name="devis")
public class Devis implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private String guid;
	
	@Column(nullable=false)
	@Size(min=1,max=30, message="Minimum: 1, Maximum : 30")
	private String reference;
	
	@Column(nullable=false)
	private String date;
	
	@Column(nullable=false)
	private String created_by;
	
	@Column(nullable=false)
	private boolean valid;
	
	@Column(nullable=false)
	private String date_validity;
	
	@Column(nullable=false)
	private boolean pdf_created;
	
	@ManyToOne
	@JoinColumn(name="customer_id", nullable=false)
	private Customer_Pro customer;
	
	@OneToOne(mappedBy="devis")
    private BonDeLivraison bonDeLivraison;
	
	@OneToOne(mappedBy="devis")
    private Facture facture;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="devis")
	private Collection<Line> line;
	
	@OneToMany(mappedBy="devis")
	private Collection<Sortie_Vente> sortie_vente;

	@OneToOne(mappedBy="devis")
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

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public String getDate_validity() {
		return date_validity;
	}

	public void setDate_validity(String date_validity) {
		this.date_validity = date_validity;
	}

	public BonDeLivraison getBonDeLivraison() {
		return bonDeLivraison;
	}

	public void setBonDeLivraison(BonDeLivraison bonDeLivraison) {
		this.bonDeLivraison = bonDeLivraison;
	}

	public Facture getFacture() {
		return facture;
	}

	public void setFacture(Facture facture) {
		this.facture = facture;
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

	public Customer_Pro getCustomer() {
		return customer;
	}

	public void setCustomer(Customer_Pro customer) {
		this.customer = customer;
	}

	public boolean isPdf_created() {
		return pdf_created;
	}

	public void setPdf_created(boolean pdf_created) {
		this.pdf_created = pdf_created;
	}

	public Collection<Sortie_Vente> getSortie_vente() {
		return sortie_vente;
	}

	public void setSortie_vente(Collection<Sortie_Vente> sortie_vente) {
		this.sortie_vente = sortie_vente;
	}
}
