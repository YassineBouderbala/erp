package com.erp.myapp.comptabilite;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.erp.myapp.entity.Avoir;
import com.erp.myapp.entity.Devis;
import com.erp.myapp.entity.Line;
import com.erp.myapp.metier.IGlobalMetier;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

public class Pdf_Comptabilite { 
	private static Integer numberLinePerPage = 6;

	public static void create(String argTypeOfDocument, String argGuid,IGlobalMetier metier){ // On crée un pdf de comptabilité ( devis, bon de livraison , facture)
		try {
			/*Declaration des variables pour creer un pdf */
			Document doc=new Document(PageSize.A3, 10, 10, 10, 10);
			XMLWorkerHelper worker=XMLWorkerHelper.getInstance();
			ByteArrayInputStream is = null;
			File file = null;
			String nameOfDocument = "";
			ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			Avoir a = new Avoir();
			Devis d = new Devis();
			/* ********** */
			if(argTypeOfDocument.toLowerCase().equals("avoir")){
				a = metier.getAvoirByGuid(argGuid);				
			}else{
				d = metier.getDevisByGuid(argGuid);				
			}
			if(argTypeOfDocument.toLowerCase().equals("devis")){
				nameOfDocument = d.getReference();
				d.setPdf_created(true);// Initialisation à 1 dans la bas de donnéee
				metier.updateDevis(d);
			}else if(argTypeOfDocument.toLowerCase().equals("facture")){
				nameOfDocument = d.getFacture().getReference();
				d.getFacture().setPdf_created(true);
				metier.updateFacture(d.getFacture());
			}
			else if(argTypeOfDocument.toLowerCase().equals("avoir")){
				nameOfDocument = a.getReference();
				a.setPdf_created(true);
				metier.updateAvoir(a);
			}
			else{
				nameOfDocument = d.getBonDeLivraison().getReference();
				d.getBonDeLivraison().setPdf_created(true);
				metier.updateBDL(d.getBonDeLivraison());
			}
			Html_Comptabilite html_comptabilite = new Html_Comptabilite(metier);
			file = new File(requestAttributes.getRequest().getRealPath("/resources/comptabilite/"+argTypeOfDocument.toLowerCase()+"/"+nameOfDocument+".pdf"));
			FileOutputStream fos=new FileOutputStream(file);
			PdfWriter pdfWriter=PdfWriter.getInstance(doc, fos);
			doc.open();
			help_create(html_comptabilite, is,  worker, pdfWriter, doc, argTypeOfDocument,metier,argGuid);
			doc.close();
			fos.close();																																																																																																																																																																																																																																																																																																																																																																																																																																																																																											//ICIIIIIIIIIIIIIIIII
			System.out.println("Done.");
		}
		catch (Exception e) {
			e.getMessage();
		}		
	}

	public static void help_create(Html_Comptabilite html_comptabilite,ByteArrayInputStream is, XMLWorkerHelper worker,PdfWriter pdfWriter,Document doc,String argTypeOfDocument,IGlobalMetier metier,String argGuidDevis) throws IOException{
		Avoir a = new Avoir();
		Devis d = new Devis();
		/* ********** */
		if(argTypeOfDocument.toLowerCase().equals("avoir")){
			a = metier.getAvoirByGuid(argGuidDevis);				
		}else{
			d = metier.getDevisByGuid(argGuidDevis);				
		}
		int compteur =0;
		Integer currentPage = new Integer(0); // Page courante
		Integer numberOfPage = new Integer(1); // nombre de page
		List<Line> lines_devis = new ArrayList<Line>(); // Cette liste sera l'intérmediaire pour pouvoir afficher les lignes que l'on veut
		List<Line> lines_document = new ArrayList<Line>();
		String myString = null;
		boolean breakTheLoop =  true;
		if(argTypeOfDocument.toLowerCase().equals("avoir")){
			lines_document = (List<Line>) a.getLine();
		}
		else
		{
			lines_document = (List<Line>) d.getLine();
		}
		for (Line line : lines_document) {
			lines_devis.add(line);
			compteur ++;
			if(lines_document.size() % numberLinePerPage == 0)
			{
				numberOfPage = lines_document.size() / numberLinePerPage; 
			}
			else
			{
				numberOfPage = lines_document.size() / numberLinePerPage + 1;
			}

			if (compteur % numberLinePerPage == 0 && compteur != lines_document.size()) { // Si on arrive au nombre de ligne max par page mais que le nombre de ligne contenu dans le tbaleau n'est pas atteinte
				currentPage++;
				if(argTypeOfDocument.toLowerCase().equals("devis") || argTypeOfDocument.toLowerCase().equals("facture") ){ //On affiche pas la partie 5
					myString = html_comptabilite.part_one() + html_comptabilite.part_two(argTypeOfDocument,argGuidDevis) + html_comptabilite.part_three(argTypeOfDocument,argGuidDevis) + html_comptabilite.parth_four("NO_FOOTER_LINE",argGuidDevis,lines_devis) + html_comptabilite.parth_six() + html_comptabilite.parth_seven(currentPage.toString(), numberOfPage.toString());
				}
				else if(argTypeOfDocument.toLowerCase().equals("avoir")){
					myString = html_comptabilite.part_one() + html_comptabilite.part_two(argTypeOfDocument,argGuidDevis) + html_comptabilite.part_three(argTypeOfDocument,argGuidDevis) + html_comptabilite.parth_four("NO_FOOTER_LINE",argGuidDevis,lines_devis) + html_comptabilite.parth_six() + html_comptabilite.parth_seven(currentPage.toString(), numberOfPage.toString());
				}
				else
				{
					myString = html_comptabilite.part_one() + html_comptabilite.part_two(argTypeOfDocument,argGuidDevis) + html_comptabilite.part_three(argTypeOfDocument,argGuidDevis) + html_comptabilite.parth_four(argTypeOfDocument,argGuidDevis,lines_devis) + html_comptabilite.parth_six() + html_comptabilite.parth_seven(currentPage.toString(), numberOfPage.toString());
				}
				is = new ByteArrayInputStream(myString.getBytes());
				worker.parseXHtml(pdfWriter, doc, is);
				doc.newPage();
				breakTheLoop = false;
				lines_devis.clear(); // initialisation du tableau intermediaire pour stocker les lignes voulues
				myString = null;
			}
			else if(compteur % numberLinePerPage == 0 && compteur == lines_document.size() || compteur % numberLinePerPage != 0 && compteur == lines_document.size()){ // on affiche tout lorsque on arrive a la taille du tableau finale.
				currentPage++;
				myString = html_comptabilite.part_one() + html_comptabilite.part_two(argTypeOfDocument,argGuidDevis) + html_comptabilite.part_three(argTypeOfDocument,argGuidDevis) + html_comptabilite.parth_four(argTypeOfDocument,argGuidDevis,lines_devis) + html_comptabilite.parth_five(argTypeOfDocument) + html_comptabilite.parth_six() + html_comptabilite.parth_seven(currentPage.toString(), numberOfPage.toString());
				is = new ByteArrayInputStream(myString.getBytes());
				worker.parseXHtml(pdfWriter, doc, is);
				breakTheLoop = false;
				lines_devis.clear();
				myString = null;
			}
		}
		if(breakTheLoop == true){ // Si le nombre de ligne par page contenu dans la liste est egale ou moins au nombre max de ligne par page (Cela va creer une seule page)
			currentPage++;
			myString = html_comptabilite.part_one() + html_comptabilite.part_two(argTypeOfDocument,argGuidDevis) + html_comptabilite.part_three(argTypeOfDocument,argGuidDevis) + html_comptabilite.parth_four(argTypeOfDocument,argGuidDevis,lines_devis) + html_comptabilite.parth_five(argTypeOfDocument) + html_comptabilite.parth_six() + html_comptabilite.parth_seven(currentPage.toString(), numberOfPage.toString());
			is = new ByteArrayInputStream(myString.getBytes());
			worker.parseXHtml(pdfWriter, doc, is);
			lines_devis.clear();
			myString = null;					
		}
	}
}
