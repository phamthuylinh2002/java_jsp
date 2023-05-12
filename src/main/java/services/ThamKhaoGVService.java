package services;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddressList;

public class ThamKhaoGVService extends WriteExcelService {
	private final int COLUMN_INDEX_TENGV = 0;
	private final int COLUMN_INDEX_EMAILFPT = 1;
	private final int COLUMN_INDEX_EMAILFE = 2;
	private final int COLUMN_INDEX_SDT = 3;
	private final int COLUMN_INDEX_CN = 4;
	private final int COLUMN_INDEX_NHOMHD = 5;

	
	@Override
	public void createSheet2(Workbook workbook,int rowIndex) {
		int rowheader = 0;
		Sheet sheet = workbook.createSheet("Giang_Vien");
		autosizeColumn(sheet, 6);
		CellStyle cellStyle = createStyleForHeader(sheet);

		Row row = sheet.createRow(rowheader);

		cellStyle.setLocked(true);
		
		
		Cell cell = row.createCell(COLUMN_INDEX_TENGV);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("TENGV");
		
		cell = row.createCell(COLUMN_INDEX_EMAILFPT);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("EMAILFPT");
		
		cell = row.createCell(COLUMN_INDEX_EMAILFE);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("EMAILFE");

		cell = row.createCell(COLUMN_INDEX_SDT);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("SDT");

		cell = row.createCell(COLUMN_INDEX_CN);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("CHUYÊN NGÀNH");

		cell = row.createCell(COLUMN_INDEX_NHOMHD);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("NHÓM HƯỚNG DẪN");
		


		int row_demo=1;

		row=sheet.createRow(row_demo);
		int last_column=rowIndex;
		String column="B$"+last_column;
		DataValidationHelper dvHelper = sheet.getDataValidationHelper();
		DataValidationConstraint dvConstraint = dvHelper.createFormulaListConstraint("Tham_Khao!$B$2:$"+column);
		CellRangeAddressList addressList = new CellRangeAddressList(1, 1, 4, 4);
		DataValidation validation = dvHelper.createValidation(dvConstraint, addressList);
		sheet.addValidationData(validation);
		
	}
}
