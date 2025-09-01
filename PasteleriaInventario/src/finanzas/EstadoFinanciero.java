package finanzas;

public class EstadoFinanciero {
   private double ingresos;
   private double gastos;

    public EstadoFinanciero(double ingresos, double gastos) {
        this.ingresos = ingresos;
        this.gastos = gastos;
    }
   
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

    public double getMargen() {
        if (ingresos == 0) return 0; 
        return (getGanancias() / ingresos) * 100;
    }
}
