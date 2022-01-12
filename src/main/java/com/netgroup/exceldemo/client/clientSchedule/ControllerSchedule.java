package com.netgroup.exceldemo.client.clientSchedule;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netgroup.exceldemo.data.dao.Excel;
import com.netgroup.exceldemo.repository.ExcelRepository;
import com.netgroup.exceldemo.service.ExcelService;
import com.netgroup.exceldemo.util.ConverterExcel;

@RestController
@RequestMapping(value = "/api/schedule")
public class ControllerSchedule {

	@Autowired
	ConverterExcel converterExcel;

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	ExcelRepository excelRepository;
	
	@Autowired
	ExcelService excelService;
	
	public static String urlM = "https://8df0-151-73-239-129.ngrok.io/api/home/dto";

	public static String urlF = "http://82ca-94-34-9-215.ngrok.io/api/home/list";
	
	@Scheduled(cron = "0 0 * * * *")
	@GetMapping(value = "/entity/dto")
	public List<Excel> getToDataDTO(){
		ResponseEntity<Excel[]> response = restTemplate.getForEntity(urlM, Excel[].class);
		Excel[] excel = response.getBody();
		return excelService.arrayToList(excel);
	}

	@Scheduled(cron = "0 0 * * * *")
	@GetMapping(value = "/entity")
	public List<Excel> getToData() {
		ResponseEntity<Excel[]> response = restTemplate.getForEntity(urlF, Excel[].class);
		Excel[] excel = response.getBody();
		return excelService.arrayToList(excel);
	}

	
	@GetMapping(value="/all")
	public List<Excel> findAll(){
		return excelRepository.findAll();
	}

}
