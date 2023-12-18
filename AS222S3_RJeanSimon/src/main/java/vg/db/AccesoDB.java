package vg.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class AccesoDB {

    public static Connection cnx = null;

    public static Connection conectar() throws Exception {
        try {
            String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            String url = "jdbc:sqlserver://localhost:1433;databaseName=H232_RJeanSimon;encrypt=true;TrustServerCertificate=True;";
            String user = "sa";
            String pwd = "jeansimon16";

            Class.forName(driver);
            cnx = DriverManager.getConnection(url, user, pwd);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error de Conexión" + "  " + e.getMessage() + "  " + e.getStackTrace());
        }
        return cnx;
    }

    public static void main(String[] args) throws Exception {
        try {
            AccesoDB.conectar();
            if (AccesoDB.cnx != null) {
                System.out.println("Estas Conectado mi king color kong");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error " + e.getMessage());
        }
    }

    public void cerrar() throws Exception {
        if (cnx != null) {
            cnx.close();
            System.out.println("Cerrado");
        }
    }

}
