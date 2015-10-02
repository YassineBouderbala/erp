package com.erp.myapp.security;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Datetime {
	
	public static String getCurrentDate(){
		SimpleDateFormat formater = null;
		Date aujourdhui = new Date();
		formater = new SimpleDateFormat("dd-MM-yy");
		return formater.format(aujourdhui);
	}
}
