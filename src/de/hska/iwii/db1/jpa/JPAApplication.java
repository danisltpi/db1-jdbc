package de.hska.iwii.db1.jpa;

import java.sql.Time;
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

		Kunde kunde1 = new Kunde();
		kunde1.setFirstName("bob");
		kunde1.setLastName("hubert");
		kunde1.setEmail("bob.hubert@email.com");

		Kunde kunde2 = new Kunde();
		kunde2.setFirstName("alice");
		kunde2.setLastName("charlie");
		kunde2.setEmail("alice.charlie@email.com");

		Flug flug1 = new Flug();
		flug1.setNr("PG271");
		flug1.setAirport("FRA");
		flug1.setStartTime(new Time(System.currentTimeMillis()));

		Flug flug2 = new Flug();
		flug2.setNr("D090L");
		flug2.setAirport("BJS");
		flug2.setStartTime(new Time(System.currentTimeMillis()));

		Flug flug3 = new Flug();
		flug3.setNr("EH029");
		flug3.setAirport("ARN");
		flug3.setStartTime(new Time(System.currentTimeMillis()));

		em.persist(kunde1);
		em.persist(kunde2);
		em.persist(flug1);
		em.persist(flug2);
		em.persist(flug3);
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
