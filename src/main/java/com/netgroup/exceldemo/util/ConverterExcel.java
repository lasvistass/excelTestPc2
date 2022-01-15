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

	public boolean Excel2Data(InputStream iStream) throws EncryptedDocumentException, InvalidFormatException, IOException{

		 boolean check1 = false;
		 boolean check2 = false;
		 boolean check3 = false;
		 
		 boolean check4 = false;
		 boolean check5 = false;
		 boolean check6 = false;
		 boolean checkFinal = false;

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
					String nomeProdotto = "Nome Prodotto";
					String cNomeProdotto = c.getStringCellValue();
					String lowerNomeProdotto = nomeProdotto.toLowerCase();
					String lowerCNomeProdotto = cNomeProdotto.toLowerCase();
					if(lowerNomeProdotto.equals(lowerCNomeProdotto)) {
						check1 = true;
					}
					break;
				case 1:
					String categoria = "Categoria";
					String cCategoria = c.getStringCellValue();
					String lowerCategoria = categoria.toLowerCase();
					String lowerCCategoria= cCategoria.toLowerCase();
					if (lowerCategoria.equals(lowerCCategoria)) {
						check2 = true;
					}
					break;
				case 2:
					String prezzo = "Prezzo";
					String cPrezzo = c.getStringCellValue();
					String lowerPrezzo = prezzo.toLowerCase();
					String lowerCPrezzo = cPrezzo.toLowerCase();
					if(lowerPrezzo.equals(lowerCPrezzo)) {
						check3 = true;
					}
					break;
				}
			}

		
			if ( check1 && check2 && check3) {
				while(rowIterator.hasNext()) {
					Excel excel = new Excel();
					Row nextRow = rowIterator.next();
					Iterator<Cell> cellIterator = nextRow.cellIterator();
					while(cellIterator.hasNext()) {
					
						Cell nextCell = cellIterator.next();
						int columnIndex = nextCell.getColumnIndex();
						switch (columnIndex) {
						case 0:
							String nomeProdotto = nextCell.getStringCellValue();
							if( nomeProdotto instanceof String && nomeProdotto != null) {
								check4 = true;
							}
							excel.setNomeProdotto(nomeProdotto);
							break;
						case 1:
							String categoria = nextCell.getStringCellValue();
							String catogoriaUP = categoria.toUpperCase();
							if(catogoriaUP instanceof String && catogoriaUP != null) {
								check5 = true;
							}
							excel.setCategoriaProdotto(CategoriaProdotto.valueOf(catogoriaUP));
							break;
						case 2:
							double prezzo = nextCell.getNumericCellValue();
							if( prezzo > -1 ) {
								check6 = true;
							}
							excel.setPrezzo(nextCell.getNumericCellValue());
							break;
							
						
						}
	
	
					}
					if(check4 && check5 && check6) {
						LocalDate ld = LocalDate.now();
						excel.setLocaldate(ld);
						excelRepository.save(excel);
					}

					
	
	
				}
			}
			
			workbook.close();

			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		if ( check1 && check2 && check3 && check4 && check5 && check6) {
			checkFinal = true;
		}
		return checkFinal;
		

		}
		
		
	}

	















