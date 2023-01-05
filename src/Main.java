import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import de.hska.iwii.db1.jdbc.DatabaseConnection;
import de.hska.iwii.db1.jdbc.PrintTableData;
import de.hska.iwii.db1.weather.model.Weather;
import de.hska.iwii.db1.weather.model.WeatherForecast;
import de.hska.iwii.db1.weather.reader.WeatherReader;

public class Main {

  private static final long STATION_IDS[] = { 11032, 10519, 10763, 10637, 10385 };

  public static void main(String[] args) throws SQLException, IOException {
    Connection conn = DatabaseConnection.establishConnection();
    // insertRowsInBatch(conn, STATION_IDS);
    printVorhersagen(conn, STATION_IDS[3]);

    LocalDate d = LocalDate.of(2023, 1, 6);

    printStationId(conn, Date.valueOf(d), 20, 100);
    conn.close();
  }

  public static void printStationId(Connection conn, java.sql.Date date, float minTemp, float maxTemp)
      throws SQLException {
    String query = "SELECT station, min_temp, max_temp FROM vorhersage WHERE (vorhersage_datum = ?) AND min_temp > ? AND max_temp < ?";
    PreparedStatement ps = conn.prepareStatement(query);
    ps.setDate(1, date);
    ps.setFloat(2, minTemp);
    ps.setFloat(3, maxTemp);
    ResultSet rs = ps.executeQuery();
    PrintTableData.printResults(rs);
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

  public static void printVorhersagen(Connection conn, Long stationId) throws SQLException {
    String query = "SELECT * FROM vorhersage WHERE station = ?";
    PreparedStatement ps = conn.prepareStatement(query);
    ps.setLong(1, stationId);
    ResultSet rs = ps.executeQuery();
    PrintTableData.printResults(rs);
  }

}
