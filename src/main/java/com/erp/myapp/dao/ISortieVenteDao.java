package com.erp.myapp.dao;

import java.util.Collection;

import com.erp.myapp.entity.Article;
import com.erp.myapp.entity.Entry_Achat;
import com.erp.myapp.entity.Sortie_Vente;

public interface ISortieVenteDao {
	public void addSortieVente(Sortie_Vente v);
	public Collection<Sortie_Vente> getSortieVenteByDevisId(Long id);
	public void removeSortieVenteByDevisIdAndUpdateStock(Long id);
	public Collection<Sortie_Vente> getAllSortieVente();
	public Collection<Sortie_Vente> getSortieVentebyArticle(Article a);
}
