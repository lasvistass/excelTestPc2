package com.netgroup.exceldemo.controller2.controllerJsp;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.netgroup.exceldemo.data.dao.Excel;
import com.netgroup.exceldemo.service.ExcelService;
import com.netgroup.exceldemo.util.ExcelUtils;

@Controller
@RequestMapping
public class downloadExcelController {

	@Autowired
	ExcelService excelService;
	
	private static final Logger log = LoggerFactory.getLogger(downloadExcelController.class);

	@GetMapping(value = "/export")
	public void exportToExcel(HttpServletResponse resp) throws IOException {
		resp.setContentType("application/octet-stream");
		String headerKey = "Content-Disposition";
		String headervalue = "attachment; filename=prodotto.xlsx";
		
		resp.setHeader(headerKey, headervalue);
		List<Excel> listExcel = excelService.listFile();
		ExcelUtils exp = new ExcelUtils(listExcel);
		exp.export(resp);
	}
	
	
	

}
