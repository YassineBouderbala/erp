package com.erp.myapp.metier;

import java.util.Collection;

import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.erp.myapp.dao.IGlobalDao;
import com.erp.myapp.entity.Article;
import com.erp.myapp.entity.Avoir;
import com.erp.myapp.entity.BonDeLivraison;
import com.erp.myapp.entity.Customer_Pro;
import com.erp.myapp.entity.Devis;
import com.erp.myapp.entity.Employee;
import com.erp.myapp.entity.Entreprise;
import com.erp.myapp.entity.Entry_Achat;
import com.erp.myapp.entity.Facture;
import com.erp.myapp.entity.Fournisseur_Pro;
import com.erp.myapp.entity.Line;
import com.erp.myapp.entity.Sortie_Vente;
import com.erp.myapp.entity.Theme;
import com.erp.myapp.entity.Totals;
import com.erp.myapp.entity.User;
@Transactional
public class MetierImplement implements IGlobalMetier {
	private IGlobalDao dao;
	
	public void setDao(IGlobalDao dao){
		this.dao = dao;
	}
	/******** DEVIS PART ********/
	
	@Override
	public Devis getDevisByGuid(String guid) {
		return dao.getDevisByGuid(guid);
	}
	
	@Override
	public Devis getLastDevis() {
		return dao.getLastDevis();
	}
	
	@Override
	public void addDevis(Devis d) {
		dao.addDevis(d);
	}
	
	@Override
	public void updateDevis(Devis d) {
		 dao.updateDevis(d);
	}
	
	@Override
	public void removeDevis(String guid) {
		dao.removeDevis(guid);
	}

	/******** BON DE LIVRAISON PART ********/
	
	@Override
	public BonDeLivraison getLastBDL() {
		return dao.getLastBDL();
	}
	
	@Override
	public void addBDL(BonDeLivraison b) {
		dao.addBDL(b);
	}
	
	@Override
	public void deleteBonDeLivraisonByDevisId(Long id) {
		dao.deleteBonDeLivraisonByDevisId(id);
	}
	@Override
	public BonDeLivraison getBonDeLivraisonById(Long id) {
		return dao.getBonDeLivraisonById(id);
	}
	
	public BonDeLivraison getBonDeLivraisonByGuid(String guid){
		return dao.getBonDeLivraisonByGuid(guid);
	}
	
	/******** FACTURE **********/
	
	@Override
	public Collection<Facture> getAllFacture(){
		return dao.getAllFacture();
	}
	
	@Override
	public void addFacture(Facture f) {
		dao.addFacture(f);
	}
	@Override
	public void updateFacture(Facture f) {
		dao.updateFacture(f);
	}
	@Override
	public Facture getLastFacture() {
		return dao.getLastFacture();
	}
	
	@Override
	public void deleteFactureByDevisId(Long id) {
		dao.deleteFactureByDevisId(id);
	}
	@Override
	public Facture getFactureById(Long id) {
		return dao.getFactureById(id);
	}
	
	@Override
	public Facture getFactureByGuid(String guid) {
		return dao.getFactureByGuid(guid);
	}
	
	/******** AVOIR PART ********/
	
	@Override
	public Avoir getLastAvoir() {
		return dao.getLastAvoir();
	}
	
	@Override
	public void addAvoir(Avoir a) {
		dao.addAvoir(a);
	}
	
	public Avoir getAvoirByGuid(String guid){
		return dao.getAvoirByGuid(guid);
	}
	
	@Override
	public void updateAvoir(Avoir a) {
		dao.updateAvoir(a);	
	}
	
	/******** USER PART ********/
	
	@Override
	public User getUserById(Long id) {
		return dao.getUserById(id);
	}
	
	@Override
	public void updateBDL(BonDeLivraison b) {
		dao.updateBDL(b);
	}
	
	/******** ENTREPRISE PART ********/

	@Override
	public Entreprise getEntreprise() {
		return dao.getEntreprise();
	}
	
	public void addEntreprise(Entreprise e){
		dao.addEntreprise(e);
	}
	
	public void updateEntreprise(Entreprise e){
		dao.updateEntreprise(e);
	}
	
	/******** CUSTOMER PART ********/
	@Override
	public Customer_Pro getCustomerByGuid(String guid) {
		return dao.getCustomerByGuid(guid);
	}
	
	/******** ARTICLE PART ********/
	
	@Override
	public Collection<Article> getAllArticles() {
		return dao.getAllArticles();
	}

	@Override
	public Article getArticleByDesignation(String designation) {
		return dao.getArticleByDesignation(designation);
	}
	
	@Override
	public void updateArticle(Article a) {
		dao.updateArticle(a);
	}
	
	public Article getLastArticle(){
		return dao.getLastArticle();
	}
	
	public void addArticle(Article a){
		dao.addArticle(a);
	}
	
	@Override
	public Article getArticleByReference(String reference) {
		return dao.getArticleByReference(reference);
	}
	@Override
	public Collection<Article> getArticleCroissantByQantity() {
		return dao.getArticleCroissantByQantity();
	}
	@Override
	public Collection<Article> getArticleCroissantByPrixAchat() {
		return dao.getArticleCroissantByPrixAchat();
	}
	@Override
	public Collection<Article> getArticleCroissantByPrixVente() {
		return dao.getArticleCroissantByPrixVente();
	}
		
	/******** LINE PART ********/
	
	@Override
	public void addLine(Line l) {
		dao.addLine(l);
	}
	
	@Override
	public Line getLineByDescription(String description) {
		return dao.getLineByDescription(description);
	}
	
	@Override
	public Collection<Line> getLineByDevisId(Long id) {
		return dao.getLineByDevisId(id);
	}
	
	@Override
	public void removeLine(Long id) {
		dao.removeLine(id);
	}
	
	@Override
	public Line getLineById(Long id) {
		return dao.getLineById(id);
	}
	
	public Collection<Line> getLineByAvoirId(Long id){
		return dao.getLineByAvoirId(id);
	}
		
	/******** TOTAL PART ********/	
	
	@Override
	public void addTotal(Totals t) {
		dao.addTotal(t);
	}
	
	@Override
	public void removeTotal(Long id) {
		dao.removeTotal(id);
	}
	@Override
	public Totals getTotalById(Long id) {
		return dao.getTotalById(id);
	}
	
	/******** SORTIE_VENTE PART ********/
	
	@Override
	public void addSortieVente(Sortie_Vente v) {
		dao.addSortieVente(v);
	}
	@Override
	public Collection<Sortie_Vente> getSortieVenteByDevisId(Long id) {
		return dao.getSortieVenteByDevisId(id);
	}
	@Override
	public void removeSortieVenteByDevisIdAndUpdateStock(Long id) {
		dao.removeSortieVenteByDevisIdAndUpdateStock(id);
	}
	
	@Override
	public Collection<Sortie_Vente> getAllSortieVente() {
		return dao.getAllSortieVente();
	}
	@Override
	public Collection<Sortie_Vente> getSortieVentebyArticle(Article a) {
		return dao.getSortieVentebyArticle(a);
	}
	
	/******** ENTRY_ACHAT PART ********/
	
	@Override
	public void addEntryAchat(Entry_Achat e) {
		dao.addEntryAchat(e);
	}
	
	@Override
	public void deleteEntryAchatById(Long id) {
		dao.deleteEntryAchatById(id);
	}
	
	@Override
	public Collection<Entry_Achat> getAllEntryAchat() {
		return dao.getAllEntryAchat();
	}
	@Override
	public Collection<Entry_Achat> getEntryAchatbyArticle(Article a) {
		return dao.getEntryAchatbyArticle(a);
	}
	
	/******** THEME PART ********/
	
	@Override
	public Theme getTheme() {
		return dao.getTheme();
	}
	@Override
	public void addTheme(Theme t) {
		dao.addTheme(t);
	}
	@Override
	public void updateTheme(Theme t) {
		dao.updateTheme(t);
	}
	
	/******** FOURNISSEUR PART ********/
	
	@Override
	public Fournisseur_Pro getFournisseurByGuid(String guid) {
		return dao.getFournisseurByGuid(guid);
	}
	
	@Override
	public Collection<Fournisseur_Pro> getAllFournisseur() {
		return dao.getAllFournisseur();
	}
	@Override
	public Fournisseur_Pro getLastFournisseur() {
		return dao.getLastFournisseur();
	}
	@Override
	public void updateFournisseur(Fournisseur_Pro f) {
		dao.updateFournisseur(f);
	}
	@Override
	public void deleteFournisseur(Long id) {
		dao.deleteFournisseur(id);
	}
	@Override
	public Fournisseur_Pro getFournisseurById(Long id) {
		return dao.getFournisseurById(id);
	}
	@Override
	public void addFournisseur(Fournisseur_Pro f) {
		dao.addFournisseur(f);
	}

}
