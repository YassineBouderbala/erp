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
@Table(name="themes")
public class Theme implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=true)
	@Size(min=1,max=1000)
	private String footer_devis;
	
	@Column(nullable=true)
	@Size(min=1,max=1000)
	private String footer_facture;
	
	@Column(nullable=true)
	@Size(min=1,max=1000)
	private String font_color;
	
	@Column(nullable=true)
	@Size(min=1,max=1000)
	private String background_color;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFooter_devis() {
		return footer_devis;
	}

	public void setFooter_devis(String footer_devis) {
		this.footer_devis = footer_devis;
	}

	public String getFooter_facture() {
		return footer_facture;
	}

	public void setFooter_facture(String footer_facture) {
		this.footer_facture = footer_facture;
	}

	public String getFont_color() {
		return font_color;
	}

	public void setFont_color(String font_color) {
		this.font_color = font_color;
	}

	public String getBackground_color() {
		return background_color;
	}

	public void setBackground_color(String background_color) {
		this.background_color = background_color;
	}
}
