package com.erp.myapp.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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

public class DaoImplement implements IGlobalDao {
	@PersistenceContext
	EntityManager em;
	
	/******** DEVIS PART ********/
	@Override
	public void addDevis(Devis d) {
		em.persist(d);
	}

	@Override
	public Devis getDevisByGuid(String guid) {
		Query req= em.createQuery("SELECT d FROM Devis d where d.guid='"+guid+"'");
		return (Devis) req.getSingleResult();		
	}
	
	@Override
	public Devis getLastDevis() {
		Query req= em.createQuery("SELECT d FROM  Devis d WHERE d.id = (SELECT MAX(d.id)  FROM Devis d)");
		return (Devis) req.getSingleResult();
	}
	
	@Override
	public void updateDevis(Devis d) {
		em.merge(d);
	}
	
	@Override
	public void removeDevis(String guid) {
		Devis d = getDevisByGuid(guid);
		em.remove(d);
	}
	
	/******** BON DE LIVRAISON PART 
	 * @return ********/
	
	@Override
	public BonDeLivraison getLastBDL() {
		Query req= em.createQuery("SELECT b FROM  BonDeLivraison b WHERE b.id = (SELECT MAX(b.id)  FROM BonDeLivraison b)");
		return (BonDeLivraison) req.getSingleResult();	
	}
	
	@Override
	public void addBDL(BonDeLivraison b) {
		em.persist(b);
	}
	
	@Override
	public void updateBDL(BonDeLivraison b) {
		em.merge(b);
	}
	
	@Override
	public void deleteBonDeLivraisonByDevisId(Long id) {
		BonDeLivraison b = getBonDeLivraisonById(id);
		em.remove(b);
	}
	
	@Override
	public BonDeLivraison getBonDeLivraisonById(Long id) {
		Query req= em.createQuery("SELECT b FROM  BonDeLivraison b WHERE b.devis.id ="+id+"");
		return (BonDeLivraison) req.getSingleResult();	
	}
	
	public BonDeLivraison getBonDeLivraisonByGuid(String guid){
		Query req= em.createQuery("SELECT b FROM  BonDeLivraison b WHERE b.guid ='"+guid+"'");
		return (BonDeLivraison) req.getSingleResult();		
	}
	
	/****** FACTURE ****/
	
	@Override
	public Collection<Facture> getAllFacture(){
		Query req= em.createQuery("SELECT f FROM  Facture f");
		return req.getResultList();			
	}
	
	@Override
	public void addFacture(Facture f) {
		em.persist(f);
	}

	@Override
	public void updateFacture(Facture f) {
		em.merge(f);
	}	
	@Override
	public Facture getLastFacture() {
		Query req= em.createQuery("SELECT f FROM  Facture f WHERE f.id = (SELECT MAX(f.id)  FROM Facture f)");
		return (Facture) req.getSingleResult();	
	}
	
	@Override
	public void deleteFactureByDevisId(Long id) {
		Facture f = getFactureById(id);
		em.remove(f);
	}
	
	@Override
	public Facture getFactureById(Long id) {
		Query req= em.createQuery("SELECT f FROM  Facture f WHERE f.devis.id ="+id+"");
		return (Facture) req.getSingleResult();	
	}
	
	@Override
	public Facture getFactureByGuid(String guid) {
		Query req= em.createQuery("SELECT f FROM  Facture f WHERE f.guid ='"+guid+"'");
		return (Facture) req.getSingleResult();	
	}
	
	/******** AVOIR PART ********/
	
	@Override
	public Avoir getLastAvoir() {
		Query req= em.createQuery("SELECT a FROM  Avoir a WHERE a.id = (SELECT MAX(a.id)  FROM Avoir a)");
		return (Avoir) req.getSingleResult();	
	}
	
	@Override
	public void addAvoir(Avoir a) {
		em.persist(a);
	}
	
	public Avoir getAvoirByGuid(String guid){
		Query req= em.createQuery("SELECT a FROM  Avoir a WHERE a.guid ='"+guid+"'");
		return (Avoir) req.getSingleResult();		
	}
	
	@Override
	public void updateAvoir(Avoir a) {
		em.merge(a);
	}
	
	/******** USER PART ********/
	@Override
	public User getUserById(Long id) {
		return em.find(User.class, id);
	}
	
	/******** ENTREPRISE PART ********/
	
	@Override
	public Entreprise getEntreprise() {
		Query req= em.createQuery("select e from Entreprise e");
		return (Entreprise) req.getSingleResult();
	}
	
	public void addEntreprise(Entreprise e){
		em.persist(e);
	}
	
	@Override
	public void updateEntreprise(Entreprise e) {
		em.merge(e);
	}
	
	/******** CUSTOMER PART ********/
	@Override
	public Customer_Pro getCustomerByGuid(String guid) {
		Query req= em.createQuery("SELECT c FROM Customer_Pro c where c.guid='"+guid+"'");
		return (Customer_Pro) req.getSingleResult();		
	}
	
	/******** ARTICLE PART ********/
	
	@Override
	public Collection<Article> getAllArticles() {
		Query req = em.createQuery("SELECT a FROM Article a where a.deleted = 0 order by a.designation");
		return req.getResultList();
	}

	@Override
	public Article getArticleByDesignation(String designation) {
		Query req= em.createQuery("SELECT a FROM Article a where a.deleted = 0 AND a.designation='"+designation+"'");
		return (Article) req.getSingleResult();
	}
	
	@Override
	public void updateArticle(Article a) {
		em.merge(a);
	}
	
	public Article getLastArticle(){
		Query req= em.createQuery("SELECT a FROM  Article a WHERE a.id = (SELECT MAX(a.id)  FROM Article a)");
		return (Article) req.getSingleResult();		
	}
	
	public void addArticle(Article a){
		em.persist(a);
	}
	
	@Override
	public Article getArticleByReference(String reference) {
		Query req= em.createQuery("SELECT a FROM Article a where a.deleted = 0 AND a.reference='"+reference+"'");
		return (Article) req.getSingleResult();
	}

	@Override
	public Collection<Article> getArticleCroissantByQantity() {
		Query req = em.createQuery("SELECT a FROM Article a where a.deleted = 0 order by a.quantite");
		return req.getResultList();
	}

	@Override
	public Collection<Article> getArticleCroissantByPrixAchat() {
		Query req = em.createQuery("SELECT a FROM Article a where a.deleted = 0 order by a.prix_ht_achat");
		return req.getResultList();
	}

	@Override
	public Collection<Article> getArticleCroissantByPrixVente() {
		Query req = em.createQuery("SELECT a FROM Article a where a.deleted = 0 order by a.prix_ht_vente");
		return req.getResultList();
	}
		
	/******** LINE PART ********/
	
	@Override
	public void addLine(Line l) {
		em.persist(l);
	}
	
	@Override
	public Line getLineByDescription(String description) {
		Query req= em.createQuery("SELECT l FROM Line l where l.description='"+description+"'");
		return (Line) req.getSingleResult();
	}
	
	@Override
	public Collection<Line> getLineByDevisId(Long id) {
		Query req = em.createQuery("SELECT l FROM Line l where l.devis.id='"+id+"'");
		return req.getResultList();		
	}
	
	@Override
	public Collection<Line> getLineByAvoirId(Long id) {
		Query req = em.createQuery("SELECT l FROM Line l where l.avoir.id='"+id+"'");
		return req.getResultList();		
	}
		
	@Override
	public void removeLine(Long id) {
		Line l = getLineById(id);
		em.remove(l);
	}
	
	@Override
	public Line getLineById(Long id) {
		Query req= em.createQuery("SELECT l FROM  Line l WHERE l.id ="+id+"");
		return (Line) req.getSingleResult();
	}

	/******** TOTAL PART ********/
	
	@Override
	public void addTotal(Totals t) {
		em.persist(t);
	}
	
	@Override
	public void removeTotal(Long id) {
		Totals t = getTotalById(id);
		em.remove(t);
	}
	
	@Override
	public Totals getTotalById(Long id) {
		Query req= em.createQuery("SELECT t FROM  Totals t WHERE t.id ="+id+"");
		return (Totals) req.getSingleResult();
	}
	
	/******** SORTIE_VENTE PART ********/
	
	@Override
	public void addSortieVente(Sortie_Vente v) {
		em.persist(v);
	}

	@Override
	public Collection<Sortie_Vente> getSortieVenteByDevisId(Long id) {
		Query req = em.createQuery("SELECT s FROM Sortie_Vente s where s.devis.id='"+id+"'");
		return req.getResultList();	
	}
	
	@Override
	public Collection<Sortie_Vente> getAllSortieVente() {
		Query req = em.createQuery("SELECT s FROM Sortie_Vente s where s.article.deleted = 0 order by s.id desc");
		return req.getResultList();	
	}

	@Override
	public Collection<Sortie_Vente> getSortieVentebyArticle(Article a) {
		Query req = em.createQuery("SELECT s FROM Sortie_Vente s where s.article.id = "+a.getId()+" AND s.article.deleted = 0 order by s.id desc");
		return req.getResultList();	
	}

	@Override
	public void removeSortieVenteByDevisIdAndUpdateStock(Long id) {
		List<Sortie_Vente> listStock = (List<Sortie_Vente>) getSortieVenteByDevisId(id);
		Long quantite = (long)0;
		for (Sortie_Vente sortie_Vente : listStock) {
			sortie_Vente.getArticle().setQuantite(sortie_Vente.getArticle().getQuantite() + sortie_Vente.getQuantity());
			em.remove(sortie_Vente);
			updateArticle(sortie_Vente.getArticle());
		}
	}

	/******** ENTRY_ACHAT PART ********/
	
	@Override
	public void addEntryAchat(Entry_Achat e) {
		em.persist(e);
	}
	
	@Override
	public void deleteEntryAchatById(Long id){
		Entry_Achat e = em.find(Entry_Achat.class, id);
		Article a = getArticleByDesignation(e.getArticle().getDesignation());
		a.setQuantite(a.getQuantite() + e.getQuantity());
		em.merge(a);
		em.remove(e);
	}
	
	@Override
	public Collection<Entry_Achat> getAllEntryAchat() {
		Query req = em.createQuery("SELECT e FROM Entry_Achat e where e.article.deleted = 0 order by e.id desc");
		return req.getResultList();			
	}

	@Override
	public Collection<Entry_Achat> getEntryAchatbyArticle(Article a) {
		Query req = em.createQuery("SELECT e FROM Entry_Achat e where e.article.id = "+a.getId()+" AND e.article.deleted = 0 order by e.id desc");
		return req.getResultList();	
	}
	
	/******** THEME PART ********/

	@Override
	public Theme getTheme() {
		Query req= em.createQuery("select t from Theme t");
		return (Theme) req.getSingleResult();
	}

	@Override
	public void addTheme(Theme t) {
		em.persist(t);
	}

	@Override
	public void updateTheme(Theme t) {
		em.merge(t);
	}
	
	/******** FOURNISSEUR PART ********/
	
	@Override
	public Fournisseur_Pro getFournisseurByGuid(String guid) {
		Query req= em.createQuery("SELECT f FROM Fournisseur_Pro f where f.guid='"+guid+"'");
		return (Fournisseur_Pro) req.getSingleResult();			
	}
	
	public Collection<Fournisseur_Pro> getAllFournisseur(){
		Query req= em.createQuery("SELECT f FROM Fournisseur_Pro f order by f.id");
		return req.getResultList();			
	}

	@Override
	public Fournisseur_Pro getLastFournisseur() {
		Query req= em.createQuery("SELECT f FROM  Fournisseur_Pro f WHERE f.id = (SELECT MAX(f.id)  FROM Fournisseur_Pro f)");
		return (Fournisseur_Pro) req.getSingleResult();	
	}
	
	public void addFournisseur(Fournisseur_Pro f){
		em.persist(f);
	}
	public void updateFournisseur(Fournisseur_Pro f){
		em.merge(f);
	}
	public void deleteFournisseur(Long id){
		em.remove(getFournisseurById(id));
	}

	@Override
	public Fournisseur_Pro getFournisseurById(Long id) {
		return em.find(Fournisseur_Pro.class, id);
	}
}
