package com.erp.myapp.utils;

import com.erp.myapp.entity.Fournisseur_Pro;
import com.erp.myapp.metier.IGlobalMetier;

public class Fournisseur_Util {
	public static Long idForUpdate = null;
	
	public static boolean isUniqueEmail(Fournisseur_Pro fournisseur,IGlobalMetier metier){
		for (Fournisseur_Pro f : metier.getAllFournisseur()) {
			if(f.getEmail().toLowerCase().equals(fournisseur.getEmail().toLowerCase()) && f.getId() != fournisseur.getId()){
				return false;
			}
		}
		return true;
	}
	
	public static boolean isUniqueName(Fournisseur_Pro fournisseur,IGlobalMetier metier){
		for (Fournisseur_Pro f : metier.getAllFournisseur()) {
			if(f.getName().toLowerCase().equals(fournisseur.getName().toLowerCase()) && f.getId() != fournisseur.getId()){
				return false;
			}
		}
		return true;
	}
}
