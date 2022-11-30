package de.hska.iwii.db1.jdbc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;

public class Main {
  public static void main(String[] args) throws IOException, SQLException {
    // aufgabe 4.3
    Connection conn = DatabaseConnection.establishConnection();
    printQueryTable(conn);
    printQueryTable(conn, "Rafa%");
    DatabaseConnection.disconnectDatabase(conn);
  }

  public static String readFile(String path) throws IOException {
    return new String(Files.readAllBytes(Paths.get(path)));
  }

  public static void printQueryTable(Connection conn) throws SQLException, IOException {
    String query = readFile("sql/4_3.sql");
    Statement st = conn.createStatement();
    ResultSet rs = st.executeQuery(query);
    PrintTableData.printResults(rs);
  }

  public static void printQueryTable(Connection conn, String search) throws SQLException, IOException {
    String query = readFile("sql/4_3parameterized.sql");
    PreparedStatement st = conn.prepareStatement(query);
    st.setString(1, search);
    ResultSet rs = st.executeQuery();
    PrintTableData.printResults(rs);
  }
}
