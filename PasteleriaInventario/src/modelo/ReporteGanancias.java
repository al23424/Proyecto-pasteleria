package modelo;

import java.util.Date;

public class ReporteGanancias {
    private int idReporte;
    private Date fechaInicio;
    private Date fechaFin;
    private double totalVentas;
    private double totalGastos;
    private double gananciasBrutas;
    private double gananciasNetas;
    
    // Constructor
    public ReporteGanancias(Date fechaInicio, Date fechaFin, double totalVentas, double totalGastos) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.totalVentas = totalVentas;
        this.totalGastos = totalGastos;
        this.gananciasBrutas = totalVentas;
        this.gananciasNetas = totalVentas - totalGastos;
    }
    
    // Método para calcular margen de ganancia
    public double calcularMargenGanancia() {
        if (totalVentas > 0) {
            return (gananciasNetas / totalVentas) * 100;
        }
        return 0;
    }
    
    // Getters y Setters
}
