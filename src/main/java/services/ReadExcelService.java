package services;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelService {
	public List<Object[]> readExcel(String excelFilePath, Object[] header, String... str) {
		try {
			List<Object[]> lst = new ArrayList<>();
			// Get file
			InputStream inputStream = new FileInputStream(new File(excelFilePath));
			// Get workbook
			Workbook workbook = getWorkbook(inputStream, excelFilePath);
			// Get sheet
			String sheet_name = str[0];
			Sheet sheet = workbook.getSheet(sheet_name);
			
			// Get all rows
			Iterator<Row> iterator = sheet.iterator();
			while (iterator.hasNext()) {
				Row nextRow = iterator.next();
				if (nextRow.getRowNum() == 0) {
					continue;
				}
				Iterator<Cell> cellIterator = nextRow.cellIterator();
				Object[] obs = new Object[header.length];
				while (cellIterator.hasNext()) {
					// Read cell
					Cell cell = cellIterator.next();
					Object cellValue = getCellValue(cell);
					if (cellValue == null || cellValue.toString().isEmpty()) {
						continue;
					}
					int columnIndex = cell.getColumnIndex();
					if (columnIndex == header.length) {
						break;
					}
					obs[columnIndex] = cellValue;
				}
				lst.add(obs);
			}
			workbook.close();
			inputStream.close();
			return lst;
		} catch (Exception e) {
			e.printStackTrace();
			return null; // sendRedirect tơi
		}
	}

	public Workbook getWorkbook(InputStream inputStream, String excelFilePath) {
		Workbook workbook = null;
		try {
			if (excelFilePath.endsWith("xlsx")) {
				workbook = new XSSFWorkbook(inputStream);
			} else if (excelFilePath.endsWith("xls")) {
				workbook = new HSSFWorkbook(inputStream);
			} else {
				throw new IllegalArgumentException("The specified file is not Excel file");
			}
			return workbook;
		} catch (Exception e) {
			return null;
		}
	}

	public Object getCellValue(Cell cell) {
		CellType cellType = cell.getCellType();
		Object cellValue = null;
		switch (cellType) {
		case BOOLEAN:
			cellValue = cell.getBooleanCellValue();
			break;
		case FORMULA:
			Workbook workbook = cell.getSheet().getWorkbook();
			FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
			cellValue = evaluator.evaluate(cell).getNumberValue();
			break;
		case NUMERIC:
			cellValue = cell.getNumericCellValue();
			break;
		case STRING:
			cellValue = cell.getStringCellValue();
			break;
		case _NONE:
		case BLANK:
		case ERROR:
			break;
		default:
			break;
		}

		return cellValue;

	}
}
