package pao.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/proiect?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "parolalab11";

    private  static Connection db;

    private DatabaseConnection() { }

    public static Connection getDb(){

        try {
            if (db == null || db.isClosed()){
                db = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return db;
    }

    public static void closeConnection() {
        try {
            if (db != null && !db.isClosed()){
                db.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
