package com.erp.myapp.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;

import com.erp.myapp.entity.Article;
import com.erp.myapp.entity.Avoir;
import com.erp.myapp.entity.Customer_Pro;
import com.erp.myapp.entity.Devis;
import com.erp.myapp.entity.Facture;
import com.erp.myapp.entity.Line;
import com.erp.myapp.entity.Totals;
import com.erp.myapp.metier.IGlobalMetier;
import com.erp.myapp.security.Datetime;
import com.erp.myapp.security.Guid;

public class Avoir_Util {
	public static void create(List<String> typeList,List<String> referenceList,List<String> designationList,List<String> quantityList,List<String> tvaListList,List<String> prix_htList,List<String> total_htList,IGlobalMetier metier,Facture f) throws Exception{
		Long reference;
		try {
			reference = metier.getLastAvoir().getId() + 1; // we create a reference
		} catch (Exception e) {
			reference = (long)1;
		}
		Avoir a = new Avoir();
		a.setGuid(Guid.generateAvoir(metier));
		a.setDate(Datetime.getCurrentDate());
		a.setReference("A00"+reference.toString());
		a.setCustomer(f.getCustomer());
		a.setFacture(f);
		metier.addAvoir(a);
		Avoir_Util.insertLine(typeList, referenceList, designationList, quantityList, tvaListList, prix_htList, total_htList, a, metier);
	}
	
	private static void insertLine(List<String> typeList,List<String> referenceList,List<String> designationList,List<String> quantityList,List<String> tvaList,List<String> prix_htList,List<String> total_htList,Avoir avoir,IGlobalMetier metier)throws Exception{
		List<Line> lineAvoirList = new ArrayList<Line>();
		
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
			line.setAvoir(avoir);
			line.setArticle(a);
			lineAvoirList.add(line);
		}
		for (Line line : lineAvoirList) {
			metier.addLine(line);
		}
		insertTotal(lineAvoirList, avoir, metier);
	}
	
	private static void insertTotal(List<Line> lineDevisList, Avoir avoir,IGlobalMetier metier) throws Exception{
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
		total.setAvoir(avoir);
		metier.addTotal(total);	
	}
	public static List<Line> createTabToAvoir(List<String> argType,List<String> argDesignation,List<String> argReference,List<String> argTva,List<String> argQuantity,List<String> argPrix_ht){
		List<Line> listTabAvoir = new ArrayList<Line>();
		for (int i = 0; i < argType.size(); i++) {
			Line l = new Line();
			l.setType(argType.get(i));
			l.setDescription(argDesignation.get(i));
			l.setReference(argReference.get(i));
			l.setTva(Double.parseDouble(argTva.get(i)));
			l.setQuantity(Long.parseLong(argQuantity.get(i)));
			l.setPrix_ht(Double.parseDouble(argPrix_ht.get(i)));
			listTabAvoir.add(l);
		}
		return listTabAvoir;
	}
}
