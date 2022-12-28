import java.util.List;

import de.hska.iwii.db1.weather.model.Weather;
import de.hska.iwii.db1.weather.reader.WeatherReader;

public class Main {

  public static void main(String[] args) {
    final long STATION_IDS[] = { 10519, 12828, 8099, 11675, 11828 };
    WeatherReader wr = new WeatherReader();
    for (long id : STATION_IDS) {
      List<Weather> ws = wr.readWeatherForecast(id).getWeather();
      for (Weather w : ws) {
        System.out.println(w.getDate() + " " + w.getMaxTemp());
      }
    }
  }
}
