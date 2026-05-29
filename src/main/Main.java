package main;

import grafos.CargaGrafo;
import grafos.Grafo;
// TEST PARA LOS GRAFOS
public class Main {

    public static void main(String[] args) {

        Grafo grafo =
            CargaGrafo.cargarGrafo();

        grafo.mostrarGrafo();
    }
}