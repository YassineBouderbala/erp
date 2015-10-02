package com.erp.myapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.erp.myapp.entity.Entreprise;
import com.erp.myapp.entity.Fournisseur_Pro;
import com.erp.myapp.metier.IGlobalMetier;
import com.erp.myapp.security.Guid;
import com.erp.myapp.security.Path;
import com.erp.myapp.security.Reference;
import com.erp.myapp.security.ResourceNotFoundException;
import com.erp.myapp.utils.Fournisseur_Util;

@Controller
public class FournisseurController {
	@Autowired
	IGlobalMetier metier;
	
	@RequestMapping(value=""+Path.stock+"/fournisseur", method = RequestMethod.GET)
	public String Form(Model model,HttpServletRequest request){
		Fournisseur_Pro f = new Fournisseur_Pro();
		try {
			if(request.getParameter("modify").equals("1")){
				f = metier.getFournisseurByGuid(request.getParameter("guid"));
				model.addAttribute("modify","1");
			}
			model.addAttribute("fournisseur", f);
			model.addAttribute("listFournisseur", metier.getAllFournisseur());		
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			throw new ResourceNotFoundException();
		}
		return "Stock/fournisseur";
	}
	
	@RequestMapping(value=""+Path.stock+"/fournisseurEdit", method = RequestMethod.POST)
	public String FormEdit(@ModelAttribute("fournisseur")@Valid Fournisseur_Pro f, BindingResult bindingResult,Model model,HttpServletRequest request){
		try {
			if(request.getParameter("modify").equals("1")){
				model.addAttribute("modify","1");
				Fournisseur_Pro four = metier.getFournisseurByGuid(request.getParameter("guid"));
				f.setId(four.getId());
			}
			if(!bindingResult.hasErrors())
			{
				if(!Fournisseur_Util.isUniqueEmail(f, metier)){
					model.addAttribute("errorEmail","Email déjà pris");
				}else if(!Fournisseur_Util.isUniqueName(f, metier)){
					model.addAttribute("errorName","Nom déjà pris");
				}else{
					f.setGuid(Guid.generateFournisseur(metier));
					f.setReference(Reference.generateFournisseur(metier));
					metier.updateFournisseur(f);
					return "redirect:"+Path.stock+"/showFournisseur?guid="+f.getGuid()+"";
				}				
			}
			model.addAttribute("fournisseur", f);
			model.addAttribute("listFournisseur", metier.getAllFournisseur());
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			throw new ResourceNotFoundException();
		}
		return "Stock/fournisseur";
	}
	
	@RequestMapping(value=""+Path.stock+"/showFournisseur", method = RequestMethod.GET)
	public String showFournisseur(Model model,HttpServletRequest request){
		Fournisseur_Pro f = new Fournisseur_Pro();
		try {
			f = metier.getFournisseurByGuid(request.getParameter("guid"));
			model.addAttribute("fournisseur", f);
			model.addAttribute("listFournisseur", metier.getAllFournisseur());		
		} catch (Exception ex) {
			throw new ResourceNotFoundException();
		}
		return "Stock/fournisseur_show";
	}
	
	@RequestMapping(value=""+Path.stock+"/fournisseur/delete", method = RequestMethod.GET)
	public String fournisseur_delete(Model model,HttpServletRequest request){
		Fournisseur_Pro f = new Fournisseur_Pro();
		try {
			f = metier.getFournisseurByGuid(request.getParameter("guid"));
			if(f.getArticle().isEmpty()){
				metier.deleteFournisseur(f.getId());
				return "redirect:"+Path.stock+"/fournisseur?modify=0";
			}else{
				throw new ResourceNotFoundException();	
			}
		} catch (Exception ex) {
			throw new ResourceNotFoundException();
		}
	}
}
