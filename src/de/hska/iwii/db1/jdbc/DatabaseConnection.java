package de.hska.iwii.db1.jdbc;

import java.sql.*;
import java.util.Properties;

public class DatabaseConnection { 
  public static void main(String args[]) throws ClassNotFoundException {
    String url = "jdbc:postgresql://datenbanken1.ddns.net:3690/";
    Properties props = new Properties();
    props.setProperty("user", "g22");
    props.setProperty("password", "agmbQckaKCj");

    try {
      Connection conn = DriverManager.getConnection(url, props);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
