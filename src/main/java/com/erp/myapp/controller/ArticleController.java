package com.erp.myapp.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.myapp.entity.Article;
import com.erp.myapp.entity.Entry_Achat;
import com.erp.myapp.entity.Fournisseur_Pro;
import com.erp.myapp.metier.IGlobalMetier;
import com.erp.myapp.security.Datetime;
import com.erp.myapp.security.Guid;
import com.erp.myapp.security.Path;
import com.erp.myapp.security.Reference;
import com.erp.myapp.security.ResourceNotFoundException;
import com.erp.myapp.utils.Article_Util;
@Controller
public class ArticleController {
	@Autowired
	IGlobalMetier metier;
	
	/* AJOUT D'ARTICLE */
	
	@RequestMapping(value=""+Path.stock+"/addArticleIn", method = RequestMethod.GET)
	public String add_article(Model model, HttpServletRequest request){
		try {
			model.addAttribute("article",new Article());
			model.addAttribute("fournisseur", metier.getFournisseurByGuid(request.getParameter("refFournisseur")));
			/** Tout ce qu'on met ici on le met en bas **/
			return "/Stock/add_article";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new ResourceNotFoundException(); // Génère une exception 404
		}

	}
	
	@RequestMapping(value=""+Path.stock+"/addArticleEdit", method = RequestMethod.POST)
	public String add_articleEdit(Model model, HttpServletRequest request){
		String messageCreate = null;
		List<String> errorMessageListForm = new ArrayList<String>();
		try {
			Fournisseur_Pro f = metier.getFournisseurByGuid(request.getParameter("refFournisseur"));
			Article a = new Article();
			Entry_Achat e = new Entry_Achat();
			if(request.getParameter("type_article").toLowerCase().equals("stock")){
				if(Article_Util.isCreatableStock(metier, errorMessageListForm, request.getParameter("name_article"), request.getParameter("prix_ht_achat_article"), request.getParameter("prix_ht_vente_article"), request.getParameter("tva_article"), request.getParameter("quantite_article")).size() == 0){
					a = metier.getArticleByDesignation(request.getParameter("name_article"));
					a.setQuantite(a.getQuantite() + Long.parseLong(request.getParameter("quantite_article")));
					metier.updateArticle(a); // on update l'article puisqu'il existe déjà
					e.setArticle(a);
					e.setDate(Datetime.getCurrentDate());
					e.setQuantity(Long.parseLong(request.getParameter("quantite_article")));
					metier.addEntryAchat(e); // on ajoute une entrée pour l'achat
					messageCreate = "Article bien ajouté";
					a = new Article();
				}
			}else if(request.getParameter("type_article").toLowerCase().equals("autre") || request.getParameter("type_article").toLowerCase().equals("")){
				try {
					a.setDesignation(request.getParameter("name_article"));
					a.setPrix_ht_achat(Double.parseDouble(request.getParameter("prix_ht_achat_article")));
					a.setPrix_ht_vente(Double.parseDouble(request.getParameter("prix_ht_vente_article")));
					a.setQuantite(Long.parseLong(request.getParameter("quantite_article")));
					a.setTva(Long.parseLong(request.getParameter("tva_article")));						
				} catch (Exception e2) {}
				if(Article_Util.isCreatable(metier, a, errorMessageListForm, request.getParameter("name_article"), request.getParameter("prix_ht_achat_article"), request.getParameter("prix_ht_vente_article"), request.getParameter("tva_article"), request.getParameter("quantite_article")).size() != 0){ // Si on retourne un tableau pas vide il 'ya des erreur

				}else{
					a.setFournisseur(f);
					a.setGuid(Guid.generateArticle(metier));
					a.setReference(Reference.generateArticle(metier));
					metier.addArticle(a); // on ajoute un article
					e.setArticle(metier.getArticleByDesignation(request.getParameter("name_article")));
					e.setDate(Datetime.getCurrentDate());
					e.setQuantity(Long.parseLong(request.getParameter("quantite_article")));
					metier.addEntryAchat(e); // on ajoute une entrée pour l'achat
					messageCreate = "Article bien ajouté";
					a = new Article();
				}
			}else{
				throw new ResourceNotFoundException(); // Génère une exception 404				
			}
			model.addAttribute("article",a);
			model.addAttribute("messageCreate", messageCreate);
			model.addAttribute("listErreur", errorMessageListForm);
			model.addAttribute("fournisseur", f);
			/** Tout ce qu'on met ici on le met en haut **/
			return "/Stock/add_article";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new ResourceNotFoundException(); // Génère une exception 404
		}

	}
	
	/* LISTER, RECHERCHER ARTICLE */
	
	@RequestMapping(value=""+Path.stock+"/articles", method = RequestMethod.GET)
	public String articles(Model model, HttpServletRequest request){
		try {
			List<Article> listArticles = new ArrayList<Article>();
			if(request.getParameter("all") != null){ // Si il ya un parametre all alors on met tout les articles
				try {
					listArticles = (List<Article>) metier.getAllArticles();					
				} catch (Exception e) {}
			}
			else if(request.getParameter("triQuantite") != null){
				try {
					listArticles = (List<Article>) metier.getArticleCroissantByQantity();					
				} catch (Exception e) {}
			}
			else if(request.getParameter("triAchat") != null){
				try {
					listArticles = (List<Article>) metier.getArticleCroissantByPrixAchat();					
				} catch (Exception e) {}
			}
			else if(request.getParameter("triVente") != null){
				try {
					listArticles = (List<Article>) metier.getArticleCroissantByPrixVente();					
				} catch (Exception e) {}
			}
			else if(request.getParameter("refArticle") != null){
				try {
					listArticles.add(metier.getArticleByDesignation(request.getParameter("refArticle")));					
				} catch (Exception e) {}
			}
			model.addAttribute("list_article",listArticles);
			return "/Stock/list_articles";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new ResourceNotFoundException(); // Génère une exception 404
		}

	}	

	@RequestMapping(value=""+Path.stock+"/searchArticleEdit", method = RequestMethod.POST)
	public String searchArticleEdit(Model model, HttpServletRequest request){
		List<Article> listArticles = new ArrayList<Article>();
		try {
			if(request.getParameter("reference").toString().isEmpty() && !request.getParameter("designation").toString().isEmpty()){
				try {
					return "redirect:"+Path.stock+"/articles?refArticle="+request.getParameter("designation")+"";
				} catch (Exception e) {}
			}else if(!request.getParameter("reference").toString().isEmpty() && request.getParameter("designation").toString().isEmpty()){
				try {
					listArticles.add(metier.getArticleByReference(request.getParameter("reference").toString()));					
				} catch (Exception e) {}
			}
			model.addAttribute("list_article",listArticles);
			return "/Stock/list_articles";
		} catch (Exception e) {
			throw new ResourceNotFoundException(); // Génère une exception 404
		}
	}
	
	@RequestMapping(value=""+Path.stock+"/delete_article", method = RequestMethod.GET)
	public String delete_article(Model model, HttpServletRequest request){
		try {
			Article a = metier.getArticleByDesignation(request.getParameter("refArticle"));
			a.setQuantite((long)0);
			a.setDeleted(true);
			metier.updateArticle(a);
			return "redirect:"+Path.stock+"/articles?all";
		} catch (Exception e) {
			throw new ResourceNotFoundException(); // Génère une exception 404
		}
	}
	
	/********** AJAX PART ************/

	@RequestMapping(value = ""+Path.comptabilite+"/ajax/getAllArticles", method = RequestMethod.POST) // Cette fonction retourne un select avec tous les articles dans la base de donnée
	public @ResponseBody String getAllArticlesDescription(HttpServletRequest request) {
		String result = "<select name='designation' class='select_designation' id='"+request.getParameter("cmp")+"' ><option value=''></option>";
		for (Article a : metier.getAllArticles()) {
			result+= "<option value='"+a.getDesignation()+"'>"+a.getDesignation()+"</option>";
		}
		result += "</select>";
		return result;
	}
	
	@RequestMapping(value = ""+Path.stock+"/ajax/getAllArticles", method = RequestMethod.POST) // Cette fonction retourne un select avec tous les articles dans la base de donnée
	public @ResponseBody String getAllArticlesDescriptionStock(HttpServletRequest request) {
		String result = "<select name='name_article' class='select_article_name' id='"+request.getParameter("cmp")+"' ><option value=''></option>";
		for (Article a : metier.getAllArticles()) {
			result+= "<option value='"+a.getDesignation()+"'>"+a.getDesignation()+"</option>";
		}
		result += "</select>";
		return result;
	}
	
	@RequestMapping(value = ""+Path.comptabilite+"/ajax/getArticleByOne", method = RequestMethod.POST) // Cette fonction retourne un json avec les informations de l'article a afficher
	public @ResponseBody String getArticleByOne(HttpServletRequest request) {
		Article a = metier.getArticleByDesignation(request.getParameter("designation"));
		JSONObject obj = new JSONObject();
		obj.put("reference", a.getReference());
		obj.put("tva", a.getTva());
		obj.put("prix_ht", a.getPrix_ht_vente());
		return obj.toString();
	}
	
	@RequestMapping(value = ""+Path.stock+"/ajax/getArticleByOne", method = RequestMethod.POST) // Cette fonction retourne un json avec les informations de l'article a afficher
	public @ResponseBody String getArticleByOneStock(HttpServletRequest request) {
		Article a = metier.getArticleByDesignation(request.getParameter("designation"));
		JSONObject obj = new JSONObject();
		obj.put("reference", a.getReference());
		obj.put("tva", a.getTva());
		obj.put("prix_ht", a.getPrix_ht_vente());
		obj.put("prix_ht_achat", a.getPrix_ht_achat());
		obj.put("quantity", a.getQuantite());
		return obj.toString();
	}
}
