package finanzas;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EstadoFinanciero {
    private double ingresos;
    private double gastos;
    private LocalDateTime fechaCreacion;

    public EstadoFinanciero(double ingresos, double gastos) {
        this.ingresos = ingresos;
        this.gastos = gastos;
        this.fechaCreacion = LocalDateTime.now();
    }

    // Constructor adicional para inicializar en ceros
    public EstadoFinanciero() {
        this(0.0, 0.0);
    }

    // Getters
    public double getIngresos() {
        return ingresos;
    }

    public double getGastos() {
        return gastos;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    // Setters
    public void setIngresos(double ingresos) {
        if (ingresos >= 0) {
            this.ingresos = ingresos;
        } else {
            System.out.println("Los ingresos no pueden ser negativos.");
        }
    }

    public void setGastos(double gastos) {
        if (gastos >= 0) {
            this.gastos = gastos;
        } else {
            System.out.println("Los gastos no pueden ser negativos.");
        }
    }

    // Método que faltaba: calcular ganancias
    public double getGanancias() {
        return ingresos - gastos;
    }

    // Método corregido: calcular margen de ganancia
    public double getMargen() {
        if (ingresos == 0) {
            return 0;
        }
        return (getGanancias() / ingresos) * 100;
    }

    // Métodos adicionales útiles
    public void agregarIngresos(double cantidad) {
        if (cantidad > 0) {
            this.ingresos += cantidad;
        } else {
            System.out.println("La cantidad debe ser positiva.");
        }
    }

    public void agregarGastos(double cantidad) {
        if (cantidad > 0) {
            this.gastos += cantidad;
        } else {
            System.out.println("La cantidad debe ser positiva.");
        }
    }

    // Verificar si hay pérdidas
    public boolean hayPerdidas() {
        return getGanancias() < 0;
    }

    // Verificar si hay ganancias
    public boolean hayGanancias() {
        return getGanancias() > 0;
    }

    // Verificar si está en punto de equilibrio
    public boolean enEquilibrio() {
        return getGanancias() == 0;
    }

    // Obtener estado financiero como texto
    public String getEstado() {
        if (hayGanancias()) {
            return "GANANCIA";
        } else if (hayPerdidas()) {
            return "PÉRDIDA";
        } else {
            return "EQUILIBRIO";
        }
    }

    // Calcular ratio de gastos sobre ingresos
    public double getRatioGastos() {
        if (ingresos == 0) {
            return gastos > 0 ? Double.POSITIVE_INFINITY : 0;
        }
        return (gastos / ingresos) * 100;
    }

    // Resetear valores
    public void reset() {
        this.ingresos = 0.0;
        this.gastos = 0.0;
        this.fechaCreacion = LocalDateTime.now();
    }

    // Combinar con otro estado financiero
    public void combinarCon(EstadoFinanciero otro) {
        this.ingresos += otro.getIngresos();
        this.gastos += otro.getGastos();
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return "EstadoFinanciero{" +
                "ingresos=$" + String.format("%.2f", ingresos) +
                ", gastos=$" + String.format("%.2f", gastos) +
                ", ganancias=$" + String.format("%.2f", getGanancias()) +
                ", margen=" + String.format("%.2f", getMargen()) + "%" +
                ", estado=" + getEstado() +
                ", fecha=" + fechaCreacion.format(formatter) +
                '}';
    }

    // Método para generar reporte detallado
    public void mostrarReporte() {
        System.out.println("=== REPORTE FINANCIERO ===");
        System.out.println("Ingresos:     $" + String.format("%,.2f", ingresos));
        System.out.println("Gastos:       $" + String.format("%,.2f", gastos));
        System.out.println("Ganancias:    $" + String.format("%,.2f", getGanancias()));
        System.out.println("Margen:       " + String.format("%.2f", getMargen()) + "%");
        System.out.println("Ratio Gastos: " + String.format("%.2f", getRatioGastos()) + "%");
        System.out.println("Estado:       " + getEstado());
        System.out.println("Fecha:        " + fechaCreacion.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
        System.out.println("==========================");
    }
}