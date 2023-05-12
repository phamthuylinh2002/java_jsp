package services;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.extern.log4j.Log4j2;
import model.GvHuongDan;
import model.Nhom;
import model.SinhVien;
import repositories.GvHuongDanRepositories;
import repositories.NhomRepositories;
import repositories.SinhVienRepositories;

@Log4j2
public class CreateTService {

	private GvHuongDanRepositories gv_hd;
	private NhomRepositories nhom;
	private SinhVienRepositories svRep;

	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
	LocalDateTime now = LocalDateTime.now();   

	public CreateTService() {
		this.gv_hd = new GvHuongDanRepositories();
		this.nhom = new NhomRepositories();
		this.svRep = new SinhVienRepositories();
	}

	public String genCode() {
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 20;
		Random random = new Random();
		StringBuilder buffer = new StringBuilder(targetStringLength);
		for (int i = 0; i < targetStringLength; i++) {
			int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
			buffer.append((char) randomLimitedInt);
		}
		String generatedString = buffer.toString();

		return generatedString;
	}

	public SinhVien createTeam(SinhVien sv, int gv_id, String trangThaiNhom, String tenDeTai, HttpServletRequest request) {

		Nhom nhom = new Nhom();

		nhom.setDotDangKyId(sv.getDotDangKyId());
		nhom.setGiangVienHdId(gv_id);

		if (trangThaiNhom.equals("0")) {

			String codeTeam = "";
			Boolean kt=true;
						
			while (kt) {
				codeTeam = genCode();
				if(this.nhom.checkCode(codeTeam)==0) {
					kt = false;
				}
			}

			nhom.setCode(codeTeam);
		} else {
			nhom.setCode(null);
		}
		nhom.setNhomTruongId(sv.getMaSv());

		nhom.setTenDeTai(tenDeTai);
		
		sv.setThoiGianDkNhom(dtf.format(now));
		nhom.setNgayTao(dtf.format(now));
		nhom = this.nhom.insert(nhom);
		sv.setNhomId(nhom.getId());
		this.svRep.update(sv);
		request.getSession().setAttribute("ScopeTT", nhom.getCode()==null?1:0);
		
		return sv;
	}

	public void updateTeam(Nhom nhom, SinhVien sv, String gv_id, String trangThaiNhom, String tenDeTai)
			throws UnsupportedEncodingException {
		nhom.setDotDangKyId(sv.getDotDangKyId());

		
		if (trangThaiNhom.equalsIgnoreCase("0") && nhom.getCode() == null) {

			String codeTeam = genCode();

			nhom.setCode(codeTeam);
		} else {
			if(trangThaiNhom.equalsIgnoreCase("1")) {
				nhom.setCode(null);
			}
		}
		nhom.setNhomTruongId(sv.getMaSv());

		nhom.setTenDeTai(tenDeTai);
		nhom.setGiangVienHdId(utils.HelperUtils.parseInteger(gv_id));
		sv.setThoiGianDkNhom(dtf.format(now));
		nhom = this.nhom.update(nhom);
	}
}
