package controlador; //sebastian

public class ControladorGanancias {
    private double ingresosTotales;
    private double costosTotales;

    public ControladorGanancias() {
        this.ingresosTotales = 0.0;
        this.costosTotales = 0.0;
    }

    // Agregar ingresos
    public void agregarIngreso(double monto) {
        if (monto > 0) {
            ingresosTotales += monto;
        } else {
            System.out.println("El ingreso debe ser mayor a 0.");
        }
    }

    // Agregar costos
    public void agregarCosto(double monto) {
        if (monto > 0) {
            costosTotales += monto;
        } else {
            System.out.println("El costo debe ser mayor a 0.");
        }
    }

    // Calcular ganancia neta
    public double calcularGananciaNeta() {
        return ingresosTotales - costosTotales;
    }

    // Obtener ingresos totales
    public double getIngresosTotales() {
        return ingresosTotales;
    }

    // Obtener costos totales
    public double getCostosTotales() {
        return costosTotales;
    }

    // Reiniciar valores
    public void reiniciar() {
        ingresosTotales = 0.0;
        costosTotales = 0.0;
    }
}

