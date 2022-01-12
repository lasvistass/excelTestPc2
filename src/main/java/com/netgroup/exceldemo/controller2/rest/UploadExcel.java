package com.netgroup.exceldemo.controller2.rest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.netgroup.exceldemo.util.ConverterExcel;


@RestController
@RequestMapping("/upload")
public class UploadExcel {
	
	
	@Autowired
	ConverterExcel converterExcel;
	
	@GetMapping(value="/excel")
	public String saveExcel() throws InvalidFormatException, EncryptedDocumentException, IOException {
		converterExcel.Excel2Data("C:\\Users\\simon\\OneDrive\\Desktop\\esempio\\esempio.xlsx");
		return "Salvataggio andato a buon fine";
	}
	
//	@Scheduled(fixedRate = 5000)
//	@GetMapping(value="/prova")
//	public void prova() {
//		System.out.println("ciao ciao");
//	}

}
