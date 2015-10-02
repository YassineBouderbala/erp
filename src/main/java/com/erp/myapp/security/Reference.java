package com.erp.myapp.security;

import com.erp.myapp.metier.IGlobalMetier;

public class Reference {
	public static String generateArticle(IGlobalMetier metier){
		String GuidArticle = "ART00"; // On genere un UUID
		Long lastId;
		try {
			lastId = metier.getLastArticle().getId() + 1;
			return GuidArticle += lastId.toString();
		} catch (Exception e) {
			return GuidArticle += "1";
		}
	}
	public static String generateFournisseur(IGlobalMetier metier){
		String GuidFournisseur = "F00"; // On genere un UUID
		Long lastId;
		try {
			lastId = metier.getLastFournisseur().getId() + 1;
			return GuidFournisseur += lastId.toString();
		} catch (Exception e) {
			return GuidFournisseur += "1";
		}
	}
}
