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
			Sheet firstSheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = firstSheet.iterator();

			Row row = rowIterator.next();
			Iterator<Cell> cell = row.cellIterator();
			while(cell.hasNext()) {
				
				Cell c = cell.next();
				int columnIndex = c.getColumnIndex();
				switch (columnIndex) {
				case 0:
					System.out.println(c.getStringCellValue());
					break;
				case 1:
					System.out.println(c.getStringCellValue());
					break;
				case 2:
					System.out.println(c.getStringCellValue());
					break;
				}
			}

			
			
			while(rowIterator.hasNext()) {
				Excel excel = new Excel();
				Row nextRow = rowIterator.next();
				Iterator<Cell> cellIterator = nextRow.cellIterator();
				while(cellIterator.hasNext()) {
				
					Cell nextCell = cellIterator.next();
					int columnIndex = nextCell.getColumnIndex();
					switch (columnIndex) {
					case 0:
						excel.setNomeProdotto(nextCell.getStringCellValue());
						break;
					case 1:
						excel.setCategoriaProdotto(CategoriaProdotto.valueOf(nextCell.getStringCellValue()));
						break;
					case 2:
						excel.setPrezzo(nextCell.getNumericCellValue());
						break;
						
					
					}


				}
				LocalDate ld = LocalDate.now();
				excel.setLocaldate(ld);
				excelRepository.save(excel);

			}
			
			workbook.close();

			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		

		}
		
		
	}

	















