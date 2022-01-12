package com.netgroup.exceldemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.netgroup.exceldemo.service.ExcelService;

@Controller
@RequestMapping
public class downloadExcelController {

	@Autowired
	ExcelService excelService;
	
	private static final Logger log = LoggerFactory.getLogger(downloadExcelController.class);

//	@GetMapping(value = "/export")
//	public void exportToExcel(HttpServletResponse resp) throws IOException {
//		resp.setContentType("application/octet-stream");
//		String headerKey = "Content-Disposition";
//		String headervalue = "attachment; filename=prodotto.xlsx";
//		
//		resp.setHeader(headerKey, headervalue);
//		List<Excel> listExcel = excelService.listFile();s
//		excelUtils exp = new excelUtils(listExcel);
//		exp.export(resp);
//	}
//	
	
	

}
