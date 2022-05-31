package pao.service;

import pao.connections.DatabaseConnection;

import java.sql.*;

public class ClientService {

    private Audit audit;
    private Connection connection = DatabaseConnection.getDb();

    public ClientService() {
        audit = Audit.getInstance("data/Audit.csv");
    }

    public void creareTabel() {
        audit.scriereFisierAudit();
        String sqlCreate = "CREATE TABLE IF NOT EXISTS client" +
                "(nume varchar(50), prenume varchar(50), telefon varchar(20), email varchar(30), reducere int DEFAULT 0);";

        try {

            Statement stmt = connection.createStatement();
            stmt.execute(sqlCreate);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void adaugaClient(String nume, String prenume, String telefon, String email) {
        audit.scriereFisierAudit();
        String sqlInsert = "INSERT INTO client(nume, prenume, telefon, email) VALUES (?,?,?,?)";

        try {

            PreparedStatement stmt = connection.prepareStatement(sqlInsert);
            stmt.setString(1, nume);
            stmt.setString(2, prenume);
            stmt.setString(3, telefon);
            stmt.setString(4, email);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modificaReducereClient(String nume, int reducere) {
        audit.scriereFisierAudit();
        String sqlUpdate = "UPDATE client SET reducere=? WHERE nume=?";

        try {

            PreparedStatement stmt = connection.prepareStatement(sqlUpdate);
            stmt.setInt(1, reducere);
            stmt.setString(2, nume);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int obtineReducereClient(String nume) {
        audit.scriereFisierAudit();
        String sqlSelect = "SELECT reducere FROM client WHERE name=?";

        try {

            PreparedStatement stmt = connection.prepareStatement(sqlSelect);
            stmt.setString(1, nume);
            ResultSet set = stmt.executeQuery();
            return set.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public void stergeClient(String nume) {
        audit.scriereFisierAudit();
        String sqlDelete = "DELETE FROM client WHERE nume=?";

        try {

            PreparedStatement stmt = connection.prepareStatement(sqlDelete);
            stmt.setString(1,nume);
            stmt.executeUpdate();

            DatabaseConnection.closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
