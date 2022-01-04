package com.netgroup.exceldemo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netgroup.exceldemo.service.ExcelService;

@RestController
@RequestMapping("/api/excel")
public class ExcelRest {

	@Autowired
	ExcelService excelService;
	
	
	
	
}
