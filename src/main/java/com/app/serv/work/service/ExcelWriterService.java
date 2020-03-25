package com.app.serv.work.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.serv.work.model.TestData;
import com.app.serv.work.repository.TestServiceDao;

@Component
public class ExcelWriterService {

	@Autowired
	private TestServiceDao testserviceDao;

	public String saveData(TestData testdata) {
		testserviceDao.save(testdata);
		System.out.println("saved successfully.....");
		return "success";
	}
	
	public String doTestExcel() {
		int rowNum = 0;

		String[] columns = { "Scenario", "Input URL", "Input Json", "Expected Json", "Result Json", "Expected response",
				"Result response" };

		List<TestData> lstTestData = testserviceDao.findAll();

		// Create a Workbook
		Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file

		/*
		 * CreationHelper helps us create instances for various things like DataFormat,
		 * Hyperlink, RichTextString etc in a format (HSSF, XSSF) independent way
		 */
		CreationHelper createHelper = workbook.getCreationHelper();

		// Create a Sheet
		Sheet sheet = workbook.createSheet("Employee");

		// Create a Font for styling header cells
		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setFontHeightInPoints((short) 14);
		headerFont.setColor(IndexedColors.BLUE.getIndex());

		// Create a CellStyle with the font
		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);
		// Create a Row
		Row infoRow = sheet.createRow(rowNum++);
		Row headerRow = sheet.createRow(rowNum++);
		// Creating cells
		for (int i = 0; i < columns.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columns[i]);
			cell.setCellStyle(headerCellStyle);
		}
		// Cell Style for formatting Date
		CellStyle dateCellStyle = workbook.createCellStyle();
		dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));
		for (TestData testdata : lstTestData) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(testdata.getTestDesc());
			row.createCell(1).setCellValue(testdata.getInputUri());
			row.createCell(2).setCellValue(testdata.getInputJson());
			row.createCell(3).setCellValue(testdata.getExpJson());
			row.createCell(4).setCellValue(testdata.getResultJson());
			row.createCell(5).setCellValue(testdata.getExpResp());
			row.createCell(6).setCellValue(testdata.getResultresp());
		}
		// Resize all columns to fit the content size
		for (int i = 0; i < columns.length; i++) {
			sheet.autoSizeColumn(i);
		}

		// Write the output to a file
		FileOutputStream fileOut = null;
		try {
			fileOut = new FileOutputStream("report" + Math.random() + ".xlsx");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			workbook.write(fileOut);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			fileOut.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}

	public TestServiceDao getTestserviceDao() {
		return testserviceDao;
	}

	public void setTestserviceDao(TestServiceDao testserviceDao) {
		this.testserviceDao = testserviceDao;
	}

}