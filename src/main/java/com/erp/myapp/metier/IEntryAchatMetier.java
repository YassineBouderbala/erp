package com.erp.myapp.metier;

import java.util.Collection;

import com.erp.myapp.entity.Article;
import com.erp.myapp.entity.Entry_Achat;

public interface IEntryAchatMetier {
	public void addEntryAchat(Entry_Achat e);
	public Collection<Entry_Achat> getAllEntryAchat();
	public Collection<Entry_Achat> getEntryAchatbyArticle(Article a);
	public void deleteEntryAchatById(Long id);
}
