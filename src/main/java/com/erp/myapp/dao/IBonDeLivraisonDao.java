package com.erp.myapp.dao;

import com.erp.myapp.entity.BonDeLivraison;

public interface IBonDeLivraisonDao {
	public BonDeLivraison getLastBDL();
	public void addBDL(BonDeLivraison b);
	public void updateBDL(BonDeLivraison b);
	public void deleteBonDeLivraisonByDevisId(Long id);
	public BonDeLivraison getBonDeLivraisonById(Long id);
	public BonDeLivraison getBonDeLivraisonByGuid(String guid);
}
