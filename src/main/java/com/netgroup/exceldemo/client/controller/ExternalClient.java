package com.netgroup.exceldemo.client.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.netgroup.exceldemo.client.service.ServiceTemplate;
import com.netgroup.exceldemo.data.dao.Excel;
import com.netgroup.exceldemo.repository.ExcelRepository;
import com.netgroup.exceldemo.service.ExcelService;
import com.netgroup.exceldemo.util.ConverterExcel;

@RestController
@RequestMapping(value = "/api/schedule")
public class ExternalClient {

	@Autowired
	ConverterExcel converterExcel;

	@Autowired
	RestTemplate restTemplate;
		
	@Autowired
	ExcelService excelService;
	

	@Autowired
	ServiceTemplate clientService;
	
	public static String urlM = "https://8df0-151-73-239-129.ngrok.io/api/home/dto";

	public static String urlF = "http://localhost:8080/excel/free";

	@Scheduled(cron = "0 0 * * * *")
	@GetMapping(value = "/entity/dto")
	public List<Excel> getToDataDTO(){
		try {
			ResponseEntity<Excel[]> response = restTemplate.getForEntity(urlM, Excel[].class);
			Excel[] excel = response.getBody();
			return excelService.arrayToList(excel);
		}catch(Exception e) {
			System.out.println("##################################################");
			System.out.println("             Servizio esterno off                 ");
			System.out.println("##################################################");
			return null;
		}

	}
	@Scheduled(cron = "0 0 * * * *")
	@GetMapping(value = "/entity")
	public List<Excel> getToData() {
		try {
			ResponseEntity<Excel[]> response = restTemplate.getForEntity(urlF, Excel[].class);
			Excel[] excel = response.getBody();
			return excelService.arrayToList(excel);
		}catch(Exception e) {
			System.out.println("##################################################");
			System.out.println("             Servizio esterno off                 ");
			System.out.println("##################################################");
			return null;
		}

	}
	@Scheduled(cron = "0 0 * * * *")
	@RequestMapping(value = "/getResponse", method = RequestMethod.GET)
	public List<Excel> getToDataJWT(){
		try {
			return Arrays.asList(clientService.getResponse());
		}catch(Exception e) {
			System.out.println("##################################################");
			System.out.println("             Servizio esterno off                 ");
			System.out.println("##################################################");
			return null;
		}
	}

	
	@GetMapping(value="/all")
	public List<Excel> findAll(){
		return excelService.listFile();
	}

}