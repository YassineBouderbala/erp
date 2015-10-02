package com.erp.myapp.dao;

import java.util.Collection;

import com.erp.myapp.entity.Article;

public interface IArticleDao {
	public Collection<Article> getAllArticles();
	public Article getArticleByDesignation(String designation);
	public void updateArticle(Article a);
	public Article getLastArticle();
	public void addArticle(Article a);
	public Article getArticleByReference(String reference);
	public Collection<Article> getArticleCroissantByQantity();
	public Collection<Article> getArticleCroissantByPrixAchat();
	public Collection<Article> getArticleCroissantByPrixVente();
}
