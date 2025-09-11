package modelo;

import java.util.UUID;

public class ventas {
    private String id;
    private String nombreProducto;
    private int cantidad;
    private double total;

    // Constructor
    public ventas(String nombreProducto, int cantidad, double total) {
        this.id = UUID.randomUUID().toString(); // ID único
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.total = total;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "Venta{id='" + id + "', nombreProducto='" + nombreProducto + "', cantidad=" + cantidad + ", total=" + total + "}";
    }
}
