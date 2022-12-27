package de.hska.iwii.db1.weather.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Wettervorhersagen fuer mehrere Tage.
 */
@JsonIgnoreProperties(value = {"forecast1", "forecast2", "forecastStart", "warnings", "threeHourSummaries"})
public class WeatherForecast {

	// Wettervorhersagen fuer mehrere Tage 
	@JsonProperty("days")
	List<Weather> weather = new ArrayList<>();

	/**
	 * Erzeugt eine neue Wettervorhersage.
	 */
	public WeatherForecast() {
	}

	/**
	 * Liest die Vorhersagen fuer mehrere Tage aus.
	 * @return Vorhersagen fuer mehrere Tage.
	 */
	public List<Weather> getWeather() {
		return weather;
	}

	/**
	 * Traegt die Vorhersagen fuer mehrere Tage ein.
	 * @param weather Vorhersagen fuer mehrere Tage.
	 */
	public void setWeather(List<Weather> weather) {
		this.weather = weather;
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
