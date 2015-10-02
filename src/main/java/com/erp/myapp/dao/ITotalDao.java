package com.erp.myapp.dao;

import com.erp.myapp.entity.Totals;

public interface ITotalDao {
	public void addTotal(Totals t);
	public void removeTotal(Long id);
	public Totals getTotalById(Long id);
}
