package repositories;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.NhanVien;
import utils.UtilsJDBC;

public class NhanVienRepositories extends Repositeries<NhanVien> {
	private EntityManager em;
	private ChuyenNganhRepositories cn_rep;

	public NhanVienRepositories() {
		super(NhanVien.class);
		this.em = UtilsJDBC.getEntityManager();
		this.cn_rep = new ChuyenNganhRepositories();
	}

	public NhanVien findByEmai(String email) {
		String hql = "SELECT s FROM NhanVien s WHERE s.emailFPT = :email";
		TypedQuery<NhanVien> query = em.createQuery(hql, NhanVien.class);
		query.setParameter("email", email);
		List<NhanVien> lst = query.getResultList();
		for (NhanVien nv : lst) {
			return nv;
		}
		return null;
	}
	public NhanVien findByMaNV(String manv) {
		String hql = "SELECT s FROM NhanVien s WHERE s.manv = :manv";
		TypedQuery<NhanVien> query = em.createQuery(hql, NhanVien.class);
		query.setParameter("manv", manv);
		List<NhanVien> lst = query.getResultList();
		for (NhanVien nv : lst) {
			return nv;
		}
		return null;
	}
	
	public List<String> getMaByCoSoId(Integer co_so_id) {

		String hql = "SELECT s.id FROM NhanVien s WHERE s.coSoId = :co_so_id";
		TypedQuery<String> query = em.createQuery(hql, String.class);
		query.setParameter("co_so_id", co_so_id);

		List<String> lst = query.getResultList();
		return lst;
	}

	public List<Object[]> getNv_Gv(int role, int dot_dk, int co_so_id, int chuyen_nganh_id) {
		String hql = "SELECT gv.id, nv.ten FROM GvHuongDan  gv join NhanVien nv on nv.id = gv.nhanVienId "
				+ " where nv.coSoId = :co_so and nv.role = :role and gv.chuyenNganhId = :cn_id and gv.dotDangKyId = :dotdky  "
				+ " and  (SELECT COUNT(1) from Nhom n1 JOIN GvHuongDan gv1 on n1.giangVienHdId = gv1.id WHERE gv1.nhanVienId = nv.id)"
				+ " < (select gvhd.soNhomHuongDan from GvHuongDan gvhd where gvhd.id = gv.id)";
		TypedQuery<Object[]> query = em.createQuery(hql, Object[].class);
		query.setParameter("role", role);
		query.setParameter("dotdky", dot_dk);
		query.setParameter("co_so", co_so_id);
		query.setParameter("cn_id", chuyen_nganh_id);

		List<Object[]> lst = query.getResultList();

		return lst;
	}

	public NhanVien getById(int id) {
		String hql = "SELECT n FROM NhanVien n where n.id = :id";
		TypedQuery<NhanVien> query = em.createQuery(hql, NhanVien.class).setParameter("id", id);
		List<NhanVien> lst = query.getResultList();
		return lst.get(0);
	}
	
	public String getTenById(int id) {
		String hql = "SELECT n.ten FROM NhanVien n where n.id = :id";
		TypedQuery<String> query = em.createQuery(hql, String.class).setParameter("id", id);
		List<String> lst = query.getResultList();
		return lst.get(0);
	}
	
	public Integer getByMa(String ma) {
		String hql = "SELECT n.id FROM NhanVien n where n.manv = :ma";
		TypedQuery<Integer> query = em.createQuery(hql, Integer.class).setParameter("ma", ma);
		return query.getSingleResult();
		 
	}

	public List<Object[]> findGVHD_DotDK_byNVid(Integer idNV) {
		String hql = "SELECT g.id, g.dotDangKyId FROM NhanVien n " + "INNER JOIN GvHuongDan g ON n.id = g.nhanVienId "
				+ "where n.id = :idNV ";
		TypedQuery<Object[]> query = em.createQuery(hql, Object[].class);
		query.setParameter("idNV", idNV);
		List<Object[]> lst = query.getResultList();
		return lst;
	}

	public List<Object[]> getNhanVienPDT(Integer idCoSo) {
		String hql = "SELECT n.emailFE, n.ten FROM NhanVien n " + "INNER JOIN CoSo cs ON n.id = cs.nhanVienId "
				+ "where cs.id = :idCoSo ";
		TypedQuery<Object[]> query = em.createQuery(hql, Object[].class);

		query.setParameter("idCoSo", idCoSo);
		List<Object[]> lst = query.getResultList();
		return lst;
	}

	public List<Object[]> getListNV(Integer idCoSo) {
		String hql = "SELECT n.id, n.manv, n.ten, n.sdt, n.emailFPT, n.role, n.boMonId FROM NhanVien n "
				+ "where n.coSoId = :idCoSo ";
		TypedQuery<Object[]> query = em.createQuery(hql, Object[].class);
		query.setParameter("idCoSo", idCoSo);
		List<Object[]> lst = query.getResultList();
		if (lst==null || lst.size()==0) {
			return null;
		}
		List<Object[]> lstOb = new ArrayList<Object[]>();

		for (Object[] x : lst) {
			Integer role = utils.HelperUtils.parseInteger(x[5] + "");
			if (role == 0) {
				lstOb.add(new Object[] { x[0], x[1], x[2], x[3], x[4], "Amin", x[6],role });
			} else if (role == 1) {
				lstOb.add(new Object[] { x[0], x[1], x[2], x[3], x[4], "Phòng đào tạo", x[6],role });
			} else if (role == 2) {
				lstOb.add(new Object[] { x[0], x[1], x[2], x[3], x[4], "Chủ nhiệm bộ môn",
						cn_rep.getTenById(utils.HelperUtils.parseInteger(x[6] + "")) + "",role });
			} else if (role == 3) {
				lstOb.add(new Object[] { x[0], x[1], x[2], x[3], x[4], "Giảng Viên", x[6],role });
			}
		}
		return lstOb;
	}
	public List<Object[]> getListNV() {
		String hql = "SELECT n.id, n.manv, n.ten, n.sdt, n.emailFPT, n.role, n.boMonId, n.emailFE, n.coSoId FROM NhanVien n ";
		TypedQuery<Object[]> query = em.createQuery(hql, Object[].class);
		List<Object[]> lst = query.getResultList();
		if (lst==null || lst.size()==0) {
			return null;
		}
		List<Object[]> lstOb = new ArrayList<Object[]>();

		for (Object[] x : lst) {
			Integer role = utils.HelperUtils.parseInteger(x[5] + "");
			if (role == 0) {
				lstOb.add(new Object[] { x[0], x[1], x[2], x[3], x[4], "Amin", x[6],role, x[7], x[8] });
			} else if (role == 1) {
				lstOb.add(new Object[] { x[0], x[1], x[2], x[3], x[4], "Phòng đào tạo", x[6],role,x[7], x[8]  });
			} else if (role == 2) {
				lstOb.add(new Object[] { x[0], x[1], x[2], x[3], x[4], "Chủ nhiệm bộ môn",
						cn_rep.getTenById(utils.HelperUtils.parseInteger(x[6] + "")) + "",role,x[7], x[8]  });
			} else if (role == 3) {
				lstOb.add(new Object[] { x[0], x[1], x[2], x[3], x[4], "Giảng Viên", x[6],role,x[7], x[8]  });
			}
		}
		return lstOb;
	}

	public List<Object[]> getListNVTimKiem(Integer idCoSo, Integer roleId) {
		String queryRole = "AND n.role=:role";
		String queryCoSo = "n.coSoId = :idCoSo ";
		if (roleId == -1) {
			queryRole = "AND 0 = 0";
		}
		if (idCoSo == 0) {
			queryCoSo = "0 = 0 ";
		}
		String hql = "SELECT n.id, n.manv, n.ten, n.sdt, n.emailFPT, n.role, n.boMonId, n.emailFE, n.coSoId FROM NhanVien n "
				+ "where "+queryCoSo + queryRole;
		TypedQuery<Object[]> query = em.createQuery(hql, Object[].class);
		
		if (roleId != -1) {
			query.setParameter("role", roleId);
		}
		if (idCoSo != 0) {
			query.setParameter("idCoSo", idCoSo);
		}

		List<Object[]> lst = query.getResultList();
		List<Object[]> lstOb = new ArrayList<Object[]>();

		if (lst != null || lst.size()==0) {
			for (Object[] x : lst) {
				int role = utils.HelperUtils.parseInteger(x[5] + "");
				if (role == 0) {
					lstOb.add(new Object[] { x[0], x[1], x[2], x[3], x[4], "Admin", x[6], role, x[7], x[8]});
				} else if (role == 1) {
					lstOb.add(new Object[] { x[0], x[1], x[2], x[3], x[4], "Phòng đào tạo", x[6], role, x[7], x[8]});
				} else if (role == 2) {
					lstOb.add(new Object[] { x[0], x[1], x[2], x[3], x[4], "Chủ nhiệm bộ môn",
							cn_rep.getTenById(utils.HelperUtils.parseInteger(x[6] + "")) + "" , role, x[7], x[8]});
				} else if (role == 3) {
					lstOb.add(new Object[] { x[0], x[1], x[2], x[3], x[4], "Giảng viên", x[6], role, x[7], x[8]});
				}
			}
		}
		return lstOb;
	}
	public Integer findCoSoByMaNV(String manv) {
		String hql = "SELECT n.coSoId FROM NhanVien n WHERE n.manv = :manv";
		TypedQuery<Integer> query = em.createQuery(hql, Integer.class);
		query.setParameter("manv", manv);
		List<Integer> lst = query.getResultList();
		for (Integer x : lst) {
			return x;
		}
		return null;
	}
	
	public Integer getGVHDId(Integer nv_id) {
		String hql ="SELECT gv.id FROM NhanVien n join GvHuongDan gv"
				+ " on n.id = gv.nhanVienId "
				+ " WHERE n.id = :nv_id";
		
		TypedQuery<Integer> query = em.createQuery(hql,Integer.class);
		query.setParameter("nv_id", nv_id);
		
		return query.getSingleResult();
		
	}

	
	public List<Object[]> getAll(){
		String hql="SELECT nv.id, nv.manv FROM NhanVien nv";
		
		TypedQuery<Object[]> query = em.createQuery(hql,Object[].class);
		List<Object[]> lst = query.getResultList();
		return lst;
	}
	
	public long countNV(String manv) {
		String hql="SELECT count(1) from NhanVien nv where nv.manv = :manv";
		TypedQuery<Long> query = em.createQuery(hql,Long.class).setParameter("manv", manv);
		
		return query.getSingleResult();
		
	}
	
//	public long tongNV() {
//		String hql="select count(1) from NhanVien nv where nv.role =: 3 or nv.role =:2";
//	}
	

}
