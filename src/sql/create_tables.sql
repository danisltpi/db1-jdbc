DROP TABLE IF EXISTS vorhersage;
CREATE TABLE vorhersage (
  id SERIAL PRIMARY KEY,
  station BIGINT NOT NULL,
  aufbruf_datum DATE NOT NULL,
  vorhersage_datum DATE NOT NULL,
  min_temp REAL NOT NULL,
  max_temp REAL NOT NULL,
  niederschlag INTEGER NOT NULL,
  dauer_sonnenschein INTEGER NOT NULL
)