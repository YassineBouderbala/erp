package com.erp.myapp.metier;

import java.util.Collection;

import com.erp.myapp.entity.Facture;

public interface IFactureMetier {
	public Collection<Facture> getAllFacture();
	public void addFacture(Facture f);
	public void updateFacture(Facture f);
	public Facture getLastFacture();
	public void deleteFactureByDevisId(Long id);
	public Facture getFactureById(Long id);
	public Facture getFactureByGuid(String guid);
}
