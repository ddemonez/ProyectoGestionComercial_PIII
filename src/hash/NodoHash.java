package hash;

import modelos.Producto;

public class NodoHash {

    public Producto producto;
    public NodoHash siguiente;
    public NodoHash(Producto producto) {

        this.producto = producto;

        this.siguiente = null;
    }
}
