package services;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcelService {

	public void writeExcel(List<Object[]> lst, String path, Object[] header, String...str) {
		Workbook workbook = getWorkbook(path);
		//str = "Danh_Sach"
		String sheet_name = str[0];

		Sheet sheet = workbook.createSheet(sheet_name);

		int rowIndex = 0;
		// Write header
		writeHeader(sheet, rowIndex, header);
		// Write data
		rowIndex++;
		for (Object[] st : lst) {
			Row row = sheet.createRow(rowIndex);
			writeList(st, row, header);
			rowIndex++;
		}
		int numberOfColumn = sheet.getRow(0).getPhysicalNumberOfCells();
		autosizeColumn(sheet, numberOfColumn);
		createSheet2(workbook, rowIndex);

		createOutputFile(workbook, path);
	}
	
	
	public void createSheet2(Workbook workbook, int rowIndex) {
	}


	public Workbook getWorkbook(String path) {
		Workbook workbook = null;
		if (path.endsWith("xlsx")) {
			workbook = new XSSFWorkbook();
		} else if (path.endsWith("xls")) {
			workbook = new HSSFWorkbook();
		} else {
			throw new IllegalArgumentException("The specified file is not Excel file");
		}

		return workbook;
	}

	public void writeHeader(Sheet sheet, int rowIndex, Object[] header) {
		CellStyle cellStyle = createStyleForHeader(sheet);

		Row row = sheet.createRow(rowIndex);
		for (int column_index = 0; column_index < header.length; column_index++) {
			Cell cell = row.createCell(column_index);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(header[column_index] + "");
		}
	}

	public void writeList(Object[] t, Row row, Object[] value) {
		for (int column_index = 0; column_index < value.length; column_index++) {
			Cell cell = row.createCell(column_index);
			cell.setCellValue(t[column_index] + "");
		}
	}

	public CellStyle createStyleForHeader(Sheet sheet) {
		Font font = sheet.getWorkbook().createFont();
		font.setFontName("Calibri");
		font.setBold(true);
		font.setFontHeightInPoints((short) 14);
		font.setColor(IndexedColors.WHITE.getIndex());
		CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
		cellStyle.setFont(font);
		cellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cellStyle.setBorderBottom(BorderStyle.THIN);
		return cellStyle;
	}

	public void autosizeColumn(Sheet sheet, int lastColumn) {
		for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
			sheet.autoSizeColumn(columnIndex);
		}
	}

	public void createOutputFile(Workbook workbook, String excelFilePath) {
		try (OutputStream os = new FileOutputStream(excelFilePath)) {
			workbook.write(os);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
