package de.hska.iwii.db1.jpa;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "buchung")
public class Buchung {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @ManyToOne
  @JoinColumn(name = "id")
  private Flug flug;

  public Flug getFlug() {
    return flug;
  }

  public void setFlug(Flug flug) {
    this.flug = flug;
  }

  @ManyToOne
  @JoinColumn(name = "id")
  private Kunde kunde;

  public Kunde getKunde() {
    return kunde;
  }

  public void setKunde(Kunde kunde) {
    this.kunde = kunde;
  }

  @Column(name = "plaetze")
  private int seats;

  public int getSeats() {
    return seats;
  }

  public void setSeats(int seats) {
    this.seats = seats;
  }

  @Column(name = "datum")
  @Temporal(TemporalType.DATE)
  private Date date;

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }
}
