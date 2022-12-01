package de.hska.iwii.db1.jdbc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.math.BigDecimal;

public class Main {
  public static void main(String[] args) throws IOException, SQLException {
    Connection conn = DatabaseConnection.establishConnection();

    // reset database
    new JDBCBikeShop().reInitializeDB(conn);

    // aufgabe 4.3
    printAufgabe4_3(conn);
    printAufgabe4_3(conn, "Rafa%");

    // aufgabe 4.4
    // einfuegen
    insertKunde(conn, 7, "Pedal Pals", "Gaertnerplatz 7", 23980, "Lindau", "0");
    printTableData(conn, "SELECT * FROM kunde;");
    insertAuftrag(conn, 6, new java.sql.Date(System.currentTimeMillis()), 7, 2);
    printTableData(conn, "SELECT * FROM auftrag");
    insertAuftragsposten(conn, 61, 6, 300002, 1, new BigDecimal(370));
    printTableData(conn, "SELECT * FROM auftragsposten");

    // aktualisieren
    updateKunde(conn, 7, "1");
    printTableData(conn, "SELECT * FROM kunde;");

    // loeschen
    deleteKunde(conn, 7);
    printTableData(conn, "SELECT * FROM kunde;");

    DatabaseConnection.disconnectDatabase(conn);
  }

  public static String readFile(String path) throws IOException {
    return new String(Files.readAllBytes(Paths.get(path)));
  }

  public static void printAufgabe4_3(Connection conn) throws SQLException, IOException {
    String query = readFile("sql/4_3.sql");
    Statement st = conn.createStatement();
    ResultSet rs = st.executeQuery(query);
    PrintTableData.printResults(rs);
  }

  public static void printAufgabe4_3(Connection conn, String search) throws SQLException, IOException {
    String query = readFile("sql/4_3parameterized.sql");
    PreparedStatement st = conn.prepareStatement(query);
    st.setString(1, search);
    ResultSet rs = st.executeQuery();
    PrintTableData.printResults(rs);
  }

  public static void printTableData(Connection conn, String query) throws SQLException {
    Statement st = conn.createStatement();
    ResultSet rs = st.executeQuery(query);
    PrintTableData.printResults(rs);
  }

  public static void insertKunde(Connection conn, int nr, String name, String strasse, int plz, String ort,
      String sperre) throws SQLException {
    String query = "INSERT INTO kunde VALUES (?, ?, ?, ?, ?, ?)";
    PreparedStatement st = conn.prepareStatement(query);
    st.setInt(1, nr);
    st.setString(2, name);
    st.setString(3, strasse);
    st.setInt(4, plz);
    st.setString(5, ort);
    st.setString(6, sperre);
    st.executeUpdate();
  }

  public static void insertAuftrag(Connection conn, int auftrnr, Date datum, int kundnr, int persnr)
      throws SQLException {
    String query = "INSERT INTO auftrag VALUES (?, ?, ?, ?);";
    PreparedStatement st = conn.prepareStatement(query);
    st.setInt(1, auftrnr);
    st.setDate(2, datum);
    st.setInt(3, kundnr);
    st.setInt(4, persnr);
    st.executeUpdate();
  }

  public static void insertAuftragsposten(Connection conn, int posnr, int auftrnr, int teilnr, int anzahl,
      BigDecimal gesamtpreis) throws SQLException {
    String query = "INSERT INTO auftragsposten VALUES (?, ?, ?, ?, ?);";
    PreparedStatement st = conn.prepareStatement(query);
    st.setInt(1, posnr);
    st.setInt(2, auftrnr);
    st.setInt(3, teilnr);
    st.setInt(4, anzahl);
    st.setBigDecimal(5, gesamtpreis);
    st.executeUpdate();
  }

  public static void updateKunde(Connection conn, int nr, String sperre) throws SQLException {
    String query = "UPDATE kunde SET sperre = ? WHERE nr = ?;";
    PreparedStatement st = conn.prepareStatement(query);
    st.setString(1, sperre);
    st.setInt(2, nr);
    st.executeUpdate();
  }

  public static void deleteKunde(Connection conn, int nr) throws SQLException {
    // get auftrag
    String query = "SELECT auftrnr FROM auftrag WHERE kundnr = ?;";
    PreparedStatement st = conn.prepareStatement(query);
    st.setInt(1, nr);
    ResultSet rs = st.executeQuery();
    rs.next();
    int auftrnr = rs.getInt(1);
    System.out.println(auftrnr);

    // remove auftragsposten
    st = conn.prepareStatement("DELETE FROM auftragsposten WHERE auftrnr = ?");
    st.setInt(1, auftrnr);
    st.executeUpdate();

    // remove auftrag
    st = conn.prepareStatement("DELETE FROM auftrag WHERE kundnr = ?");
    st.setInt(1, nr);
    st.executeUpdate();
    st = conn.prepareStatement(query);
    st.setInt(1, 6);

    // remove kunde
    st = conn.prepareStatement("DELETE FROM kunde WHERE nr = ?;");
    st.setInt(1, nr);
    st.executeUpdate();
  }
}
