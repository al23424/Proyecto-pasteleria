package modelo;

import java.util.Date;

public class ReportePerdidas {
    private int idReporte;
    private Date fecha;
    private String tipoProducto;
    private int cantidadPerdida;
    private String motivo; // Vencimiento, daño, robo, etc.
    private double valorPerdida;
    
    // Constructores y métodos
    
    public double calcularImpactoEconomico() {
        return valorPerdida;
    }
}
