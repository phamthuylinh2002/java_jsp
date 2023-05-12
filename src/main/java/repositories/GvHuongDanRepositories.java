package repositories;

import java.util.List;

import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.GiangVienHD;
import model.GvHuongDan;

public class GvHuongDanRepositories extends Repositeries<GvHuongDan> {
	private EntityManager em;
	public GvHuongDanRepositories() {
		super(GvHuongDan.class);
		this.em= utils.UtilsJDBC.getEntityManager();
	}

	public Integer selectSoNhomHD(String gv_id) {
		String hql="SELECT g.soNhomHuongDan FROM GvHuongDan g where g.nhanVienId = :gv_id";
		TypedQuery<Integer> query = em.createQuery(hql,Integer.class).setParameter("gv_id", gv_id);
		List<Integer> lst = query.getResultList();
		for (Integer x : lst) {
			return x;
		}
		return null;
	}

	public List<String> getMaNvByDotDK(Integer dot_DK){

		String hql="SELECT g.nhanVienId FROM GvHuongDan g where g.dotDangKyId = :dot_DK";
		TypedQuery<String> query = em.createQuery(hql,String.class).setParameter("dot_DK", dot_DK);
		List<String> lst = query.getResultList();

		return lst;
	}

	public GvHuongDan getNV (int nhan_vien_id, int chuyen_nganh_id , int dot_dk){
		String hql="SELECT DISTINCT g "
				+ "FROM NhanVien n join GvHuongDan g on n.id = g.nhanVienId "
				+ "	JOIN ChuyenNganhGv c on c.nhanVienId=n.id"
				+ "    WHERE n.id= :nhan_vien_id and g.chuyenNganhId = :chuyen_nganh_id and g.dotDangKyId= :dot_dang_ky";

		TypedQuery<GvHuongDan> query = em.createQuery(hql,GvHuongDan.class);
		query.setParameter("nhan_vien_id", nhan_vien_id);
		query.setParameter("chuyen_nganh_id", chuyen_nganh_id);
		query.setParameter("dot_dang_ky", dot_dk);

		GvHuongDan gv = query.getSingleResult();
		return gv;
	}

	public int getMaNV (int maGV) {
		String hql="SELECT g.nhanVienId FROM GvHuongDan g WHERE g.id = :maGV";
		TypedQuery<Integer> query = em.createQuery(hql,Integer.class).setParameter("maGV", maGV);
		Integer ma = query.getSingleResult();
		return ma;
	}
	public boolean deleGVHDbyIDdNV (int idNV) {
		List<GvHuongDan> lst = this.findGVHDbyID(idNV);
		if (lst==null || lst.size()==0) {
			return true;
		}
		try {
			for (GvHuongDan x : lst) {
				this.delete(x);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Object[]> getGvByCoSo_DotDK_ChuyenNganh(Integer dot_DK, Integer co_so){
		String hql="SELECT DISTINCT gvHD.id, nv.ten FROM ChuyenNganhGv cnGV "
				+ "INNER JOIN NhanVien nv on cnGV.nhanVienId = nv.id  "
				+ "INNER JOIN GvHuongDan gvHD on nv.id = gvHD.nhanVienId "
				+ "INNER JOIN DotDangKy dDK on dDK.id = gvHD.dotDangKyId "
					+ "where dDK.id = :dot_DK "
					+ "AND dDK.coSoId = :co_so ";

		TypedQuery<Object[]> query = em.createQuery(hql,Object[].class);

		query.setParameter("dot_DK", dot_DK);
		query.setParameter("co_so", co_so);


		List<Object[]> lst = query.getResultList();
		return lst;
	}
	
	public List<Object[]> getGvHDByCoSo_DotDK_ChuyenNganh(Integer dot_DK, Integer co_so , Integer cn_id){
		String hql="SELECT DISTINCT gv.id, nv.ten FROM GvHuongDan gv JOIN NhanVien nv on gv.nhanVienId = nv.id "
				+ "WHERE gv.chuyenNganhId = :cn_id and nv.coSoId = :co_so and gv.dotDangKyId= :dot_DK";

		TypedQuery<Object[]> query = em.createQuery(hql,Object[].class);

		query.setParameter("dot_DK", dot_DK);
		query.setParameter("co_so", co_so);
		query.setParameter("cn_id", cn_id);


		List<Object[]> lst = query.getResultList();
		return lst;
	}

	public Integer getDotDky(int nv_id){
		String hql="SELECT gv.dotDangKyId FROM GvHuongDan gv WHERE gv.nhanVienId = :nv_id";
		TypedQuery<Integer> query = em.createQuery(hql,Integer.class);
		query.setParameter("nv_id", nv_id);
		List<Integer> lst = query.getResultList();
		for (Integer x : lst) {
			return x;
		}
		return null;
	}
	public List<Integer> getGVHDbyCOSO_DotDK(Integer dot_dk, Integer co_so) {
		String hql = "select DISTINCT a.nhanVienId from GvHuongDan a "
				+ "JOIN NhanVien b on a.nhanVienId=b.id "
				+ "WHERE b.coSoId=:co_so and a.dotDangKyId =:dot_dk";
		TypedQuery<Integer> query = em.createQuery(hql,Integer.class);
		query.setParameter("dot_dk", dot_dk);
		query.setParameter("co_so", co_so);
		List<Integer> lst = query.getResultList();
		return lst;
	}

	public List<Integer> getGVHDbyCOSO_DotDK_CN(Integer dot_dk, Integer co_so, Integer cn) {
		String hql = "select DISTINCT a.nhanVienId from GvHuongDan a "
				+ "JOIN NhanVien b on a.nhanVienId=b.id "
				+ "WHERE b.coSoId=:co_so and a.dotDangKyId =:dot_dk and "
				+ " a.chuyenNganhId =:cn";
		TypedQuery<Integer> query = em.createQuery(hql,Integer.class);
		query.setParameter("dot_dk", dot_dk);
		query.setParameter("co_so", co_so);
		query.setParameter("cn", cn);
		List<Integer> lst = query.getResultList();
		return lst;
	}

	
	public Long checkGV(Integer nvID) {
		String hql="Select count(gv.nhanVienId) from GvHuongDan gv where gv.nhanVienId =:nvID";
		TypedQuery<Long> query = em.createQuery(hql,Long.class).setParameter("nvID", nvID);
		
		return query.getSingleResult();
	}

	public Long countGV(Integer nvID,int cn_id) {
		String hql="Select count(gv.nhanVienId) from GvHuongDan gv where gv.nhanVienId =:nvID and gv.chuyenNganhId =:cn_id";
		TypedQuery<Long> query = em.createQuery(hql,Long.class);
		query.setParameter("nvID", nvID);
		query.setParameter("cn_id", cn_id);
		
		return query.getSingleResult();
	}
	
	public GvHuongDan getGVHDByMaCN(int nvid, int cnId) {
		String hql="SELECT gv from GvHuongDan gv where gv.nhanVienId =:nvid and gv.chuyenNganhId =:cnId";
		
		TypedQuery<GvHuongDan> query =em.createQuery(hql,GvHuongDan.class);
		query.setParameter("nvid", nvid);
		query.setParameter("cnId", cnId);
		
		return query.getSingleResult();
	}
	public List<Object[]> getGvByCoSo_DotDK_ChuyenNganh(Integer dot_DK, Integer co_so, Integer cn){
		String queryCN ="gvHD.chuyenNganhId = :cn";
		if (cn==0) {
			queryCN = "0 = 0";
		}
		
		String hql="SELECT DISTINCT gvHD.id, nv.ten FROM ChuyenNganhGv cnGV "
				+ "INNER JOIN NhanVien nv on cnGV.nhanVienId = nv.id  "
				+ "INNER JOIN GvHuongDan gvHD on nv.id = gvHD.nhanVienId "
				+ "INNER JOIN DotDangKy dDK on dDK.id = gvHD.dotDangKyId "
					+ "where dDK.id = :dot_DK "
					+ "AND dDK.coSoId = :co_so "
					+ "AND "+queryCN;

		TypedQuery<Object[]> query = em.createQuery(hql,Object[].class);
		if (cn!=0) {
			query.setParameter("cn", cn);
		}
		query.setParameter("dot_DK", dot_DK);
		query.setParameter("co_so", co_so);


		List<Object[]> lst = query.getResultList();
		return lst;
	}
	public List<GvHuongDan> findGVHDbyID(Integer nv_id) {
		String hql="SELECT g FROM GvHuongDan g where g.nhanVienId = :nv_id";
		TypedQuery<GvHuongDan> query = em.createQuery(hql,GvHuongDan.class).setParameter("nv_id", nv_id);
		List<GvHuongDan> lst = query.getResultList();
		return lst;
	}
	
	public long getSum(Integer gv_id) {
		String hql="SELECT COUNT(1) from Nhom n1 JOIN GvHuongDan gv1 on n1.giangVienHdId = gv1.id WHERE n1.giangVienHdId = :gv_id ";
		TypedQuery<Long> query = em.createQuery(hql,Long.class).setParameter("gv_id", gv_id);
		
		return query.getSingleResult();
	}
	
	public Integer selectSoNhomHD(Integer gv_id) {
		String hql="SELECT g.soNhomHuongDan FROM GvHuongDan g where g.id =: gv_id";
		TypedQuery<Integer> query = em.createQuery(hql,Integer.class).setParameter("gv_id", gv_id);
		List<Integer> lst = query.getResultList();
		for (Integer x : lst) {
			return x;
		}
		return null;
	}
}
