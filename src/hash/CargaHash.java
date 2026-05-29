package hash;

import conexion.Conexion;
import modelos.Producto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CargaHash {

    public static TablaHash cargarProductos() {

        TablaHash tabla =
            new TablaHash(50);

        try {

            Connection con =
                Conexion.conectar();

            Statement st =
                con.createStatement();

            ResultSet rs =
                st.executeQuery(
                    "SELECT * FROM PRODUCTO"
                );

            long inicio =
                System.nanoTime();

            while(rs.next()) {

                Producto p =
                    new Producto(

                        rs.getInt("ID_PRODUCTO"),

                        rs.getString("NOMBRE"),

                        rs.getDouble("PRECIO"),

                        rs.getInt("STOCK"),

                        rs.getInt("ID_MARCA")
                    );

                tabla.insertar(p);
            }

            long fin =
                System.nanoTime();

            System.out.println(
                "Tiempo de carga: "
                + (fin - inicio)
                + " ns"
            );

            System.out.println(
                "Colisiones: "
                + tabla.getColisiones()
            );

            con.close();

        } catch(Exception e) {

            System.out.println(e);
        }

        return tabla;
    }
}
