package com.app.serv.work.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
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

import com.app.serv.work.model.Worker;


public class ExcelWriter {

	public String doExcel() {

		String[] columns = { "Name", "Email", "Date Of Birth", "Salary" };

		List<Worker> employees = new ArrayList<>();

		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.set(1992, 7, 21);
		employees.add(new Worker("Rajeev Singh", "rajeev@example.com", dateOfBirth.getTime(), 1200000.0));

		dateOfBirth.set(1965, 10, 15);
		employees.add(new Worker("Thomas cook", "thomas@example.com", dateOfBirth.getTime(), 1500000.0));

		dateOfBirth.set(1987, 4, 18);
		employees.add(new Worker("Steve Maiden", "steve@example.com", dateOfBirth.getTime(), 1800000.0));

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
		headerFont.setColor(IndexedColors.RED.getIndex());

		// Create a CellStyle with the font
		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);

		// Create a Row
		Row headerRow = sheet.createRow(0);

		// Creating cells
		for (int i = 0; i < columns.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columns[i]);
			cell.setCellStyle(headerCellStyle);
		}

		// Cell Style for formatting Date
		CellStyle dateCellStyle = workbook.createCellStyle();
		dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));

		// Create Other rows and cells with employees data
		int rowNum = 1;
		for (Worker employee : employees) {
			Row row = sheet.createRow(rowNum++);

			row.createCell(0).setCellValue(employee.getName());

			row.createCell(1).setCellValue(employee.getEmail());

			Cell dateOfBirthCell = row.createCell(2);
			dateOfBirthCell.setCellValue(employee.getDateOfBirth());
			dateOfBirthCell.setCellStyle(dateCellStyle);

			row.createCell(3).setCellValue(employee.getSalary());
		}

		// Resize all columns to fit the content size
		for (int i = 0; i < columns.length; i++) {
			sheet.autoSizeColumn(i);
		}

		// Write the output to a file
		FileOutputStream fileOut = null;
		try {
			fileOut = new FileOutputStream("poi-generated-file2.xlsx");
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

}