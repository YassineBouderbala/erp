package com.erp.myapp.comptabilite;

import java.util.List;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.erp.myapp.entity.Avoir;
import com.erp.myapp.entity.Customer_Pro;
import com.erp.myapp.entity.Devis;
import com.erp.myapp.entity.Entreprise;
import com.erp.myapp.entity.Line;
import com.erp.myapp.entity.Theme;
import com.erp.myapp.entity.Totals;
import com.erp.myapp.metier.IGlobalMetier;

public class Html_Comptabilite {
	private IGlobalMetier metier;
	private String footer_devis = "&nbsp;";
	private String footer_facture = "&nbsp;";
	
	public Html_Comptabilite(){

	}

	public Html_Comptabilite(IGlobalMetier metier){
		this.metier = metier;
	}

	public String part_one(){ // Partie en commun qui contient l'image et les informations de l'entreprise
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		Entreprise e = metier.getEntreprise();
		return "<html> <head> <style type='text/css'> #table_general{ width: 98%;height:1122px;} .part1{height: 200px; } .part2{height: 180px; } .part3{height: 85px;  } .part4{height: 735px;} .part5{height: 280px;} .part6{height: 40px;} .part7{height: 40px;} #logo{width: 400px; height: 195px; float:left;} #logo img {height:100%;width: 100%;} #details_header{float: left;} #details_header{font-size: 20px;padding-top:25px;} #information_document{ width: 560px;height: 168px; float: left; padding-top: 14px;} #information_client { font-size: 18px; float: left; padding-top: 20px;} #table_information_client{ width: 480px;height:110px; border-width: 1px; border-style: solid; border-color: black; } .table_information_validity_td{border-width: 1px; border-style: solid; border-color: black; height: 35px; width: 200px;text-align: center;} #table_information_validity{border-collapse: collapse;} #puch_validity{float: left;} #information_validity{float: left;} #table_information_generale_devis{border-collapse: collapse;} .table_information_generale_devis_td{text-align:center;height: 37px; padding-top: 10px; padding-bottom: 10px; border-width: 1px; border-style: solid; border-color: black;} #information_generale_devis{float: left;} #push_information_generale_devis{ float:left; width: 0px;} #before_footer{width: 300px; float: left;height: 260px;} #header_table {background-color:#303233; color:white;} #color_header{color:#303233;} </style> </head> <body> <table id='table_general'> <tr> <td class='part1'> <div id='logo'> <img src='"+requestAttributes.getRequest().getRealPath("/resources/pictures/picture_entreprise/"+e.getLogo()+"")+"'/> </div><div style='float: left;'> &nbsp;&nbsp;&nbsp;&nbsp; </div><div id='details_header'> <span><b>"+e.getName()+"</b></span><br/> <span>"+e.getForme_juridique()+" au capital de "+e.getCapital()+" "+e.getMoney_type()+"</span><br/> <span>"+e.getAddress()+"</span><br/> <span>"+e.getCode_postal()+" "+e.getCity()+"</span><br/> <span>N° SIREN : "+e.getSiren_number()+"</span><br/> <span>N° TVA : "+e.getTv_number()+"</span> </div> </td> </tr>";
	}

	public String part_two(String argtype,String arguid){ // Partie qui contient à qui est destiné le devis ainsi que la reference du document
		Devis d = new Devis();
		Avoir a = new Avoir();
		Customer_Pro c = new Customer_Pro();
		String refOfDocument = "";
		if(argtype.toLowerCase().equals("devis"))
		{
			d = metier.getDevisByGuid(arguid);
			c = metier.getCustomerByGuid(d.getCustomer().getGuid());
			refOfDocument = d.getReference(); // On affiche la reference du devis
		}
		else if(argtype.toLowerCase().equals("facture"))
		{
			d = metier.getDevisByGuid(arguid);
			c = metier.getCustomerByGuid(d.getCustomer().getGuid());
			refOfDocument = d.getFacture().getReference(); // on affiche la reference de la facture
		}
		else if(argtype.toLowerCase().equals("avoir")){
			a = metier.getAvoirByGuid(arguid);
			c = metier.getCustomerByGuid(a.getFacture().getCustomer().getGuid());
			refOfDocument = a.getReference();
		}
		else
		{
			d= metier.getDevisByGuid(arguid);
			c = metier.getCustomerByGuid(d.getCustomer().getGuid());
			refOfDocument = d.getBonDeLivraison().getReference(); // on affiche la reference du bon de livraison
			argtype = "BON DE LIVRAISON";
		}
		return "<tr> <td class='part2'> <div id='information_document'> <h2 id='color_header' >"+argtype.toUpperCase()+"</h2> <h3>N° <b>"+refOfDocument+"</b></h3> </div> <div id='information_client'> <table id='table_information_client'> <tr> <td style='font-size:30px; text-align:center;' id='color_header'>"+c.getName()+"</td> </tr> <tr> <td style='text-align:center;'>"+c.getAddress()+"</td> </tr> <tr> <td style='text-align:center;'>"+c.getCode_postal()+" "+c.getCity()+", "+c.getCountry()+"</td> </tr> </table> </div> </td> </tr>";
	}

	public String part_three(String argType,String arguid){ // Cette partie contient les validite des des documents ( dates validite etc)
		Devis d = new Devis();
		Avoir a = new Avoir();
		if(argType.toLowerCase().equals("devis"))
		{
			d = metier.getDevisByGuid(arguid);
			return "<tr> <td class='part3'> <div id='puch_validity' style='width: 21%;'> <span>&nbsp;</span> </div> <div id='information_validity'> <table id='table_information_validity'> <tr> <td id='header_table' class='table_information_validity_td'>DATE</td> <td id='header_table' class='table_information_validity_td'>VALIDITE</td> <td id='header_table' class='table_information_validity_td'>Réf client</td> </tr> <tr> <td class='table_information_validity_td'>"+d.getDate()+"</td> <td class='table_information_validity_td'>"+d.getDate_validity()+"</td> <td class='table_information_validity_td'>"+d.getCustomer().getReference()+"</td> </tr> </table> </div> </td> </tr>";
		}
		else if(argType.toLowerCase().equals("avoir")){
			a = metier.getAvoirByGuid(arguid);
			return "<tr> <td class='part3'> <div id='puch_validity' style='width: 22%;'> <span>&nbsp;</span> </div> <div id='information_validity'> <table id='table_information_validity'> <tr> <td id='header_table' class='table_information_validity_td'>DATE</td> <td id='header_table' class='table_information_validity_td'>Réf facture</td> <td id='header_table' class='table_information_validity_td'>Réf client</td> </tr> <tr> <td class='table_information_validity_td'>"+a.getDate()+"</td> <td class='table_information_validity_td'>"+a.getFacture().getReference()+"</td> <td class='table_information_validity_td'>"+a.getFacture().getCustomer().getReference()+"</td> </tr> </table> </div> </td> </tr>";
		}
		else if (argType.toLowerCase().equals("bon_de_livraison")) {
			d = metier.getDevisByGuid(arguid);
			return "<tr> <td class='part3'> <div id='puch_validity' style='width: 21%;'> <span>&nbsp;</span> </div> <div id='information_validity'> <table id='table_information_validity'> <tr> <td id='header_table' class='table_information_validity_td'>DATE</td> <td id='header_table' class='table_information_validity_td'>DATE DE LIVRAISON</td> <td id='header_table' class='table_information_validity_td'>Réf client</td> </tr> <tr> <td class='table_information_validity_td'>"+d.getBonDeLivraison().getDate()+"</td> <td class='table_information_validity_td'>"+d.getBonDeLivraison().getDate_livraison()+"</td> <td class='table_information_validity_td'>"+d.getCustomer().getReference()+"</td> </tr> </table> </div> </td> </tr>";
		}
		else{
			d = metier.getDevisByGuid(arguid);
			return "<tr><td class='part3'> <div id='puch_validity' style='width: 13%';> <span>&nbsp;</span> </div> <div id='information_validity'> <table id='table_information_validity'> <tr> <td id='header_table' class='table_information_validity_td'>DATE</td> <td id='header_table' class='table_information_validity_td'>DELAI DE PAYEMENT</td> <td id='header_table' class='table_information_validity_td'>MODE DE REGLEMENT</td> <td id='header_table' class='table_information_validity_td'>Réf client</td> </tr> <tr> <td class='table_information_validity_td'>"+d.getFacture().getDate()+"</td> <td class='table_information_validity_td'>"+d.getFacture().getDelay_paiement()+"</td> <td class='table_information_validity_td'>"+d.getFacture().getMode_paiement()+"</td> <td class='table_information_validity_td'>"+d.getCustomer().getReference()+"</td> </tr> </table> </div> </td> </tr>";
		}
	}

	public String parth_four(String argType,String arguid,List<Line> argListLine){ // Cette partie affiche les informations du devis
		Devis d = new Devis();
		Avoir a = new Avoir();
		Totals t = new Totals();
		if(argType.toLowerCase().equals("avoir")){
			a = metier.getAvoirByGuid(arguid);
			t = a.getTotal();
		}
		else{
			d = metier.getDevisByGuid(arguid);
			t = d.getTotal();
		}
		String inCommunFirst = "<tr> <td class='part4'> <div id='push_information_generale_devis'> <span>&nbsp;</span> </div> <div id='information_generale_devis'> <table id='table_information_generale_devis'> <tr> <td id='header_table' class='table_information_generale_devis_td' style='width:100px;'>TYPE</td> <td id='header_table' class='table_information_generale_devis_td' style='width:130px;'>REFERENCE</td> <td id='header_table' class='table_information_generale_devis_td' 					>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;DESCRIPTION&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td> <td id='header_table' class='table_information_generale_devis_td' style='width:110px;'>QUANTITE</td> <td id='header_table' class='table_information_generale_devis_td' style='width:100px;' >TVA(%)</td> <td id='header_table' class='table_information_generale_devis_td' style='width:130px;'>PRIX HT</td> <td id='header_table' class='table_information_generale_devis_td' style='width:130px;'>TOTAL HT</td> </tr>";
		String middle = "";
		String inCommunLast = "</table> </div> </td> </tr><tr> <td class='part5'>";

		for (Line line : argListLine) {
			inCommunFirst += "<tr> <td class='table_information_generale_devis_td'>"+line.getType()+"</td> <td class='table_information_generale_devis_td'>"+line.getReference()+"</td> <td class='table_information_generale_devis_td'>"+line.getDescription()+"</td> <td class='table_information_generale_devis_td'>"+line.getQuantity()+"</td> <td class='table_information_generale_devis_td'>"+line.getTva()+"</td> <td class='table_information_generale_devis_td'>"+line.getPrix_ht()+"</td> <td class='table_information_generale_devis_td'>"+line.getTotal_ht()+"</td> </tr>";
		}

		if(argType.toLowerCase().equals("devis") || argType.toLowerCase().equals("facture") || argType.toLowerCase().equals("avoir")){
			String toPaye = null;
			toPaye = (argType.toLowerCase().equals("avoir")) ? "A déduire" : "A payer";
			middle = "<tr> <td class=''></td> <td class=''></td> <td class=''></td> <td class=''></td> <td class=''></td> <td class='table_information_generale_devis_td' id='header_table'>Total Ht</td> <td class='table_information_generale_devis_td'>"+t.getTotal_ht()+"</td> </tr> <tr> <td class=''></td> <td class=''></td> <td class=''></td> <td class=''></td> <td class=''></td> <td class='table_information_generale_devis_td' id='header_table'>Montant TVA</td> <td class='table_information_generale_devis_td'>"+t.getTva()+"</td> </tr> <tr> <td class=''></td> <td class=''></td> <td class=''></td> <td class=''></td> <td class=''></td> <td class='table_information_generale_devis_td' id='header_table'>Total ttc</td> <td class='table_information_generale_devis_td'>"+t.getTotal_ttc()+"</td> </tr> <tr> <td class=''></td> <td class=''></td> <td class=''></td> <td class=''></td> <td class=''></td> <td class='table_information_generale_devis_td' id='header_table'>"+toPaye+"</td> <td class='table_information_generale_devis_td'>"+t.getTotal_ttc()+"</td> </tr>";
		}
		return inCommunFirst + middle + inCommunLast;
	}

	public String parth_five(String argType){ // cette partie affiche le bas du documents qui n'est pas affiché si on ajoute une page
		try { 
			Theme t = metier.getTheme();
			if(t.getFooter_devis() != null)
			{
				this.footer_devis = t.getFooter_devis();				
			}
			if(t.getFooter_facture() != null)
			{
				this.footer_facture = t.getFooter_facture();				
			}			
		} catch (Exception e) {
			
		}
		if(argType.toLowerCase().equals("devis"))
		{
			return "<div id='before_footer' style='float: left;'> <p>BON POUR ACCORD</p> <table id='table_before_footer'> <tr> <td style='border-width: 1px; border-style: solid; border-color: black; width:300px; height: 150px;'> </td> </tr> </table> <p> Indiquez votre signature ainsi que votre cachet ci-dessus</p> </div> <div id='before_footer_reglement' style='float: left;width:700px;'> <table> <tr> <td style='padding-left:25px;padding-top:55px; font-size:12px;'> <i style='color:#575A5A;'>"+this.footer_devis+"</i> </td> </tr> </table> </div>";
		}
		else if (argType.toLowerCase().equals("bon_de_livraison")) {
			return "<h3>Marchandises reçues le _____________</h3> <h3>Signature client</h3>";
		}
		else if (argType.toLowerCase().equals("facture")){
			return "<i style='font-size: 17px; color:#575A5A'>"+this.footer_facture+"</i>";
		}
		else
		{
			return "<div></div>";
		}
	}

	public String parth_six(){ // footer
		Entreprise e = metier.getEntreprise();
		return "</td> </tr><tr> <td class='part6'> <div style='width: 100%;'> <p style='text-align:center; font-size:12px;'>"+e.getName()+" - Tél : "+e.getPhone()+" - Fax : "+e.getFax()+" - Email: "+e.getEmail()+"</p> </div> </td> </tr>";
	}

	public String parth_seven(String actual_page, String total_page){ // ici est donnée le numero de page
		return "<tr> <td class='part7'> <div> "+actual_page+"/"+total_page+" </div> </td> </tr> </table> </body> </html>";
	}
}
