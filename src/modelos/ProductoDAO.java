package modelos;

import conexion.Conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ProductoDAO {

    public void mostrarProductos() {

        try {

            Connection con = Conexion.conectar();

            Statement st = con.createStatement();

            ResultSet rs =
                st.executeQuery("SELECT * FROM PRODUCTO");

            while(rs.next()) {

                int id = rs.getInt("ID_PRODUCTO");

                String nombre =
                    rs.getString("NOMBRE");

                double precio =
                    rs.getDouble("PRECIO");

                System.out.println(
                    id + " - " +
                    nombre + " - Q" +
                    precio
                );
            }

            con.close();

        } catch(Exception e) {

            System.out.println(e);

        }
    }
}