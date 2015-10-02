package com.erp.myapp.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.erp.myapp.entity.Article;
import com.erp.myapp.entity.Avoir;
import com.erp.myapp.entity.Entry_Achat;
import com.erp.myapp.entity.Facture;
import com.erp.myapp.entity.Fournisseur_Pro;
import com.erp.myapp.entity.Line;
import com.erp.myapp.entity.Sortie_Vente;
import com.erp.myapp.metier.IGlobalMetier;
import com.erp.myapp.security.Datetime;
import com.erp.myapp.security.Guid;
import com.erp.myapp.security.Path;
import com.erp.myapp.security.Reference;
import com.erp.myapp.security.ResourceNotFoundException;
import com.erp.myapp.utils.Article_Util;

@Controller
public class StockController {
	@Autowired
	IGlobalMetier metier;

	@RequestMapping(value=""+Path.stock+"/entries", method = RequestMethod.GET)
	public String entries(Model model, HttpServletRequest request){
		List<Entry_Achat> listEntries = new ArrayList<Entry_Achat>();
		try {
			if(request.getParameter("all") != null){
				try {
					listEntries = (List<Entry_Achat>) metier.getAllEntryAchat();					
				} catch (Exception e) {}
			}else if(request.getParameter("refArticle") !=null){
				try {
					listEntries = (List<Entry_Achat>) metier.getEntryAchatbyArticle(metier.getArticleByDesignation(request.getParameter("refArticle")));
				} catch (Exception e) {}
			}
			model.addAttribute("listEntries", listEntries);
			return "/Stock/entries_stock";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new ResourceNotFoundException(); // Génère une exception 404
		}
	}
	
	@RequestMapping(value=""+Path.stock+"/searchEntriesEdit", method = RequestMethod.POST)
	public String searchEntriesEdit(Model model, HttpServletRequest request){
		List<Entry_Achat> listEntries = new ArrayList<Entry_Achat>();
		try {
			if(request.getParameter("date1") != null && request.getParameter("date2") != null){
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
		        	Date date1 = sdf.parse(request.getParameter("date1"));
		        	Date date2 = sdf.parse(request.getParameter("date2"));
		        	for (Entry_Achat entry_Achat : metier.getAllEntryAchat()) {
						Date dateToCompare = sdf.parse(entry_Achat.getDate());
						if(dateToCompare.compareTo(date1)>=0 && dateToCompare.compareTo(date2)<=0){
							listEntries.add(entry_Achat);
						}
					}
				} catch (Exception e) {}        	
			}else{
				try {
					listEntries = (List<Entry_Achat>) metier.getAllEntryAchat();					
				} catch (Exception e) {}
			}
			model.addAttribute("date1",request.getParameter("date1"));
			model.addAttribute("date2",request.getParameter("date2"));
			model.addAttribute("listEntries", listEntries);
			return "/Stock/entries_stock";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new ResourceNotFoundException(); // Génère une exception 404
		}

	}
	
	@RequestMapping(value=""+Path.stock+"/delete_entry", method = RequestMethod.GET)
	public String delete_entry(Model model, HttpServletRequest request){
		try {
			metier.deleteEntryAchatById(Long.parseLong(request.getParameter("ref")));
			return "redirect:"+Path.stock+"/entries?all";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new ResourceNotFoundException(); // Génère une exception 404
		}

	}
	
	@RequestMapping(value=""+Path.stock+"/outputs", method = RequestMethod.GET)
	public String outputs(Model model, HttpServletRequest request){
		List<Sortie_Vente> listOuputs = new ArrayList<Sortie_Vente>();
		try {
			if(request.getParameter("all") != null){
				try {
					listOuputs = (List<Sortie_Vente>) metier.getAllSortieVente();					
				} catch (Exception e) {}
			}else if(request.getParameter("refArticle") !=null){
				try {
					listOuputs = (List<Sortie_Vente>) metier.getSortieVentebyArticle(metier.getArticleByDesignation(request.getParameter("refArticle")));
				} catch (Exception e) {}
			}
			model.addAttribute("listOuputs", listOuputs);
			return "/Stock/outputs_stock";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new ResourceNotFoundException(); // Génère une exception 404
		}

	}
	
	@RequestMapping(value=""+Path.stock+"/searchOutputsEdit", method = RequestMethod.POST)
	public String searchOutputsEdit(Model model, HttpServletRequest request){
		List<Sortie_Vente> listOutputs = new ArrayList<Sortie_Vente>();
		try {
			if(request.getParameter("date1") != null && request.getParameter("date2") != null){
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
		        	Date date1 = sdf.parse(request.getParameter("date1"));
		        	Date date2 = sdf.parse(request.getParameter("date2"));
		        	for (Sortie_Vente sortie_Vente : metier.getAllSortieVente()) {
						Date dateToCompare = sdf.parse(sortie_Vente.getDate());
						if(dateToCompare.compareTo(date1)>=0 && dateToCompare.compareTo(date2)<=0){
							listOutputs.add(sortie_Vente);
						}
					}
				} catch (Exception e) {}        	
			}else{
				try {
					listOutputs = (List<Sortie_Vente>) metier.getAllSortieVente();					
				} catch (Exception e) {}
			}
			model.addAttribute("date1",request.getParameter("date1"));
			model.addAttribute("date2",request.getParameter("date2"));
			model.addAttribute("listOuputs", listOutputs);
			return "/Stock/outputs_stock";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new ResourceNotFoundException(); // Génère une exception 404
		}

	}
	
	@RequestMapping(value=""+Path.stock+"/details", method = RequestMethod.GET)
	public String details(Model model, HttpServletRequest request){
		List<Facture> listFacture = new ArrayList<Facture>();
		try {			
			model.addAttribute("listFacture", listFacture);
			return "/Stock/details_stock";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new ResourceNotFoundException(); // Génère une exception 404
		}

	}
	
	@RequestMapping(value=""+Path.stock+"/detailsEdit", method = RequestMethod.POST)
	public String detailsEdit(Model model, HttpServletRequest request){
		List<Facture> listFacture = new ArrayList<Facture>();
		Double tva_collectee = (double) 0;
		Double tva_deductible = (double) 0;
		Double tva_a_payer = (double) 0;
		Double chiffre_affaire = (double) 0;
		try {
			if(request.getParameter("date1") != null && request.getParameter("date2") != null){
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
		        	Date date1 = sdf.parse(request.getParameter("date1"));
		        	Date date2 = sdf.parse(request.getParameter("date2"));
		        	for (Facture facture : metier.getAllFacture()) {
						Date dateToCompare = sdf.parse(facture.getDate());
						if(dateToCompare.compareTo(date1)>=0 && dateToCompare.compareTo(date2)<=0){
							listFacture.add(facture); // On ajoute toutes les factures qui sont entre les dates correspondantes.
						}
					}
		        	for (Facture facture : listFacture) {
		        		try {
							for (Line line : facture.getDevis().getLine()) {
								try {
									if(line.getType().toLowerCase().equals("matériel")){
										tva_deductible += line.getQuantity() * line.getArticle().getPrix_ht_achat() * line.getArticle().getTva() / 100; //On détermine la tva déductible avec les articles
										try {
											for (Avoir avoir : facture.getAvoir()) {
												for (Line l : avoir.getLine()) {
													if(l.getType().toLowerCase().equals("matériel")){
														tva_deductible -= l.getQuantity() * l.getArticle().getPrix_ht_achat() * l.getArticle().getTva() / 100; //On ajoute sur l'avoir la tva													
													}
												}
											}
										} catch (Exception e) {}
									}
								} catch (Exception e) {}
							}
							tva_collectee += facture.getDevis().getTotal().getTva(); // ON determine la tva collectee en fonction du total des factures
							try {
								for (Avoir avoir : facture.getAvoir()) {
									tva_collectee -= avoir.getTotal().getTva();
								}
							} catch (Exception e) {}
							chiffre_affaire += facture.getDevis().getTotal().getTotal_ht();
							try {
								for (Avoir avoir : facture.getAvoir()) {
									chiffre_affaire -= avoir.getTotal().getTotal_ht();
								}
							} catch (Exception e) {}
		        		} catch (Exception e) {}
					}
		        	tva_a_payer = tva_collectee - tva_deductible;
				} catch (Exception e) {}        	
			}
			model.addAttribute("tva_collectee",tva_collectee);
			model.addAttribute("tva_deductible",tva_deductible);
			model.addAttribute("chiffre_affaire",chiffre_affaire);
			model.addAttribute("tva_a_payer",tva_a_payer);
			model.addAttribute("date1",request.getParameter("date1"));
			model.addAttribute("date2",request.getParameter("date2"));
			model.addAttribute("listFacture", listFacture);
			return "/Stock/details_stock";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new ResourceNotFoundException(); // Génère une exception 404
		}

	}
}
