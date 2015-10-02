package com.erp.myapp.metier;

import com.erp.myapp.entity.Totals;

public interface ITotalMetier {
	public void addTotal(Totals t);
	public void removeTotal(Long id);
	public Totals getTotalById(Long id);
}
