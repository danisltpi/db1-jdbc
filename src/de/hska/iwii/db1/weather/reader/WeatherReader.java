package de.hska.iwii.db1.weather.reader;

import java.io.IOException;
import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.hska.iwii.db1.weather.model.WeatherForecast;

/**
 * Reader zum Lesen und Parsen der Wetterdaten.
 */
public class WeatherReader {

	private static String BASE_URL = "https://dwd.api.proxy.bund.dev/v30/stationOverviewExtended?stationIds=";
	
	private ObjectMapper objectMapper = new ObjectMapper();
	private HttpClient client;
	
	/**
	 * Erzeugt den Parser fuer die Verwendung ohne Proxy-Server.
	 */
	public WeatherReader() {
		client = HttpClient.newBuilder()
				.version(Version.HTTP_2)
				.followRedirects(Redirect.ALWAYS)
				.build();
	}

	/**
	 * Erzeugt den Parser fuer die Verwendung mit authentifizierendem Proxy-Server.
	 * @param proxyAddress Adresse des Proxy-Servers (proxy.hs-karlsruhe.de im
	 * 			Fall der Hochschule Karlsruhe).
	 * @param proxyPort Port des Proxy-Servers (8888 im
	 * 			Fall der Hochschule Karlsruhe).
	 * @param proxyUser IZ-Nutzername.
	 * @param proxyPassword IZ-Passwort.
	 * 
	 */
	public WeatherReader(String proxyAddress, int proxyPort, String proxyUser, String proxyPassword) {
		System.setProperty("jdk.http.auth.tunneling.disabledSchemes", "");
		Authenticator.setDefault(new ProxyAuthenticator(proxyUser, proxyPassword));
		client = HttpClient.newBuilder()
				.version(Version.HTTP_2)
				.followRedirects(Redirect.ALWAYS)
				.proxy(ProxySelector.of(new InetSocketAddress(proxyAddress, proxyPort)))
				.authenticator(new ProxyAuthenticator(proxyUser, proxyPassword))
				.build();
	}

	/**
	 * Liest die JSON-Daten ueber eine URL aus.
	 * @param stationId ID der Wetterstation.
	 * @return JSON-Daten oder eine leere Zeichenkette im Fehlerfall.
	 * @throws IOException
	 * @throws InterruptedException
	 */
	private String readData(long stationId) throws IOException, InterruptedException {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(BASE_URL + stationId))
				.build();
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		return response.statusCode() == 200 ? response.body() : "";
	}

	/**
	 * Liest die Vorhersage fuer eine Wetterstation aus.
	 * @param stationId ID der Wetterstation.
	 * @return Vorhersage fuer eine Wetterstation <code>null</code>.
	 * 			Im Fall eines Fehlers wird ebenfalls <code>null</code>
	 * 			zurueckgegeben.
	 */
	public WeatherForecast readWeatherForecast(long stationId) {
		try {
			String weather = readData(stationId);
			if (weather.length() > 0) {
				// Etwas Muell beseitigen, um das Parsen zu vereinfachen.
				int index = weather.indexOf("\"days\":[");
				if (index > 0) {
					weather = "{" + weather.substring(index).trim();
					weather = weather.substring(0, weather.length() - 1);
				}
				return objectMapper.readValue(weather, WeatherForecast.class);
			}
		} catch (IOException | InterruptedException e) {
			System.err.println(e);
		}
		return null;
	}
}
