package de.hska.iwii.db1.jdbc;

import java.sql.*;

public class PrintTableData {
  public static void main(String[] args) {
    try {
      Connection conn = DatabaseConnection.establishConnection();
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery("SELECT persnr, name, ort, aufgabe FROM personal");
      printResults(rs);
      rs.close();
      st.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void printResults(ResultSet rs) {
    try {
      ResultSetMetaData rsmd = rs.getMetaData();
      int columnsNumber = rsmd.getColumnCount();
      while (rs.next()) {
        for (int i = 1; i <= columnsNumber; i++) {
          if (i > 1)
            System.out.print(",  ");
          String columnValue = rs.getString(i);
          System.out.print(columnValue + " " + rsmd.getColumnName(i));
        }
        System.out.println("");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
