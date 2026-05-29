package main;

import grafos.CargaGrafo;
import grafos.Grafo;

public class Main {

    public static void main(String[] args) {

        Grafo grafo =
            CargaGrafo.cargarGrafo();

        grafo.mostrarGrafo();
    }
}