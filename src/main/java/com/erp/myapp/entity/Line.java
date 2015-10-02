package com.erp.myapp.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.erp.myapp.utils.Article_Util;


@Entity
@Table(name="line")
public class Line implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=true)
	private String reference;
	
	@Column(nullable=false)
	private String type;
	
	@Column(nullable=false)
	@Size(min=1,max=Article_Util.lengthLine, message="Champs obligatoire, maximum de caractères "+Article_Util.lengthLine+"")
	private String description;
	
	@Column(nullable=false)
	private Long quantity;
	
	@Column(nullable=false)
	private double tva;
	
	@Column(nullable=false)
	private double prix_ht;
	
	@Column(nullable=false)
	private double total_ht;
	
	@ManyToOne
	@JoinColumn(name="devis_id", nullable=true)
	Devis devis;
	
	@ManyToOne
	@JoinColumn(name="bonDeLivraison_id", nullable=true)
	BonDeLivraison bonDeLivraison;
	
	@ManyToOne
	@JoinColumn(name="facture_id", nullable=true)
	Facture facture;
	
	@ManyToOne
	@JoinColumn(name="article_id", nullable=true)
	Article article;
	
	@ManyToOne
	@JoinColumn(name="avoir_id", nullable=true)
	Avoir avoir;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public double getTva() {
		return tva;
	}

	public void setTva(double tva) {
		this.tva = tva;
	}

	public double getPrix_ht() {
		return prix_ht;
	}

	public void setPrix_ht(double prix_ht) {
		this.prix_ht = prix_ht;
	}

	public double getTotal_ht() {
		return total_ht;
	}

	public void setTotal_ht(double total_ht) {
		this.total_ht = total_ht;
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

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Avoir getAvoir() {
		return avoir;
	}

	public void setAvoir(Avoir avoir) {
		this.avoir = avoir;
	}
}
