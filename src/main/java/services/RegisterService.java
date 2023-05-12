package services;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import model.Nhom;
import model.SinhVien;
import repositories.NhomRepositories;
import repositories.SinhVienRepositories;

public class RegisterService {
	public final int SINH_VIEN_DANG_KY_DATN = 1;
	public final int SINH_VIEN_CO_NHOM = 1;
	public final int SINH_VIEN_CHUA_CO_NHOM = 0;
	public final int NGAY_HOM_SAU = 1;

	SinhVienRepositories sv_rep;
	NhomRepositories nhom_rep;

	public RegisterService() {
		this.sv_rep = new SinhVienRepositories();
		this.nhom_rep = new NhomRepositories();
	}

	public SinhVien capNhatTrangThaiSV(SinhVien sv, int trangThai) {
		if (trangThai == 0 && sv.getNhomId()!=null) {
			sv = roiNhom(sv);
			if (sv == null ) {
				return null;
			}
		}
		sv.setTrangThai(trangThai);
		sv = sv_rep.update(sv);
		return sv;
	}

	public SinhVien roiNhom(SinhVien sv) {
		sv = sv_rep.findByEmai(sv.getEmail());

		Calendar c = Calendar.getInstance();
		try {
			c.setTime(utils.HelperUtils.sdf.parse(sv.getThoiGianDkNhom()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(c);	

		c.roll(Calendar.DATE, NGAY_HOM_SAU);

		if (c.getTime().before(new Date())) {
			int idNhom = sv.getNhomId();

			// roi nhom
			sv.setThoiGianDkNhom(null);
			sv.setNhomId(null);
			sv_rep.update(sv);

			List<SinhVien> lst = sv_rep.getListSV_byIdNhom(idNhom);
			if (lst == null || lst.size()==0) {
				Nhom nhom_temp = nhom_rep.findById(idNhom);
				if (nhom_temp != null) {
					nhom_rep.delete(nhom_temp);
				}
			}else {
				Nhom nhom_temp = nhom_rep.findById(lst.get(0).getNhomId());
				nhom_temp.setNhomTruongId(lst.get(0).getMaSv()+"");
				nhom_rep.update(nhom_temp);
			}
			return sv;
		} else {
			// kh dc out nhom
			return null;
		}
	}
}
