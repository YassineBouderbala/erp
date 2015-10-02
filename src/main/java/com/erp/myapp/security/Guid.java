package com.erp.myapp.security;

import java.util.UUID;

import com.erp.myapp.metier.IGlobalMetier;

public class Guid {
	public static String generateDevis(IGlobalMetier metier){
		String GuidDevis = UUID.randomUUID().toString(); // On genere un UUID
		Long lastId;
		try {
			lastId = metier.getLastDevis().getId() + 1;
			return GuidDevis += lastId.toString(); // Si il y'a un devis en base de donnée on ajoute +1 à L'id
		} catch (Exception e) {
			return GuidDevis += "1";	// Si il n'éxiste pas de devis en database alors on met 1
		}
	}
	public static String generateBDL(IGlobalMetier metier){
		String GuidBDL = UUID.randomUUID().toString(); 
		Long lastId;
		try {
			lastId = metier.getLastBDL().getId() + 1;
			return GuidBDL += lastId.toString(); 
		} catch (Exception e) {
			return GuidBDL += "1";
		}
	}
	public static String generateFacture(IGlobalMetier metier){
		String GuidBDL = UUID.randomUUID().toString();
		Long lastId;
		try {
			lastId = metier.getLastFacture().getId() + 1;
			return GuidBDL += lastId.toString();
		} catch (Exception e) {
			return GuidBDL += "1";
		}
	}
	public static String generateAvoir(IGlobalMetier metier){
		String GuidAvoir = UUID.randomUUID().toString();
		Long lastId;
		try {
			lastId = metier.getLastAvoir().getId() + 1;
			return GuidAvoir += lastId.toString();
		} catch (Exception e) {
			return GuidAvoir += "1";
		}
	}
	public static String generateArticle(IGlobalMetier metier){
		String GuidArticle = UUID.randomUUID().toString(); // On genere un UUID
		Long lastId;
		try {
			lastId = metier.getLastArticle().getId() + 1;
			return GuidArticle += lastId.toString();
		} catch (Exception e) {
			return GuidArticle += "1";
		}
	}
	public static String generateFournisseur(IGlobalMetier metier){
		String GuidFournisseur = UUID.randomUUID().toString(); // On genere un UUID
		Long lastId;
		try {
			lastId = metier.getLastFournisseur().getId()+ 1;
			return GuidFournisseur += lastId.toString();
		} catch (Exception e) {
			return GuidFournisseur += "1";
		}
	}
}
