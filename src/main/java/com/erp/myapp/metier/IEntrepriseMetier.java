package com.erp.myapp.metier;

import com.erp.myapp.entity.Entreprise;

public interface IEntrepriseMetier {
	public Entreprise getEntreprise();
	public void addEntreprise(Entreprise e);
	public void updateEntreprise(Entreprise e);
}
