package com.example.demo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.TakmicenjeService;

import model.Takmicenje;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired
	TakmicenjeService ts;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

    
    @GetMapping("/")
    public String home(Model model) {
    	List<Takmicenje> odrzana = ts.getOdrzanaTakmicenja();
    	List<Takmicenje> najavljena = ts.getNajavljenaTakmicenja();
    	model.addAttribute("odrzanaTakmicenja", odrzana);
    	model.addAttribute("najavljenaTakmicenja", najavljena);
    	return "index";
    }
}
