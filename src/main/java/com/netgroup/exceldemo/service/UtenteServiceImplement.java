package com.netgroup.exceldemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netgroup.exceldemo.data.dao.Utente;
import com.netgroup.exceldemo.repository.UtenteRepository;

@Service
public class UtenteServiceImplement implements UtenteService {

	@Autowired
	UtenteRepository uten;

	@Override
	public void salva(Utente utent) {
		uten.save(utent);

	}

	@Override
	public Optional<Utente> cercaUtente(String username) {
		return uten.findByUsername(username);
	}

	@Override
	public List<Utente> lista() {
		// TODO Auto-generated method stub
		return uten.findAll();
	}

}
