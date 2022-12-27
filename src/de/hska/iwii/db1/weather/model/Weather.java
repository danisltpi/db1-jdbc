package de.hska.iwii.db1.weather.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Wettervorhersage fuer einen Tag
 */
@JsonIgnoreProperties(value = {"stationId", "icon", "icon1", "icon2", "windGust", "windDirection", "windSpeed"})
public class Weather {

	// Datum, fuer das die Vorhersage gueltig ist.
	@JsonProperty("dayDate")
	private Date date;
	
	// Tagestiefstemperatur in Zentelgrad.
	@JsonProperty("temperatureMin")
	private float minTemp;

	// Tageshoechstemperatur in Zentelgrad.
	@JsonProperty("temperatureMax")
	private float maxTemp;
	
	// Niederschlagsmenge
//	@JsonProperty("precipitation")
	private int precipitation;
	
	// Dauer des Sonnenscheins in Minuten
	private int sunshine;

	/**
	 * Erzeugt eine neue Wetterangabe.
	 */
	public Weather() {
	}
	
	/**
	 * Liest das Datum aus, fuer das diese Wetterangabe gueltig ist.
	 * @return Datum, fuer das diese Wetterangabe gueltig ist.
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Traegt das Datum ein, fuer das diese Wetterangabe gueltig ist.
	 * @param applicableDate Datum, fuer das diese Wetterangabe gueltig ist.
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	
	/**
	 * Liest die Tagestieftstemperatur in Zentelgrad aus.
	 * @return Tagestieftstemperatur in Grad Celsius.
	 */
	public float getMinTemp() {
		return minTemp;
	}

	/**
	 * Traegt die Tagestieftstemperatur in Zentelgrad ein.
	 * @param minTemp Tagestieftstemperatur in Grad Celsius.
	 */
	public void setMinTemp(float minTemp) {
		this.minTemp = minTemp;
	}

	/**
	 * Liest die Tageshoechstemperatur in Zentelgrad aus.
	 * @return Tageshoechstemperatur in Grad Celsius.
	 */
	public float getMaxTemp() {
		return maxTemp;
	}

	/**
	 * Traegt die Tageshoechstemperatur in Zentelgrad ein.
	 * @param maxTemp Tageshoechstemperatur in Grad Celsius.
	 */
	public void setMaxTemp(float maxTemp) {
		this.maxTemp = maxTemp;
	}
	
	/**
	 * Liest die Niederschlagsmenge aus.
	 * @return Niederschlagsmenge.
	 */
	public int getPrecipitation() {
		return precipitation;
	}

	/**
	 * Traegt die Niederschlagsmenge ein.
	 * @param precipitation Niederschlagsmenge.
	 */
	public void setPrecipitation(int precipitation) {
		this.precipitation = precipitation;
	}
	
	/**
	 * Liest die Dauer des Sonnenscheins aus.
	 * @return Dauer des Sonnenscheins in Minuten.
	 */
	public int getSunshine() {
		return sunshine;
	}

	/**
	 * Traegt die Dauer des Sonnenscheins ein.
	 * @param sunshine Dauer des Sonnenscheins in Minuten.
	 */
	public void setSunshine(int sunshine) {
		this.sunshine = sunshine;
	}
	
	/**
	 * Fuer den Fall undokumentierter Properties.
	 * @param key Schluessel des Properties.
	 * @param value Wert des Properties.
	 */
	@JsonAnySetter
	public void addAdditionalInformation(String key, Object value) {
	}
}
