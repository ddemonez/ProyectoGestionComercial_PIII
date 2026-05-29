package grafos;

import conexion.Conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CargaGrafo {

    public static Grafo cargarGrafo() {

        Grafo grafo = new Grafo();

        try {

            Connection con =
                Conexion.conectar();

            Statement st =
                con.createStatement();

            String sql =

                "SELECT " +
                "C.NOMBRE AS CLIENTE, " +
                "F.ID_FACTURA, " +
                "P.NOMBRE AS PRODUCTO, " +
                "M.NOMBRE AS MARCA " +

                "FROM CLIENTE C " +

                "INNER JOIN FACTURA F " +
                "ON C.ID_CLIENTE = F.ID_CLIENTE " +

                "INNER JOIN DETALLE_FACTURA D " +
                "ON F.ID_FACTURA = D.ID_FACTURA " +

                "INNER JOIN PRODUCTO P " +
                "ON D.ID_PRODUCTO = P.ID_PRODUCTO " +

                "INNER JOIN MARCA M " +
                "ON P.ID_MARCA = M.ID_MARCA";

            ResultSet rs =
                st.executeQuery(sql);

            while(rs.next()) {

                String cliente =
                    rs.getString("CLIENTE");

                String factura =
                    "Factura "
                    + rs.getInt("ID_FACTURA");

                String producto =
                    rs.getString("PRODUCTO");

                String marca =
                    rs.getString("MARCA");

                // CLIENTE -> FACTURA
                grafo.agregarConexion(
                    cliente,
                    factura
                );

                // FACTURA -> PRODUCTO
                grafo.agregarConexion(
                    factura,
                    producto
                );

                // PRODUCTO -> MARCA
                grafo.agregarConexion(
                    producto,
                    marca
                );
            }

            con.close();

        } catch(Exception e) {

            System.out.println(e);
        }

        return grafo;
    }
}
