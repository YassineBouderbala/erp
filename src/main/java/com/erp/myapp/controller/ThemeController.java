package com.erp.myapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.erp.myapp.entity.Theme;
import com.erp.myapp.metier.IGlobalMetier;
import com.erp.myapp.security.Path;
import com.erp.myapp.security.ResourceNotFoundException;

@Controller
public class ThemeController {
	@Autowired
	IGlobalMetier metier;
	
	@RequestMapping(value=""+Path.admin+"/config/theme",method=RequestMethod.GET)
	public String formFooterDevis(Model model, HttpServletRequest request){
		try {
			Theme t = new Theme();
			try {
				t = metier.getTheme();				
			} catch (Exception e) {}
			model.addAttribute("theme", t);
			return "Config/config_theme";
		} catch (Exception e) {
			throw new ResourceNotFoundException(); // Génère une exception 404
		}
	}
	
	@RequestMapping(value=""+Path.admin+"/config/theme_footer_devis_create",method=RequestMethod.POST)
	public String formFooterDevisEdit(Model model, HttpServletRequest request){
		Integer nbCharacterMax = 1000;
		try {
			request.setCharacterEncoding("UTF-8");
			Theme t = new Theme();
			try {
				t = metier.getTheme();
				t.setFooter_devis(request.getParameter("footer_devis")); // Si il y'à déja un theme de crée on update
				metier.updateTheme(t);
			} catch (Exception e) {
				if(request.getParameter("footer_devis") == ""){
					return "redirect:"+Path.admin+"/config/theme";
				}
				else if(request.getParameter("footer_devis").length() > nbCharacterMax){
					model.addAttribute("theme", t);
					model.addAttribute("errorFooterDevis", "Nombre de caractère dépassé : "+request.getParameter("footer_devis").length()+"/"+nbCharacterMax+"");
					return "Config/config_theme";						
				}
				else{
					t.setFooter_devis(request.getParameter("footer_devis"));
					metier.addTheme(t); // Sinon on ajoute
				}				
			}
			return "redirect:"+Path.admin+"/config/theme";
		} catch (Exception e) {
			System.out.print(e.getMessage());
			throw new ResourceNotFoundException(); // Génère une exception 404
		}
	}
	
	@RequestMapping(value=""+Path.admin+"/config/theme_footer_facture_create",method=RequestMethod.POST)
	public String formFooterFactureEdit(Model model, HttpServletRequest request){
		Integer nbCharacterMax = 1000;
		try {
			request.setCharacterEncoding("UTF-8");
			Theme t = new Theme();
			try {
				t = metier.getTheme();
				t.setFooter_facture(request.getParameter("footer_facture")); // Si il y'à déja un theme de crée on update
				metier.updateTheme(t);
			} catch (Exception e) {
				if(request.getParameter("footer_facture") == ""){
					return "redirect:"+Path.admin+"/config/theme";
				}
				else if(request.getParameter("footer_facture").length() > nbCharacterMax){
					model.addAttribute("theme", t);
					model.addAttribute("errorFooterFacture", "Nombre de caractère dépassé : "+request.getParameter("footer_facture").length()+"/"+nbCharacterMax+"");
					return "Config/config_theme";						
				}
				else{
					t.setFooter_devis(request.getParameter("footer_facture"));
					metier.addTheme(t); // Sinon on ajoute
				}				
			}
			return "redirect:"+Path.admin+"/config/theme";
		} catch (Exception e) {
			System.out.print(e.getMessage());
			throw new ResourceNotFoundException(); // Génère une exception 404
		}
	}
}
