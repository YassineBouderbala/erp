package com.erp.myapp.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.erp.myapp.entity.Avoir;
import com.erp.myapp.entity.Entreprise;
import com.erp.myapp.entity.Facture;
import com.erp.myapp.entity.Line;
import com.erp.myapp.metier.IGlobalMetier;

public class Facture_Util {
	public static void createExcelFacture(IGlobalMetier metier,HttpServletRequest request,Facture f) throws IOException{
		short font_size = 14;
		/* VARIABLES AND OBJECT */
		Entreprise e = metier.getEntreprise();
		request.setCharacterEncoding("UTF-8");
		/* FUNCTION EXCEL */
		
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(f.getReference());
		
		HSSFRow row = null;
		HSSFCell cell = null;
		HSSFCellStyle cellStyle = null;
		
		/*********** PARTIE ENTREPRISE *****************/
		
		row = sheet.createRow(0); // Première ligne
		cell = row.createCell(0, HSSFCell.CELL_TYPE_STRING); // Premiere colone
		cell.setCellValue(new HSSFRichTextString(e.getName()));
		cellStyle = wb.createCellStyle();
		HSSFFont hSSFFont = wb.createFont();
		hSSFFont.setFontHeightInPoints(font_size);
        cellStyle.setFont(hSSFFont);
        cell.setCellStyle(cellStyle);
		
		row = sheet.createRow(1); 
		cell = row.createCell(0, HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(new HSSFRichTextString(e.getForme_juridique()+" au capital de "+e.getCapital()+" "+e.getMoney_type()+""));
		cellStyle = wb.createCellStyle();
		hSSFFont.setFontHeightInPoints(font_size);
        cellStyle.setFont(hSSFFont);
        cell.setCellStyle(cellStyle);
        
		row = sheet.createRow(2); 
		cell = row.createCell(0, HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(new HSSFRichTextString(e.getAddress()));
		cellStyle = wb.createCellStyle();
		hSSFFont.setFontHeightInPoints(font_size);
        cellStyle.setFont(hSSFFont);
        cell.setCellStyle(cellStyle);
        
		row = sheet.createRow(3); 
		cell = row.createCell(0, HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(new HSSFRichTextString(e.getCode_postal()+" "+e.getCity()));
		cellStyle = wb.createCellStyle();
		hSSFFont.setFontHeightInPoints(font_size);
        cellStyle.setFont(hSSFFont);
        cell.setCellStyle(cellStyle);
        
		row = sheet.createRow(4); 
		cell = row.createCell(0, HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(new HSSFRichTextString("N° siren : "+e.getSiren_number()));
		cellStyle = wb.createCellStyle();
		hSSFFont.setFontHeightInPoints(font_size);
        cellStyle.setFont(hSSFFont);
        cell.setCellStyle(cellStyle);
        
		row = sheet.createRow(5); 
		cell = row.createCell(0, HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(new HSSFRichTextString("N° tva : "+e.getTv_number()));
		cellStyle = wb.createCellStyle();
		hSSFFont.setColor(HSSFColor.DARK_TEAL.index); // GENERAL POUR ENTREPRISE
		hSSFFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		hSSFFont.setFontHeightInPoints(font_size);
        cellStyle.setFont(hSSFFont);
        cell.setCellStyle(cellStyle);
        
        /*********** PARTIE FACTURE INFO *****************/
        
		row = sheet.createRow(7); 
		cell = row.createCell(0, HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(new HSSFRichTextString("Facture n° : "+f.getReference()));
		cellStyle = wb.createCellStyle();
		hSSFFont = wb.createFont();
		hSSFFont.setFontHeightInPoints(font_size);
        cellStyle.setFont(hSSFFont);
        cell.setCellStyle(cellStyle);
        
        
        /*********** PARTIE INFO CUSTOMER *****************/
        
        sheet.addMergedRegion(new Region(7,(short)9,7,(short)16)); // Fusionne les cellules pour le client
        sheet.addMergedRegion(new Region(9,(short)9,9,(short)16)); // Fusionne les cellules pour le client
        sheet.addMergedRegion(new Region(11,(short)9,11,(short)16)); // Fusionne les cellules pour le client
        
		cell = row.createCell(9, HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(new HSSFRichTextString(f.getCustomer().getName()));
		cellStyle = wb.createCellStyle();
		hSSFFont = wb.createFont();
		hSSFFont.setFontHeightInPoints((short)16);
		hSSFFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle.setFont(hSSFFont);
        cell.setCellStyle(cellStyle);
        
		row = sheet.createRow(8); 
		cell = row.createCell(0, HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(new HSSFRichTextString("Date : "+f.getDate()));
		cellStyle = wb.createCellStyle();
		hSSFFont = wb.createFont();
		hSSFFont.setFontHeightInPoints(font_size);
        cellStyle.setFont(hSSFFont);
        cell.setCellStyle(cellStyle);
        
        
		row = sheet.createRow(9); 
		cell = row.createCell(0, HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(new HSSFRichTextString("Delai : "+f.getDelay_paiement()));
		cellStyle = wb.createCellStyle();
		hSSFFont = wb.createFont();
		hSSFFont.setFontHeightInPoints(font_size);
		hSSFFont.setColor(HSSFColor.BLACK.index);
        cellStyle.setFont(hSSFFont);
        cell.setCellStyle(cellStyle);
        
		cell = row.createCell(9, HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(new HSSFRichTextString(f.getCustomer().getAddress()));
		cellStyle = wb.createCellStyle();
		hSSFFont = wb.createFont();
		hSSFFont.setFontHeightInPoints(font_size);
		hSSFFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle.setFont(hSSFFont);
        cell.setCellStyle(cellStyle);
        
		row = sheet.createRow(10); 
		cell = row.createCell(0, HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(new HSSFRichTextString("Mode de règlement : "+f.getMode_paiement()));
		cellStyle = wb.createCellStyle();
		hSSFFont = wb.createFont();
		hSSFFont.setFontHeightInPoints(font_size);
        cellStyle.setFont(hSSFFont);
        cell.setCellStyle(cellStyle);
        	        
		row = sheet.createRow(11); 
		cell = row.createCell(0, HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(new HSSFRichTextString("Réf client : "+f.getCustomer().getReference()));
		cellStyle = wb.createCellStyle();
		hSSFFont = wb.createFont();
		hSSFFont.setFontHeightInPoints(font_size);
		hSSFFont.setColor(HSSFColor.BLACK.index);
        cellStyle.setFont(hSSFFont);
        cell.setCellStyle(cellStyle);
        
		cell = row.createCell(9, HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(new HSSFRichTextString(f.getCustomer().getCode_postal()+" "+f.getCustomer().getCity()+" "+f.getCustomer().getCountry()));
		cellStyle = wb.createCellStyle();
		hSSFFont = wb.createFont();
		hSSFFont.setFontHeightInPoints(font_size);
		hSSFFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle.setFont(hSSFFont);
        cell.setCellStyle(cellStyle);
   	        
        /*********** PARTIE INFO LINE FACTURE *****************/
        
        Integer beginInfoLine = new Integer(17);
        short columnToTotal = 18;
        
        sheet.addMergedRegion(new Region(beginInfoLine,(short)0,beginInfoLine,(short)1));
        sheet.addMergedRegion(new Region(beginInfoLine,(short)2,beginInfoLine,(short)3));
        sheet.addMergedRegion(new Region(beginInfoLine,(short)4,beginInfoLine,(short)13));
        sheet.addMergedRegion(new Region(beginInfoLine,(short)14,beginInfoLine,(short)15));
        sheet.addMergedRegion(new Region(beginInfoLine,(short)16,beginInfoLine,(short)17));
        sheet.addMergedRegion(new Region(beginInfoLine,columnToTotal,beginInfoLine,(short)19));
        sheet.addMergedRegion(new Region(beginInfoLine,(short)20,beginInfoLine,(short)21));
        	        
		row = sheet.createRow(beginInfoLine); 
		cell = row.createCell(0, HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(new HSSFRichTextString("Type"));
		cellStyle = wb.createCellStyle();
		hSSFFont = wb.createFont();
		hSSFFont.setFontHeightInPoints(font_size);
		hSSFFont.setColor(HSSFColor.WHITE.index);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setFillForegroundColor(IndexedColors.BLACK.getIndex());
		cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cellStyle.setFont(hSSFFont);
        cell.setCellStyle(cellStyle);
        
		cell = row.createCell(2, HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(new HSSFRichTextString("Référence"));
		cellStyle = wb.createCellStyle();
		hSSFFont.setFontHeightInPoints(font_size);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setFillForegroundColor(IndexedColors.BLACK.getIndex());
		cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cellStyle.setFont(hSSFFont);
        cell.setCellStyle(cellStyle);
        
		cell = row.createCell(4, HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(new HSSFRichTextString("Désignation"));
		cellStyle = wb.createCellStyle();
		hSSFFont.setFontHeightInPoints(font_size);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setFillForegroundColor(IndexedColors.BLACK.getIndex());
		cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cellStyle.setFont(hSSFFont);
        cell.setCellStyle(cellStyle);
        
		cell = row.createCell(14, HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(new HSSFRichTextString("Quantité"));
		cellStyle = wb.createCellStyle();
		hSSFFont.setFontHeightInPoints(font_size);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setFillForegroundColor(IndexedColors.BLACK.getIndex());
		cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cellStyle.setFont(hSSFFont);
        cell.setCellStyle(cellStyle);
        
		cell = row.createCell(16, HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(new HSSFRichTextString("Tva (%)"));
		cellStyle = wb.createCellStyle();
		hSSFFont.setFontHeightInPoints(font_size);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setFillForegroundColor(IndexedColors.BLACK.getIndex());
		cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cellStyle.setFont(hSSFFont);
        cell.setCellStyle(cellStyle);
        
		cell = row.createCell(18, HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(new HSSFRichTextString("Prix HT"));
		cellStyle = wb.createCellStyle();
		hSSFFont.setFontHeightInPoints(font_size);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setFillForegroundColor(IndexedColors.BLACK.getIndex());
		cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cellStyle.setFont(hSSFFont);
        cell.setCellStyle(cellStyle);
        
		cell = row.createCell(20, HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(new HSSFRichTextString("Total HT"));
		cellStyle = wb.createCellStyle();
		hSSFFont.setFontHeightInPoints(font_size);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setFillForegroundColor(IndexedColors.BLACK.getIndex());
		cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cellStyle.setFont(hSSFFont);
        cell.setCellStyle(cellStyle);
        
        /***** CREATION DE TOUTES LES LIGNES *////
        
        for (Line l : f.getDevis().getLine()) {
        	beginInfoLine++;
	        sheet.addMergedRegion(new Region(beginInfoLine,(short)0,beginInfoLine,(short)1));
	        sheet.addMergedRegion(new Region(beginInfoLine,(short)2,beginInfoLine,(short)3));
	        sheet.addMergedRegion(new Region(beginInfoLine,(short)4,beginInfoLine,(short)13));
	        sheet.addMergedRegion(new Region(beginInfoLine,(short)14,beginInfoLine,(short)15));
	        sheet.addMergedRegion(new Region(beginInfoLine,(short)16,beginInfoLine,(short)17));
	        sheet.addMergedRegion(new Region(beginInfoLine,columnToTotal,beginInfoLine,(short)19));
	        sheet.addMergedRegion(new Region(beginInfoLine,(short)20,beginInfoLine,(short)21));
	        
			row = sheet.createRow(beginInfoLine); 
			cell = row.createCell(0, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(new HSSFRichTextString(l.getType()));
			cellStyle = wb.createCellStyle();
			hSSFFont = wb.createFont();
			hSSFFont.setFontHeightInPoints(font_size);
			hSSFFont.setColor(HSSFColor.BLACK.index);
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        cellStyle.setFont(hSSFFont);
	        cell.setCellStyle(cellStyle);
	        
			cell = row.createCell(2, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(new HSSFRichTextString(l.getReference()));
			cellStyle = wb.createCellStyle();
			hSSFFont.setFontHeightInPoints(font_size);
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        cellStyle.setFont(hSSFFont);
	        cell.setCellStyle(cellStyle);
	        
			cell = row.createCell(4, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(new HSSFRichTextString(l.getDescription()));
			cellStyle = wb.createCellStyle();
			hSSFFont.setFontHeightInPoints(font_size);
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        cellStyle.setFont(hSSFFont);
	        cell.setCellStyle(cellStyle);
	        
			cell = row.createCell(14, HSSFCell.CELL_TYPE_NUMERIC);
			cell.setCellValue(l.getQuantity());
			cellStyle = wb.createCellStyle();
			hSSFFont.setFontHeightInPoints(font_size);
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        cellStyle.setFont(hSSFFont);
	        cell.setCellStyle(cellStyle);
	        
			cell = row.createCell(16, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(new HSSFRichTextString(Double.toString(l.getTva())));
			cellStyle = wb.createCellStyle();
			hSSFFont.setFontHeightInPoints(font_size);
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        cellStyle.setFont(hSSFFont);
	        cell.setCellStyle(cellStyle);
	        
			cell = row.createCell(18, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(new HSSFRichTextString(Double.toString(l.getPrix_ht())));
			cellStyle = wb.createCellStyle();
			hSSFFont.setFontHeightInPoints(font_size);
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        cellStyle.setFont(hSSFFont);
	        cell.setCellStyle(cellStyle);
	        
			cell = row.createCell(20, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(new HSSFRichTextString(Double.toString(l.getTotal_ht())));
			cellStyle = wb.createCellStyle();
			hSSFFont.setFontHeightInPoints(font_size);
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        cellStyle.setFont(hSSFFont);
	        cell.setCellStyle(cellStyle);		        
		}
        beginInfoLine++;
        short tot2 = (short) (columnToTotal + 2);
        short tot3 = (short) (columnToTotal + 3);
        sheet.addMergedRegion(new Region(beginInfoLine,columnToTotal,beginInfoLine,(short)19));
        sheet.addMergedRegion(new Region(beginInfoLine,tot2,beginInfoLine,(short)tot3));
        
		row = sheet.createRow(beginInfoLine); 
		cell = row.createCell(columnToTotal, HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(new HSSFRichTextString("Total HT"));
		cellStyle = wb.createCellStyle();
		hSSFFont = wb.createFont();
		hSSFFont.setFontHeightInPoints(font_size);
		hSSFFont.setColor(HSSFColor.WHITE.index);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setFillForegroundColor(IndexedColors.BLACK.getIndex());
		cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cellStyle.setFont(hSSFFont);
        cell.setCellStyle(cellStyle);
        
		cell = row.createCell(tot2, HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(new HSSFRichTextString(Double.toString(f.getDevis().getTotal().getTotal_ht())));
		cellStyle = wb.createCellStyle();
		hSSFFont = wb.createFont();
		hSSFFont.setFontHeightInPoints(font_size);
		hSSFFont.setColor(HSSFColor.GREEN.index);
		hSSFFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle.setFont(hSSFFont);
        cell.setCellStyle(cellStyle);
        
        beginInfoLine++;
        sheet.addMergedRegion(new Region(beginInfoLine,columnToTotal,beginInfoLine,(short)19));
        sheet.addMergedRegion(new Region(beginInfoLine,tot2,beginInfoLine,(short)tot3));
        
		row = sheet.createRow(beginInfoLine); 
		cell = row.createCell(columnToTotal, HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(new HSSFRichTextString("Montant TVA"));
		cellStyle = wb.createCellStyle();
		hSSFFont = wb.createFont();
		hSSFFont.setFontHeightInPoints(font_size);
		hSSFFont.setColor(HSSFColor.WHITE.index);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setFillForegroundColor(IndexedColors.BLACK.getIndex());
		cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cellStyle.setFont(hSSFFont);
        cell.setCellStyle(cellStyle);
        
		cell = row.createCell(tot2, HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(new HSSFRichTextString(Double.toString(f.getDevis().getTotal().getTva())));
		cellStyle = wb.createCellStyle();
		hSSFFont = wb.createFont();
		hSSFFont = wb.createFont();
		hSSFFont.setFontHeightInPoints(font_size);
		hSSFFont.setColor(HSSFColor.GREEN.index);
		hSSFFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle.setFont(hSSFFont);
        cell.setCellStyle(cellStyle);
        
        beginInfoLine++;
        sheet.addMergedRegion(new Region(beginInfoLine,columnToTotal,beginInfoLine,(short)19));
        sheet.addMergedRegion(new Region(beginInfoLine,tot2,beginInfoLine,(short)tot3));
        
		row = sheet.createRow(beginInfoLine); 
		cell = row.createCell(columnToTotal, HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(new HSSFRichTextString("Total TTC"));
		cellStyle = wb.createCellStyle();
		hSSFFont = wb.createFont();
		hSSFFont.setFontHeightInPoints(font_size);
		hSSFFont.setColor(HSSFColor.WHITE.index);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setFillForegroundColor(IndexedColors.BLACK.getIndex());
		cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cellStyle.setFont(hSSFFont);
        cell.setCellStyle(cellStyle);
        
		cell = row.createCell(tot2, HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(new HSSFRichTextString(Double.toString(f.getDevis().getTotal().getTotal_ttc())));
		cellStyle = wb.createCellStyle();
		hSSFFont = wb.createFont();
		hSSFFont.setFontHeightInPoints(font_size);
		hSSFFont.setColor(HSSFColor.GREEN.index);
		hSSFFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle.setFont(hSSFFont);
        cell.setCellStyle(cellStyle);
        
        beginInfoLine++;
        sheet.addMergedRegion(new Region(beginInfoLine,columnToTotal,beginInfoLine,(short)19));
        sheet.addMergedRegion(new Region(beginInfoLine,tot2,beginInfoLine,(short)tot3));
        
		row = sheet.createRow(beginInfoLine); 
		cell = row.createCell(columnToTotal, HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(new HSSFRichTextString("A payer"));
		cellStyle = wb.createCellStyle();
		hSSFFont = wb.createFont();
		hSSFFont.setFontHeightInPoints(font_size);
		hSSFFont.setColor(HSSFColor.WHITE.index);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setFillForegroundColor(IndexedColors.BLACK.getIndex());
		cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cellStyle.setFont(hSSFFont);
        cell.setCellStyle(cellStyle);
        
		cell = row.createCell(tot2, HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(new HSSFRichTextString(Double.toString(f.getDevis().getTotal().getTotal_ttc())));
		cellStyle = wb.createCellStyle();
		hSSFFont = wb.createFont();
		hSSFFont.setFontHeightInPoints(font_size);
		hSSFFont.setColor(HSSFColor.GREEN.index);
		hSSFFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle.setFont(hSSFFont);
        cell.setCellStyle(cellStyle);
        
        createAvoirExcel(metier, request, wb, sheet, row, cell, cellStyle, f);
		
	}
	
	
	
	public static void createAvoirExcel(IGlobalMetier metier, HttpServletRequest request,HSSFWorkbook wb,HSSFSheet sheet,HSSFRow row,HSSFCell cell,HSSFCellStyle cellStyle,Facture f) throws IOException{
		for (Avoir avoir : f.getAvoir()) {
			short font_size = 14;
			/* VARIABLES AND OBJECT */
			Entreprise e = metier.getEntreprise();
			request.setCharacterEncoding("UTF-8");
			/* FUNCTION EXCEL */
			
			sheet = wb.createSheet(avoir.getReference());
			
			row = null;
			cell = null;
			cellStyle = null;
			
			/*********** PARTIE ENTREPRISE *****************/
			
			row = sheet.createRow(0); // Première ligne
			cell = row.createCell(0, HSSFCell.CELL_TYPE_STRING); // Premiere colone
			cell.setCellValue(new HSSFRichTextString(e.getName()));
			cellStyle = wb.createCellStyle();
			HSSFFont hSSFFont = wb.createFont();
			hSSFFont.setFontHeightInPoints(font_size);
	        cellStyle.setFont(hSSFFont);
	        cell.setCellStyle(cellStyle);
			
			row = sheet.createRow(1); 
			cell = row.createCell(0, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(new HSSFRichTextString(e.getForme_juridique()+" au capital de "+e.getCapital()+" "+e.getMoney_type()+""));
			cellStyle = wb.createCellStyle();
			hSSFFont.setFontHeightInPoints(font_size);
	        cellStyle.setFont(hSSFFont);
	        cell.setCellStyle(cellStyle);
	        
			row = sheet.createRow(2); 
			cell = row.createCell(0, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(new HSSFRichTextString(e.getAddress()));
			cellStyle = wb.createCellStyle();
			hSSFFont.setFontHeightInPoints(font_size);
	        cellStyle.setFont(hSSFFont);
	        cell.setCellStyle(cellStyle);
	        
			row = sheet.createRow(3); 
			cell = row.createCell(0, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(new HSSFRichTextString(e.getCode_postal()+" "+e.getCity()));
			cellStyle = wb.createCellStyle();
			hSSFFont.setFontHeightInPoints(font_size);
	        cellStyle.setFont(hSSFFont);
	        cell.setCellStyle(cellStyle);
	        
			row = sheet.createRow(4); 
			cell = row.createCell(0, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(new HSSFRichTextString("N° siren : "+e.getSiren_number()));
			cellStyle = wb.createCellStyle();
			hSSFFont.setFontHeightInPoints(font_size);
	        cellStyle.setFont(hSSFFont);
	        cell.setCellStyle(cellStyle);
	        
			row = sheet.createRow(5); 
			cell = row.createCell(0, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(new HSSFRichTextString("N° tva : "+e.getTv_number()));
			cellStyle = wb.createCellStyle();
			hSSFFont.setColor(HSSFColor.DARK_TEAL.index); // GENERAL POUR ENTREPRISE
			hSSFFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			hSSFFont.setFontHeightInPoints(font_size);
	        cellStyle.setFont(hSSFFont);
	        cell.setCellStyle(cellStyle);
	        
	        /*********** PARTIE FACTURE INFO *****************/
	        
			row = sheet.createRow(7); 
			cell = row.createCell(0, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(new HSSFRichTextString("Avoir n° : "+avoir.getReference()));
			cellStyle = wb.createCellStyle();
			hSSFFont = wb.createFont();
			hSSFFont.setFontHeightInPoints(font_size);
	        cellStyle.setFont(hSSFFont);
	        cell.setCellStyle(cellStyle);
	        
	        
	        /*********** PARTIE INFO CUSTOMER *****************/
	        
	        sheet.addMergedRegion(new Region(7,(short)9,7,(short)16)); // Fusionne les cellules pour le client
	        sheet.addMergedRegion(new Region(9,(short)9,9,(short)16)); // Fusionne les cellules pour le client
	        sheet.addMergedRegion(new Region(11,(short)9,11,(short)16)); // Fusionne les cellules pour le client
	        
			cell = row.createCell(9, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(new HSSFRichTextString(f.getCustomer().getName()));
			cellStyle = wb.createCellStyle();
			hSSFFont = wb.createFont();
			hSSFFont.setFontHeightInPoints((short)16);
			hSSFFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        cellStyle.setFont(hSSFFont);
	        cell.setCellStyle(cellStyle);
	        
			row = sheet.createRow(8); 
			cell = row.createCell(0, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(new HSSFRichTextString("Date : "+avoir.getDate()));
			cellStyle = wb.createCellStyle();
			hSSFFont = wb.createFont();
			hSSFFont.setFontHeightInPoints(font_size);
	        cellStyle.setFont(hSSFFont);
	        cell.setCellStyle(cellStyle);
	        
	        
			row = sheet.createRow(9); 
			cell = row.createCell(0, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(new HSSFRichTextString("Réf Facture : "+avoir.getFacture().getReference()));
			cellStyle = wb.createCellStyle();
			hSSFFont = wb.createFont();
			hSSFFont.setFontHeightInPoints(font_size);
			hSSFFont.setColor(HSSFColor.BLACK.index);
	        cellStyle.setFont(hSSFFont);
	        cell.setCellStyle(cellStyle);
	        
			cell = row.createCell(9, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(new HSSFRichTextString(f.getCustomer().getAddress()));
			cellStyle = wb.createCellStyle();
			hSSFFont = wb.createFont();
			hSSFFont.setFontHeightInPoints(font_size);
			hSSFFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        cellStyle.setFont(hSSFFont);
	        cell.setCellStyle(cellStyle);
	        
			row = sheet.createRow(10); 
			cell = row.createCell(0, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(new HSSFRichTextString("Réf client : "+avoir.getCustomer().getReference()));
			cellStyle = wb.createCellStyle();
			hSSFFont = wb.createFont();
			hSSFFont.setFontHeightInPoints(font_size);
	        cellStyle.setFont(hSSFFont);
	        cell.setCellStyle(cellStyle);
	        	        	        
			row = sheet.createRow(11); 
			cell = row.createCell(0, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(new HSSFRichTextString(" "));
			cellStyle = wb.createCellStyle();
			hSSFFont = wb.createFont();
			hSSFFont.setFontHeightInPoints(font_size);
			hSSFFont.setColor(HSSFColor.BLACK.index);
	        cellStyle.setFont(hSSFFont);
	        cell.setCellStyle(cellStyle);
	        
			cell = row.createCell(9, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(new HSSFRichTextString(f.getCustomer().getCode_postal()+" "+f.getCustomer().getCity()+" "+f.getCustomer().getCountry()));
			cellStyle = wb.createCellStyle();
			hSSFFont = wb.createFont();
			hSSFFont.setFontHeightInPoints(font_size);
			hSSFFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        cellStyle.setFont(hSSFFont);
	        cell.setCellStyle(cellStyle);
	   	        
	        /*********** PARTIE INFO LINE FACTURE *****************/
	        
	        Integer beginInfoLine = new Integer(17);
	        short columnToTotal = 18;
	        
	        sheet.addMergedRegion(new Region(beginInfoLine,(short)0,beginInfoLine,(short)1));
	        sheet.addMergedRegion(new Region(beginInfoLine,(short)2,beginInfoLine,(short)3));
	        sheet.addMergedRegion(new Region(beginInfoLine,(short)4,beginInfoLine,(short)13));
	        sheet.addMergedRegion(new Region(beginInfoLine,(short)14,beginInfoLine,(short)15));
	        sheet.addMergedRegion(new Region(beginInfoLine,(short)16,beginInfoLine,(short)17));
	        sheet.addMergedRegion(new Region(beginInfoLine,columnToTotal,beginInfoLine,(short)19));
	        sheet.addMergedRegion(new Region(beginInfoLine,(short)20,beginInfoLine,(short)21));
	        	        
			row = sheet.createRow(beginInfoLine); 
			cell = row.createCell(0, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(new HSSFRichTextString("Type"));
			cellStyle = wb.createCellStyle();
			hSSFFont = wb.createFont();
			hSSFFont.setFontHeightInPoints(font_size);
			hSSFFont.setColor(HSSFColor.WHITE.index);
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellStyle.setFillForegroundColor(IndexedColors.BLACK.getIndex());
			cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
	        cellStyle.setFont(hSSFFont);
	        cell.setCellStyle(cellStyle);
	        
			cell = row.createCell(2, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(new HSSFRichTextString("Référence"));
			cellStyle = wb.createCellStyle();
			hSSFFont.setFontHeightInPoints(font_size);
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellStyle.setFillForegroundColor(IndexedColors.BLACK.getIndex());
			cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
	        cellStyle.setFont(hSSFFont);
	        cell.setCellStyle(cellStyle);
	        
			cell = row.createCell(4, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(new HSSFRichTextString("Désignation"));
			cellStyle = wb.createCellStyle();
			hSSFFont.setFontHeightInPoints(font_size);
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellStyle.setFillForegroundColor(IndexedColors.BLACK.getIndex());
			cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
	        cellStyle.setFont(hSSFFont);
	        cell.setCellStyle(cellStyle);
	        
			cell = row.createCell(14, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(new HSSFRichTextString("Quantité"));
			cellStyle = wb.createCellStyle();
			hSSFFont.setFontHeightInPoints(font_size);
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellStyle.setFillForegroundColor(IndexedColors.BLACK.getIndex());
			cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
	        cellStyle.setFont(hSSFFont);
	        cell.setCellStyle(cellStyle);
	        
			cell = row.createCell(16, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(new HSSFRichTextString("Tva (%)"));
			cellStyle = wb.createCellStyle();
			hSSFFont.setFontHeightInPoints(font_size);
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellStyle.setFillForegroundColor(IndexedColors.BLACK.getIndex());
			cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
	        cellStyle.setFont(hSSFFont);
	        cell.setCellStyle(cellStyle);
	        
			cell = row.createCell(18, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(new HSSFRichTextString("Prix HT"));
			cellStyle = wb.createCellStyle();
			hSSFFont.setFontHeightInPoints(font_size);
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellStyle.setFillForegroundColor(IndexedColors.BLACK.getIndex());
			cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
	        cellStyle.setFont(hSSFFont);
	        cell.setCellStyle(cellStyle);
	        
			cell = row.createCell(20, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(new HSSFRichTextString("Total HT"));
			cellStyle = wb.createCellStyle();
			hSSFFont.setFontHeightInPoints(font_size);
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellStyle.setFillForegroundColor(IndexedColors.BLACK.getIndex());
			cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
	        cellStyle.setFont(hSSFFont);
	        cell.setCellStyle(cellStyle);
	        
	        /***** CREATION DE TOUTES LES LIGNES *////
	        
	        for (Line l : avoir.getLine()) {
	        	beginInfoLine++;
		        sheet.addMergedRegion(new Region(beginInfoLine,(short)0,beginInfoLine,(short)1));
		        sheet.addMergedRegion(new Region(beginInfoLine,(short)2,beginInfoLine,(short)3));
		        sheet.addMergedRegion(new Region(beginInfoLine,(short)4,beginInfoLine,(short)13));
		        sheet.addMergedRegion(new Region(beginInfoLine,(short)14,beginInfoLine,(short)15));
		        sheet.addMergedRegion(new Region(beginInfoLine,(short)16,beginInfoLine,(short)17));
		        sheet.addMergedRegion(new Region(beginInfoLine,columnToTotal,beginInfoLine,(short)19));
		        sheet.addMergedRegion(new Region(beginInfoLine,(short)20,beginInfoLine,(short)21));
		        
				row = sheet.createRow(beginInfoLine); 
				cell = row.createCell(0, HSSFCell.CELL_TYPE_STRING);
				cell.setCellValue(new HSSFRichTextString(l.getType()));
				cellStyle = wb.createCellStyle();
				hSSFFont = wb.createFont();
				hSSFFont.setFontHeightInPoints(font_size);
				hSSFFont.setColor(HSSFColor.BLACK.index);
				cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		        cellStyle.setFont(hSSFFont);
		        cell.setCellStyle(cellStyle);
		        
				cell = row.createCell(2, HSSFCell.CELL_TYPE_STRING);
				cell.setCellValue(new HSSFRichTextString(l.getReference()));
				cellStyle = wb.createCellStyle();
				hSSFFont.setFontHeightInPoints(font_size);
				cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		        cellStyle.setFont(hSSFFont);
		        cell.setCellStyle(cellStyle);
		        
				cell = row.createCell(4, HSSFCell.CELL_TYPE_STRING);
				cell.setCellValue(new HSSFRichTextString(l.getDescription()));
				cellStyle = wb.createCellStyle();
				hSSFFont.setFontHeightInPoints(font_size);
				cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		        cellStyle.setFont(hSSFFont);
		        cell.setCellStyle(cellStyle);
		        
				cell = row.createCell(14, HSSFCell.CELL_TYPE_NUMERIC);
				cell.setCellValue(l.getQuantity());
				cellStyle = wb.createCellStyle();
				hSSFFont.setFontHeightInPoints(font_size);
				cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		        cellStyle.setFont(hSSFFont);
		        cell.setCellStyle(cellStyle);
		        
				cell = row.createCell(16, HSSFCell.CELL_TYPE_STRING);
				cell.setCellValue(new HSSFRichTextString(Double.toString(l.getTva())));
				cellStyle = wb.createCellStyle();
				hSSFFont.setFontHeightInPoints(font_size);
				cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		        cellStyle.setFont(hSSFFont);
		        cell.setCellStyle(cellStyle);
		        
				cell = row.createCell(18, HSSFCell.CELL_TYPE_STRING);
				cell.setCellValue(new HSSFRichTextString(Double.toString(l.getPrix_ht())));
				cellStyle = wb.createCellStyle();
				hSSFFont.setFontHeightInPoints(font_size);
				cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		        cellStyle.setFont(hSSFFont);
		        cell.setCellStyle(cellStyle);
		        
				cell = row.createCell(20, HSSFCell.CELL_TYPE_STRING);
				cell.setCellValue(new HSSFRichTextString(Double.toString(l.getTotal_ht())));
				cellStyle = wb.createCellStyle();
				hSSFFont.setFontHeightInPoints(font_size);
				cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		        cellStyle.setFont(hSSFFont);
		        cell.setCellStyle(cellStyle);		        
			}
	        beginInfoLine++;
	        short tot2 = (short) (columnToTotal + 2);
	        short tot3 = (short) (columnToTotal + 3);
	        sheet.addMergedRegion(new Region(beginInfoLine,columnToTotal,beginInfoLine,(short)19));
	        sheet.addMergedRegion(new Region(beginInfoLine,tot2,beginInfoLine,(short)tot3));
	        
			row = sheet.createRow(beginInfoLine); 
			cell = row.createCell(columnToTotal, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(new HSSFRichTextString("Total HT"));
			cellStyle = wb.createCellStyle();
			hSSFFont = wb.createFont();
			hSSFFont.setFontHeightInPoints(font_size);
			hSSFFont.setColor(HSSFColor.WHITE.index);
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellStyle.setFillForegroundColor(IndexedColors.BLACK.getIndex());
			cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
	        cellStyle.setFont(hSSFFont);
	        cell.setCellStyle(cellStyle);
	        
			cell = row.createCell(tot2, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(new HSSFRichTextString(Double.toString(avoir.getTotal().getTotal_ht())));
			cellStyle = wb.createCellStyle();
			hSSFFont = wb.createFont();
			hSSFFont.setFontHeightInPoints(font_size);
			hSSFFont.setColor(HSSFColor.GREEN.index);
			hSSFFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        cellStyle.setFont(hSSFFont);
	        cell.setCellStyle(cellStyle);
	        
	        beginInfoLine++;
	        sheet.addMergedRegion(new Region(beginInfoLine,columnToTotal,beginInfoLine,(short)19));
	        sheet.addMergedRegion(new Region(beginInfoLine,tot2,beginInfoLine,(short)tot3));
	        
			row = sheet.createRow(beginInfoLine); 
			cell = row.createCell(columnToTotal, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(new HSSFRichTextString("Montant TVA"));
			cellStyle = wb.createCellStyle();
			hSSFFont = wb.createFont();
			hSSFFont.setFontHeightInPoints(font_size);
			hSSFFont.setColor(HSSFColor.WHITE.index);
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellStyle.setFillForegroundColor(IndexedColors.BLACK.getIndex());
			cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
	        cellStyle.setFont(hSSFFont);
	        cell.setCellStyle(cellStyle);
	        
			cell = row.createCell(tot2, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(new HSSFRichTextString(Double.toString(avoir.getTotal().getTva())));
			cellStyle = wb.createCellStyle();
			hSSFFont = wb.createFont();
			hSSFFont = wb.createFont();
			hSSFFont.setFontHeightInPoints(font_size);
			hSSFFont.setColor(HSSFColor.GREEN.index);
			hSSFFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        cellStyle.setFont(hSSFFont);
	        cell.setCellStyle(cellStyle);
	        
	        beginInfoLine++;
	        sheet.addMergedRegion(new Region(beginInfoLine,columnToTotal,beginInfoLine,(short)19));
	        sheet.addMergedRegion(new Region(beginInfoLine,tot2,beginInfoLine,(short)tot3));
	        
			row = sheet.createRow(beginInfoLine); 
			cell = row.createCell(columnToTotal, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(new HSSFRichTextString("Total TTC"));
			cellStyle = wb.createCellStyle();
			hSSFFont = wb.createFont();
			hSSFFont.setFontHeightInPoints(font_size);
			hSSFFont.setColor(HSSFColor.WHITE.index);
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellStyle.setFillForegroundColor(IndexedColors.BLACK.getIndex());
			cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
	        cellStyle.setFont(hSSFFont);
	        cell.setCellStyle(cellStyle);
	        
			cell = row.createCell(tot2, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(new HSSFRichTextString(Double.toString(avoir.getTotal().getTotal_ttc())));
			cellStyle = wb.createCellStyle();
			hSSFFont = wb.createFont();
			hSSFFont.setFontHeightInPoints(font_size);
			hSSFFont.setColor(HSSFColor.GREEN.index);
			hSSFFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        cellStyle.setFont(hSSFFont);
	        cell.setCellStyle(cellStyle);
	        
	        beginInfoLine++;
	        sheet.addMergedRegion(new Region(beginInfoLine,columnToTotal,beginInfoLine,(short)19));
	        sheet.addMergedRegion(new Region(beginInfoLine,tot2,beginInfoLine,(short)tot3));
	        
			row = sheet.createRow(beginInfoLine); 
			cell = row.createCell(columnToTotal, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(new HSSFRichTextString("A déduire"));
			cellStyle = wb.createCellStyle();
			hSSFFont = wb.createFont();
			hSSFFont.setFontHeightInPoints(font_size);
			hSSFFont.setColor(HSSFColor.WHITE.index);
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellStyle.setFillForegroundColor(IndexedColors.BLACK.getIndex());
			cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
	        cellStyle.setFont(hSSFFont);
	        cell.setCellStyle(cellStyle);
	        
			cell = row.createCell(tot2, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(new HSSFRichTextString(Double.toString(avoir.getTotal().getTotal_ttc())));
			cellStyle = wb.createCellStyle();
			hSSFFont = wb.createFont();
			hSSFFont.setFontHeightInPoints(font_size);
			hSSFFont.setColor(HSSFColor.GREEN.index);
			hSSFFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        cellStyle.setFont(hSSFFont);
	        cell.setCellStyle(cellStyle);			
		}
		FileOutputStream fileOut;
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		String facture_excel = requestAttributes.getRequest().getRealPath("/resources/comptabilite/facture/excel/"+f.getReference()+".xls"); 
		fileOut = new FileOutputStream(facture_excel); 
		wb.write(fileOut); //Creation du fichier excel
		fileOut.close();
	}
}
