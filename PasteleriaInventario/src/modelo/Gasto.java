package modelo;

import java.util.Date;

public class Gasto {
    private int idGasto;
    private String descripcion;
    private double monto;
    private Date fecha;
    private String categoria; // Ingredientes, Servicios, Equipos, etc.
    private String proveedor;
    
    // Constructores
    public Gasto() {}
    
    public Gasto(int idGasto, String descripcion, double monto, Date fecha, String categoria, String proveedor) {
        this.idGasto = idGasto;
        this.descripcion = descripcion;
        this.monto = monto;
        this.fecha = fecha;
        this.categoria = categoria;
        this.proveedor = proveedor;
    }
    
    // Getters y Setters completos
}
