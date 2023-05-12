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


public class ThamKhaoSVService extends WriteExcelService {

	private final int COLUMN_INDEX_MASV = 0;
	private final int COLUMN_INDEX_HOTEN = 1;
	private final int COLUMN_INDEX_KHOA = 2;
	private final int COLUMN_INDEX_EMAIL = 3;
	private final int COLUMN_INDEX_CHUYENNGANH = 4;
	private final int COLUMN_INDEX_COSO = 5;
	@Override
	public void createSheet2(Workbook workbook,int rowIndex) {
		int rowheader = 0;
		Sheet sheet = workbook.createSheet("Sinh_Vien");
		CellStyle cellStyle = createStyleForHeader(sheet);

		Row row = sheet.createRow(rowheader);
		cellStyle.setLocked(true);

		Cell cell = row.createCell(COLUMN_INDEX_MASV);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("MASV");

		cell = row.createCell(COLUMN_INDEX_HOTEN);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("TÊN");

		cell = row.createCell(COLUMN_INDEX_KHOA);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("KHÓA");

		cell = row.createCell(COLUMN_INDEX_EMAIL);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("EMAIL");

		cell = row.createCell(COLUMN_INDEX_CHUYENNGANH);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("CHUYÊN NGÀNH");

		cell = row.createCell(COLUMN_INDEX_COSO);
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
