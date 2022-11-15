package de.hska.iwii.db1.jdbc;

import java.sql.*;
import java.util.Properties;

public class DatabaseConnection {
  public static void main(String args[]) throws ClassNotFoundException {
    Connection conn = establishConnection();
    getInfo(conn);
    disconnectDatabase(conn);
  }

  public static Connection establishConnection() {
    String url = "jdbc:postgresql://datenbanken1.ddns.net:3690/";
    Properties props = new Properties();
    props.setProperty("user", "g22");
    props.setProperty("password", "agmbQckaKC");

    try {
      Connection conn = DriverManager.getConnection(url, props);
      return conn;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public static void getInfo(Connection conn) {
    try {
      DatabaseMetaData metaData = conn.getMetaData();
      String dbName = metaData.getDatabaseProductName();
      String dbDriver = metaData.getDriverName();

      String information = String.format("Database Name: %s\nDriver: %s", dbName, dbDriver);
      System.out.println(information);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void disconnectDatabase(Connection conn) {
    try {
      conn.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
