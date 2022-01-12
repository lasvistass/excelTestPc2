
package com.netgroup.exceldemo.controller2.rest;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netgroup.exceldemo.data.dao.Excel;
import com.netgroup.exceldemo.service.ExcelService;
import com.netgroup.exceldemo.util.ExcelUtils;

@RestController
@RequestMapping("/api/excel")
public class ExcelRest {

	@Autowired
	ExcelService excelService;
	
	
	
	@GetMapping(value = "/export")
	public void exportToExcel(HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");
		String headerKey = "Content-Disposition";
		String headervalue = "attachment; filename=prodotto.xlsx";

		response.setHeader(headerKey, headervalue);
		List<Excel> listExcel = excelService.listFile();
		ExcelUtils exp = new ExcelUtils(listExcel);
		
		
		exp.export(response);

	
	
	
}
}
