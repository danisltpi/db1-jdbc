package de.hska.iwii.db1.jpa;

import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAApplication {
	private EntityManagerFactory entityManagerFactory;

	public JPAApplication() {
		Logger.getLogger("org.hibernate").setLevel(Level.ALL);
		entityManagerFactory = Persistence.createEntityManagerFactory("DB1");
	}

	public void testFlights() {
		EntityManager em = this.entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		Kunde kunde = new Kunde();
		kunde.setFirstName("bob");
		kunde.setLastName("hubert");
		kunde.setEmail("bob.hubert@email.com");
		em.persist(kunde);
		em.getTransaction().commit();
		em.close();
	}

	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	public static void main(String[] args) {
		JPAApplication app = new JPAApplication();
		app.testFlights();
		app.entityManagerFactory.close();
	}
}
