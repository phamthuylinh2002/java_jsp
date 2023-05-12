package repositories;

import java.util.List;

import javax.persistence.TypedQuery;

import model.CoSo;

public class CoSoRepositories extends Repositeries<CoSo> {
	public CoSoRepositories() {
		super(CoSo.class);
	}
	public Integer findByName(String name) {
		String hql ="SELECT c.id FROM CoSo c WHERE c.ten = :name";
		TypedQuery<Integer> query = em.createQuery(hql,Integer.class);

		query.setParameter("name", name);
		List<Integer> lst = query.getResultList();
		for (Integer x : lst) {
			return x;
		}
		return null;
	}
	public List<CoSo> getAll() {
		String hql ="SELECT c FROM CoSo c ";
		TypedQuery<CoSo> query = em.createQuery(hql,CoSo.class);

		List<CoSo> lst = query.getResultList();
		return lst;
	}
}
