
import java.io.IOException;

import de.hska.iwii.db1.weather.model.Weather;
import de.hska.iwii.db1.weather.model.WeatherForecast;
import de.hska.iwii.db1.weather.reader.WeatherReader;

/**
 * Demo-Klasse fuer den Zugriff auf das Wetter der Stadt Karlsruhe. 
 */
public class DemoWeather {

	public static void main(String[] args) throws IOException {
		// 1. Erzeugt ein WeatherReader-Objekt fuer die komplette
		//    Serverkommunikation. Fuer den Zugriff uber den
		//    Proxy der Hochschule muss der zweite Konstruktur mit
		//    den Proxy-Parametern verwendet werden.
		//    Proxy-Server: proxy.hs-karlsruhe.de
		//    Port des Proxy-Servers: 8888
		WeatherReader reader = new WeatherReader();
		
		// 2. Auslesen von Informationen ueber einen oder mehrere Orte.
		// Die Liste der Stationen ist hier verlinkt (4. Spalte enthaelt die ID):
		// https://www.dwd.de/DE/leistungen/klimadatendeutschland/statliste/statlex_html.html
		// Beispiele:
		// 10519: Karlsruhe Durlach
		// 10321: Stuttgart-Degerloch
		// Direktes Aufrufen der API:
		// https://dwd.api.bund.dev/
		WeatherForecast forecast = reader.readWeatherForecast(10519);
		if (forecast != null) {
			for (Weather weather: forecast.getWeather()) {
				System.out.println(weather.getDate() + ", " + weather.getMinTemp() / 10.0 + ", " + weather.getMaxTemp() / 10.0);
			}
		}
	}
}
