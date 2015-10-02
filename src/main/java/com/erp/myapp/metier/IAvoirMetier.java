package com.erp.myapp.metier;

import com.erp.myapp.entity.Avoir;


public interface IAvoirMetier {
	public Avoir getLastAvoir();
	public void addAvoir(Avoir a);
	public Avoir getAvoirByGuid(String guid);
	public void updateAvoir(Avoir a);
}
