package com.netgroup.exceldemo.util;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.netgroup.exceldemo.data.dao.Excel;
 
@Component
public class ExcelUtils {
 
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	
	private List<Excel> listExcel;
	
	
	public ExcelUtils(List<Excel> listExcel) {
		this.listExcel=listExcel;
		workbook = new XSSFWorkbook();
		
	}
	
	private void createCell(Row row,int columnCount, Object value,CellStyle style) {
		sheet.autoSizeColumn(columnCount);
		Cell cell=row.createCell(columnCount);
		if(value instanceof Long) {
			cell.setCellValue((Long) value);
		}else if(value instanceof Integer) {
			cell.setCellValue((Integer) value);
		}else if(value instanceof Double) {
			cell.setCellValue((Double) value);
		}else {
			cell.setCellValue((String) value);
		}
		cell.setCellStyle(style);
	}
	
	private void writeHeaderLine() {
		sheet=workbook.createSheet("excel");
		
		Row row = sheet.createRow(0);
		CellStyle style = workbook.createCellStyle();
		XSSFFont font=workbook.createFont();
		font.setBold(true);
		font.setFontHeight(20);
		style.setFont(font);
		style.setAlignment(HorizontalAlignment.CENTER);
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,4));
		font.setFontHeightInPoints((short)(10));
		row=sheet.createRow(1);
		font.setBold(true);
  
	}
  
  
  
	
  
  private void writeDataLines() {
    int rowCount=2;
    
    CellStyle style=workbook.createCellStyle();
    XSSFFont font=workbook.createFont();
    font.setFontHeight(14);
    style.setFont(font);
    
    for(Excel excel:listExcel) {
      Row row=sheet.createRow(rowCount++);
      int columnCount=0;
      createCell(row, columnCount++, excel.getNomeProdotto(), style);
      createCell(row, columnCount++, excel.getCategoriaProdotto(), style);
      createCell(row, columnCount++, excel.getPrezzo(), style);

    }
  }
  
  public void export(HttpServletResponse response) throws IOException{
    writeHeaderLine();
    writeDataLines();
 
    ServletOutputStream outputStream=response.getOutputStream();
    workbook.write(outputStream);
    workbook.close();
    outputStream.close();
  }
  
}