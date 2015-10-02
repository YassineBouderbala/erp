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

import com.erp.myapp.utils.Article_Util;
@Entity
@Table(name="articles")
public class Article implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private String guid;
	
	@Column(nullable=false)
	private String reference;
	
	@Column(nullable=false)
	private double tva;
	
	@Column(nullable=false)
	@Size(min=1,max=Article_Util.lengthArticle, message="Champs obligatoire, Maximum : "+Article_Util.lengthArticle+"")
	private String designation;
	
	@Column(nullable=false)
	private double prix_ht_achat;
	
	@Column(nullable=false)
	private double prix_ht_vente;
	
	@Column(nullable=false)
	private Long quantite;
	
	@Column(nullable=false)
	private boolean deleted;
	
	@ManyToOne
	@JoinColumn(name="fournisseur_id", nullable=false)
	private Fournisseur_Pro fournisseur;
	
	@OneToMany(mappedBy="article",cascade=CascadeType.ALL)
	private Collection<Sortie_Vente> sortie_vente;
	
	@OneToMany(mappedBy="article",cascade=CascadeType.ALL)
	private Collection<Entry_Achat> entry_achat;
	
	@OneToMany(mappedBy="article",cascade=CascadeType.ALL)
	private Collection<Line> line;

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

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Fournisseur_Pro getFournisseur() {
		return fournisseur;
	}

	public void setFournisseur(Fournisseur_Pro fournisseur) {
		this.fournisseur = fournisseur;
	}

	public Collection<Sortie_Vente> getSortie_vente() {
		return sortie_vente;
	}

	public void setSortie_vente(Collection<Sortie_Vente> sortie_vente) {
		this.sortie_vente = sortie_vente;
	}

	public Collection<Entry_Achat> getEntry_achat() {
		return entry_achat;
	}

	public void setEntry_achat(Collection<Entry_Achat> entry_achat) {
		this.entry_achat = entry_achat;
	}

	public double getTva() {
		return tva;
	}

	public void setTva(double tva) {
		this.tva = tva;
	}

	public double getPrix_ht_achat() {
		return prix_ht_achat;
	}

	public void setPrix_ht_achat(double prix_ht_achat) {
		this.prix_ht_achat = prix_ht_achat;
	}

	public double getPrix_ht_vente() {
		return prix_ht_vente;
	}

	public void setPrix_ht_vente(double prix_ht_vente) {
		this.prix_ht_vente = prix_ht_vente;
	}

	public Long getQuantite() {
		return quantite;
	}

	public void setQuantite(Long quantite) {
		this.quantite = quantite;
	}

	public Collection<Line> getLine() {
		return line;
	}

	public void setLine(Collection<Line> line) {
		this.line = line;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
}
