package services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import model.ChuyenNganhGv;
import model.GvHuongDan;
import model.NhanVien;
import model.SinhVien;
import model.GiangVienHD;
import repositories.ChuyenNganhGvRepositories;
import repositories.ChuyenNganhRepositories;
import repositories.CoSoRepositories;
import repositories.GvHuongDanRepositories;
import repositories.NhanVienRepositories;
import repositories.NhomRepositories;
import repositories.SinhVienRepositories;
import utils.HibernateUtils;
import java.text.Normalizer;
import java.util.regex.Pattern;

@Log4j2
public class ExcelService {
	ChuyenNganhRepositories cn_rep;
	CoSoRepositories cs_rep;
	SinhVienRepositories sv_rep;
	NhanVienRepositories nv_rep;
	NhomRepositories nhom_rep;
	ChuyenNganhGvRepositories cnGv_rep;
	GvHuongDanRepositories gvHDRep;
	ManagerNVService managerNV;


	private static final Logger LOGGER = LoggerFactory.getLogger(ExcelService.class);

	public ExcelService() {
		this.sv_rep = new SinhVienRepositories();
		this.nhom_rep = new NhomRepositories();
		this.cn_rep = new ChuyenNganhRepositories();
		this.nv_rep = new NhanVienRepositories();
		this.cnGv_rep = new ChuyenNganhGvRepositories();
		this.gvHDRep = new GvHuongDanRepositories();
		this.managerNV = new ManagerNVService();
		this.cs_rep = new CoSoRepositories();
	}

	private void writeExcel(WriteExcelService service, List<Object[]> lst, String path, Object[] header,
			String... str) {
		service.writeExcel(lst, path, header, str);
	}

	private List<Object[]> readExcel(String excelFilePath, Object[] header, String... str) {
		ReadExcelService service = new ReadExcelService();
		return service.readExcel(excelFilePath, header, str);
	}
	private List<Object[]> readExcelNV(String excelFilePath, Object[] header, String... str) {
		ReadExcelService service = new ReadExcelService();
		return service.readExcel(excelFilePath, header, str);
	}

	public void exportThamKhao(String path) {
		List lst = cn_rep.getAll();
		Object[] header = { "ID", "TEN" };
		String sheet_name = "Tham_Khao";
		writeExcel(new ThamKhaoSVService(), lst, path, header, sheet_name);
	}

	public void exportThamKhaoNV(String path) {
		List lst = cn_rep.getAll();
		Object[] header = { "ID", "TEN" };
		String sheet_name = "Tham_Khao";
		writeExcel(new ThamKhaoNVService(), lst, path, header, sheet_name);
	}

	public void exportSinhVien(List<Object[]> lst, String path) {
		Object[] obs = { "Masv", "Hoten", "Khoa", "email", "chuyennganh", "coso", "dodtangky", "trangthai" };
		String sheet_name = "Sinh_Vien";
		writeExcel(new WriteExcelService(), lst, path, obs, sheet_name);
	}

	public List<SinhVien> importSinhVien(String path) {
		int COLUMN_INDEX_MASV = 0;
		int COLUMN_INDEX_HOTEN = 1;
		int COLUMN_INDEX_KHOA = 2;
		int COLUMN_INDEX_EMAIL = 3;
		int COLUMN_INDEX_CHUYENNGANH = 4;
		int COLUMN_INDEX_COSO = 5;
		Object[] obs = { "MASV", "HOTEN", "KHOA", "EMAIL", "CHUYENNGANH", "COSO" };
		String sheet_name = "Sinh_Vien";
		List<Object[]> lst = readExcel(path, obs, sheet_name);
		List<SinhVien> lstSV = new ArrayList<>();
		for (Object[] ob : lst) {
			SinhVien sv = new SinhVien();
			sv.setMaSv((String) ob[COLUMN_INDEX_MASV]);
			sv.setHoTen((String) ob[COLUMN_INDEX_HOTEN]);
			sv.setKhoa(ob[COLUMN_INDEX_KHOA] + "");
			sv.setEmail((String) ob[COLUMN_INDEX_EMAIL]);
			sv.setChuyenNganhId(cn_rep.findId((String) ob[COLUMN_INDEX_CHUYENNGANH]));
			sv.setCoSoId(0);
			sv.setHocKy(6);
			sv.setNhomId(null);
			sv.setDotDangKyId(1);
			sv.setThoiGianDkNhom(null);
			sv.setTrangThai(0);
			lstSV.add(sv);
		}
		return lstSV;
	}

	public void exportNhom(Integer dotdk, Integer chuyen_nganh_id, String path, int co_so) {
		if (chuyen_nganh_id == null) {
			chuyen_nganh_id = 0;
		}
		if (dotdk == null) {
			dotdk = 0;
		}
		String sql = "";
		if (chuyen_nganh_id != 0) {
			sql = " AND cn.id =" + chuyen_nganh_id;
		}
		Object[] obs = { "ID", "TEN DE TAI","MASV","HO TEN SV", "CHUYEN NGANH", "SO THANH VIEN", "GVHD" };
		String sheet_name = "Danh_sach";
		if (dotdk != 0) {
			sql += " AND s.dotDangKyId =" + dotdk;
		}
		List<Object[]> lst = nhom_rep.getListNhom(co_so, sql);

		writeExcel(new WriteExcelService(), lst, path, obs, sheet_name);
	}

	public void exportEx(int co_so_id, Integer chuyen_nganh_id, int trangThai, Integer dot_dk, String path) {
		if (chuyen_nganh_id == null) {
			chuyen_nganh_id = 0;
		}
		if (dot_dk == null) {
			dot_dk = 0;
		}
		String sql = "";
		if (chuyen_nganh_id != 0) {
			sql += " AND cn.id =" + chuyen_nganh_id;
		}
		if (dot_dk != 0) {
			sql += "AND dk.id =" + dot_dk;
		}
		List<Object[]> lst = sv_rep.getExportList(co_so_id, trangThai, sql);
		this.exportSinhVien(lst, path);
	}

	public void insertIntoDS(Integer dotdk, String path, int co_so) {
		if (dotdk == null) {
			return;
		}
		List<SinhVien> lst = this.importSinhVien(path);

//			if(this.sv_rep.findDotDangKyByMaSV(sv.getMaSv())==dotdk || this.sv_rep.findDotDangKyByEmail(sv.getEmail())==dotdk) {
//				continue;
//			}
//			this.sv_rep.insert(sv);
		try (SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
				Session session = sessionFactory.openSession();) {

			LOGGER.info("Statistics enabled = " + sessionFactory.getStatistics().isStatisticsEnabled());

			session.beginTransaction();
			int i = 1;
			final int numberOfRecords = lst.size();
			final int batchSize = 50; // same as the JDBC batch size
			for (SinhVien sv : lst) {
				sv.setDotDangKyId(dotdk);
				sv.setCoSoId(co_so);
//				if(this.sv_rep.findDotDangKyByMaSV(sv.getMaSv())==dotdk || this.sv_rep.findDotDangKyByEmail(sv.getEmail())==dotdk) {
//					continue;
//				}
				session.persist(sv);
				if (i % batchSize == 0 && i != numberOfRecords) {
					LOGGER.info("Flush a batch of INSERT & release memory: {} time(s)", (i / batchSize));
					session.flush();
					session.clear();
				}
				i++;
			}

			LOGGER.info("Flush the last time at commit time");

			session.getTransaction().commit();
		}
	}

	public void deleteDS(Integer dotdk, String path, int co_so) {
		if (dotdk == null) {
			return;
		}
		List<SinhVien> lst = this.importSinhVien(path);
		for (SinhVien sv : lst) {
			sv.setDotDangKyId(dotdk);
			sv.setCoSoId(co_so);
			SinhVien sv_d = this.sv_rep.findByMaSV(sv.getMaSv());
			if (sv_d == null || sv_d.getDotDangKyId() != dotdk) {
				continue;
			}
			this.sv_rep.delete(sv_d);
		}
	}

	// --------------------------------------------------------------------
	public void exportThamKhaoNhanVien(String path) {

		List lst = cn_rep.getAll();
		Object[] header = { "ID", "TEN" };
		String sheet_name = "Tham_Khao";
		writeExcel(new ThamKhaoNVService(), lst, path, header, sheet_name);
	}

	public void exportThamKhaoNhanVien(List<Object[]> lst, String path) {
		Object[] obs = { "MaNv", "Hoten", "SDT", "email", "chuyennganh", "role", "coso", "emailFE"  };
		String sheet_name = "Nhan_Vien";
		writeExcel(new WriteExcelService(), lst, path, obs, sheet_name);
	}

	public List<NhanVien> importNhanVien(String path) {
		int COLUMN_INDEX_HOTEN = 0;
		int COLUMN_INDEX_SDT = 1;
		int COLUMN_INDEX_EMAIL_FPT = 2;
		int COLUMN_INDEX_EMAIL_FE = 3;
		int COLUMN_INDEX_CHUYENNGANH_CHI_DANH_CNBM = 4;
		int COLUMN_INDEX_ROLE = 5;
		int COLUMN_INDEX_COSOID = 6;
		
		Object[] obs = {"HOTEN", "SDT", "email","emailFE", "chuyennganh", "role", "coso" };
		String sheet_name = "Nhan_Vien";
		List<Object[]> lst = readExcel(path, obs, sheet_name);
		List<NhanVien> lstSV = new ArrayList<>();
		for (Object[] ob : lst) {
			NhanVien nv = new NhanVien();
			String email = (String) ob[COLUMN_INDEX_EMAIL_FPT];
			String ma = email.substring(0,email.indexOf("@"));
			nv.setManv(ma);
			nv.setTen((String) ob[COLUMN_INDEX_HOTEN]);
			nv.setSdt(ob[COLUMN_INDEX_SDT] + "");
			nv.setEmailFPT(email);
			nv.setEmailFE((String) ob[COLUMN_INDEX_EMAIL_FE]);
			int role = 0;
			String roleStr = removeAccent(ob[COLUMN_INDEX_ROLE].toString()).replaceAll(" ", "").toLowerCase();

			if (roleStr.equals("admin")) {
				role = 0;
			} else if (roleStr.equals("phongdaotao")) {
				role = 1;
			} else if (roleStr.equals("chunhiembomon")) {
				nv.setBoMonId(cn_rep.findId((String) ob[COLUMN_INDEX_CHUYENNGANH_CHI_DANH_CNBM]));
				role = 2;
			} else if (roleStr.equals("giangvien")) {
				role = 3;
			}
			//nv.setRole(new BigDecimal(ob[COLUMN_INDEX_ROLE].toString()).intValue());
			nv.setRole(role);
			nv.setCoSoId(cs_rep.findByName(ob[COLUMN_INDEX_COSOID] + ""));
			lstSV.add(nv);
		}
		return lstSV;
	}

	public void insertNVtoDS(String path) {		
		List<NhanVien> lst = this.importNhanVien(path);
		try (SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
				Session session = sessionFactory.openSession();) {

			LOGGER.info("Statistics enabled = " + sessionFactory.getStatistics().isStatisticsEnabled());

			session.beginTransaction();
			int i = 1;
			final int numberOfRecords = lst.size();
			final int batchSize = 50; // same as the JDBC batch size
			for (NhanVien nv : lst) {
				if (nv_rep.findByEmai(nv.getEmailFPT().trim()) != null || nv_rep.findByMaNV(nv.getManv().trim())!=null) {
					i++;
					continue;
				}
				session.persist(nv);
				if (i % batchSize == 0 && i != numberOfRecords) {
					LOGGER.info("Flush a batch of INSERT & release memory: {} time(s)", (i / batchSize));
					session.flush();
					session.clear();
				}
				i++;
			}

			LOGGER.info("Flush the last time at commit time");

			session.getTransaction().commit();
		}
	}

	public void deleteNVtoDS(String path) {
		List<NhanVien> lst = this.importNhanVien(path);
		for (NhanVien nv : lst) {
			if (nv_rep.findByEmai(nv.getEmailFPT()) == null || nv_rep.findByMaNV(nv.getManv())==null) {
				continue;
			}
			managerNV.xoaNhanVien(nv_rep.getByMa(nv.getManv()));
			
		}
		
	}

	// giảng viên
	public void exportThamKhaoGV(String path,int bo_mon) {
		List lst = cn_rep.getCNByBMon(bo_mon);
		if(bo_mon==0) {
			lst = cn_rep.getCNByBMon();
		}
		Object[] header = { "ID", "TEN" };
		String sheet_name = "Tham_Khao";
		writeExcel(new ThamKhaoGVService(), lst, path, header, sheet_name);
	}


	public void exportGiangVien(List<Object[]> lst, String path) {
		Object[] obs = {"TENGV","EMAIL FPT","EMAIL FE","SDT","CHUYENNGANH","SỐ NHÓM HƯỚNG DẪN"};
		String sheet_name = "Giang_Vien";
		writeExcel(new WriteExcelService(), lst, path, obs, sheet_name);
	}

	public List<GiangVienHD> importGiangVien(String path,int bo_mon) {
		int COLUMN_INDEX_TENGV = 0;
		int COLUMN_INDEX_EMAIL_FPT = 1;
		int COLUMN_INDEX_EMAIL_FE = 2;
		int COLUMN_INDEX_SDT = 3;
		int COLUMN_INDEX_CN = 4;
		int COLUMN_INDEX_NHOMHD = 5;
		
		Object[] obs = { "TENGV","EMAIL FPT","EMAIL FE","SDT","CHUYENNGANH","SỐ NHÓM HƯỚNG DẪN"};
		String sheet_name = "Giang_Vien";
		List<Object[]> lst = readExcel(path, obs, sheet_name);
		List<GiangVienHD> lstGV = new ArrayList<>();
		for (Object[] ob : lst) {

			GiangVienHD gv = new GiangVienHD();
			String email = (String) ob[COLUMN_INDEX_EMAIL_FPT];
			String ma = email.substring(0,email.indexOf("@"));
			gv.setMaGv(ma);
			gv.setTenGv((String) ob[COLUMN_INDEX_TENGV]);
			gv.setSdt(ob[COLUMN_INDEX_SDT] + "");
			gv.setEmailFPT((String) ob[COLUMN_INDEX_EMAIL_FPT]);
			gv.setEmailFE((String) ob[COLUMN_INDEX_EMAIL_FE]);
			gv.setCnId(cn_rep.findId((String) ob[COLUMN_INDEX_CN]));
			gv.setNhomHD(new BigDecimal(ob[COLUMN_INDEX_NHOMHD].toString()).intValue());
			lstGV.add(gv);
			

		}
		return lstGV;
	}

	public void insertIntoDSGV(String path, int co_so,int bo_mon,int dotdky) {
		
		List<GiangVienHD> lst = this.importGiangVien(path,bo_mon);
		
//		for (ChuyenNganhGv gv : lst) {
//			if (this.cnGv_rep.checkNV(gv.getNhanVienId(), cn_id) == 0) {
//				this.cnGv_rep.insert(gv);
//			}
//		}
		
		insertNV(path, co_so, bo_mon, dotdky);
		insertCNGV(path, co_so, bo_mon, dotdky);
		insertGVHD(path, co_so, bo_mon, dotdky);

	}
	
public void insertNV(String path, int co_so,int bo_mon,int dotdky) {
		
		List<GiangVienHD> lst = this.importGiangVien(path,bo_mon);
		
//		for (ChuyenNganhGv gv : lst) {
//			if (this.cnGv_rep.checkNV(gv.getNhanVienId(), cn_id) == 0) {
//				this.cnGv_rep.insert(gv);
//			}
//		}
		
		try (SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
				Session session = sessionFactory.openSession();) {

			LOGGER.info("Statistics enabled = " + sessionFactory.getStatistics().isStatisticsEnabled());

			session.beginTransaction();
			int i = 1;
			final int numberOfRecords = lst.size();
			final int batchSize = 50; // same as the JDBC batch size
			for (GiangVienHD gv : lst) {
				
				// check nhân viên
				long count =  this.nv_rep.countNV(gv.getMaGv());
				if (count ==0) {
					NhanVien nv = new NhanVien();
					nv.setManv(gv.getMaGv());
					nv.setCoSoId(co_so);
					nv.setEmailFPT(gv.getEmailFPT());
					nv.setEmailFE(gv.getEmailFE());
					nv.setRole(3);
					nv.setSdt(gv.getSdt());
					nv.setTen(gv.getTenGv());
					
					session.persist(nv);
				}
				
				if (i % batchSize == 0 && i != numberOfRecords) {
					LOGGER.info("Flush a batch of INSERT & release memory: {} time(s)", (i / batchSize));
					session.flush();
					session.clear();
				}
				i++;
			}

			LOGGER.info("Flush the last time at commit time");

			session.getTransaction().commit();
		}

	}

public void insertCNGV(String path, int co_so,int bo_mon,int dotdky) {
	
	List<GiangVienHD> lst = this.importGiangVien(path,bo_mon);
	
//	for (ChuyenNganhGv gv : lst) {
//		if (this.cnGv_rep.checkNV(gv.getNhanVienId(), cn_id) == 0) {
//			this.cnGv_rep.insert(gv);
//		}
//	}
	
	try (SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
			Session session = sessionFactory.openSession();) {

		LOGGER.info("Statistics enabled = " + sessionFactory.getStatistics().isStatisticsEnabled());

		session.beginTransaction();
		int i = 1;
		final int numberOfRecords = lst.size();
		final int batchSize = 50; // same as the JDBC batch size
		for (GiangVienHD gv : lst) {
			
			// check nhân viên

			
			// check chuyên ngành gv
			System.out.println(gv.getMaGv());
			int nvId = this.nv_rep.getByMa(gv.getMaGv());
			long count = this.cn_rep.countGV(nvId,gv.getCnId());
			if(count==0) {
				ChuyenNganhGv cn = new ChuyenNganhGv();
				cn.setChuyenNganhId(gv.getCnId());
				cn.setNhanVienId(nvId);
				
				session.persist(cn);
			}
			
			
			
			if (i % batchSize == 0 && i != numberOfRecords) {
				LOGGER.info("Flush a batch of INSERT & release memory: {} time(s)", (i / batchSize));
				session.flush();
				session.clear();
			}
			i++;
		}

		LOGGER.info("Flush the last time at commit time");

		session.getTransaction().commit();
	}

}

public void insertGVHD(String path, int co_so,int bo_mon,int dotdky) {
	
	List<GiangVienHD> lst = this.importGiangVien(path,bo_mon);
	
//	for (ChuyenNganhGv gv : lst) {
//		if (this.cnGv_rep.checkNV(gv.getNhanVienId(), cn_id) == 0) {
//			this.cnGv_rep.insert(gv);
//		}
//	}
	
	try (SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
			Session session = sessionFactory.openSession();) {

		LOGGER.info("Statistics enabled = " + sessionFactory.getStatistics().isStatisticsEnabled());

		session.beginTransaction();
		int i = 1;
		final int numberOfRecords = lst.size();
		final int batchSize = 50; // same as the JDBC batch size
		for (GiangVienHD gv : lst) {
			
			
			// check gv hướng dẫn
			int nvId = this.nv_rep.getByMa(gv.getMaGv());
			long count = this.gvHDRep.countGV(nvId,gv.getCnId());
			if (count ==0) {
				GvHuongDan gvhd = new GvHuongDan();
				gvhd.setChuyenNganhId(gv.getCnId());
				gvhd.setDotDangKyId(dotdky);
				gvhd.setNhanVienId(nvId);
				gvhd.setSoNhomHuongDan(gv.getNhomHD());
				
				session.persist(gvhd);
			}
			
			if (i % batchSize == 0 && i != numberOfRecords) {
				LOGGER.info("Flush a batch of INSERT & release memory: {} time(s)", (i / batchSize));
				session.flush();
				session.clear();
			}
			i++;
		}

		LOGGER.info("Flush the last time at commit time");

		session.getTransaction().commit();
	}

}

	public void deleteDSGV(String path,  int co_so,int bo_mon) {

		List<GiangVienHD> lst = this.importGiangVien(path,bo_mon);
		for (GiangVienHD gv : lst) {
			
			int nvId = this.nv_rep.getByMa(gv.getMaGv());
			long count = this.gvHDRep.countGV(nvId,gv.getCnId());
			if (count !=0) {
			GvHuongDan gvhd = this.gvHDRep.getGVHDByMaCN(nvId,gv.getCnId());
			this.gvHDRep.delete(gvhd);
			}
			

		}
	}

	public void exportNhomCNBM(Integer dotdk, List<Integer> chuyen_nganh_id, String path, int co_so) {

		if (dotdk == null) {
			dotdk = 0;
		}
		String sql = "";

		Object[] obs = { "ID", "TEN DE TAI", "MASV", "HO TEN", "CHUYEN NGANH", "SO THANH VIEN", "GVHD" };
		String sheet_name = "Danh_sach";
		if (dotdk != 0) {
			sql += " AND s.dotDangKyId =" + dotdk;
		}
		String cnSql = "";
		if (chuyen_nganh_id != null) {
			cnSql = " AND  (cn.id = " + chuyen_nganh_id.get(0);
			for (Integer x = 1; x < chuyen_nganh_id.size(); x++) {
				cnSql += "or  cn.id =" + chuyen_nganh_id.get(x);
			}

		}
		sql += cnSql + ")";

		List<Object[]> lst = nhom_rep.getListNhom(co_so, sql);

		writeExcel(new WriteExcelService(), lst, path, obs, sheet_name);
	}

	public void exportExCNBM(int co_so_id, List<Integer> chuyen_nganh_id, int trangThai, Integer dot_dk, String path) {
		if (dot_dk == null) {
			dot_dk = 0;
		}
		String sql = "";

		if (dot_dk != 0) {
			sql += "AND dk.id =" + dot_dk;
		}
		String cnSql = "";
		if (chuyen_nganh_id != null) {
			cnSql = " AND  (cn.id = " + chuyen_nganh_id.get(0);
			for (Integer x = 1; x < chuyen_nganh_id.size(); x++) {
				cnSql += " or  cn.id =" + chuyen_nganh_id.get(x);
			}

		}
		sql += cnSql + ")";
		List<Object[]> lst = sv_rep.getExportList(co_so_id, trangThai, sql);
		this.exportSinhVien(lst, path);
	}
	public String removeAccent(String s) {

		String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(temp).replaceAll("");
	

}
}
