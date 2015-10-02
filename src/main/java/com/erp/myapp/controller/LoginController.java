package com.erp.myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.erp.myapp.metier.IGlobalMetier;

@Controller
public class LoginController {
	@Autowired
	IGlobalMetier metier;
	
	@RequestMapping("/")
	public String context(Model model){
		return "redirect:/login";
	}
	
	@RequestMapping("/login")
	public String home(Model model){
		return "Authentification/login";
	}
}
