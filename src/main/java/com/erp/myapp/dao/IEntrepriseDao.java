package com.erp.myapp.dao;

import com.erp.myapp.entity.Entreprise;

public interface IEntrepriseDao {
	public Entreprise getEntreprise();
	public void addEntreprise(Entreprise e);
	public void updateEntreprise(Entreprise e);
}
