package com.netgroup.exceldemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netgroup.exceldemo.data.Excel;
import com.netgroup.exceldemo.repositrory.ExcelRepository;

@Service
public class ExcelServiceImp implements ExcelService {

	@Autowired
	ExcelRepository excelRepo;

	@Override
	public void salva(Excel excel) {
		excelRepo.save(excel);
	}

	@Override
	public List<Excel> listaExcelByUtente(int id) {
		return excelRepo.findAll();
	}

}
