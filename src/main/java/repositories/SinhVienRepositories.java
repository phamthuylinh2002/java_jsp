package repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.SinhVien;
import utils.UtilsJDBC;

public class SinhVienRepositories extends Repositeries<SinhVien> {
	private EntityManager em;

	public SinhVienRepositories() {
		super(SinhVien.class);
		this.em = UtilsJDBC.getEntityManager();
	}

	public SinhVien findByEmai(String email) {
		String hql = "SELECT s FROM SinhVien s WHERE s.email = :email ORDER BY s.id DESC";
		TypedQuery<SinhVien> query = em.createQuery(hql, SinhVien.class);
		query.setParameter("email", email);
		List<SinhVien> lst = query.getResultList();
		for (SinhVien sv : lst) {
			return sv;
		}
		return null;
	}

	public SinhVien findByMaSV(String masv) {
		String hql = "SELECT s FROM SinhVien s WHERE s.maSv = :masv ORDER BY s.id DESC";
		TypedQuery<SinhVien> query = em.createQuery(hql, SinhVien.class);
		query.setParameter("masv", masv);
		List<SinhVien> lst = query.getResultList();
		for (SinhVien sv : lst) {
			return sv;
		}
		return null;
	}

	public SinhVien findById(Integer id) {
		String hql = "SELECT s FROM SinhVien s WHERE s.id = :id";
		TypedQuery<SinhVien> query = em.createQuery(hql, SinhVien.class);
		query.setParameter("id", id);
		List<SinhVien> lst = query.getResultList();
		for (SinhVien sv : lst) {
			return sv;
		}
		return null;
	}

	public Integer findChuyenDeByMaSV(String masv) {

		String hql = "SELECT s.chuyenNganhId FROM SinhVien s WHERE s.maSv = :masv";
		TypedQuery<Integer> query = em.createQuery(hql, Integer.class);
		query.setParameter("masv", masv);
		List<Integer> lst = query.getResultList();
		for (Integer x : lst) {
			return x;
		}
		return null;
	}

	public Integer findDotDangKyByMaSV(String masv) {

		String hql = "SELECT s.dotDangKyId FROM SinhVien s WHERE s.maSv = :masv";
		TypedQuery<Integer> query = em.createQuery(hql, Integer.class);
		query.setParameter("masv", masv);
		List<Integer> lst = query.getResultList();
		for (Integer x : lst) {
			return x;
		}
		return null;
	}
	public Integer findDotDangKyByEmail(String email) {

		String hql = "SELECT s.dotDangKyId FROM SinhVien s WHERE s.email = :email";
		TypedQuery<Integer> query = em.createQuery(hql, Integer.class);
		query.setParameter("email", email);
		List<Integer> lst = query.getResultList();
		for (Integer x : lst) {
			return x;
		}
		return null;
	}

	public List<Object[]> selectSVByNhomIdOb(int nhom_id) {
		String hql = "SELECT s.hoTen, s.maSv, cn.ten FROM SinhVien s "
				+ "inner join ChuyenNganh cn on cn.id = s.chuyenNganhId "
				+ "WHERE s.nhomId = :nhom_id";
		TypedQuery<Object[]> query = em.createQuery(hql, Object[].class).setParameter("nhom_id", nhom_id);

		List<Object[]> lst = query.getResultList();

		return lst;
	}
	public List<SinhVien> selectSVByNhomId(int nhom_id) {
		String hql = "SELECT s FROM SinhVien s "
				+ "WHERE s.nhomId = :nhom_id";
		TypedQuery<SinhVien> query = em.createQuery(hql, SinhVien.class).setParameter("nhom_id", nhom_id);

		List<SinhVien> lst = query.getResultList();

		return lst;
	}
	public List<Object[]> getSVByNhomId(int nhom_id) {
		String hql = "SELECT s.id, s.maSv, s.hoTen,cn.ten, s.trangThai, s.khoa, s.email, s.hocKy FROM SinhVien s "
				+ "inner join ChuyenNganh cn on cn.id = s.chuyenNganhId"
				+ " WHERE s.nhomId = :nhom_id";
		TypedQuery<Object[]> query = em.createQuery(hql, Object[].class).setParameter("nhom_id", nhom_id);

		List<Object[]> lst = query.getResultList();

		return lst;
	}

	public List<SinhVien> getListSV_byIdNhom(Integer id) {
		String hql = "SELECT s FROM SinhVien s WHERE s.nhomId=:nhomId";

		TypedQuery<SinhVien> query = em.createQuery(hql, SinhVien.class);
		query.setParameter("nhomId", id);
		List<SinhVien> lst = query.getResultList();

		return lst;
	}

	public List<SinhVien> getListSV_DaDangKy(Integer dotDK_id, Integer coSo_id) {
		String hql = "SELECT s FROM SinhVien s WHERE s.dotDangKyId=:dotDangKyId "
				+ "AND s.trangThai = 1 AND s.coSoId = :coSoId";

		TypedQuery<SinhVien> query = em.createQuery(hql, SinhVien.class);
		query.setParameter("dotDangKyId", dotDK_id);
		query.setParameter("coSoId", coSo_id);
		List<SinhVien> lst = query.getResultList();
		return lst;
	}
	public List<SinhVien> getListSV_DaDangKy(Integer dotDK_id, Integer coSo_id,Integer cn_id) {
		String hql = "SELECT s FROM SinhVien s WHERE s.dotDangKyId=:dotDangKyId "
				+ "AND s.trangThai = 1 AND s.coSoId = :coSoId and s.chuyenNganhId = :cn_id";

		TypedQuery<SinhVien> query = em.createQuery(hql, SinhVien.class);
		query.setParameter("dotDangKyId", dotDK_id);
		query.setParameter("coSoId", coSo_id);
		query.setParameter("cn_id", cn_id);
		List<SinhVien> lst = query.getResultList();
		return lst;
	}

	public List<SinhVien> getListSV_ChuaDangKy(Integer dotDK_id, Integer coSo_id) {
		String hql = "SELECT s FROM SinhVien s WHERE s.dotDangKyId =:dotDangKyId "
				+ "AND s.trangThai = 0 AND s.coSoId = :coSoId";

		TypedQuery<SinhVien> query = em.createQuery(hql, SinhVien.class);
		query.setParameter("dotDangKyId", dotDK_id);
		query.setParameter("coSoId", coSo_id);
		List<SinhVien> lst = query.getResultList();
		return lst;
	}
	public List<SinhVien> getListSV_ChuaDangKy(Integer dotDK_id, Integer coSo_id, Integer chuyen_nganh_id) {
		String hql = "SELECT s FROM SinhVien s WHERE s.dotDangKyId =:dotDangKyId "
				+ "AND s.trangThai = 0 AND s.coSoId = :coSoId AND s.chuyenNganhId = :chuyen_nganh_id";

		TypedQuery<SinhVien> query = em.createQuery(hql, SinhVien.class);
		query.setParameter("dotDangKyId", dotDK_id);
		query.setParameter("coSoId", coSo_id);
		query.setParameter("chuyen_nganh_id", chuyen_nganh_id);
		List<SinhVien> lst = query.getResultList();
		return lst;
	}

	public List<String> getKhoaSV() {
		String hql = "SELECT DISTINCT s.khoa FROM SinhVien s";

		TypedQuery<String> query = em.createQuery(hql, String.class);
		List<String> lst = query.getResultList();
		return lst;
	}
	public List<Object[]> getExportList(int coso,int trangthai,String...sql){
		String hql ="SELECT sv.maSv,sv.hoTen,sv.khoa,sv.email,cn.ten,cs.ten,dk.hocKy,sv.trangThai "
				+ "FROM SinhVien sv join CoSo cs on sv.coSoId= cs.id "
				+ "join ChuyenNganh cn on sv.chuyenNganhId= cn.id "
				+ "join DotDangKy dk on dk.id=sv.dotDangKyId "
				+ " WHERE sv.trangThai =:trangthai and sv.coSoId=:coso "+sql[0];
		TypedQuery<Object[]> query = em.createQuery(hql, Object[].class);
		query.setParameter("trangthai", trangthai);
		query.setParameter("coso",coso);
		List<Object[]> lst = query.getResultList();
		return lst;
	}

	public List<Object[]> getListSV(int coso){
		String hql ="SELECT s.id, s.maSv, s.hoTen, s.email, s.khoa, cn.ten, cn.id FROM SinhVien s join ChuyenNganh cn on s.chuyenNganhId = cn.id "
				+ "where s.coSoId =: coso";
		TypedQuery<Object[]> query = em.createQuery(hql,Object[].class);
		query.setParameter("coso", coso);
		List<Object[]> lst = query.getResultList();
		return lst;
	}
	public List<Object[]> getListSV(){
		String hql ="SELECT s.id, s.maSv, s.hoTen, s.email, s.khoa, cn.ten, cn.id FROM SinhVien s join ChuyenNganh cn on s.chuyenNganhId = cn.id";
		TypedQuery<Object[]> query = em.createQuery(hql,Object[].class);
		List<Object[]> lst = query.getResultList();
		return lst;
	}

	public List<Object[]> getListSVTT(int coso, int trangThai){
		String hql ="SELECT s.id, s.maSv, s.hoTen, s.email, s.khoa, cn.ten, cn.id"
				+ "	FROM SinhVien s join ChuyenNganh cn on s.chuyenNganhId = cn.id "
				+ "	where s.coSoId = :coso and s.trangThai =: trangThai";
		TypedQuery<Object[]> query = em.createQuery(hql, Object[].class);

		query.setParameter("coso", coso);
		query.setParameter("trangThai", trangThai);
		List<Object[]> lst = query.getResultList();
		return lst;
	}
	public List<Object[]> getListSVTT(int trangThai){
		String hql ="SELECT s.id, s.maSv, s.hoTen, s.email, s.khoa, cn.ten, cn.id"
				+ "	FROM SinhVien s join ChuyenNganh cn on s.chuyenNganhId = cn.id "
				+ "	where s.trangThai =: trangThai";
		TypedQuery<Object[]> query = em.createQuery(hql, Object[].class);
		query.setParameter("trangThai", trangThai);
		List<Object[]> lst = query.getResultList();
		return lst;
	}
	
	public List<Object[]> getListSVTT(int coso,int dotdk, int trangThai,int cn_id){
		String hql ="SELECT s.id, s.maSv, s.hoTen, s.email, s.khoa, cn.ten, cn.id"
				+ "	FROM SinhVien s join ChuyenNganh cn on s.chuyenNganhId = cn.id "
				+ "	where s.coSoId = :coso and s.trangThai =: trangThai and s.chuyenNganhId = :cn_id and s.dotDangKyId =:dotdk";
		TypedQuery<Object[]> query = em.createQuery(hql, Object[].class);

		query.setParameter("coso", coso);
		query.setParameter("trangThai", trangThai);
		query.setParameter("cn_id", cn_id);
		query.setParameter("dotdk", dotdk);
		List<Object[]> lst = query.getResultList();
		return lst;
	}
	
	public List<Object[]> getListSVDKChuaNhom(int coso,int dotdk, int trangThai,int cn_id){
		String hql ="SELECT s.id, s.maSv, s.hoTen, s.email, s.khoa, cn.ten, cn.id"
				+ "	FROM SinhVien s join ChuyenNganh cn on s.chuyenNganhId = cn.id "
				+ "	where s.coSoId = :coso and s.trangThai =: trangThai and s.chuyenNganhId = :cn_id and s.nhomId = null"
				+ " and s.dotDangKyId =:dotdk";
		TypedQuery<Object[]> query = em.createQuery(hql, Object[].class);

		query.setParameter("coso", coso);
		query.setParameter("trangThai", trangThai);
		query.setParameter("cn_id", cn_id);
		query.setParameter("dotdk", dotdk);
		
		List<Object[]> lst = query.getResultList();
		return lst;
	}

	public List<Object[]> getListSV_TimKiem(Integer dotDK_id, Integer coSo_id, Integer chuyenNganhId, String khoa, Integer trangThai) {
		String queryDotDK = "s.dotDangKyId= :dotDangKyId ";
		String queryCosoId = "s.coSoId = :coSoId ";
		String queryChuyenNganhId = "s.chuyenNganhId = :chuyenNganhId ";
		String queryKhoa = "s.khoa like :khoa ";

		if (dotDK_id == 0) {
			queryDotDK = "0 = 0 ";
		}
		if (coSo_id == 0) {
			queryCosoId = "0 = 0 ";
		}
		if (chuyenNganhId == 0) {
			queryChuyenNganhId = "0 = 0 ";
		}
		if (khoa.trim().equals("0")) {
			queryKhoa = "0 = 0 ";
		}
		String hql = "SELECT s.id, s.maSv, s.hoTen, s.email, s.khoa, cn.ten, cn.id "
				+ "FROM SinhVien s inner join ChuyenNganh cn on s.chuyenNganhId = cn.id "
				+ "WHERE s.trangThai = :trangThai "
				+ "and " + queryDotDK
				+ "and "+ queryCosoId
				+ "and "+ queryKhoa
				+ "and " +queryChuyenNganhId;
		TypedQuery<Object[]> query = em.createQuery(hql, Object[].class);
		if (dotDK_id != 0) {
			query.setParameter("dotDangKyId", dotDK_id);
		}
		if (coSo_id != 0) {
			query.setParameter("coSoId", coSo_id);
		}
		if (chuyenNganhId != 0) {
			query.setParameter("chuyenNganhId", chuyenNganhId);
		}
		if (!khoa.trim().equals("0")) {
			query.setParameter("khoa", khoa);
		}
		
		query.setParameter("trangThai", trangThai);

		List<Object[]> lst = query.getResultList();
		return lst;
	}
	

	public List<Object[]> getListSV_TimKiemFull(Integer dotDK_id, Integer coSo_id, Integer chuyenNganhId, Integer trangThai) {
		String queryDotDK = "s.dotDangKyId= :dotDangKyId ";
		String queryCosoId = "s.coSoId = :coSoId ";
		String queryChuyenNganhId = "s.chuyenNganhId = :chuyenNganhId ";

		if (dotDK_id == 0) {
			queryDotDK = "0 = 0 ";
		}
		if (coSo_id == 0) {
			queryCosoId = "0 = 0 ";
		}
		if (chuyenNganhId == 0) {
			queryChuyenNganhId = "0 = 0 ";
		}
		String hql = "SELECT s.id, s.maSv, s.hoTen, s.email, s.khoa, cn.ten, cn.id "
				+ "FROM SinhVien s inner join ChuyenNganh cn on s.chuyenNganhId = cn.id "
				+ "WHERE s.trangThai = :trangThai "
				+ "and " + queryDotDK
				+ "and "+ queryCosoId
				+ "and   s.khoa like :khoa "
				+ "and " +queryChuyenNganhId;
		TypedQuery<Object[]> query = em.createQuery(hql, Object[].class);
		if (dotDK_id != 0) {
			query.setParameter("dotDangKyId", dotDK_id);
		}
		if (coSo_id != 0) {
			query.setParameter("coSoId", coSo_id);
		}
		if (chuyenNganhId != 0) {
			query.setParameter("chuyenNganhId", chuyenNganhId);
		}

		query.setParameter("trangThai", trangThai);

		List<Object[]> lst = query.getResultList();
		return lst;
	}
	

	public List<Object[]> getListSV_TimKiem(int coso, int dotDangKyId, int chuyenNganhId,String key){
		String queryChuyenNganhId = "cn.id = :chuyenNganhId ";
		String queryDotDK = "s.dotDangKyId= :dotDangKyId ";
		String keyword ="(s.maSv like :key or s.hoTen like :key or s.email like :key)";
		String queryCoSo = "s.coSoId =: coso ";
		if (chuyenNganhId == 0) {
			queryChuyenNganhId = "0 = 0 ";
		}
		if (dotDangKyId == 0) {
			queryDotDK = "0 = 0 ";
		}
		if (coso == 0) {
			queryCoSo = "0 = 0 ";
		}
		String hql ="SELECT s.id, s.maSv, s.hoTen, s.email, s.khoa, cn.ten, cn.id "
				+ "FROM SinhVien s join ChuyenNganh cn on s.chuyenNganhId = cn.id "
				+ "where " + queryCoSo
				+ "AND " + queryDotDK
				+ "AND "+ queryChuyenNganhId
				+" AND "+keyword;
		TypedQuery<Object[]> query = em.createQuery(hql,Object[].class);
		
		query.setParameter("key", "%"+key+"%");
		if (dotDangKyId != 0) {
			query.setParameter("dotDangKyId", dotDangKyId);
		}
		if (chuyenNganhId != 0) {
			query.setParameter("chuyenNganhId", chuyenNganhId);
		}
		if (coso != 0) {
			query.setParameter("coso", coso);
		}

		List<Object[]> lst = query.getResultList();
		return lst;
	}
	
	public List<SinhVien> getUsingSession(int position, int pageSize) {
		  TypedQuery<SinhVien> query = em.createQuery("FROM SinhVien", SinhVien.class);
		  query.setFirstResult(position);
		  query.setMaxResults(pageSize);
		  return query.getResultList();
		}
	
	

	public List<SinhVien> findByKeyword(int coso, int dotDKyId, String keyword){
		String key ="%"+keyword+"%";
		String hql ="SELECT * FROM SinhVien s WHERE s.maSv like :key or s.hoTen like :key or s.email like :key";
		TypedQuery<SinhVien> query = em.createQuery(hql,SinhVien.class);
		query.setParameter("key", key);
		List<SinhVien> lst = query.getResultList();
		return lst;
	}
	
	public long countSV() {
		String hql="select count(*) from SinhVien";
		TypedQuery<Long> query = em.createQuery(hql,Long.class);
		return query.getSingleResult();
	}
}
