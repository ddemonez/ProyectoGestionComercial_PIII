package reportes;

import hash.NodoHash;
import hash.TablaHash;

import java.io.FileOutputStream;

import org.apache.poi.xwpf.usermodel.*;


public class ReporteProductosDOCX {

    public static void generar(
            TablaHash tabla
    ) {

        try {

            // CREAR DOCUMENTO
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
                "REPORTE DE PRODUCTOS" // titulo
            );

            // SALTO
            documento.createParagraph();

            // TABLA
            XWPFTable tablaDoc =
                documento.createTable(); // creamos la tabla del .docx

            // ENCABEZADOS de la tabla
            XWPFTableRow fila0 =
                tablaDoc.getRow(0); 

            fila0.getCell(0).setText("ID");

            fila0.addNewTableCell()
                .setText("Producto");

            fila0.addNewTableCell()
                .setText("Precio");

            fila0.addNewTableCell()
                .setText("Hash");

            fila0.addNewTableCell()
                .setText("Posicion");

            fila0.addNewTableCell()
                .setText("Tiempo Busqueda");

            // RECORRER HASH
            NodoHash[] arreglo =
                tabla.getTabla();

            for(int i = 0;
                i < arreglo.length;
                i++) {

                NodoHash actual =
                    arreglo[i];

                while(actual != null) {

    // MEDIR TIEMPO BUSQUEDA
                long inicio =
                    System.nanoTime();

                    tabla.buscar(
                        actual.producto
                            .getIdProducto()
                    );

                long fin =
                    System.nanoTime();

                long tiempo =
                    fin - inicio;

    // HASH CALCULADO
                int posicion =
                    tabla.obtenerPosicion(
                        actual.producto
                        .getIdProducto()
                );

    // CREAR FILA
                XWPFTableRow fila =
                    tablaDoc.createRow();

    // ID
                fila.getCell(0)
                    .setText(
                        String.valueOf(
                        actual.producto
                            .getIdProducto()
                        )
                    );

    // PRODUCTO
                fila.getCell(1)
                    .setText(
                        actual.producto
                        .getNombre()
                    );

    // PRECIO
                fila.getCell(2)
                    .setText(
                    "Q"
                    + actual.producto
                        .getPrecio()
                    );

    // HASH
                fila.getCell(3)
                    .setText(
                        String.valueOf(posicion)
                    );

    // POSICION TABLA
                fila.getCell(4)
                    .setText(
                        String.valueOf(posicion)
                    );

    // TIEMPO BUSQUEDA
                fila.getCell(5)
                    .setText(
                        tiempo + " ns"
                    );

                    actual =
                    actual.siguiente;
                }
            }

            // GUARDAR
            FileOutputStream archivo =
                new FileOutputStream(
                    "ReporteProductos.docx"
                );

            documento.write(archivo);

            archivo.close();

            documento.close();

            System.out.println(
                "Reporte DOCX generado."
            );

        } catch(Exception e) {

            System.out.println(e);
        }
    }
}