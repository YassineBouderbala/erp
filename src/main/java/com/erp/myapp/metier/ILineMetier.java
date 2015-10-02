package com.erp.myapp.metier;

import java.util.Collection;

import com.erp.myapp.entity.Devis;
import com.erp.myapp.entity.Line;

public interface ILineMetier {
	public void addLine(Line l);
	public Line getLineByDescription(String description);
	public Collection<Line> getLineByDevisId(Long id);
	public Collection<Line> getLineByAvoirId(Long id);
	public void removeLine(Long id);
	public Line getLineById(Long id);
}
