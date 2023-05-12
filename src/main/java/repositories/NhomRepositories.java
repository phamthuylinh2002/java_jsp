package repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Nhom;
import model.ThongTinNhom;
import utils.UtilsJDBC;

public class NhomRepositories extends Repositeries<Nhom> {
	private EntityManager em;

	public NhomRepositories() {
		super(Nhom.class);
		this.em = UtilsJDBC.getEntityManager();
	}

	public Long getSoLuongNhom(int id) {
		Long soLuong;
		String sql = "SELECT COUNT(*) FROM SinhVien WHERE nhomId=:nhom_id ";
		TypedQuery<Long> query = em.createQuery(sql, Long.class);
		query.setParameter("nhom_id", id);
		soLuong = query.getSingleResult();
		return soLuong;
	}
	
	public Nhom findByNhomTruong(String nhomTruong) {
		String hql="SELECT n From Nhom n WHERE nhomTruongId =: nhomTruong";
		TypedQuery<Nhom> query = em.createQuery(hql,Nhom.class).setParameter("nhomTruong", nhomTruong);
		
		return query.getSingleResult();
	}

	public Nhom findByCode(String code) {
		try {
			String sql = "SELECT u FROM Nhom u WHERE u.code=:code";
			TypedQuery<Nhom> query = em.createQuery(sql, Nhom.class);
			query.setParameter("code", code);
			Nhom nhom = query.getSingleResult();
			return nhom;

		} catch (Exception e) {
			return null;
		}
	}

	public Integer getSoNhomCuaGV(String gv_id) {

		String hql = "SELECT COUNT(*) FROM Nhom n WHERE n.giangVienHdId=:gv_id ";

		TypedQuery<Integer> query = em.createQuery(hql, Integer.class);
		query.setParameter("gv_id", gv_id);
		List<Integer> lst = query.getResultList();
		for (Integer x : lst) {
			return x;
		}
		return null;
	}

	public List<ThongTinNhom> getAllNhomByCoSoChuyenNganh(int co_so_id, int chuyen_nganh_id, int dot_dang_ki) {

		String hql = "SELECT n.id, n.tenDeTai, s.chuyenNganhId FROM Nhom n "
				+ "INNER JOIN SinhVien s ON n.nhomTruongId = s.maSv " + "where n.dotDangKyId = :dot_dang_ki "
				+ "AND s.coSoId = :co_so_id " + "AND s.chuyenNganhId = :chuyen_nganh_id and n.code = null";

		TypedQuery<Object[]> query = em.createQuery(hql, Object[].class);

		query.setParameter("co_so_id", co_so_id);
		query.setParameter("chuyen_nganh_id", chuyen_nganh_id);
		query.setParameter("dot_dang_ki", dot_dang_ki);

		List<Object[]> lst = query.getResultList();

		List<ThongTinNhom> lstNhom = new ArrayList<>();

		for (Object[] x : lst) {
			ThongTinNhom nhom = new ThongTinNhom();
			nhom.setId((int) x[0]);
			nhom.setTenDeTai(x[1] + "");
			nhom.setChuyenNganh(x[2] + "");
			nhom.setSoLuongThanhVien(getSoLuongNhom((int) x[0]));

			lstNhom.add(nhom);
		}
		return lstNhom;
	}
	public List<Object[]> getAllNhomByCoSoBoMon(int co_so_id, int boMonId, int dot_dang_ki) {

		String hql = "SELECT n.id, n.tenDeTai, cn.ten, (SELECT COUNT(*) FROM SinhVien sv2 WHERE sv2.nhomId= n.id) FROM Nhom n "
				+ "INNER JOIN SinhVien s ON n.nhomTruongId = s.maSv "
				+ "INNER JOIN ChuyenNganh cn ON cn.id = s.chuyenNganhId "
				+ "INNER JOIN BoMon bm on cn.boMonId = bm.id " 
				+ "where n.dotDangKyId = :dot_dang_ki "
				+ "AND s.coSoId = :co_so_id " 
				+ "AND cn.boMonId = :boMonId and n.code = null "
				+ "AND (SELECT COUNT(*) FROM SinhVien sv2 WHERE sv2.nhomId= n.id) <7";

		TypedQuery<Object[]> query = em.createQuery(hql, Object[].class);

		query.setParameter("co_so_id", co_so_id);
		query.setParameter("boMonId", boMonId);
		query.setParameter("dot_dang_ki", dot_dang_ki);
		List<Object[]> lst = query.getResultList();
		return lst;
	}

	public Nhom findNhomId(Integer id) {
		String sql = "SELECT u FROM Nhom u WHERE u.id=:id";
		TypedQuery<Nhom> query = em.createQuery(sql, Nhom.class);
		query.setParameter("id", id);
		Nhom nhom = query.getSingleResult();
		return nhom;
	}
	public List<Nhom> getListNhomByCoSo(Integer dotDK_id, Integer coso_id) {
		String sql = "SELECT u FROM Nhom u "
				+ "INNER JOIN DotDangKy d ON u.dotDangKyId = d.id "
				+ "where d.coSoId = :coSoId AND u.dotDangKyId = :dotDangKyId";
		TypedQuery<Nhom> query = em.createQuery(sql, Nhom.class);
		query.setParameter("coSoId", coso_id);
		query.setParameter("dotDangKyId", dotDK_id);

		List<Nhom> lst = query.getResultList();
		return lst;
	}
	public List<Nhom> getListNhomByCoSo(Integer dotDK_id, Integer coso_id,Integer gv_hd_id) {
		String sql = "SELECT u FROM Nhom u "
				+ "INNER JOIN DotDangKy d ON u.dotDangKyId = d.id "
				+ "where d.coSoId = :coSoId AND u.dotDangKyId = :dotDangKyId and u.giangVienHdId =:gv_hd_id";
		TypedQuery<Nhom> query = em.createQuery(sql, Nhom.class);
		query.setParameter("coSoId", coso_id);
		query.setParameter("dotDangKyId", dotDK_id);
		query.setParameter("gv_hd_id", gv_hd_id);
		List<Nhom> lst = query.getResultList();
		return lst;
	}

	
	public Long getSLNhomByCN(Integer dotDK_id, Integer coso_id,Integer cn_id) {
		String sql = "select COUNT(1) from Nhom n join SinhVien s on n.nhomTruongId = s.maSv   "
				+ "WHERE s.chuyenNganhId=:cn_id and s.coSoId =:coso_id AND n.dotDangKyId= :dotDK_id"; 
		TypedQuery<Long> query = em.createQuery(sql, Long.class);
		query.setParameter("coso_id", coso_id);
		query.setParameter("dotDK_id", dotDK_id);
		query.setParameter("cn_id", cn_id);
		
		return query.getSingleResult();
	}


	public List<ThongTinNhom> getAllNhomByGVHuong_dan(Integer co_so_id, Integer dot_dang_ki, Integer gv_id) {

		String hql = "SELECT n.id, n.tenDeTai, s.chuyenNganhId FROM Nhom n "
				+ "INNER JOIN SinhVien s ON n.nhomTruongId = s.maSv "
				+ "where n.dotDangKyId = :dot_dang_ki "
				+ "AND s.coSoId = :co_so_id "
				+ "AND n.giangVienHdId = :giangVienHdId";

		TypedQuery<Object[]> query = em.createQuery(hql, Object[].class);

		query.setParameter("co_so_id", co_so_id);
		query.setParameter("giangVienHdId", gv_id);
		query.setParameter("dot_dang_ki", dot_dang_ki);

		List<Object[]> lst = query.getResultList();

		List<ThongTinNhom> lstNhom = new ArrayList<>();

		for (Object[] x : lst) {
			ThongTinNhom nhom = new ThongTinNhom();
			nhom.setId((int) x[0]);
			nhom.setTenDeTai(x[1] + "");
			nhom.setChuyenNganh(x[2] + "");
			nhom.setSoLuongThanhVien(getSoLuongNhom((int) x[0]));

			lstNhom.add(nhom);
		}
		return lstNhom;
	}
	public List<Object[]> getExportFile(Integer co_so_id,Integer dot_dang_ky){
		String hql = "SELECT n.id,n.tenDeTai,n.code, FROM NHOM n";

		List<Object[]> lst = new ArrayList<>();
		return lst;
	}

	public List<Object[]> getListNhom(Integer coso, Integer dot_dk){
		String hql ="SELECT n.id, n.tenDeTai,n.code,cn.ten, (select COUNT(*) from SinhVien s WHERE s.nhomId = n.id),nv.ten  "
				+ "from Nhom n join SinhVien s on s.maSv = n.nhomTruongId "
				+ "join GvHuongDan gv on gv.id = n.giangVienHdId "
				+ "JOIN NhanVien nv on nv.id = gv.nhanVienId "
				+ "JOIN ChuyenNganh cn on cn.id = gv.chuyenNganhId "
				+ "where s.coSoId = :coso and s.dotDangKyId = :dot_dky "
				+ " order by n.id";
		TypedQuery<Object[]> query = em.createQuery(hql,Object[].class);
		query.setParameter("coso", coso);
		query.setParameter("dot_dky", dot_dk);
		List<Object[]> lst = query.getResultList();
		return lst;
	}
	public List<Object[]> getListNhom(Integer coso, Integer dot_dk, int GVHD){
		String hql ="SELECT n.id, n.tenDeTai,n.code,cn.ten, (select COUNT(*) from SinhVien s WHERE s.nhomId = n.id),nv.ten  "
				+ "from Nhom n join SinhVien s on s.maSv = n.nhomTruongId "
				+ "join GvHuongDan gv on gv.id = n.giangVienHdId "
				+ "JOIN NhanVien nv on nv.id = gv.nhanVienId "
				+ "JOIN ChuyenNganh cn on cn.id = gv.chuyenNganhId "
				+ "where s.coSoId = :coso and s.dotDangKyId = :dot_dky "
				+ " AND gv.id= :GVHD";
		TypedQuery<Object[]> query = em.createQuery(hql,Object[].class);
		query.setParameter("coso", coso);
		query.setParameter("dot_dky", dot_dk);
		query.setParameter("GVHD", GVHD);
		List<Object[]> lst = query.getResultList();
		return lst;
	}

	public List<Object[]> getListNhomChiTiet(Integer coso, Integer dot_dk, Integer NhomID){
		String queryCoSo = "s.coSoId = :coso ";
		if (coso ==0) {
			queryCoSo = "0 = 0 ";
		}
		String hql ="SELECT n.id, n.tenDeTai,n.code,cn.ten, (select COUNT(*) from SinhVien s WHERE s.nhomId = n.id),nv.ten  "
				+ "from Nhom n join SinhVien s on s.maSv = n.nhomTruongId "
				+ "join GvHuongDan gv on gv.id = n.giangVienHdId "
				+ "JOIN NhanVien nv on nv.id = gv.nhanVienId "
				+ "JOIN ChuyenNganh cn on cn.id = gv.chuyenNganhId "
				+ "where "+ queryCoSo + "and s.dotDangKyId = :dot_dky "
				+ " AND n.id= :NhomID";
		TypedQuery<Object[]> query = em.createQuery(hql,Object[].class);
		if (coso !=0 ) {
			query.setParameter("coso", coso);
		}
		query.setParameter("dot_dky", dot_dk);
		query.setParameter("NhomID", NhomID);
		List<Object[]> lst = query.getResultList();
		return lst;
	}
	public List<Object[]> getListNhomCN(Integer coso, Integer dot_dk, Integer cn_id){
		String hql ="SELECT n.id, n.tenDeTai,n.code,cn.ten, (select COUNT(*) from SinhVien s WHERE s.nhomId = n.id),nv.ten  "
				+ "from Nhom n join SinhVien s on s.maSv = n.nhomTruongId "
				+ "join GvHuongDan gv on gv.id = n.giangVienHdId "
				+ "JOIN NhanVien nv on nv.id = gv.nhanVienId "
				+ "JOIN ChuyenNganh cn on cn.id = gv.chuyenNganhId "
				+ "where s.coSoId = :coso and s.dotDangKyId = :dot_dky "
				+ " AND s.chuyenNganhId = :cn_id";
		TypedQuery<Object[]> query = em.createQuery(hql,Object[].class);
		query.setParameter("coso", coso);
		query.setParameter("dot_dky", dot_dk);
		query.setParameter("cn_id", cn_id);
		List<Object[]> lst = query.getResultList();
		return lst;
	}
	public List<Object[]> getListNhom(Integer coso,String...sql){
		String hql ="SELECT n.id, n.tenDeTai, s.maSv, s.hoTen,cn.ten, (select COUNT(*) from SinhVien s WHERE s.nhomId = n.id),nv.ten  "
				+ "from Nhom n join SinhVien s on s.nhomId = n.id "
				+ "join GvHuongDan gv on gv.id = n.giangVienHdId "
				+ "JOIN NhanVien nv on nv.id = gv.nhanVienId "
				+ "JOIN ChuyenNganh cn on cn.id = gv.chuyenNganhId "
				+ "where s.coSoId = :coso "+sql[0]
				+ " order by n.id";
		TypedQuery<Object[]> query = em.createQuery(hql,Object[].class);
		query.setParameter("coso", coso);
		List<Object[]> lst = query.getResultList();

		return lst;
	}
	public List<Object[]> getListNhomTimKiem(int coSo_id, int chuyenNganhId, int dotDK_id, int giangVienHD, long sltv){
		String queryDotDK = "s.dotDangKyId= :dotDangKyId ";
		String queryCosoId = "s.coSoId = :coSoId ";
		String queryChuyenNganhId = "cn.id = :chuyenNganhId ";
		String queryGV_HD = "gv.id = :giangVienHD ";
		String querySLTV = "(select COUNT(*) from SinhVien s WHERE s.nhomId = n.id) = :sltv ";

		if (dotDK_id == 0) {
			queryDotDK = "0 = 0 ";
		}
		if (coSo_id == 0) {
			queryCosoId = "0 = 0 ";
		}
		if (chuyenNganhId == 0) {
			queryChuyenNganhId = "0 = 0 ";
		}
		if (giangVienHD == 0) {
			queryGV_HD = "0 = 0 ";
		}
		if (sltv == 0) {
			querySLTV = "0 = 0 ";
		}


		String hql ="SELECT n.id, n.tenDeTai,n.code,cn.ten, (select COUNT(*) from SinhVien s WHERE s.nhomId = n.id),nv.ten  "
				+ "from Nhom n join SinhVien s on s.maSv = n.nhomTruongId "
				+ "join GvHuongDan gv on gv.id = n.giangVienHdId "
				+ "JOIN NhanVien nv on nv.id = gv.nhanVienId "
				+ "JOIN ChuyenNganh cn on cn.id = gv.chuyenNganhId "
				+ "WHERE " + queryGV_HD
				+ " AND " + queryDotDK
				+ " AND " + queryCosoId
				+ " AND " + queryChuyenNganhId
				+ " AND " + querySLTV;
		TypedQuery<Object[]> query = em.createQuery(hql,Object[].class);
		if (dotDK_id != 0) {
			query.setParameter("dotDangKyId", dotDK_id);
		}
		if (coSo_id != 0) {
			query.setParameter("coSoId", coSo_id);
		}
		if (chuyenNganhId != 0) {
			query.setParameter("chuyenNganhId", chuyenNganhId);
		}
		if (giangVienHD != 0) {
			query.setParameter("giangVienHD", giangVienHD);
		}
		if (sltv != 0 && sltv>0) {
			query.setParameter("sltv", sltv);
		}

		List<Object[]> lst = query.getResultList();
		return lst;
	}
	public List<Object[]> getListNhomByGVHD(Integer coso, Integer dot_dk, Integer idNV){
		String hql ="SELECT n.id, n.tenDeTai,n.code,cn.ten, (select COUNT(*) from SinhVien s WHERE s.nhomId = n.id),nv.ten  "
				+ "from Nhom n join SinhVien s on s.maSv = n.nhomTruongId "
				+ "join GvHuongDan gv on gv.id = n.giangVienHdId "
				+ "JOIN NhanVien nv on nv.id = gv.nhanVienId "
				+ "JOIN ChuyenNganh cn on cn.id = gv.chuyenNganhId "
				+ "where s.coSoId = :coso and s.dotDangKyId = :dot_dky "
				+ " AND nv.id = :idNV";
		TypedQuery<Object[]> query = em.createQuery(hql,Object[].class);
		query.setParameter("coso", coso);
		query.setParameter("dot_dky", dot_dk);
		query.setParameter("idNV", idNV);
		List<Object[]> lst = query.getResultList();
		return lst;
	}
	
	public long checkCode(String code) {
		String hql="SELECT count(1) from Nhom n where n.code = :code";
		
		TypedQuery<Long> query = em.createQuery(hql,Long.class).setParameter("code", code);
		
		return query.getSingleResult();
		
	}

}
