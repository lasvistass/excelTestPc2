package com.netgroup.exceldemo.service;

import java.util.List;

import com.netgroup.exceldemo.data.Excel;

public interface ExcelService {

	public void salva(Excel excel);
	public List<Excel> listFile();
	
	
}