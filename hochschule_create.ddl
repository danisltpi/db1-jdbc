CREATE TABLE Dozent (
  mail             varchar(255) NOT NULL, 
  name             varchar(255), 
  vorname          varchar(255), 
  grad             varchar(255), 
  veranstaltung_id int4 NOT NULL, 
  PRIMARY KEY (mail));
CREATE TABLE Gebauede (
  id     SERIAL NOT NULL, 
  name   varchar(35), 
  nummer int4, 
  PRIMARY KEY (id));
CREATE TABLE Labor (
  veranstaltung_id int4 NOT NULL);
CREATE TABLE "Lehrbeauftragte/r" (
  dozent_mail varchar(255) NOT NULL);
CREATE TABLE "Mitarbeiter/in" (
  sprechzeiten time(7), 
  buero        int4 NOT NULL, 
  dozent_mail  varchar(255) NOT NULL);
CREATE TABLE "Professor/in" (
  sprechzeiten time(7), 
  buero        int4 NOT NULL, 
  dozent_mail  varchar(255) NOT NULL);
CREATE TABLE Raum (
  id          SERIAL NOT NULL, 
  name        varchar(255) NOT NULL, 
  nummer      int4 NOT NULL, 
  gebauede_id int4 NOT NULL, 
  PRIMARY KEY (id));
CREATE TABLE Raum_Veranstaltung (
  raum_id          int4 NOT NULL, 
  veranstaltung_id int4 NOT NULL, 
  PRIMARY KEY (raum_id, 
  veranstaltung_id));
CREATE TABLE Studiengang (
  kuerzel varchar(35) NOT NULL, 
  name    varchar(255), 
  PRIMARY KEY (kuerzel));
CREATE TABLE Stundenplan (
  sg_kuerzel varchar(35) NOT NULL, 
  id         SERIAL NOT NULL, 
  PRIMARY KEY (id));
CREATE TABLE Uebung (
  veranstaltung_id int4 NOT NULL);
CREATE TABLE Veranstaltung (
  id             SERIAL NOT NULL, 
  wochentag      varchar(255), 
  startzeit      time(7), 
  endzeit        time(7), 
  fachsemester   int4, 
  haeufigkeit    varchar(255), 
  name           varchar(255), 
  sg_kuerzel     varchar(35) NOT NULL, 
  stundenplan_id int4 NOT NULL, 
  PRIMARY KEY (id));
CREATE TABLE Vorlesung (
  veranstaltung_id int4 NOT NULL);
CREATE INDEX Veranstaltung 
  ON Veranstaltung (id);
CREATE INDEX Veranstaltung_id 
  ON Veranstaltung (id);
ALTER TABLE Labor ADD CONSTRAINT FKLabor178735 FOREIGN KEY (veranstaltung_id) REFERENCES Veranstaltung (id);
ALTER TABLE Uebung ADD CONSTRAINT FKUebung176531 FOREIGN KEY (veranstaltung_id) REFERENCES Veranstaltung (id);
ALTER TABLE Vorlesung ADD CONSTRAINT FKVorlesung132324 FOREIGN KEY (veranstaltung_id) REFERENCES Veranstaltung (id);
ALTER TABLE Stundenplan ADD CONSTRAINT FKStundenpla149346 FOREIGN KEY (sg_kuerzel) REFERENCES Studiengang (kuerzel);
ALTER TABLE Dozent ADD CONSTRAINT FKDozent937743 FOREIGN KEY (veranstaltung_id) REFERENCES Veranstaltung (id);
ALTER TABLE "Professor/in" ADD CONSTRAINT "FKProfessor/10599" FOREIGN KEY (dozent_mail) REFERENCES Dozent (mail);
ALTER TABLE "Lehrbeauftragte/r" ADD CONSTRAINT FKLehrbeauft966314 FOREIGN KEY (dozent_mail) REFERENCES Dozent (mail);
ALTER TABLE "Mitarbeiter/in" ADD CONSTRAINT FKMitarbeite152559 FOREIGN KEY (dozent_mail) REFERENCES Dozent (mail);
ALTER TABLE Raum ADD CONSTRAINT FKRaum236685 FOREIGN KEY (gebauede_id) REFERENCES Gebauede (id);
ALTER TABLE Veranstaltung ADD CONSTRAINT FKVeranstalt32967 FOREIGN KEY (sg_kuerzel) REFERENCES Studiengang (kuerzel);
ALTER TABLE Veranstaltung ADD CONSTRAINT FKVeranstalt875895 FOREIGN KEY (stundenplan_id) REFERENCES Stundenplan (id);
ALTER TABLE "Professor/in" ADD CONSTRAINT "FKProfessor/603275" FOREIGN KEY (buero) REFERENCES Raum (id);
ALTER TABLE "Mitarbeiter/in" ADD CONSTRAINT FKMitarbeite205157 FOREIGN KEY (buero) REFERENCES Raum (id);
ALTER TABLE Raum_Veranstaltung ADD CONSTRAINT FKRaum_Veran912408 FOREIGN KEY (raum_id) REFERENCES Raum (id);
ALTER TABLE Raum_Veranstaltung ADD CONSTRAINT FKRaum_Veran78963 FOREIGN KEY (veranstaltung_id) REFERENCES Veranstaltung (id);
