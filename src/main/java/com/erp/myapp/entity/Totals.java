package com.erp.myapp.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name="totals")
public class Totals implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(nullable=false)
	private double total_ht;

	@Column(nullable=false)
	private double tva;

	@Column(nullable=false)
	private double total_ttc;

	@OneToOne
	@JoinColumn(name="devis_id", nullable=true)
	private Devis devis;
	
	@OneToOne
	@JoinColumn(name="bonDeLivraison_id", nullable=true)
	private BonDeLivraison bonDeLivraison;
	
	@OneToOne
	@JoinColumn(name="facture_id", nullable=true)
	private Facture facture;
	
	@OneToOne
	@JoinColumn(name="avoir_id", nullable=true)
	private Avoir avoir;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getTotal_ht() {
		return total_ht;
	}

	public void setTotal_ht(double total_ht) {
		this.total_ht = total_ht;
	}

	public double getTva() {
		return tva;
	}

	public void setTva(double tva) {
		this.tva = tva;
	}

	public double getTotal_ttc() {
		return total_ttc;
	}

	public void setTotal_ttc(double total_ttc) {
		this.total_ttc = total_ttc;
	}

	public Devis getDevis() {
		return devis;
	}

	public void setDevis(Devis devis) {
		this.devis = devis;
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

	public Avoir getAvoir() {
		return avoir;
	}

	public void setAvoir(Avoir avoir) {
		this.avoir = avoir;
	}
}
