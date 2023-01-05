-- Aufgabe 3
SELECT *
FROM vorhersage
WHERE station = 10637;
-- Aufgabe 4
SELECT station
FROM vorhersage
WHERE (vorhersage_datum = '2023-01-06')
  AND min_temp > '10'
  AND max_temp < '140'