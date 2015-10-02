package com.erp.myapp.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.erp.myapp.entity.Entreprise;
import com.erp.myapp.entity.Facture;
import com.erp.myapp.metier.IGlobalMetier;
import com.erp.myapp.security.Path;
import com.erp.myapp.security.ResourceNotFoundException;

@Controller
public class EntrepriseController {
	@Autowired
	IGlobalMetier metier;
	
	@RequestMapping(value=""+Path.admin+"/config/entreprise", method = RequestMethod.GET)
	public String Form(Model model,HttpServletRequest request){ // config pour création de l'entreprise
		Entreprise e = new Entreprise();
		try {
			e = metier.getEntreprise();			
		} catch (Exception ex) {}
		model.addAttribute("entreprise", e);
		return "Config/config_entreprise";
	}
	
	@RequestMapping(value=""+Path.admin+"/config/entreprise_modify", method = RequestMethod.GET)
	public String Form_modify(Model model,HttpServletRequest request){ // config pour création de l'entreprise
		try {
			Entreprise e = metier.getEntreprise();
			model.addAttribute("entreprise", e);
			return "Config/config_entreprise_modify";
		} catch (Exception ex) {
			throw new ResourceNotFoundException();
		}
	}
	
	@RequestMapping(value=""+Path.admin+"/config/entrepriseEdit", method = RequestMethod.POST)
	public String FormEdit(@ModelAttribute("entreprise")@Valid Entreprise e, BindingResult bindingResult,Model model,HttpServletRequest request){ // config pour création de l'entreprise
		boolean Entreprisenull = true;
		Entreprise entreprise = new Entreprise();
		try {
			entreprise = metier.getEntreprise();
		} catch (Exception e2) {
			Entreprisenull = false;
		}
		try {
			if(!Entreprisenull || request.getParameter("modify") != null )
			{
				if(!bindingResult.hasErrors())
				{
					if(request.getParameter("modify") != null){ // Si il t'a le parametre modify alors on modifie
						Long id = entreprise.getId();
						String logo = entreprise.getLogo();
						entreprise = e;
						entreprise.setId(id);
						entreprise.setLogo(logo);
						metier.updateEntreprise(entreprise);
					}else{
						metier.addEntreprise(e);						
					}
					return "redirect:"+Path.admin+"/config/entreprise";
				}
				model.addAttribute("entreprise", e);
				if(request.getParameter("modify") != null){
					return "Config/config_entreprise_modify";					
				}else{
					return "Config/config_entreprise";
				}
			}
			else
			{
				throw new ResourceNotFoundException(); // Génère une exception 404
			}
		} catch (Exception ex) {
			throw new ResourceNotFoundException(); // Génère une exception 404
		}
	}
	
    @RequestMapping(value=""+Path.admin+"/config/entrepriseLogoEdit", method = RequestMethod.POST)
    String uploadFileHandler(@RequestParam("file") MultipartFile file, HttpServletRequest request, Model model) {
    	String error = "";
    	Random rand = new Random();
    	int nombreAleatoire = rand.nextInt(50000000 - 0 + 1) + 0;
    	String name = Integer.toString(nombreAleatoire)+".png";
    	Entreprise e = new Entreprise();
    	try {
    		e = metier.getEntreprise();		
		} catch (Exception e2) {}
        if (!file.isEmpty() && file.getSize() <= 5242880) { // l'image ne doit pas faire plus de 5 mb
            try {
            	e = metier.getEntreprise();
                byte[] bytes = file.getBytes();
        		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) 
        		RequestContextHolder.currentRequestAttributes();
                // Creating the directory to store file
                File dir = new File(requestAttributes.getRequest().getRealPath("/resources/pictures/picture_entreprise"));
                if (!dir.exists())
                    dir.mkdirs();
 
                // Create the file on server
                
                File serverFile = new File(dir.getAbsolutePath()+ File.separator + name);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
                
                
                try {
                    File fileDel = new File(requestAttributes.getRequest().getRealPath("/resources/pictures/picture_entreprise/"+e.getLogo()));
                    fileDel.delete();					
				} catch (Exception e2) {}
				e.setLogo(name);
				metier.updateEntreprise(e);
				              
                return "redirect:"+Path.admin+"/config/entreprise";

            } catch (Exception ex) {
            	error = "Erreur d'ajout l'image : " + name + " => " + ex.getMessage()+"";
            	throw new ResourceNotFoundException(); 
            }
        }
        else if(file.getSize() > 5242880)
        {
        	error = "Image trop lourde";
        }
        else {
        	error = "Image vide";
        }
        model.addAttribute("entreprise", e);
        model.addAttribute("errorLogo", error);
        if(request.getParameter("modify") == null)
        {
            return "Config/config_entreprise";       	
        }else{
        	return "Config/config_entreprise_modify";
        }

    }
}
