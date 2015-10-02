package com.erp.myapp.dao;

import java.util.Collection;

import com.erp.myapp.entity.Facture;

public interface IFactureDao {
	public void addFacture(Facture f);
	public void updateFacture(Facture f);
	public Facture getLastFacture();
	public void deleteFactureByDevisId(Long id);
	public Facture getFactureById(Long id);
	public Facture getFactureByGuid(String guid);
	public Collection<Facture> getAllFacture();
}
