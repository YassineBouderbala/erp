package com.erp.myapp.metier;

import com.erp.myapp.entity.Devis;

public interface IDevisMetier {
	public Devis getDevisByGuid(String guid);
	public Devis getLastDevis();
	public void addDevis(Devis d);
	public void updateDevis(Devis d);
	public void removeDevis(String guid);
}
