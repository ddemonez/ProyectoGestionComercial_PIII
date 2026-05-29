package reportes;

import conexion.Conexion;

import java.io.FileOutputStream;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.poi.xwpf.usermodel.*;

public class ReporteGrafoDOCX {

    public static void generar() {

        try {

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
                "REPORTE GRAFOS"
            );

            documento.createParagraph();

            Connection con =
                Conexion.conectar();

            String sql =
                "SELECT "
                + "C.NOMBRE AS CLIENTE, "
                + "F.ID_FACTURA, "
                + "EXTRACT(YEAR FROM "
                + "F.FECHA_FACTURA) "
                + "AS ANIO, "
                + "P.NOMBRE AS PRODUCTO, "
                + "D.CANTIDAD "
                + "FROM CLIENTE C "
                + "INNER JOIN FACTURA F "
                + "ON C.ID_CLIENTE = "
                + "F.ID_CLIENTE "
                + "INNER JOIN "
                + "DETALLE_FACTURA D "
                + "ON F.ID_FACTURA = "
                + "D.ID_FACTURA "
                + "INNER JOIN PRODUCTO P "
                + "ON D.ID_PRODUCTO = "
                + "P.ID_PRODUCTO";

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
            
            // GENERAMOS LA INFORMACION DENTRO DEL .DOCX
            // CREA 6 APARTADOS EN EL DOC
            while(rs.next()) { 

                XWPFParagraph p =
                    documento.createParagraph();

                XWPFRun run =
                    p.createRun();

                run.setBold(true);

                run.setText(
                    "Cliente: "
                    + rs.getString(
                        "CLIENTE"
                    )
                );

                run.addBreak();

                run.setText(
                    "Factura: "
                    + rs.getInt(
                        "ID_FACTURA"
                    )
                );

                run.addBreak();

                run.setText(
                    "Año: "
                    + rs.getInt(
                        "ANIO"
                    )
                );

                run.addBreak();

                run.setText(
                    "Producto: "
                    + rs.getString(
                        "PRODUCTO"
                    )
                );

                run.addBreak();

                run.setText(
                    "Cantidad: "
                    + rs.getInt(
                        "CANTIDAD"
                    )
                );

                run.addBreak();

                run.setText(
                    "Tiempo Busqueda: "
                    + tiempo + " ns"
                );

                run.addBreak();

                run.setText(
                    "------------------"
                );
            }

            FileOutputStream archivo =
                new FileOutputStream(
                    "ReporteGrafos.docx" // reporte
                );

            documento.write(archivo);

            archivo.close();

            documento.close();

            rs.close();

            ps.close();

            con.close();

            System.out.println(
                "Reporte Grafos generado."
            );

        } catch(Exception e) {

            System.out.println(e);
        }
    }
}