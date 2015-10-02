package com.erp.myapp.dao;

import java.util.Collection;

import com.erp.myapp.entity.Devis;
import com.erp.myapp.entity.Line;

public interface ILineDao {
	public void addLine(Line l);
	public Line getLineByDescription(String description);
	public Collection<Line> getLineByDevisId(Long id);
	public Collection<Line> getLineByAvoirId(Long id);
	public void removeLine(Long id);
	public Line getLineById(Long id);
	
}
