package de.hska.iwii.db1.jpa;

import java.sql.Date;
import java.sql.Time;
import java.util.Iterator;
import java.util.List;
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

		Buchung k1Buchung1 = new Buchung();
		k1Buchung1.setKunde(kunde1);
		k1Buchung1.setFlug(flug1);
		k1Buchung1.setSeats(2);
		k1Buchung1.setDate(new Date(System.currentTimeMillis()));
		Buchung k1Buchung2 = new Buchung();
		k1Buchung2.setKunde(kunde1);
		k1Buchung2.setFlug(flug2);
		k1Buchung2.setSeats(2);
		k1Buchung2.setDate(new Date(System.currentTimeMillis()));

		Buchung k2Buchung1 = new Buchung();
		k2Buchung1.setKunde(kunde2);
		k2Buchung1.setFlug(flug2);
		k2Buchung1.setSeats(2);
		k2Buchung1.setDate(new Date(System.currentTimeMillis()));
		Buchung k2Buchung2 = new Buchung();
		k2Buchung2.setKunde(kunde2);
		k2Buchung2.setFlug(flug3);
		k2Buchung2.setSeats(2);
		k2Buchung2.setDate(new Date(System.currentTimeMillis()));

		em.persist(kunde1);
		em.persist(kunde2);
		em.persist(flug1);
		em.persist(flug2);
		em.persist(flug3);
		em.persist(k1Buchung1);
		em.persist(k1Buchung2);
		em.persist(k2Buchung1);
		em.persist(k2Buchung2);

		// suche buchungen nach nachnamen
		String searchName = "charlie";
		Iterator rowIter = em
				.createQuery(
						"SELECT b.id, b.seats, CAST(b.date AS string), k.firstName, k.lastName, b.flug.id, b.kunde.id FROM Buchung b JOIN Kunde k ON k.id = b.kunde.id WHERE k.lastName = :lastName")
				.setParameter("lastName", searchName)
				.getResultList().iterator();
		while (rowIter.hasNext()) {
			Object[] row = (Object[]) rowIter.next();
			int plaetze = (int) row[1];
			String datum = (String) row[2];
			String firstName = (String) row[3];
			String lastName = (String) row[4];
			int flugId = (int) row[5];
			System.out.printf("Plaetze: %d, Datum: %s, Vorname: %s, Nachname: %s, Flug: %d\n", plaetze, datum, firstName,
					lastName, flugId);
		}
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
