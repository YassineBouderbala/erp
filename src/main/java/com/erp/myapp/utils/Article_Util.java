package com.erp.myapp.utils;

import java.util.List;

import com.erp.myapp.entity.Article;
import com.erp.myapp.metier.IGlobalMetier;

public class Article_Util {
	public static final int lengthArticle = 120;
	public static final int lengthLine = 111;
	public static boolean checkUniqueArticle(IGlobalMetier metier,Article a){
		try {
			for (Article article : metier.getAllArticles()) {
				if(a.getDesignation().toLowerCase().equals(article.getDesignation().toLowerCase()) && !a.isDeleted()){
					return false;
				}
			}
			return true;			
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean isExistInStock(IGlobalMetier metier,String designation){
		try {
			for (Article article : metier.getAllArticles()) {
				if(designation.toLowerCase().equals(article.getDesignation().toLowerCase())){
					return true;
				}
			}
			return false;			
		} catch (Exception e) {
			return false;
		}
	}
	
	private static boolean isCreatableHelp(IGlobalMetier metier,List<String> argListError,String name,String prix_ht_achat,String prix_ht_vente,String tva, String quantity){
		try {
			Article a = metier.getArticleByDesignation(name);
			if(Double.parseDouble(prix_ht_achat) != a.getPrix_ht_achat() || Double.parseDouble(prix_ht_vente) != a.getPrix_ht_vente() || Double.parseDouble(tva) != a.getTva()){
				return false;
			}else{
				return true;
			}
		} catch (Exception e) {
			return false;
		}
	}

	
	public static List<String> isCreatableStock(IGlobalMetier metier,List<String> argListError,String name,String prix_ht_achat,String prix_ht_vente,String tva, String quantity){
		if(!isCreatableHelp(metier, argListError, name, prix_ht_achat, prix_ht_vente, tva, quantity)){
			argListError.add("Une erreur c'est produite");
		}
		if(name.isEmpty()){
			argListError.add("Le nom de l'article est vide");
		}
		else if(!isExistInStock(metier, name)){
			argListError.add("Le nom de l'article n'éxiste pas");
		}
		else if(name.length() > Article_Util.lengthArticle){
			argListError.add("Nom de l'article trop long. Maximum "+Article_Util.lengthArticle+" caractères.");
		}
		try {
			if(quantity.isEmpty() || Double.parseDouble(quantity) == 0){
				argListError.add("La quantité de l'article est vide");
			}			
		} catch (Exception e) {
			argListError.add("La quantité de l'article est mal formaté");
		}
		return argListError;
	}
	
	public static List<String> isCreatable(IGlobalMetier metier,Article a, List<String> argListError,String name, String prix_ht_achat,String prix_ht_vente,String tva, String quantity){
		if(name.isEmpty()){
			argListError.add("Le nom de l'article est vide");
		}
		else if(!checkUniqueArticle(metier, a)){
			argListError.add("Le nom de l'article est déjà pris");
		}
		else if(name.length() > Article_Util.lengthArticle){
			argListError.add("Nom de l'article trop long. Maximum "+Article_Util.lengthArticle+" caractères.");
		}
		try {
			if(prix_ht_achat.isEmpty() | Double.parseDouble(prix_ht_achat) == 0){
				argListError.add("Le prix d'achat de l'article est vide");
			}			
		} catch (Exception e) {
			argListError.add("Le prix d'achat de l'article est mal formaté");
		}
		try {
			if(prix_ht_vente.isEmpty() | Double.parseDouble(prix_ht_vente) == 0){
				argListError.add("Le prix de vente de l'article est vide");
			}			
		} catch (Exception e) {
			argListError.add("Le prix de vente de l'article est mal formaté");
		}
		try {
			if(tva.isEmpty() | Double.parseDouble(tva) == 0){
				argListError.add("Le taux de tva de l'article est vide");
			}			
		} catch (Exception e) {
			argListError.add("Le taux de tva de l'article est mal formaté");
		}
		try {
			if(quantity.isEmpty() || Double.parseDouble(quantity) == 0){
				argListError.add("La quantité de l'article est vide");
			}			
		} catch (Exception e) {
			argListError.add("La quantité de l'article est mal formaté");
		}
		return argListError;
	}
}
