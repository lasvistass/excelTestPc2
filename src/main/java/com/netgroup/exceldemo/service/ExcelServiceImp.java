package com.netgroup.exceldemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netgroup.exceldemo.data.dao.Excel;
import com.netgroup.exceldemo.repositrory.ExcelRepository;

@Service
public class ExcelServiceImp implements ExcelService {

	@Autowired
	ExcelRepository excelRepo;

	@Override
	public Excel salva(Excel excel) {
		return excelRepo.save(excel);
	}

	

	@Override
	public List<Excel> listFile() {
		// TODO Auto-generated method stub
		return excelRepo.findAll();
	}
	
	

}
