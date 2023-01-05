package de.hska.iwii.db1.jdbc;

import java.sql.*;

public class PrintTableData {
  public static void printResults(ResultSet rs) {
    try {
      ResultSetMetaData rsmd = rs.getMetaData();
      int cols = rsmd.getColumnCount();
      // header col names
      String header = "";
      for (int col = 1; col <= cols; col++) {
        int colWidth = rsmd.getColumnDisplaySize(col);
        String colName = rsmd.getColumnLabel(col);
        header += String.format("%-" + colWidth + "s", colName);
        header += " | ";
      }
      header += "\n";
      // header col types
      for (int col = 1; col <= cols; col++) {
        int colWidth = rsmd.getColumnDisplaySize(col);
        String colType = rsmd.getColumnTypeName(col);
        header += String.format("%-" + colWidth + "s", colType);
        header += " | ";
      }
      header += "\n";
      // separator
      for (int col = 1; col <= cols; col++) {

        // use the column size of the header if the column size of the data is too short
        int colWidth = rsmd.getColumnDisplaySize(col);
        String colName = rsmd.getColumnName(col);
        String colType = rsmd.getColumnTypeName(col);
        int colNameWidth = colName.length() >= colType.length() ? colName.length() : colType.length();
        colWidth = colWidth >= colNameWidth ? colWidth : colNameWidth;

        header += String.format("%-" + colWidth + "s", "-").replace(" ", "-");
        header += "-+-";
      }
      System.out.println(header);

      // print actual table data
      while (rs.next()) {
        String row = "";
        for (int col = 1; col <= cols; col++) {

          // use the column size of the header if the column size of the data is too short
          int colWidth = rsmd.getColumnDisplaySize(col);
          String colName = rsmd.getColumnName(col);
          String colType = rsmd.getColumnTypeName(col);
          int colNameWidth = colName.length() >= colType.length() ? colName.length() : colType.length();
          colWidth = colWidth >= colNameWidth ? colWidth : colNameWidth;

          String value = rs.getString(col);
          row += String.format("%" + colWidth + "s", value);
          row += " | ";
        }
        System.out.println(row);
      }
      System.out.println();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}