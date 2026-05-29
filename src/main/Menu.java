package main;

import grafos.CargaGrafo;
import grafos.Grafo;

import hash.CargaHash;
import hash.TablaHash;

import modelos.Producto;

import java.util.Scanner;
import reportes.ReporteProductosDOCX;
import reportes.ReporteGrafoDOCX;
import reportes.ReporteProductoMarcaDOCX;

public class Menu { /// creamos el menu con las opciones principales 

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        TablaHash tablaHash = null;

        Grafo grafo = null;

        int opcion;

        do {

            System.out.println("\n===== SISTEMA GESTION COMERCIAL =====");

            System.out.println("1. Cargar productos en Hash");

            System.out.println("2. Buscar producto");

            System.out.println("3. Mostrar tabla hash");

            System.out.println("4. Cargar y mostrar grafo");

            System.out.println("5. Buscar relaciones de un nodo");

            System.out.println("6. Buscar clientes por producto");

            System.out.println("7. Generar reporte productos DOCX");

            System.out.println("8. Generar Reporte Producto Marca DOCX");

            System.out.println("9. Generar reporte grafo DOCX");

            System.out.println("10. Salir");

            System.out.print("Seleccione opcion: ");

            opcion = sc.nextInt();

            switch(opcion) {

                case 1:

                    tablaHash =
                        CargaHash.cargarProductos();

                    System.out.println(
                        "Productos cargados correctamente." // debemos cragar primero la tabla hash
                    );

                    break;

                case 2:

                    if(tablaHash == null) {

                        System.out.println(
                            "Primero debe cargar el hash." // las opciones del menu, necesitan iniciarse primero
                        );

                    } else {

                        System.out.print(
                            "Ingrese ID producto: "
                        );

                        int id =
                            sc.nextInt();

                        long inicio =
                            System.nanoTime();

                        Producto p =
                            tablaHash.buscar(id);

                        long fin =
                            System.nanoTime();

                        if(p != null) {

                            System.out.println(
                                "Producto encontrado:" // en caso de no encontrar los elementos
                            );

                            System.out.println(p);

                        } else {

                            System.out.println(
                                "Producto no encontrado." // en caso de no encontrar los elementos
                            );
                        }

                        System.out.println(
                            "Tiempo busqueda: " // tiempo de busqueda
                            + (fin - inicio)
                            + " ns"
                        );
                    }

                    break;

                case 3:

                    if(tablaHash == null) {

                        System.out.println(
                            "Primero cargue el hash." 
                        );

                    } else {

                        tablaHash.mostrarTabla();
                    }

                    break;

                case 4:

                    grafo =
                        CargaGrafo.cargarGrafo();

                    grafo.mostrarGrafo();

                    break;

                case 5:

                    if(grafo == null) {

                        System.out.println(
                            "Primero cargue el grafo."
                        );

                    } else {

                        sc.nextLine();

                        System.out.print(
                            "Ingrese nodo: " /// deben ser los nombres
                        );

                        String nodo =
                            sc.nextLine();

                        grafo.mostrarConexiones(nodo);
                    }

                    break;

                case 6:

                    if(grafo == null) {

                        System.out.println(
                            "Primero cargue el grafo."
                        );

                    } else {

                        sc.nextLine();

                        System.out.print(
                            "Ingrese producto: "
                        );

                        String producto =
                            sc.nextLine();

                        grafo.buscarClientesPorProducto(
                            producto
                        );
                    }

                    break;

                case 7:

                    if(tablaHash == null) {

                        System.out.println("Primero cargue hash.");

                    } else {

                         ReporteProductosDOCX.generar(tablaHash);
                        }
                    break;
                    
                case 8:

                    if(tablaHash == null) {

                        System.out.println("Primero cargue hash.");

                    } else {

                        ReporteProductoMarcaDOCX.generar();
                    }

                    break;
                    
                case 9:

                    if(grafo == null) {

                        System.out.println("Primero cargue el grafo."); // necesitamos el grafo antes, opcion no.4

                    } else {

                        ReporteGrafoDOCX.generar(       );
                    }

                    break;

                default:

                    System.out.println(
                        "Opcion invalida."
                    );
            }

        } while(opcion != 10);

        sc.close();
    }
}