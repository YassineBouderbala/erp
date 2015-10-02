package com.erp.myapp.security;

public class Error_Message {
	public static String footer_pdf(Integer argcharacter, Integer argMax){
		return "Nombre de caractère dépassé : "+argcharacter+"/"+argMax+"";
	}
}
