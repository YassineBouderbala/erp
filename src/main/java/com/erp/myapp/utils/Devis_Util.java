package com.erp.myapp.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;

import com.erp.myapp.entity.Article;
import com.erp.myapp.entity.Customer_Pro;
import com.erp.myapp.entity.Devis;
import com.erp.myapp.entity.Line;
import com.erp.myapp.entity.Totals;
import com.erp.myapp.metier.IGlobalMetier;
import com.erp.myapp.security.*;

public class Devis_Util {
	public static void create(String date_validity,List<String> typeList,List<String> referenceList,List<String> designationList,List<String> quantityList,List<String> tvaListList,List<String> prix_htList,List<String> total_htList,IGlobalMetier metier,Customer_Pro c) throws Exception{
		Long reference;
		try {
			reference = metier.getLastDevis().getId() + 1; // we create a reference
		} catch (Exception e) {
			reference = (long)1;
		}
		Devis d = new Devis();
		d.setGuid(Guid.generateDevis(metier));
		d.setCreated_by(SecurityContextHolder.getContext().getAuthentication().getName());
		d.setDate(Datetime.getCurrentDate());
		d.setDate_validity(date_validity);
		d.setReference("D00"+reference.toString());
		d.setCustomer(c);
		metier.addDevis(d);
		Devis_Util.insertLine(typeList, referenceList, designationList, quantityList, tvaListList, prix_htList, total_htList, d, metier);
	}
	
	private static void insertLine(List<String> typeList,List<String> referenceList,List<String> designationList,List<String> quantityList,List<String> tvaList,List<String> prix_htList,List<String> total_htList,Devis d,IGlobalMetier metier)throws Exception{
		List<Line> lineDevisList = new ArrayList<Line>();
		
		int sizeOfArray = referenceList.size();
		for (int i = 0; i < sizeOfArray; i++) { // Chaque itération on crée un objet line
			Article a = new Article();
			try {
				a = metier.getArticleByDesignation(designationList.get(i));
			} catch (Exception e) {
				a = null;
			}
			Line line = new Line();
			line.setType(typeList.get(i));
			line.setReference(referenceList.get(i));
			line.setDescription(designationList.get(i));
			line.setQuantity(Long.parseLong(quantityList.get(i)));
			line.setTva(Double.parseDouble(tvaList.get(i)));
			line.setPrix_ht(Double.parseDouble(prix_htList.get(i)));
			line.setTotal_ht((double)Math.round((Double.parseDouble(quantityList.get(i)) * Double.parseDouble(prix_htList.get(i))) * 100) / 100);
			line.setDevis(d);
			line.setArticle(a);
			lineDevisList.add(line);
		}
		for (Line line : lineDevisList) {
			metier.addLine(line);
		}
		insertTotal(lineDevisList, d, metier);
	}
	
	private static void insertTotal(List<Line> lineDevisList, Devis d,IGlobalMetier metier) throws Exception{
		Totals total = new Totals();
		Double total_ht = 0.0;
		Double total_tva = 0.0;
		List<Double> ListIntermediateTva = new ArrayList<Double>();
		boolean addTva;
		for (Line line_devis : lineDevisList) {
			addTva = true;
			total_ht += line_devis.getTotal_ht(); // Total Ht est egal a tout les totaux du tableau			
			for (Double number : ListIntermediateTva) {
				if(number == line_devis.getTva()){ // On met tout les types de tva dans un tableau sans duplication
					addTva = false;
					break;
				}
			}
			if(addTva == true){
				ListIntermediateTva.add(line_devis.getTva());;
			}
		}
		for (Double tva : ListIntermediateTva) { // Le total de la tva est la multiplacation du total ht multipliée par la tva contenu de le tableau
			total_tva +=  (total_ht * tva) / 100;
			total_tva = (double)Math.round(total_tva * 100) / 100;
		}
		total.setTotal_ht((double)Math.round(total_ht * 100) / 100);
		total.setTva(total_tva); // Calcul de la tva
		total.setTotal_ttc((double)Math.round((total_ht + total_tva) * 100) / 100);
		total.setDevis(d);
		metier.addTotal(total);	
	}
	
	public static void update(String date_validity,List<String> typeList,List<String> referenceList,List<String> designationList,List<String> quantityList,List<String> tvaListList,List<String> prix_htList,List<String> total_htList,IGlobalMetier metier,Customer_Pro c,Devis d) throws Exception{
		d.setDate(Datetime.getCurrentDate());
		d.setDate_validity(date_validity);
		metier.updateDevis(d);
		Devis_Util.updateLine(typeList, referenceList, designationList, quantityList, tvaListList, prix_htList, total_htList, d, metier);
	}
	
	private static void updateLine(List<String> typeList,List<String> referenceList,List<String> designationList,List<String> quantityList,List<String> tvaList,List<String> prix_htList,List<String> total_htList,Devis d,IGlobalMetier metier)throws Exception{
		List<Line> lineDevisList = new ArrayList<Line>();
		
		int sizeOfArray = referenceList.size();
		for (int i = 0; i < sizeOfArray; i++) { // Chaque itération on crée un objet line
			Article a = new Article();
			try {
				a = metier.getArticleByDesignation(designationList.get(i));
			} catch (Exception e) {
				a = null;
			}
			Line line = new Line();
			line.setType(typeList.get(i));
			line.setReference(referenceList.get(i));
			line.setDescription(designationList.get(i));
			line.setQuantity(Long.parseLong(quantityList.get(i)));
			line.setTva(Double.parseDouble(tvaList.get(i)));
			line.setPrix_ht(Double.parseDouble(prix_htList.get(i)));
			line.setTotal_ht((double)Math.round((Double.parseDouble(quantityList.get(i)) * Double.parseDouble(prix_htList.get(i))) * 100) / 100);
			line.setDevis(d);
			line.setArticle(a);
			lineDevisList.add(line);
		}
		for (Line line : d.getLine()) {
			metier.removeLine(line.getId());
		}
		for (Line line : lineDevisList) {
			metier.addLine(line);
		}
		updateTotal(lineDevisList, d, metier);
	}
	
	private static void updateTotal(List<Line> lineDevisList, Devis d,IGlobalMetier metier) throws Exception{
		Totals total = new Totals();
		Double total_ht = 0.0;
		Double total_tva = 0.0;
		List<Double> ListIntermediateTva = new ArrayList<Double>();
		boolean addTva;
		for (Line line_devis : lineDevisList) {
			addTva = true;
			total_ht += line_devis.getTotal_ht(); // Total Ht est egal a tout les totaux du tableau			
			for (Double number : ListIntermediateTva) {
				if(number == line_devis.getTva()){ // On met tout les types de tva dans un tableau sans duplication
					addTva = false;
					break;
				}
			}
			if(addTva == true){
				ListIntermediateTva.add(line_devis.getTva());;
			}
		}
		for (Double tva : ListIntermediateTva) { // Le total de la tva est la multiplacation du total ht multipliée par la tva contenu de le tableau
			total_tva +=  (total_ht * tva) / 100;
			total_tva = (double)Math.round(total_tva * 100) / 100;
		}
		total.setTotal_ht((double)Math.round(total_ht * 100) / 100);
		total.setTva(total_tva); // Calcul de la tva
		total.setTotal_ttc((double)Math.round((total_ht + total_tva) * 100) / 100);
		total.setDevis(d);
		metier.removeTotal(d.getTotal().getId());
		metier.addTotal(total);
	}
	
	public static boolean isCreatable(List<String> type,List<String> description,List<String> reference,List<String> tva,List<String> quantity, List<String> prix_ht,IGlobalMetier metier){
		Boolean create = true;
		for (int cmp = 0; cmp < type.size(); cmp++) {
			if(Double.parseDouble(tva.get(cmp)) < 0 || Double.parseDouble(quantity.get(cmp)) < 0 || Double.parseDouble(prix_ht.get(cmp)) < 0){
				create = false;
				break;
			}
			else if(type.get(cmp).toLowerCase().equalsIgnoreCase("matériel")){ // Si le type est métier
				try {
					Article a = metier.getArticleByDesignation(description.get(cmp)); // On prend l'Article en fonction de la déscription donnée
					if(!( a.getDesignation().toLowerCase().equals(description.get(cmp).toLowerCase()) && a.getReference().toLowerCase().equals(reference.get(cmp).toLowerCase()) && a.getTva() == Double.parseDouble(tva.get(cmp)) && a.getPrix_ht_vente() == Double.parseDouble(prix_ht.get(cmp)))){
						create = false;
						break;
					}
				} catch (Exception e) {
					create = false;
					break;
				}
			}
			else if(!type.get(cmp).toLowerCase().equalsIgnoreCase("Matériel") && !type.get(cmp).toLowerCase().equalsIgnoreCase("heure") ){ // si c'est ni égal à matériel et ni égal à heure
				create = false;
				break;
			}
			else if(type.get(cmp).toLowerCase().equalsIgnoreCase("heure") && !reference.get(cmp).equalsIgnoreCase("")){ // Si type heure mais designation n'est pas null
				create = false;
				break;				
			}
		}
		return create;
	}
}
