package com.netgroup.exceldemo.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Iterator;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.netgroup.exceldemo.data.dao.CategoriaProdotto;
import com.netgroup.exceldemo.data.dao.Excel;
import com.netgroup.exceldemo.repository.ExcelRepository;



@Component
public class ConverterExcel {
	
	@Autowired
	ExcelRepository excelRepository;

	public void Excel2Data(InputStream iStream) throws EncryptedDocumentException, InvalidFormatException, IOException{

		try {
		      Workbook workbook = WorkbookFactory.create(iStream);
		      Sheet sheet = workbook.getSheetAt(0);
		      Iterator<Row> rows = sheet.iterator();

		      int rowNumber = 0;
		      while (rows.hasNext()) {
		        Row currentRow = rows.next();
		        if (rowNumber == 0) {
		          rowNumber++;
		          continue;
		        }

		        Iterator<Cell> cellsInRow = currentRow.iterator();
		        
		        Excel excel = new Excel();

		       

		        int cellIdx = 0;
		        while (cellsInRow.hasNext()) {
		        
		          Cell currentCell = cellsInRow.next();

		          switch (cellIdx) {
		          case 0:
		            excel.setNomeProdotto(currentCell.getStringCellValue()); 
		            break;

		          case 1:
		            excel.setCategoriaProdotto(CategoriaProdotto.valueOf(currentCell.getStringCellValue()));
		            break;

		          case 2:
		            excel.setPrezzo(currentCell.getNumericCellValue());
		            break;


		          default:
		            break;
		          }

		          cellIdx++;
		        }
		        LocalDate ld = LocalDate.now();
		        excel.setLocaldate(ld);
		        excelRepository.save(excel);
		      }

		      workbook.close();

			}catch(Exception e) {
				e.printStackTrace();
				
				
			}
		

		}
		
		
	}

	















