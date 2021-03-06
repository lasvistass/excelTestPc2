package com.netgroup.exceldemo.controller2.controllerJsp;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import com.netgroup.exceldemo.repository.ExcelRepository;
import com.netgroup.exceldemo.util.ConverterExcel;
import com.netgroup.exceldemo.data.*;
import com.netgroup.exceldemo.data.dao.Excel;


@Controller
public class ControllerEsempio {
	
	@Autowired
	ConverterExcel converterExcel;
	
	@Autowired
	ExcelRepository excelRepository;

//	@GetMapping("/index")
//	public String hello() {
//		return "uploader";
//	}
	
	
	@GetMapping("/index/jsp")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("Excel/upload");
		return model;
	}
	
	@PostMapping("/upload")
	public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile mFile) throws IllegalStateException, IOException{
		String fileName = mFile.getOriginalFilename();
		mFile.transferTo(new File("C:\\Users\\simon\\OneDrive\\Immagini\\" + fileName));
		return ResponseEntity.ok("salvataggio riuscito");
	}
	
	@PostMapping("/upload/excel")
	public ResponseEntity<?> handleFileUploadExcel(@RequestParam("file") MultipartFile mFile) throws IllegalStateException, IOException{
		try{
			converterExcel.Excel2Data(mFile.getInputStream());
			return ResponseEntity.ok("salvataggio riuscito");
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	

	
	
}
