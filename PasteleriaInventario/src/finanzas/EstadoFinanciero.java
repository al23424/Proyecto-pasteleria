package finanzas;

public class EstadoFinanciero {
   private double ingresos;
    private double gastos;

    public EstadoFinanciero(double ingresos, double gastos) {
        this.ingresos = ingresos;
        this.gastos = gastos;
    }

    // Getters y Setters
    public double getIngresos() {
        return ingresos;
    }

    public void setIngresos(double ingresos) {
        this.ingresos = ingresos;
    }

    public double getGastos() {
        return gastos;
    }

    public void setGastos(double gastos) {
        this.gastos = gastos;
    }
}
