package grafos;

import java.util.ArrayList;
import java.util.HashMap;

public class Grafo {

    // LISTA DE ADYACENCIA
    private HashMap<String,
            ArrayList<String>> grafo;

    public Grafo() {

        grafo = new HashMap<>();
    }

    // AGREGAR NODO
    public void agregarNodo(String nodo) {

        if(!grafo.containsKey(nodo)) {

            grafo.put(
                nodo,
                new ArrayList<>()
            );
        }
    }

    // AGREGAR CONEXION
    public void agregarConexion(
            String origen,
            String destino
    ) {

        agregarNodo(origen);

        agregarNodo(destino);

        grafo.get(origen)
             .add(destino);
    }

    // MOSTRAR GRAFO
    public void mostrarGrafo() {

        for(String nodo : grafo.keySet()) {

            System.out.println(
                nodo + " -> "
                + grafo.get(nodo)
            );
        }
    }
    public void mostrarConexiones(
        String nodo
) {

    if(grafo.containsKey(nodo)) {

        System.out.println(
            nodo + " -> "
            + grafo.get(nodo)
        );

    } else {

        System.out.println(
            "Nodo no encontrado"
        );
    }
   }
    public void buscarClientesPorProducto(
        String producto
) {

    for(String nodo : grafo.keySet()) {

        ArrayList<String> conexiones =
            grafo.get(nodo);

        if(conexiones.contains(producto)) {

            System.out.println(
                nodo
            );
        }
    }
}
   @Override
public String toString() {

    String texto = "";

    for(String nodo : grafo.keySet()) {

        texto += nodo
            + " -> "
            + grafo.get(nodo)
            + "\n";
    }

    return texto;
} 
}