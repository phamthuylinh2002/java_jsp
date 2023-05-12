package repositories;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.ChuyenNganhGv;
import model.GvHuongDan;
import utils.UtilsJDBC;

public class ChuyenNganhGvRepositories extends Repositeries<ChuyenNganhGv> {
	private EntityManager em;
	public ChuyenNganhGvRepositories() {
		super(ChuyenNganhGv.class);
		this.em =UtilsJDBC.getEntityManager();
	}

	public List<String> findNhanVienIDByChuyenNganhId(Integer cnId) {

		String hql="SELECT c.nhanVienId FROM ChuyenNganhGv c where c.chuyenNganhId = :cnId";
		TypedQuery< String> query = em.createQuery(hql,String.class);
		query.setParameter("cnId", cnId);
		List<String> lst = query.getResultList();

		return lst;
	}
	

	public ChuyenNganhGv findByNVidAndCN(Integer nvid,Integer cnId) {

		String hql="SELECT c FROM ChuyenNganhGv c where c.chuyenNganhId = :cnId and c.nhanVienId =:nvid";
		TypedQuery< ChuyenNganhGv> query = em.createQuery(hql,ChuyenNganhGv.class);
		query.setParameter("cnId", cnId);
		query.setParameter("nvid", nvid);
		
		return query.getSingleResult();
	}
	
	public Long checkNV(Integer nvId, Integer cnId) {
		String hql="SELECT count(1) FROM ChuyenNganhGv c where c.nhanVienId = :nvId and c.chuyenNganhId = :cnId";
		TypedQuery<Long> query = em.createQuery(hql,Long.class);
		query.setParameter("nvId", nvId);
		query.setParameter("cnId", cnId);
		return query.getSingleResult();
	}
	
	public List<Object[]> getListGVCN (Integer co_so , Integer cn, String key){
		String keyword=" (nv.manv like :key or nv.ten like :key or nv.emailFPT or nv.emailFE like :key)";
		String  hql ="SELECT nv.id, nv.manv, nv.ten,nv.sdt,nv.emailFPT, emailFE ,0 "
				+ "from NhanVien nv JOIN ChuyenNganhGv cn on nv.id = cn.nhanVienId "
				+ "where cn.chuyenNganhId = :cn and nv.coSoId = :co_so "
				+ " and nv.id not in ( select gv.nhanVienId from GvHuongDan gv)"
				+ " and "+keyword;
		
		
		TypedQuery<Object[]> query = em.createQuery(hql,Object[].class);
		query.setParameter("cn", cn);
		query.setParameter("co_so", co_so);
		query.setParameter("key", "%"+key+"%");
		List<Object[]> lst = query.getResultList();
		return lst;
	}
	
	public List<Object[]> getListGVHD (int co_so ,int dotdk, int cn, String key){
		String keyword=" (nv.manv like :key or nv.ten like :key or nv.emailFPT like :key)";
		String  hql ="SELECT nv.id, nv.ten,nv.sdt,nv.emailFPT, nv.emailFE, gv.soNhomHuongDan, "
				+ " (SELECT COUNT(1) from Nhom n JOIN GvHuongDan gv on n.giangVienHdId = gv.id WHERE gv.nhanVienId = nv.id)"
				+ " ,cn.ten,cn.id  "
				+ "from NhanVien nv join GvHuongDan gv on gv.nhanVienId = nv.id "
				+ " join ChuyenNganh cn on cn.id = gv.chuyenNganhId "				
				+ "where gv.chuyenNganhId = :cn and nv.coSoId = :co_so"
				+ " and gv.dotDangKyId =:dotdk "
				+ " and "+ keyword;
		
		
		TypedQuery<Object[]> query = em.createQuery(hql,Object[].class);
		query.setParameter("cn", cn);
		query.setParameter("co_so", co_so);
		query.setParameter("dotdk", dotdk);
		query.setParameter("key", "%"+key+"%");
		List<Object[]> lst = query.getResultList();
		return lst;
	}
	
	public List<Object[]> getListByKey (Integer co_so , Integer cn, String key){
		String keyword=" (nv.manv like :key or nv.ten like :key or nv.emailFPT or nv.emailFE like :key)";
		String hql ="SELECT nv.id, nv.manv, nv.ten,nv.sdt,nv.emailFPT , emailFE "
				+ "from NhanVien nv JOIN ChuyenNganhGv cn on nv.id = cn.nhanVienId "
				+ "where cn.chuyenNganhId = :cn and nv.coSoId = :co_so "
				+ " and "+keyword;
		
		TypedQuery<Object[]> query = em.createQuery(hql,Object[].class);
		query.setParameter("cn", cn);
		query.setParameter("co_so", co_so);
		query.setParameter("key", "%"+key+"%");
		List<Object[]> lst = query.getResultList();
		return lst;
	}


	public ChuyenNganhGv getGV (int nvID) {
		String hql ="SELECT c from ChuyenNganhGv c where c.nhanVienId =:nvID";
		
		TypedQuery<ChuyenNganhGv> query = em.createQuery(hql,ChuyenNganhGv.class);
		
		query.setParameter("nvID", nvID);
		
		ChuyenNganhGv gv = query.getSingleResult();
		return gv;
	}

	public List<ChuyenNganhGv> findCNGVbyIdNV(int nvID) {
		String hql ="SELECT c from ChuyenNganhGv c where c.nhanVienId =:nvID";
		
		TypedQuery<ChuyenNganhGv> query = em.createQuery(hql,ChuyenNganhGv.class);
		
		query.setParameter("nvID", nvID);
		
		List<ChuyenNganhGv> lst = query.getResultList();
		return lst;
	}
}
