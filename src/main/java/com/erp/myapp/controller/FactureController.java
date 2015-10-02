package com.erp.myapp.controller;

import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.HSSFRegionUtil;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.erp.myapp.comptabilite.Pdf_Comptabilite;
import com.erp.myapp.entity.Devis;
import com.erp.myapp.entity.Entreprise;
import com.erp.myapp.entity.Facture;
import com.erp.myapp.entity.Line;
import com.erp.myapp.metier.IGlobalMetier;
import com.erp.myapp.security.Datetime;
import com.erp.myapp.security.Guid;
import com.erp.myapp.security.Path;
import com.erp.myapp.security.ResourceNotFoundException;
import com.erp.myapp.utils.Facture_Util;

@Controller
public class FactureController {
	@Autowired
	IGlobalMetier metier;

	@RequestMapping(value=""+Path.comptabilite+"/createFactureEdit", method = RequestMethod.POST)
	public String Form(Model model,HttpServletRequest request){ // Partie formulaire pour création de la facture
		try {
			request.setCharacterEncoding("UTF-8");
			Devis d = metier.getDevisByGuid(request.getParameter("refDevis"));
			if(d.getFacture() == null && d.isValid())
			{
				Long reference;
				try {
					reference = metier.getLastFacture().getId() + 1;
				} catch (Exception e) {
					reference = (long)1;
				}
				Facture f = new Facture();
				f.setCreated_by(SecurityContextHolder.getContext().getAuthentication().getName());
				f.setDate(Datetime.getCurrentDate());
				f.setDelay_paiement(request.getParameter("date_payement"));
				f.setMode_paiement(request.getParameter("moyen_payement"));
				f.setGuid(Guid.generateFacture(metier));
				f.setCustomer(d.getCustomer());
				f.setReference("F00"+reference.toString());
				f.setDevis(d);
				metier.addFacture(f);				
			}
			return "redirect:"+Path.comptabilite+"/devis?refDevis="+d.getGuid()+"";		
		} catch (Exception e) {
			throw new ResourceNotFoundException(); // Génère une exception 404
		}
	}

	@RequestMapping(value=""+Path.comptabilite+"/pdf_create_facture",method=RequestMethod.GET ) // On affiche et créer le bon de livraison en pdf
	public String pdf_create_facture(Model model,HttpServletRequest request){
		try {
			ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			Devis d = metier.getDevisByGuid(request.getParameter("refDevis"));
			if(!d.getFacture().isPdf_created()){ // Si la Facture n'est pas créer alors on le crée
				Pdf_Comptabilite.create("FACTURE",d.getGuid(),metier);
			}
			return "redirect:/resources/comptabilite/facture/"+d.getFacture().getReference()+".pdf";
		} catch (Exception e) {
			throw new ResourceNotFoundException(); // Génère une exception 404
		}
	}

	@RequestMapping(value=""+Path.comptabilite+"/modifiy_facture",method=RequestMethod.POST ) // On modifie une facture
	public String modifiy_facture(Model model,HttpServletRequest request){
		try {
			ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			request.setCharacterEncoding("UTF-8");
			Devis d = metier.getDevisByGuid(request.getParameter("refDevis"));
			d.getFacture().setPdf_created(false);
			String facture_pdf = requestAttributes.getRequest().getRealPath("/resources/comptabilite/facture/"+d.getFacture().getReference()+".pdf"); 
			File deleteFacture = new File(facture_pdf);
			deleteFacture.delete();
			d.getFacture().setDelay_paiement(request.getParameter("date_payement"));
			d.getFacture().setMode_paiement(request.getParameter("moyen_payement"));
			metier.updateFacture(d.getFacture()); // on update la facture
			return "redirect:"+Path.comptabilite+"/devis?refDevis="+d.getGuid()+"";
		} catch (Exception e) {
			throw new ResourceNotFoundException(); // Génère une exception 404
		}
	}

	@RequestMapping(value=""+Path.comptabilite+"/check_paid",method=RequestMethod.GET ) // On change status de facture
	public String check_paid(Model model,HttpServletRequest request){
		try {
			Facture f = metier.getFactureByGuid(request.getParameter("refFacture"));
			if(!f.isPayed()){
				f.setPayed(true);
				f.setDate_paiement(Datetime.getCurrentDate());
				metier.updateFacture(f);
			}
			return "redirect:"+Path.comptabilite+"/devis?refDevis="+f.getDevis().getGuid()+"";
		} catch (Exception e) {
			throw new ResourceNotFoundException(); // Génère une exception 404
		}
	}

	@RequestMapping(value=""+Path.comptabilite+"/excel_facture_create",method=RequestMethod.GET ) // On change status de facture
	public String excel_facture_create(Model model,HttpServletRequest request){
		try {
			Facture f = metier.getFactureByGuid(request.getParameter("refFacture"));
			try {
				ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
				String facture_excel = requestAttributes.getRequest().getRealPath("/resources/comptabilite/facture/excel/"+f.getReference()+".xls"); 
				File deleteFacture = new File(facture_excel);
				deleteFacture.delete();				
			} catch (Exception e) {}
			Facture_Util.createExcelFacture(metier, request,f);
			return "redirect:/resources/comptabilite/facture/excel/"+f.getReference()+".xls";
		} catch (Exception exception) {
			throw new ResourceNotFoundException(); // Génère une exception 404
		}
	}
}
