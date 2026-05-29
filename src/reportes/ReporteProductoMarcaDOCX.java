package reportes;

import conexion.Conexion;

import java.io.FileOutputStream;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.poi.xwpf.usermodel.*;

public class ReporteProductoMarcaDOCX {

    public static void generar() {

        try {

            // DOCUMENTO
            XWPFDocument documento =
                new XWPFDocument();

            // TITULO
            XWPFParagraph titulo =
                documento.createParagraph();

            titulo.setAlignment(
                ParagraphAlignment.CENTER
            );

            XWPFRun runTitulo =
                titulo.createRun();

            runTitulo.setBold(true);

            runTitulo.setFontSize(18);

            runTitulo.setText(
                "REPORTE PRODUCTO - MARCA"
            );

            documento.createParagraph();

            // TABLA
            XWPFTable tabla =
                documento.createTable(1, 3);

            // ENCABEZADOS
            XWPFTableRow fila0 =
                tabla.getRow(0);

            fila0.getCell(0)
                .setText("Producto");

            fila0.getCell(1)
                .setText("Marca");

            fila0.getCell(2)
                .setText(
                    "Tiempo Busqueda"
                );

            // CONEXION
            Connection con =
                Conexion.conectar();

            String sql =
                "SELECT "
                + "P.NOMBRE AS PRODUCTO, "
                + "M.NOMBRE AS MARCA "
                + "FROM PRODUCTO P "
                + "INNER JOIN MARCA M "
                + "ON P.ID_MARCA = M.ID_MARCA";

            PreparedStatement ps =
                con.prepareStatement(sql);

            long inicio =
                System.nanoTime();

            ResultSet rs =
                ps.executeQuery();

            long fin =
                System.nanoTime();

            long tiempo =
                fin - inicio;

            // RECORRER RESULTADOS
            while(rs.next()) {

                XWPFTableRow fila =
                    tabla.createRow();

                fila.getCell(0)
                    .setText(
                        rs.getString(
                            "PRODUCTO"
                        )
                    );

                fila.getCell(1)
                    .setText(
                        rs.getString(
                            "MARCA"
                        )
                    );

                fila.getCell(2)
                    .setText(
                        tiempo + " ns"
                    );
            }

            // GUARDAR
            FileOutputStream archivo =
                new FileOutputStream(
                    "ReporteProductoMarca.docx"
                );

            documento.write(archivo);

            archivo.close();

            documento.close();

            rs.close();

            ps.close();

            con.close();

            System.out.println(
                "Reporte Producto-Marca generado."
            );

        } catch(Exception e) {

            System.out.println(e);
        }
    }
}