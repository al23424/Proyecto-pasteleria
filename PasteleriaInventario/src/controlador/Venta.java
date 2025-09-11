package controlador;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Venta {
    private String id;
    private String nombreProducto;
    private int cantidad;
    private double total;
    private LocalDateTime fechaVenta;

    public Venta(String nombreProducto, int cantidad, double total) {
        this.id = UUID.randomUUID().toString().substring(0, 8); // ID único corto
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.total = total;
        this.fechaVenta = LocalDateTime.now();
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

    public LocalDateTime getFechaVenta() {
        return fechaVenta;
    }

    // Setters
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return "Venta{" +
                "id='" + id + '\'' +
                ", producto='" + nombreProducto + '\'' +
                ", cantidad=" + cantidad +
                ", total=$" + String.format("%.2f", total) +
                ", fecha=" + fechaVenta.format(formatter) +
                '}';
    }
}