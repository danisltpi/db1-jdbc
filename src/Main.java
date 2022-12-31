import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import de.hska.iwii.db1.jdbc.DatabaseConnection;
import de.hska.iwii.db1.weather.model.Weather;
import de.hska.iwii.db1.weather.model.WeatherForecast;
import de.hska.iwii.db1.weather.reader.WeatherReader;

public class Main {

  private static final long STATION_IDS[] = { 11032, 10519 };

  public static void main(String[] args) throws SQLException, IOException {
    Connection conn = DatabaseConnection.establishConnection();
    insertRowsInBatch(conn, STATION_IDS);
    conn.close();
  }

  public static void insertRowsInBatch(Connection conn, long[] stationIds) throws SQLException, IOException {
    String query = readFile("src/sql/insert_prepare.sql");

    PreparedStatement ps = conn.prepareStatement(query);

    WeatherReader wr = new WeatherReader();
    for (long stationId : stationIds) {
      WeatherForecast forecast = wr.readWeatherForecast(stationId);
      if (forecast != null) {
        for (Weather weather : forecast.getWeather()) {
          ps.setLong(1, stationId);
          ps.setDate(2, new java.sql.Date(System.currentTimeMillis()));
          ps.setDate(3, new java.sql.Date(weather.getDate().getTime()));
          ps.setFloat(4, weather.getMinTemp());
          ps.setFloat(5, weather.getMaxTemp());
          ps.setInt(6, weather.getPrecipitation());
          ps.setInt(7, weather.getSunshine());
          ps.addBatch();
        }
      }
    }

    ps.executeBatch();
    System.out.println("Success!");
  }

  public static String readFile(String path) throws IOException {
    return new String(Files.readAllBytes(Paths.get(path)));
  }

}
