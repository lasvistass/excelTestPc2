package com.netgroup.exceldemo.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

	public List<String> Excel2Data(InputStream iStream)
			throws EncryptedDocumentException, InvalidFormatException, IOException {

		boolean check1 = false;
		boolean check2 = false;
		boolean check3 = false;

		boolean check4 = false;
		boolean check5 = false;
		boolean check6 = false;

		List<Excel> listaExcel = new ArrayList<>();
		List<String> error = new ArrayList<>();
		Integer righe = null;

		try {
			Workbook workbook = WorkbookFactory.create(iStream);
			Sheet firstSheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = firstSheet.iterator();
			righe = firstSheet.getLastRowNum();

			Row row = rowIterator.next();
			Iterator<Cell> cell = row.cellIterator();
			while (cell.hasNext()) {

				Cell c = cell.next();
				int columnIndex = c.getColumnIndex();
				switch (columnIndex) {
				case 0:
					String nomeProdotto = "Nome Prodotto";
					String cNomeProdotto = c.getStringCellValue();
					String lowerNomeProdotto = nomeProdotto.toLowerCase();
					String lowerCNomeProdotto = cNomeProdotto.toLowerCase();
					if (lowerNomeProdotto.equals(lowerCNomeProdotto)) {
						check1 = true;
					} else {
						String errProdotto = "Colonna Nome-Prodotto assente";
						error.add(errProdotto);

					}
					break;
				case 1:
					String categoria = "Categoria";
					String cCategoria = c.getStringCellValue();
					String lowerCategoria = categoria.toLowerCase();
					String lowerCCategoria = cCategoria.toLowerCase();
					if (lowerCategoria.equals(lowerCCategoria)) {
						check2 = true;
					} else {
						String errCategoria = "Colonna Categoria assente ( consultare la lista delle categorie )";
						error.add(errCategoria);
					}
					break;
				case 2:
					String prezzo = "Prezzo";
					String cPrezzo = c.getStringCellValue();
					String lowerPrezzo = prezzo.toLowerCase();
					String lowerCPrezzo = cPrezzo.toLowerCase();
					if (lowerPrezzo.equals(lowerCPrezzo)) {
						check3 = true;
					} else {
						String errPrezzo = "Colonna Prezzo assente";
						error.add(errPrezzo);
					}
					break;
				}
			}

			try {
				if (check1 && check2 && check3) {
					while (rowIterator.hasNext()) {
						Excel excel = new Excel();
						Row nextRow = rowIterator.next();
						Iterator<Cell> cellIterator = nextRow.cellIterator();
						while (cellIterator.hasNext()) {

							Cell nextCell = cellIterator.next();
							int columnIndex = nextCell.getColumnIndex();
							switch (columnIndex) {
							case 0:
								try {
									String nomeProdotto = nextCell.getStringCellValue();
									excel.setNomeProdotto(nomeProdotto);
									check4 = true;
									break;
								} catch (Exception e) {
									String x = String.valueOf(nextRow.getRowNum());
									error.add("Errore Nome-Prodotto a riga " + x);
									break;
								}

							case 1:
								try {
									String categoria = nextCell.getStringCellValue();
									String catogoriaUP = categoria.toUpperCase();
									excel.setCategoriaProdotto(CategoriaProdotto.valueOf(catogoriaUP));
									check5 = true;
									break;
								} catch (Exception e) {
									String x = String.valueOf(nextRow.getRowNum());
									error.add("Errore Categoria a riga " + x);
									break;
								}

							case 2:
								try {
									excel.setPrezzo(nextCell.getNumericCellValue());
									check6 = true;
									break;
								} catch (Exception e) {
									String x = String.valueOf(nextRow.getRowNum());
									error.add("Errore Prezzo " + x);
									break;
								}

							}

						}

						try {
							if (check4 && check5 && check6) {
								LocalDate ld = LocalDate.now();
								excel.setLocaldate(ld);
								listaExcel.add(excel);
							}

						} catch (Exception e) {
							error.add("record non salvato");
						}

					}
				} else {
					String x = "DOCUMENTO ERRATO - CARICAMENTO FALLITO";
					error.add(x);
				}
			} catch (Exception e) {
				error.add("compilare bene");
			}

			workbook.close();

		} catch (Exception e) {

			e.printStackTrace();
		}
		
		
		if (check1 && check2 && check3 && check4 && check5 && check6 && righe == listaExcel.size()) {
			for (int i = 0; i < listaExcel.size(); i++) {
				excelRepository.save(listaExcel.get(i));
			}
			error.add("Salvataggio andato a buon fine");
			return error;
		} else {
			error.add("ATTENZIONE FILE NON CORRETTO - CARICAMENTO FALLITO");
			return error;

		}

	}

}
