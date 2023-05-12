package repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

public abstract class Repositeries<T> {
	EntityManager em = utils.UtilsJDBC.getEntityManager();
	private Class<T> entityClass;


	public Repositeries(Class<T> cls) {
		this.entityClass= cls;
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}

	public T insert(T entity) {
		try {
			em.getTransaction().begin();
			em.persist(entity);
			em.getTransaction().commit();
			return entity;
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException(e);
		}

	}

	public T update(T entity) {
		try {
			em.getTransaction().begin();
			em.merge(entity);
			em.getTransaction().commit();
			return entity;
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException(e);
		}

	}

	public T delete(T entity) {
		try {
			em.getTransaction().begin();
			em.remove(entity);
			em.getTransaction().commit();
			return entity;
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException(e);
		}

	}

	public List<T> findAll(){

		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();

			cq.select(cq.from(entityClass));

			return em.createQuery(cq).getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public T findById(int idNhom) {
		EntityManager em = utils.UtilsJDBC.getEntityManager();
		T entity = em.find(this.entityClass, idNhom);
		return entity;
	}

}