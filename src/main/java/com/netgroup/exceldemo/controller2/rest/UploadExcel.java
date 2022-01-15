package com.netgroup.exceldemo.controller2.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.netgroup.exceldemo.data.dao.Excel;
import com.netgroup.exceldemo.repository.ExcelRepository;
import com.netgroup.exceldemo.util.ConverterExcel;


@RestController
@RequestMapping("/upload")
public class UploadExcel {
	
	
	@Autowired
	ConverterExcel converterExcel;
	
	@Autowired
	ExcelRepository excelRepository;
	
	@GetMapping(value="/excel")
	public String saveExcel() throws InvalidFormatException, EncryptedDocumentException, IOException {
		converterExcel.Excel2Data(new FileInputStream(new File("C:\\Users\\simon\\OneDrive\\Desktop\\esempio\\esempio.xlsx")));
		return "Salvataggio andato a buon fine";
	}
	
	@GetMapping("/all")
	public List<Excel> getAll(){
		return excelRepository.findAll();
	}
	
//	@Scheduled(fixedRate = 1000)
//	@GetMapping(value="/prova")
//	public void prova() {
//		System.out.println("ciao ciao");
//	}

}
