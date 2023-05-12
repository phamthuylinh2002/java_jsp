package utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UtilsJDBC {
	private static EntityManagerFactory factory;
	private static EntityManager em;

	private UtilsJDBC() {
		// TODO Auto-generated constructor stub
	}

	public static EntityManager getEntityManager() {
		if ( em == null||!em.isOpen()) {
			getEntityManagerFactory();
			em = factory.createEntityManager();
		}
		em.clear();
		return em;
	}

	public static EntityManagerFactory getEntityManagerFactory() {
		if (factory == null || !factory.isOpen()) {
			factory = Persistence.createEntityManagerFactory("ToolDangKy");
		}
		return factory;
	}
}
