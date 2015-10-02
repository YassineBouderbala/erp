package com.erp.myapp.metier;

import java.util.Collection;

import com.erp.myapp.entity.Article;
import com.erp.myapp.entity.Sortie_Vente;

public interface ISortieVenteMetier {
	public void addSortieVente(Sortie_Vente v);
	public Collection<Sortie_Vente> getSortieVenteByDevisId(Long id);
	public void removeSortieVenteByDevisIdAndUpdateStock(Long id);
	public Collection<Sortie_Vente> getAllSortieVente();
	public Collection<Sortie_Vente> getSortieVentebyArticle(Article a);
}
