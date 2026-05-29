package hash;

import modelos.Producto;

public class TablaHash {

    private NodoHash[] tabla;

    private int colisiones;

    public TablaHash(int tamaño) {

        tabla = new NodoHash[tamaño];

        colisiones = 0;
    }

    // METODO DIVISION
    public int funcionHash(int clave) {

        return clave % tabla.length;
    }

    // INSERTAR
    public void insertar(Producto producto) {

        int indice =
            funcionHash(producto.getIdProducto());

        NodoHash nuevo =
            new NodoHash(producto);

        // SI HAY COLISION
        if(tabla[indice] != null) {

            colisiones++;

            NodoHash actual =
                tabla[indice];

            while(actual.siguiente != null) {

                actual = actual.siguiente;
            }

            actual.siguiente = nuevo;

        } else {

            tabla[indice] = nuevo;
        }
    }

    // BUSCAR
    public Producto buscar(int idProducto) {

        int indice =
            funcionHash(idProducto);

        NodoHash actual =
            tabla[indice];

        while(actual != null) {

            if(actual.producto
                .getIdProducto() == idProducto) {

                return actual.producto;
            }

            actual = actual.siguiente;
        }

        return null;
    }

    // MOSTRAR TABLA
    public void mostrarTabla() {

        for(int i = 0; i < tabla.length; i++) {

            System.out.print(i + " -> ");

            NodoHash actual =
                tabla[i];

            while(actual != null) {

                System.out.print(
                    "[" +
                    actual.producto.getNombre()
                    + "] -> "
                );

                actual = actual.siguiente;
            }

            System.out.println("null");
        }
    }

    public int getColisiones() {

        return colisiones;
    }
    public NodoHash[] getTabla() {

    return tabla;
}
}