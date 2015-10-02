package com.erp.myapp.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.erp.myapp.comptabilite.Pdf_Comptabilite;
import com.erp.myapp.entity.BonDeLivraison;
import com.erp.myapp.entity.Customer_Pro;
import com.erp.myapp.entity.Devis;
import com.erp.myapp.entity.Facture;
import com.erp.myapp.metier.IGlobalMetier;
import com.erp.myapp.security.Datetime;
import com.erp.myapp.security.Guid;
import com.erp.myapp.security.Path;
import com.erp.myapp.security.ResourceNotFoundException;

@Controller
public class BonDeLivraisonController {
	@Autowired
	IGlobalMetier metier;
	
	@RequestMapping(value=""+Path.comptabilite+"/createBDLedit", method = RequestMethod.POST)
	public String Form(Model model,HttpServletRequest request){ // Partie formulaire pour création du Bon de livraison
		try {
			Devis d = metier.getDevisByGuid(request.getParameter("refDevis"));
			if(d.getBonDeLivraison() == null && d.isValid())
			{
				Long reference;
				try {
					reference = metier.getLastBDL().getId() + 1; // we create a reference
				} catch (Exception e) {
					reference = (long)1;
				}
				BonDeLivraison b  = new BonDeLivraison();
				b.setCreated_by(SecurityContextHolder.getContext().getAuthentication().getName());
				b.setDate(Datetime.getCurrentDate());
				b.setDate_livraison(request.getParameter("date"));
				b.setGuid(Guid.generateBDL(metier));
				b.setReference("B00"+reference.toString());
				b.setDevis(d);
				metier.addBDL(b);				
			}
			return "redirect:"+Path.comptabilite+"/devis?refDevis="+d.getGuid()+"";		
		} catch (Exception e) {
			throw new ResourceNotFoundException(); // Génère une exception 404
		}
	}
	
	@RequestMapping(value=""+Path.comptabilite+"/pdf_create_bdl",method=RequestMethod.GET ) // On affiche et créer le bon de livraison en pdf
	public String pdf_create_BDL(Model model,HttpServletRequest request){
		try {
			ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			Devis d = metier.getDevisByGuid(request.getParameter("refDevis"));
			if(!d.getBonDeLivraison().isPdf_created()){ // Si le BDL n'est pas créer alors on le crée
				Pdf_Comptabilite.create("BON_DE_LIVRAISON",d.getGuid(),metier);
			}
			return "redirect:/resources/comptabilite/bon_de_livraison/"+d.getBonDeLivraison().getReference()+".pdf";
		} catch (Exception e) {
			throw new ResourceNotFoundException(); // Génère une exception 404
		}
	}
	
	@RequestMapping(value=""+Path.comptabilite+"/modifiy_bdl",method=RequestMethod.POST ) // On modifie un bon de livraison
	public String modifiy_BDL(Model model,HttpServletRequest request){
		try {
			ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			Devis d = metier.getDevisByGuid(request.getParameter("refDevis"));
		    d.getBonDeLivraison().setPdf_created(false);
		    String bon_de_livraison_pdf = requestAttributes.getRequest().getRealPath("/resources/comptabilite/bon_de_livraison/"+d.getBonDeLivraison().getReference()+".pdf"); 
		    File deleteBDL = new File(bon_de_livraison_pdf);
		    deleteBDL.delete();
		    d.getBonDeLivraison().setDate_livraison(request.getParameter("date")); // on lui donne une autre date de livraison
		    metier.updateBDL(d.getBonDeLivraison()); // on update le bon de livraison
		    return "redirect:"+Path.comptabilite+"/devis?refDevis="+d.getGuid()+"";
		} catch (Exception e) {
			throw new ResourceNotFoundException(); // Génère une exception 404
		}
	}
	@RequestMapping(value=""+Path.delivery+"/check_delivred",method=RequestMethod.GET ) // On change status du bon de livraison
	public String check_paid(Model model,HttpServletRequest request){
		try {
			BonDeLivraison b = metier.getBonDeLivraisonByGuid(request.getParameter("refBonDeLivraison"));
			if(!b.isDelivred()){
				b.setDelivred(true);
				b.setDate_delivred(Datetime.getCurrentDate());
				metier.updateBDL(b);
			}
		    return "redirect:"+Path.comptabilite+"/devis?refDevis="+b.getDevis().getGuid()+"";
		} catch (Exception e) {
			throw new ResourceNotFoundException(); // Génère une exception 404
		}
	}

}
