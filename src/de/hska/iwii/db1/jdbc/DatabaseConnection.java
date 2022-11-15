package de.hska.iwii.db1.jdbc;

import java.sql.*;
import java.util.Properties;

public class DatabaseConnection { 
  public static void main(String args[]) throws ClassNotFoundException {
    Connection conn = establishConnection();

  }
  public static Connection establishConnection(){
    String url = "jdbc:postgresql://datenbanken1.ddns.net:3690/";
    Properties props = new Properties();
    props.setProperty("user", "g22");
    props.setProperty("password", "agmbQckaKCj");

    try {
      Connection conn = DriverManager.getConnection(url, props);
      return conn;
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }
}
