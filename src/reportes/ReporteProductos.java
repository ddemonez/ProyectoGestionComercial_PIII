package reportes;

import hash.TablaHash;
import hash.NodoHash;

import java.io.FileWriter;
import java.io.PrintWriter;

public class ReporteProductos {

    public static void generar(
            TablaHash tabla
    ) {

        try {

            FileWriter archivo =
                new FileWriter(
                    "ReporteProductos.txt"
                );

            PrintWriter escritor =
                new PrintWriter(archivo);

            escritor.println(
                "===== REPORTE PRODUCTOS ====="
            );

            escritor.println();

            NodoHash[] arreglo =
                tabla.getTabla();

            for(int i = 0;
                i < arreglo.length;
                i++) {

                NodoHash actual =
                    arreglo[i];

                while(actual != null) {

                    escritor.println(
                        "ID: "
                        + actual.producto
                            .getIdProducto()
                    );

                    escritor.println(
                        "Nombre: "
                        + actual.producto
                            .getNombre()
                    );

                    escritor.println(
                        "Precio: Q"
                        + actual.producto
                            .getPrecio()
                    );

                    escritor.println(
                        "Stock: "
                        + actual.producto
                            .getStock()
                    );

                    escritor.println(
                        "-------------------"
                    );

                    actual =
                        actual.siguiente;
                }
            }

            escritor.close();

            System.out.println(
                "Reporte generado."
            );

        } catch(Exception e) {

            System.out.println(e);
        }
    }
}