package services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.extern.log4j.Log4j2;
import model.Nhom;
import model.SinhVien;
import repositories.ChuyenNganhRepositories;
import repositories.NhomRepositories;
import repositories.SinhVienRepositories;
import utils.HelperUtils;

@Log4j2
public class JoinTeamService {
	private NhomRepositories nhomRep;
	private SinhVienRepositories svRep;
	private ChuyenNganhRepositories cnRep;

	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
	LocalDateTime now = LocalDateTime.now(); 
	private static final Logger LOGGER = LoggerFactory.getLogger(JoinTeamService.class);
	public JoinTeamService() {
		this.nhomRep = new NhomRepositories();
		// TODO Auto-generated constructor stub
		this.svRep = new SinhVienRepositories();
		this.cnRep = new ChuyenNganhRepositories();
	}

	public void joinPrivateTeam(SinhVien user, String code) {
		if (code == null) {
			return;
		}
		Nhom nhom = this.nhomRep.findByCode(code);
		if (nhom == null) {
			return;
		}
		int nhom_id = nhom.getId();
		String masv = nhom.getNhomTruongId();
		SinhVien truongNhom = this.svRep.findByMaSV(masv);
		int boMonTruongNhom = this.cnRep.getBoMonbyChuyenNganhId(truongNhom.getChuyenNganhId());
		int boMonUser = this.cnRep.getBoMonbyChuyenNganhId(user.getChuyenNganhId());
		
		if (boMonTruongNhom != boMonUser || truongNhom.getCoSoId() != user.getCoSoId()
				|| truongNhom.getDotDangKyId() != user.getDotDangKyId() || this.nhomRep.getSoLuongNhom(nhom_id) >= 7) {
				return;
		}
		user.setThoiGianDkNhom(dtf.format(now));
		user.setNhomId(nhom_id);
		this.svRep.update(user);
	}

	public void joinPublicTeam(SinhVien sv, String id) {
		Integer nhom_id = HelperUtils.parseInteger(id.trim());
		if((sv.getNhomId()==null ||sv.getNhomId()==0)&& nhom_id !=null) {
			Nhom nhom = this.nhomRep.findNhomId(nhom_id);
			String masv = nhom.getNhomTruongId();
			SinhVien truongNhom = this.svRep.findByMaSV(masv);
			int boMonTruongNhom = this.cnRep.getBoMonbyChuyenNganhId(truongNhom.getChuyenNganhId());
			int boMonUser = this.cnRep.getBoMonbyChuyenNganhId(sv.getChuyenNganhId());
			
			if (boMonTruongNhom != boMonUser || truongNhom.getCoSoId() != sv.getCoSoId()
				|| truongNhom.getDotDangKyId() != sv.getDotDangKyId() || this.nhomRep.getSoLuongNhom(nhom_id) >= 7) {
				return;
			}
			sv.setThoiGianDkNhom(dtf.format(now));
			sv.setNhomId(nhom_id);
			this.svRep.update(sv);
		}
	}

}
