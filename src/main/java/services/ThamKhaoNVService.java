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

public class ThamKhaoNVService extends WriteExcelService {
	private final int COLUMN_INDEX_HOTEN = 0;
	private final int COLUMN_INDEX_SDT = 1;
	private final int COLUMN_INDEX_EMAIL_FPT = 2;
	private final int COLUMN_INDEX_EMAIL_FE = 3;
	private final int COLUMN_INDEX_BOMON_CHI_DANH_CNBM = 4;
	private final int COLUMN_INDEX_ROLE= 5;
	private final int COLUMN_INDEX_COSOID = 6;
	
	@Override
	public void createSheet2(Workbook workbook,int rowIndex) {
		int rowheader = 0;
		Sheet sheet = workbook.createSheet("Nhan_Vien");
		CellStyle cellStyle = createStyleForHeader(sheet);

		Row row = sheet.createRow(rowheader);
		cellStyle.setLocked(true);

		Cell cell = row.createCell(COLUMN_INDEX_HOTEN);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("TÊN");

		cell = row.createCell(COLUMN_INDEX_SDT);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("SDT");

		cell = row.createCell(COLUMN_INDEX_EMAIL_FPT);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("EMAIL FPT");
		
		cell = row.createCell(COLUMN_INDEX_EMAIL_FE);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("EMAIL FE");

		cell = row.createCell(COLUMN_INDEX_BOMON_CHI_DANH_CNBM);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("CHUYÊN NGÀNH (Chỉ dành cho chủ nhiệm bộ môn)");

		cell = row.createCell(COLUMN_INDEX_ROLE);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("CHỨC VỤ");

		cell = row.createCell(COLUMN_INDEX_COSOID);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("CƠ SỞ");

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
