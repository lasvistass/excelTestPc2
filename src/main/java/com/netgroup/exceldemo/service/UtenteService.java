package com.netgroup.exceldemo.service;

import java.util.List;
import java.util.Optional;

import com.netgroup.exceldemo.data.Utente;

public interface UtenteService {
	
	public void salva(Utente utent);
	public Optional<Utente> cercaUtente(String username);
	public List<Utente> lista();
	
}
