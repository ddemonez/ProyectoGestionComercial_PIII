package conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    public static Connection conectar() {

        try {

            Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:orcl",
                "ESTUDIANTE",
                "DKK1989"
            );

            System.out.println("Conexion exitosa");

            return con;

        } catch (Exception e) {

            System.out.println("Error: " + e);

            return null;
        }
    }
}