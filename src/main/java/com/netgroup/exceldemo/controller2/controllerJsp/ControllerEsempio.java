package com.netgroup.exceldemo.controller2.controllerJsp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import com.netgroup.exceldemo.repository.ExcelRepository;
import com.netgroup.exceldemo.util.ConverterExcel;



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
	
	@GetMapping("index/home")
	public ModelAndView home() {
		ModelAndView modelandview = new ModelAndView("Home/Home");
		return modelandview;
	}
	
	@GetMapping("index/login")
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView("login/logindue");
		return modelAndView;
	}
	
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
	public ModelAndView handleFileUploadExcel(@RequestParam("file") MultipartFile mFile) throws FileSizeLimitExceededException {
		try {
			String filename = mFile.getOriginalFilename();
			if(filename.equals("")){
				List<String> list0 = new ArrayList<>();
				list0.add("*** NESSUN FILE SELEZIONATO *** - SELEZIONARE FORMATO EXCEL ( .xls )");
				ModelAndView model = new ModelAndView("/Excel/upload");
				model.addObject("list", list0);
				return model;
			}
			String x = " salvato correttamente";
			List<String> list = converterExcel.Excel2Data(mFile.getInputStream());
			String y = list.get(0);
			if(x.equals(y)) {
				list.add(0, filename);
				ModelAndView model = new ModelAndView("/Excel/upload");
				model.addObject("list", list);
				return model;
			}
			ModelAndView model = new ModelAndView("/Excel/upload");
			model.addObject("list", list);
			return model;
			
		}catch(Exception e) {
			List<String> list2 = new ArrayList<>();
			String filename = mFile.getOriginalFilename();
			ModelAndView model = new ModelAndView("/Excel/upload");
			list2.add("FILE ' " + filename + " ' NON SUPPORTATO - SELEZIONARE FORMATO EXCEL ( .xls )");
			model.addObject("list", list2);
			return model;
		}



	
	}
	
	
}