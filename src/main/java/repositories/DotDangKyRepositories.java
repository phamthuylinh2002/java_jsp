package repositories;

import java.util.List;

import javax.persistence.TypedQuery;

import model.DotDangKy;

public class DotDangKyRepositories extends Repositeries<DotDangKy> {
	public DotDangKyRepositories() {
		super(DotDangKy.class);
	}
	
	public List<DotDangKy> getDotDangKyByCoSo(Integer coSoId) {
		String hql = "SELECT s FROM DotDangKy s WHERE s.coSoId = :coSoId";


		TypedQuery<DotDangKy> query = em.createQuery(hql, DotDangKy.class);
		query.setParameter("coSoId", coSoId);
		List<DotDangKy> lst = query.getResultList();
		return lst;
	}
	public List<Object[]> getDotDK(Integer coSoId) {
		String hql = "SELECT d.id, d.hocKy, c.id, c.ten FROM DotDangKy d INNER JOIN CoSo c on c.id = d.coSoId"
				+ " where d.coSoId =:coSoId";

		TypedQuery<Object[]> query = em.createQuery(hql, Object[].class);
		query.setParameter("coSoId", coSoId);
		List<Object[]> lst = query.getResultList();
		return lst;
	}
	
	public Integer getDot(int coso) {
		String hql="select d.id from DotDangKy d where d.coSoId =:coso order by d.thoiGianBatDau desc";
		
		TypedQuery< Integer> query = em.createQuery(hql, Integer.class);
		query.setParameter("coso", coso);
		List<Integer> lst = query.getResultList();
		for (Integer x: lst) {
			return x;
		}
		return null;
	}
	public Long getSoLuongDotDK(int coso) {
		String hql = "select count(d.id) from DotDangKy d where d.coSoId=:coso";		
		TypedQuery< Long> query = em.createQuery(hql, Long.class);
		query.setParameter("coso", coso);
		List<Long> lst = query.getResultList();
		for (Long x: lst) {
			return x;
		}
		return null;
	}
	public List<Object[]> getListDotDK(int coso){
		String hql = "SELECT d.id, d.hocKy,d.thoiGianBatDau,d.thoiGianKetThuc "
				+ "from DotDangKy d "
				+ "where d.coSoId=:coso";
		TypedQuery<Object[]> query = em.createQuery(hql, Object[].class);
		query.setParameter("coso", coso);
		List<Object[]> lst = query.getResultList();
		return lst;
	}

	
	public List<Object[]> getALL_dot() {
		String hql="select d.hocKy, d.thoiGianBatDau, d.thoiGianKetThuc, c.ten from DotDangKy d INNER JOIN CoSo c on c.id = d.coSoId";
		
		TypedQuery<Object[]> query = em.createQuery(hql, Object[].class);
		List<Object[]> lst = query.getResultList();
		return lst;
	}
	public DotDangKy findDotDKbyId(Integer idDot) {
		String hql="select d from DotDangKy d where d.id = :idDot";
		
		TypedQuery<DotDangKy> query = em.createQuery(hql, DotDangKy.class).setParameter("idDot", idDot);
		DotDangKy dot = query.getSingleResult();
		return dot;
	}

}
