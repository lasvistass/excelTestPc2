package com.netgroup.exceldemo.data.converter;

import java.util.ArrayList;
import java.util.List;

import com.netgroup.exceldemo.data.dao.Utente;
import com.netgroup.exceldemo.data.dto.UtenteDto;

public class UtenteConverter {

	
	public static UtenteDto getUtenteDto(Utente utenteDao){
		
		UtenteDto utenteDto= new UtenteDto();
		
		utenteDto.setUsername(utenteDao.getUsername());
		utenteDto.setFirstName(utenteDao.getFirstName());
		utenteDto.setLastName(utenteDao.getLastName());
		utenteDto.setEmail(utenteDao.getEmail());
		utenteDto.setPassword(utenteDao.getPassword());
		return utenteDto;
		
	}
	
	public static List<UtenteDto> listUtentiDto(List<Utente> utentiDao){
		List<UtenteDto> listDto= new ArrayList<UtenteDto>();
		for (Utente utente : utentiDao) {
			listDto.add(getUtenteDto(utente));
		}
		return listDto;
	}
	
	
	
	
	
}
