package de.hska.iwii.db1.jdbc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;

public class Main {
  public static void main(String[] args) throws IOException, SQLException {
    // aufgabe 4.3
    Connection conn = DatabaseConnection.establishConnection();
    Statement st = conn.createStatement();
    String query = getQuery();
    ResultSet rs = st.executeQuery(query);
    PrintTableData.printResults(rs);
    DatabaseConnection.disconnectDatabase(conn);
  }

  public static String getQuery() throws IOException {
    return new String(Files.readAllBytes(Paths.get("sql/4_3.sql")));
  }
}
