package com.netgroup.exceldemo.util;

import java.io.File;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.netgroup.exceldemo.data.dao.Excel;
import com.netgroup.exceldemo.repository.ExcelRepository;


@Component
public class ConverterExcel {
	
	@Autowired
	ExcelRepository excelRepository;

	public void Excel2Data(String path) throws EncryptedDocumentException, InvalidFormatException, IOException{

		Workbook workbook = WorkbookFactory.create(new File(path));
		
		for(Sheet sheet: workbook) {

            for (Row row: sheet) {
            	String nome_prodotto = row.getCell(0).getStringCellValue();
            	String categoria_prodotto = row.getCell(1).getStringCellValue();
            	double prezzo = row.getCell(2).getNumericCellValue();
            	
            	Excel excel = new Excel();
            	excel.setNomeProdotto(nome_prodotto);
            	excel.setCategoriaProdotto(categoria_prodotto);
            	excel.setPrezzo(prezzo);
            	
            	excelRepository.save(excel);
            }
		}
		
		
	}

	
}














