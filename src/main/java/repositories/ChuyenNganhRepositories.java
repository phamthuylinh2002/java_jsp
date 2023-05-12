package repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.ChuyenNganh;

public class ChuyenNganhRepositories extends Repositeries<ChuyenNganh> {
	private EntityManager em;
	public ChuyenNganhRepositories() {
		super(ChuyenNganh.class);
		this.em= utils.UtilsJDBC.getEntityManager();
	}

	public String getTenById(Integer id) {
			String hql="SELECT c.ten FROM ChuyenNganh c where c.id = :id";
			TypedQuery< String> query = em.createQuery(hql,String.class);
			query.setParameter("id", id);

			List<String> ten = query.getResultList();
			for (String x : ten) {
				return x;
			}
		return null;
	}
	public Integer findId(String tenChuyenNganh) {
		String hql ="SELECT c.id FROM ChuyenNganh c WHERE c.ten = :tenChuyenNganh";
		TypedQuery<Integer> query = em.createQuery(hql,Integer.class);

		query.setParameter("tenChuyenNganh", tenChuyenNganh);
		List<Integer> lst = query.getResultList();
		for (Integer x : lst) {
			return x;
		}
		return null;
	}
	public List<Object[]> getAll() {
		String hql = "SELECT c.id, c.ten FROM ChuyenNganh c";
		TypedQuery<Object[]> query = em.createQuery(hql, Object[].class);
		List<Object[]> lst = query.getResultList();
		return lst;
	}
	@Override
	public List<ChuyenNganh> findAll() {
		String hql ="SELECT c FROM ChuyenNganh c";
		TypedQuery<ChuyenNganh> query = em.createQuery(hql,ChuyenNganh.class);

		List<ChuyenNganh> lst = query.getResultList();
		return lst;
	}
	
	public List<Integer> getCNByBoMon (int bo_mon){
		String hql ="SELECT cn.id FROM ChuyenNganh cn WHERE cn.boMonId = :bo_mon ";
		
		TypedQuery<Integer> query = em.createQuery(hql, Integer.class).setParameter("bo_mon", bo_mon);
		
		List<Integer> lst = query.getResultList();
		
		return lst;
	}
	public List<Integer> getCNByBoMon (){
		String hql ="SELECT cn.id FROM ChuyenNganh cn  ";
		
		TypedQuery<Integer> query = em.createQuery(hql, Integer.class);
		
		List<Integer> lst = query.getResultList();
		
		return lst;
	}
	public Integer getCNByBoMon (int bo_mon,String cn){
		String hql ="SELECT cn.id FROM ChuyenNganh cn WHERE cn.boMonId = :bo_mon and cn.ten = :cn";
		
		TypedQuery<Integer> query = em.createQuery(hql, Integer.class);
		query.setParameter("bo_mon", bo_mon);
		query.setParameter("cn", cn);

		return query.getSingleResult();
	}
	
	public List<Object[]> getCNByBMon (int bo_mon){
		String hql ="SELECT cn.id,cn.ten FROM ChuyenNganh cn WHERE cn.boMonId = :bo_mon ";
		
		TypedQuery<Object[]> query = em.createQuery(hql, Object[].class).setParameter("bo_mon", bo_mon);
		
		List<Object[]> lst = query.getResultList();
		
		return lst;
	}
	public List<Object[]> getCNByBMon (){
		String hql ="SELECT cn.id,cn.ten FROM ChuyenNganh cn ";
		
		TypedQuery<Object[]> query = em.createQuery(hql, Object[].class);
		
		List<Object[]> lst = query.getResultList();
		
		return lst;
	}
	
	public long countGV(int nvId, int cnid) {
		String hql="SELECT count(1) from ChuyenNganhGv cn where cn.nhanVienId =:nvId and cn.chuyenNganhId =: cnid";
		TypedQuery<Long> query = em.createQuery(hql,Long.class);
		query.setParameter("nvId", nvId);		
		query.setParameter("cnid", cnid);		
		return query.getSingleResult();
	}
	public Integer getBoMonbyChuyenNganhId(Integer cnId) {
		String hql = "SELECT cn.boMonId FROM ChuyenNganh cn where cn.id = :cnId";
		TypedQuery<Integer> query = em.createQuery(hql,Integer.class).setParameter("cnId", cnId);
		
		return query.getSingleResult();
	}
}
