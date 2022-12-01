SELECT DISTINCT kunde.name AS kunde,
  kunde.nr AS knr,
  lieferant.name AS lieferant,
  lieferant.nr AS lnr
FROM auftragsposten
  JOIN auftrag ON auftragsposten.auftrnr = auftrag.auftrnr
  JOIN kunde ON auftrag.kundnr = kunde.nr
  JOIN teilestamm ON auftragsposten.teilnr = teilestamm.teilnr
  JOIN lieferung ON teilestamm.teilnr = lieferung.teilnr
  JOIN lieferant ON lieferung.liefnr = lieferant.nr
WHERE kunde.name LIKE ?;