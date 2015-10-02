package com.erp.myapp.metier;

import java.util.Collection;

import com.erp.myapp.entity.Employee;
import com.erp.myapp.entity.Fournisseur_Pro;

public interface IFournisseur_ProMetier {
	public Fournisseur_Pro getFournisseurByGuid(String guid);
	public Collection<Fournisseur_Pro> getAllFournisseur();
	public Fournisseur_Pro getLastFournisseur();
	public void addFournisseur(Fournisseur_Pro f);
	public void updateFournisseur(Fournisseur_Pro f);
	public void deleteFournisseur(Long id);
	public Fournisseur_Pro getFournisseurById(Long id);
}
