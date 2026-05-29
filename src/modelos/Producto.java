package modelos;

public class Producto {

    private int idProducto;
    private String nombre;
    private double precio;
    private int stock;
    private int idMarca;

    public Producto(
            int idProducto,
            String nombre,
            double precio,
            int stock,
            int idMarca
    ) {

        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.idMarca = idMarca;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public int getIdMarca() {
        return idMarca;
    }

    @Override
    public String toString() {

        return idProducto + " - " +
               nombre + " - Q" +
               precio;
    }
}