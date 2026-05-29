package reportes;

import hash.TablaHash;

import java.io.FileWriter;
import java.io.PrintWriter;

public class ReporteHash {

    public static void generar(
            TablaHash tabla
    ) {

        try {

            FileWriter archivo =
                new FileWriter(
                    "ReporteHash.txt"
                );

            PrintWriter escritor =
                new PrintWriter(archivo);

            escritor.println(
                "===== REPORTE HASH ====="
            );

            escritor.println();

            escritor.println(
                "Colisiones: "
                + tabla.getColisiones()
            );

            escritor.close();

            System.out.println(
                "Reporte hash generado."
            );

        } catch(Exception e) {

            System.out.println(e);
        }
    }
}
