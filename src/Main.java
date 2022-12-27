import de.hska.iwii.db1.weather.reader.WeatherReader;

public class Main {

  public static void main(String[] args) {
    final long STATION_ID = 10519;
    WeatherReader wr = new WeatherReader();
    wr.readWeatherForecast(STATION_ID);
  }
}
