package reportes;

import grafos.Grafo;

import java.io.FileWriter;
import java.io.PrintWriter;

public class ReporteGrafo {

    public static void generar(
            Grafo grafo
    ) {

        try {

            FileWriter archivo =
                new FileWriter(
                    "ReporteGrafo.txt"
                );

            PrintWriter escritor =
                new PrintWriter(archivo);

            escritor.println(
                "===== REPORTE GRAFO ====="
            );

            escritor.println();

            escritor.println(
                grafo.toString()
            );

            escritor.close();

            System.out.println(
                "Reporte grafo generado."
            );

        } catch(Exception e) {

            System.out.println(e);
        }
    }
}
