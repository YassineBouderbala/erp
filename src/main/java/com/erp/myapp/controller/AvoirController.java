package com.erp.myapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.erp.myapp.comptabilite.Pdf_Comptabilite;
import com.erp.myapp.entity.Article;
import com.erp.myapp.entity.Avoir;
import com.erp.myapp.entity.Devis;
import com.erp.myapp.entity.Entry_Achat;
import com.erp.myapp.entity.Facture;
import com.erp.myapp.entity.Line;
import com.erp.myapp.metier.IGlobalMetier;
import com.erp.myapp.security.Datetime;
import com.erp.myapp.security.Path;
import com.erp.myapp.security.ResourceNotFoundException;
import com.erp.myapp.utils.Avoir_Util;
import com.erp.myapp.utils.Devis_Util;
import com.erp.myapp.utils.Stock_Util;

@Controller
public class AvoirController {
	@Autowired
	IGlobalMetier metier;
	
	@RequestMapping(value=""+Path.comptabilite+"/createAvoir", method = RequestMethod.GET)
	public String Form(Model model,HttpServletRequest request){ // Partie formulaire pour création de l'avoir
		try {
			Facture f = metier.getFactureByGuid(request.getParameter("refFacture"));
			model.addAttribute("facture", f);
			model.addAttribute("error",request.getParameter("error"));
			return "comptabilite/create_avoir";			
		} catch (Exception e) {
			throw new ResourceNotFoundException(); // Génère une exception 404
		}
	}
	
	@RequestMapping(value=""+Path.comptabilite+"/createAvoirEdit",method=RequestMethod.POST ) // Requete POST pour vérifier la création de l'avoir
	public String createDevisEdit(Model model,HttpServletRequest request){	
		try {
			request.setCharacterEncoding("UTF-8");
			if(Devis_Util.isCreatable(java.util.Arrays.asList(request.getParameterValues("type")), java.util.Arrays.asList(request.getParameterValues("designation")), java.util.Arrays.asList(request.getParameterValues("reference")), java.util.Arrays.asList(request.getParameterValues("tva")), java.util.Arrays.asList(request.getParameterValues("quantity")),java.util.Arrays.asList(request.getParameterValues("prix_ht")), metier)){
				if(Stock_Util.updateStock("facture",request.getParameter("refFacture"),Avoir_Util.createTabToAvoir(java.util.Arrays.asList(request.getParameterValues("type")), java.util.Arrays.asList(request.getParameterValues("designation")), java.util.Arrays.asList(request.getParameterValues("reference")), java.util.Arrays.asList(request.getParameterValues("tva")), java.util.Arrays.asList(request.getParameterValues("quantity")), java.util.Arrays.asList(request.getParameterValues("prix_ht"))),metier).size() == 0){
					Avoir_Util.create(java.util.Arrays.asList(request.getParameterValues("type")),java.util.Arrays.asList(request.getParameterValues("reference")),java.util.Arrays.asList(request.getParameterValues("designation")),java.util.Arrays.asList(request.getParameterValues("quantity")),java.util.Arrays.asList(request.getParameterValues("tva")),java.util.Arrays.asList(request.getParameterValues("prix_ht")), java.util.Arrays.asList(request.getParameterValues("total_ht")),metier, metier.getFactureByGuid(request.getParameter("refFacture")));				
					Avoir a = metier.getLastAvoir();
					for (Line l : a.getLine()) {
						if(l.getType().toLowerCase().equals("matériel")){
							Entry_Achat e = new Entry_Achat();
							Article art = metier.getArticleByDesignation(l.getDescription());
							e.setDate(Datetime.getCurrentDate());
							e.setQuantity(l.getQuantity());
							e.setArticle(art);
							metier.addEntryAchat(e);
							art.setQuantite(art.getQuantite() + l.getQuantity());
							metier.updateArticle(art);
						}
					}					
				}
				else
				{ 
					Facture f = metier.getFactureByGuid(request.getParameter("refFacture"));
					model.addAttribute("facture", f);
					model.addAttribute("listArticleAvoir", Stock_Util.updateStock("facture",request.getParameter("refFacture"),Avoir_Util.createTabToAvoir(java.util.Arrays.asList(request.getParameterValues("type")), java.util.Arrays.asList(request.getParameterValues("designation")), java.util.Arrays.asList(request.getParameterValues("reference")), java.util.Arrays.asList(request.getParameterValues("tva")), java.util.Arrays.asList(request.getParameterValues("quantity")), java.util.Arrays.asList(request.getParameterValues("prix_ht"))),metier));
					return "comptabilite/create_avoir"; // A mettre la meme chose tout ce qui y a dans create avoir
				}
			}
			else
			{
				return "redirect:"+Path.comptabilite+"/createAvoir?refFacture="+request.getParameter("refFacture")+"&error=1";				
			}			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "redirect:"+Path.comptabilite+"/createAvoir?refFacture="+request.getParameter("refFacture")+"&error=1";
		}
		return "redirect:"+Path.comptabilite+"/devis?refDevis="+metier.getLastDevis().getGuid()+"";
	}
	
	@RequestMapping(value=""+Path.comptabilite+"/pdf_create_avoir",method=RequestMethod.GET)
	public String pdf_create_avoir(Model model,HttpServletRequest request){
		try {
			Avoir a = metier.getAvoirByGuid(request.getParameter("refAvoir"));
			if(!a.isPdf_created()){ // Si le devis n'est pas créer alors on le crée
				Pdf_Comptabilite.create("AVOIR",a.getGuid(),metier);
			}
			return "redirect:/resources/comptabilite/avoir/"+a.getReference()+".pdf";
		} catch (Exception e) {
			throw new ResourceNotFoundException(); // Génère une exception 404
		}		
	}
}
