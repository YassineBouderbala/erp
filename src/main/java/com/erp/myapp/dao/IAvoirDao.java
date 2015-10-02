package com.erp.myapp.dao;

import com.erp.myapp.entity.Avoir;

public interface IAvoirDao {
	public Avoir getLastAvoir();
	public void addAvoir(Avoir a);
	public Avoir getAvoirByGuid(String guid);
	public void updateAvoir(Avoir a);
}
