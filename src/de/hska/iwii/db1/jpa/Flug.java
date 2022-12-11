package de.hska.iwii.db1.jpa;

import java.sql.Time;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "flug")
public class Flug {
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

  @Column(name = "nr")
  private String nr;

  public String getNr() {
    return nr;
  }

  public void setNr(String nr) {
    this.nr = nr;
  }

  @Column(name = "startzeit")
  @Temporal(TemporalType.TIME)
  private Time startTime;

  public Time getStartTime() {
    return startTime;
  }

  public void setStartTime(Time startTime) {
    this.startTime = startTime;
  }

  @Column(name = "flughafen")
  private String airport;

  public String getAirport() {
    return airport;
  }

  public void setAirport(String airport) {
    this.airport = airport;
  }
}
