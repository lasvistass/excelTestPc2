package com.netgroup.exceldemo.service;

import java.util.List;

import com.netgroup.exceldemo.data.dao.Excel;

public interface ExcelService {

	public Excel salva(Excel excel);
	public List<Excel> listFile();
	
	
}