package com.erp.myapp.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.myapp.comptabilite.Pdf_Comptabilite;
import com.erp.myapp.entity.Article;
import com.erp.myapp.entity.Customer_Pro;
import com.erp.myapp.entity.Devis;
import com.erp.myapp.entity.Line;
import com.erp.myapp.entity.Sortie_Vente;
import com.erp.myapp.metier.IGlobalMetier;
import com.erp.myapp.security.Path;
import com.erp.myapp.security.ResourceNotFoundException;
import com.erp.myapp.utils.Devis_Util;
import com.erp.myapp.utils.Stock_Util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Controller
public class DevisController {
	@Autowired
	IGlobalMetier metier;

	@RequestMapping(value=""+Path.comptabilite+"/createDevis", method = RequestMethod.GET)
	public String Form(Model model,HttpServletRequest request){ // Partie formulaire pour création du devis
		try {
			Customer_Pro c = metier.getCustomerByGuid(request.getParameter("refCustomer"));
			model.addAttribute("customer", c);
			model.addAttribute("error",request.getParameter("error"));
			return "comptabilite/create_devis";			
		} catch (Exception e) {
			throw new ResourceNotFoundException(); // Génère une exception 404
		}
	}

	@RequestMapping(value=""+Path.comptabilite+"/createDevisEdit",method=RequestMethod.POST ) // Requete POST pour vérifier la création du devis
	public String createDevisEdit(Model model,HttpServletRequest request){	
		try {
			request.setCharacterEncoding("UTF-8");
			if(Devis_Util.isCreatable(java.util.Arrays.asList(request.getParameterValues("type")), java.util.Arrays.asList(request.getParameterValues("designation")), java.util.Arrays.asList(request.getParameterValues("reference")), java.util.Arrays.asList(request.getParameterValues("tva")),java.util.Arrays.asList(request.getParameterValues("quantity")), java.util.Arrays.asList(request.getParameterValues("prix_ht")), metier)){
				Devis_Util.create(request.getParameter("date"),java.util.Arrays.asList(request.getParameterValues("type")),java.util.Arrays.asList(request.getParameterValues("reference")),java.util.Arrays.asList(request.getParameterValues("designation")),java.util.Arrays.asList(request.getParameterValues("quantity")),java.util.Arrays.asList(request.getParameterValues("tva")),java.util.Arrays.asList(request.getParameterValues("prix_ht")), java.util.Arrays.asList(request.getParameterValues("total_ht")),metier, metier.getCustomerByGuid(request.getParameter("refCustomer")));				
			}
			else
			{
				return "redirect:"+Path.comptabilite+"/createDevis?refCustomer="+request.getParameter("refCustomer")+"&error=1";				
			}
		} catch (Exception e) {
			return "redirect:"+Path.comptabilite+"/createDevis?refCustomer="+request.getParameter("refCustomer")+"&error=1";
		}
		return "redirect:"+Path.comptabilite+"/devis?refDevis="+metier.getLastDevis().getGuid()+"";
	}

	@RequestMapping(value=""+Path.comptabilite+"/devis",method=RequestMethod.GET ) // On affiche un devis crée
	public String devis(Model model,HttpServletRequest request){
		try {
			Devis d = metier.getDevisByGuid(request.getParameter("refDevis"));			
			model.addAttribute("devis",d); // Tout ce qu'on met ici on doit mettre dans valid_devis (model)
			return "comptabilite/devis";			
		} catch (Exception e) {
			throw new ResourceNotFoundException(); // Génère une exception 404
		}
	}
	
	@RequestMapping(value=""+Path.comptabilite+"/pdf_create_devis",method=RequestMethod.GET ) // On affiche et créer le devis en pdf
	public String pdf_create_devis(Model model,HttpServletRequest request){
		try {
			ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			Devis d = metier.getDevisByGuid(request.getParameter("refDevis"));
			if(!d.isPdf_created()){ // Si le devis n'est pas créer alors on le crée
				Pdf_Comptabilite.create("DEVIS",d.getGuid(),metier);
			}
			return "redirect:/resources/comptabilite/devis/"+d.getReference()+".pdf";
		} catch (Exception e) {
			throw new ResourceNotFoundException(); // Génère une exception 404
		}
	}
	
	@RequestMapping(value=""+Path.comptabilite+"/valid_devis",method=RequestMethod.GET ) //
	public String valid_devis(Model model,HttpServletRequest request){
		try {
			Devis d = metier.getDevisByGuid(request.getParameter("refDevis"));			
			if(metier.getSortieVenteByDevisId(d.getId()).size() == 0) // Si il n'ya pas eu déjà de validation (Securité)
			{
				if(Stock_Util.updateStock("devis",request.getParameter("refDevis"),null, metier).size() == 0){
					d.setValid(true);
					metier.updateDevis(d);	
				}else{
					model.addAttribute("listArticleStock",(Stock_Util.updateStock("devis",request.getParameter("refDevis"),null, metier)));
					model.addAttribute("devis", d);
					return "comptabilite/devis";
				}
			}
			return "redirect:"+Path.comptabilite+"/devis?refDevis="+d.getGuid()+"";
		} catch (Exception e) {
			throw new ResourceNotFoundException(); // Génère une exception 404
		}		
	}
	
	@RequestMapping(value=""+Path.comptabilite+"/unvalid_devis",method=RequestMethod.GET ) //
	public String unvalid_devis(Model model,HttpServletRequest request){
		try {
			Devis d = metier.getDevisByGuid(request.getParameter("refDevis"));
			if(d.getFacture() == null) // Si il n'à aucune facture on peut annuler un devis
			{
				metier.removeSortieVenteByDevisIdAndUpdateStock(d.getId());
				d.setValid(false);
				if(d.getBonDeLivraison() != null)
				{
					metier.deleteBonDeLivraisonByDevisId(d.getId());
					try {
						ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
					    String bon_de_livraison_pdf = requestAttributes.getRequest().getRealPath("/resources/comptabilite/bon_de_livraison/"+d.getBonDeLivraison().getReference()+".pdf"); 
					    File deleteBDL = new File(bon_de_livraison_pdf);
					    deleteBDL.delete();
					} catch (Exception e) {}
				}
				metier.updateDevis(d);
			}
			return "redirect:"+Path.comptabilite+"/devis?refDevis="+d.getGuid()+"";			
		} catch (Exception e) {
			throw new ResourceNotFoundException(); // Génère une exception 404
		}
	}
	
	@RequestMapping(value=""+Path.comptabilite+"/modify_devis",method=RequestMethod.GET ) // Modifier un devis
	public String modify_devis(Model model,HttpServletRequest request){
		try {
			Devis d = metier.getDevisByGuid(request.getParameter("refDevis"));
			if(!d.isValid()) // Le devis ne doit pas être validé
			{
				model.addAttribute("devis",d);
				model.addAttribute("error",request.getParameter("error"));
				return "comptabilite/modify_devis";				
			}else
			{
				throw new ResourceNotFoundException(); // Génère une exception 404				
			}
		} catch (Exception e) {
			throw new ResourceNotFoundException(); // Génère une exception 404
		}		
	}
	
	@RequestMapping(value=""+Path.comptabilite+"/modify_devisEdit",method=RequestMethod.POST ) // Modifier un devis
	public String modify_devisEdit(Model model,HttpServletRequest request){
		try {
			ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			request.setCharacterEncoding("UTF-8");
			Devis d = metier.getDevisByGuid(request.getParameter("refDevis"));
			if(Devis_Util.isCreatable(java.util.Arrays.asList(request.getParameterValues("type")), java.util.Arrays.asList(request.getParameterValues("designation")), java.util.Arrays.asList(request.getParameterValues("reference")), java.util.Arrays.asList(request.getParameterValues("tva")),java.util.Arrays.asList(request.getParameterValues("quantity")), java.util.Arrays.asList(request.getParameterValues("prix_ht")), metier)){
				Devis_Util.update(request.getParameter("date"),java.util.Arrays.asList(request.getParameterValues("type")),java.util.Arrays.asList(request.getParameterValues("reference")),java.util.Arrays.asList(request.getParameterValues("designation")),java.util.Arrays.asList(request.getParameterValues("quantity")),java.util.Arrays.asList(request.getParameterValues("tva")),java.util.Arrays.asList(request.getParameterValues("prix_ht")), java.util.Arrays.asList(request.getParameterValues("total_ht")),metier, d.getCustomer(),d);				
				if(d.isPdf_created())
				{
					Devis devis = metier.getDevisByGuid(request.getParameter("refDevis"));
				    String devis_pdf = requestAttributes.getRequest().getRealPath("/resources/comptabilite/devis/"+d.getReference()+".pdf"); 
				    File devis_file = new File(devis_pdf);
				    devis_file.delete();	
					devis.setPdf_created(false);
				    metier.updateDevis(devis);
				}
				return "redirect:"+Path.comptabilite+"/devis?refDevis="+d.getGuid()+"";
			}
			else
			{
				return "redirect:"+Path.comptabilite+"/modify_devis?error=1&refDevis="+request.getParameter("refDevis")+"";				
			}
		} catch (Exception e) {
			return "redirect:"+Path.comptabilite+"/modify_devis?error=1&refDevis="+request.getParameter("refDevis")+"";
		}		
	}
}
