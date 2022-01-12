package com.netgroup.exceldemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.netgroup.exceldemo.data.dao.Utente;
import com.netgroup.exceldemo.service.UtenteService;

@Controller
@RequestMapping(value = "/login")
public class utenteController {

//	@Autowired
//	EncryptionUtils encryptionPass;
	
	@Autowired
	UtenteService utenteService;
	
	private static final Logger log = LoggerFactory.getLogger(utenteController.class);
	
	
	@GetMapping(value = "/register")
	public ModelAndView register() {
		return new ModelAndView("/login/register").addObject("oggettoUtente", new Utente());
	}
	
	@PostMapping(value = "/salvaUtente")
	public ModelAndView saveArea(@ModelAttribute("oggettoUtente") Utente utente) {
		log.info(utente.getPassword());
		utenteService.salva(utente);
		return new ModelAndView("/login/login");
	}
	
	
	@PostMapping(value= "/verify")
	public void verifyUsers(@ModelAttribute("oggettoUtente") Utente utente) {
		log.info("sono dentro");
	}
	
}
