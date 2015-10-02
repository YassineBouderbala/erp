package com.erp.myapp.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.erp.myapp.entity.Article;
import com.erp.myapp.entity.Avoir;
import com.erp.myapp.entity.Devis;
import com.erp.myapp.entity.Facture;
import com.erp.myapp.entity.Line;
import com.erp.myapp.entity.Sortie_Vente;
import com.erp.myapp.metier.IGlobalMetier;

public class Stock_Util {
	private static List<Line> articleNotDestockable(List<Line> argLineDevis,String argType, List<Line> argTab,IGlobalMetier metier) throws Exception {
		List<Line> ListArticleNotDestockable = new ArrayList<Line>();
		List<Line> ListLineDevis = new ArrayList<Line>();
		ListLineDevis = argLineDevis;
		for (int cmp = 0; cmp < argLineDevis.size(); cmp++) { // Fonction qui compare si il y'a des meme lignes avec le meme materiel et les fusionnes en mettnt à jour les quantitées
			for (int compteur = 0; compteur < ListLineDevis.size(); compteur++) {
				if (argLineDevis.get(cmp).getDescription().toLowerCase().equals(ListLineDevis.get(compteur).getDescription().toLowerCase()) && compteur != cmp && argLineDevis.get(cmp).getType().toLowerCase().equals("matériel")) {
					argLineDevis.get(cmp).setQuantity(ListLineDevis.get(compteur).getQuantity() + argLineDevis.get(cmp).getQuantity());
					argLineDevis.get(compteur).setDescription("null");
					ListLineDevis.get(compteur).setDescription("null");
				}
				else if (argLineDevis.get(cmp).getDescription().toLowerCase().equals(ListLineDevis.get(compteur).getDescription().toLowerCase()) && compteur == cmp && argLineDevis.get(cmp).getType().toLowerCase().equals("matériel")) {
					String description = argLineDevis.get(cmp).getDescription();
					ListLineDevis.get(compteur).setDescription("null");
					argLineDevis.get(cmp).setDescription(description);
				}
			}
		}
		Iterator<Line> iterator = argLineDevis.iterator(); // Suppression de toutes les descriptions ayant null ( doublons)
		while ( iterator.hasNext() ) {
			Line l = iterator.next();
			if (l.getDescription().equals("null")) {
				iterator.remove();
			}
		}
		if(argType.toLowerCase().equals("devis")){
			for (int cmp = 0; cmp < argLineDevis.size(); cmp++) { // On ajoute les articles qui ne peuvent pas être stocke
				if (argLineDevis.get(cmp).getType().toLowerCase().equals("matériel")) {
					if (argLineDevis.get(cmp).getQuantity() > argLineDevis.get(cmp).getArticle().getQuantite()) {
						ListArticleNotDestockable.add(argLineDevis.get(cmp));
					}
				}
			}
		}
		else{
			List<String> descriptionOfLineAvoir = new ArrayList<String>();
			List<String> descriptionOfTypeAvoir = new ArrayList<String>();
			int quantity = 0; // quantité de la somme de facture et des avoirs
			int quantity2 = 0; // quantité pour faire un avoir
			for (int cmp = 0; cmp < argLineDevis.size(); cmp++) { // On ajoute les articles qui ne peuvent pas être stocke
				if (argLineDevis.get(cmp).getType().toLowerCase().equals("matériel")) {
					Facture f = argLineDevis.get(cmp).getDevis().getFacture();
					quantity += argLineDevis.get(cmp).getQuantity();
					for (Avoir a : f.getAvoir()) {
						for (Line line : a.getLine()) {
							if(line.getDescription().equals(argLineDevis.get(cmp).getDescription())){ // Si les avoirs contiennes le materiel alors on decremente la quantite total
								quantity -= line.getQuantity();
							}
							descriptionOfLineAvoir.add(line.getDescription()); //Prend la description de la ligne
							descriptionOfTypeAvoir.add(line.getType());
						}
					}
					for (Line line : argTab) { // Tableau d'envoi pour crée un avoir
						if(line.getDescription().equals(argLineDevis.get(cmp).getDescription())){
							quantity2 += line.getQuantity();
						}
						descriptionOfLineAvoir.add(line.getDescription()); //Prend la description de la ligne
						descriptionOfTypeAvoir.add(line.getType());
					}
					if ((isExistToFacture(argLineDevis, descriptionOfLineAvoir,descriptionOfTypeAvoir) == false && quantity2 > quantity ) || (isExistToFacture(argLineDevis, descriptionOfLineAvoir,descriptionOfTypeAvoir) == false && quantity2 == 0) || isExistToFacture(argLineDevis, descriptionOfLineAvoir,descriptionOfTypeAvoir) == false || (quantity2 > quantity) ) {
						argLineDevis.get(cmp).setQuantity((long)quantity);
						ListArticleNotDestockable.add(argLineDevis.get(cmp));
					}
					quantity = 0;
					quantity2 = 0;
				}
			}			
		}
		return ListArticleNotDestockable;
	}
	
	private static boolean isDestockable(List<Line> argLineDevis) throws Exception {
		if(argLineDevis.size() == 0){
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static List<Line> updateStock(String argType,String argGuid, List<Line> argTab,IGlobalMetier metier) throws Exception{
		Devis d = null;
		Facture facture = null;
		if(argType.toLowerCase().equals("devis")){
			d = metier.getDevisByGuid(argGuid);
		}
		else
		{
			facture = metier.getFactureByGuid(argGuid);
		}
		List<Line> listOfArticleToStock = (argType.toLowerCase().equals("devis")) ? Stock_Util.articleNotDestockable((List<Line>) metier.getLineByDevisId(d.getId()),"devis",null,metier) : Stock_Util.articleNotDestockable((List<Line>) metier.getLineByDevisId(facture.getDevis().getId()),"facture", argTab,metier);
		SimpleDateFormat formater = null;
		Date aujourdhui = new Date();
		formater = new SimpleDateFormat("dd-MM-yy");
		Long quantite = (long)0;
		Article a = null;
		if(argType.toLowerCase().equals("devis")){
			if(Stock_Util.isDestockable(listOfArticleToStock)){ // Les articles destockable on les mets en base de données
				System.out.println("Destockable");
				for (Line l : metier.getLineByDevisId(d.getId())) {
					if(l.getType().toLowerCase().equals("matériel"))
					{
						a = metier.getArticleByDesignation(l.getDescription());
						Sortie_Vente v = new Sortie_Vente();
						v.setQuantity(l.getQuantity());
						a.setQuantite(a.getQuantite() - l.getQuantity());
						v.setArticle(l.getArticle());
						v.setDate(formater.format(aujourdhui));
						v.setDevis(d);
						metier.addSortieVente(v);
						metier.updateArticle(a);
					}
				}
				return listOfArticleToStock;
			}else{
				System.out.println("Non destockable cause :\n");
				for (Line l : listOfArticleToStock) {
					System.out.println(l.getDescription() +" quantité : "+ l.getQuantity() + " reste en stock : " + l.getArticle().getQuantite());
				}
				return listOfArticleToStock;
			}
		}
		else
		{
			return listOfArticleToStock;
		}
	}
	
	private static boolean isExistToFacture(List<Line> tabFacture, List<String> descriptionAvoir, List<String> typeAvoir){
		boolean exist = true;
		int cmp = 0;
		List<Boolean> listBoolean = new ArrayList<Boolean>();
		for (String string : descriptionAvoir) {
			for (Line line : tabFacture) {
				if(!line.getDescription().equals(string) && !typeAvoir.get(cmp).toLowerCase().equals("heure") ){ // si c'est pas trouvé dans la facture et c'est pas égale à une heure
					listBoolean.add(false);
				}else{
					listBoolean.add(true);
				}
			}
			if(isAllEqualToFalse(listBoolean)){ // Si tout est égal a false alors sa n'existe pas
				exist = false;
				break;
			}
			listBoolean.clear();
			cmp++;
		}
		return exist;
	}
	
    private static boolean isAllEqualToFalse(List<Boolean> listBoolean){
        for(int i=0; i<listBoolean.size(); i++){
        	if(listBoolean.size() > 1){
                if((listBoolean.get(0) != listBoolean.get(i) || listBoolean.get(i) == true )){ // Si tout les elements sont les meme et c'est à true alors on met false
                    return false;
                }        		
        	}else{ // pour un seul article de l'avoir
                if(listBoolean.get(i) == false){
                    return true;
                }
                else
                {
                	return false;
                }
        	}
        }
        return true;
    }
}
