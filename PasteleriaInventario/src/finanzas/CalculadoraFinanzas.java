package finanzas;

public class CalculadoraFinanzas {
 private double ingresos;
    private double gastos;
    private double inversion;

    public EstadoFinanciero(double ingresos, double gastos, double inversion) {
        this.ingresos = ingresos;
        this.gastos = gastos;
        this.inversion = inversion;
    }

    public double getGanancias() {
        return CalculadoraFinanzas.calcularGanancia(ingresos, gastos);
    }

    public double getMargen() {
        return CalculadoraFinanzas.calcularMargen(ingresos, getGanancias());
    }

    public double getRentabilidad() {
        return CalculadoraFinanzas.calcularRentabilidad(inversion, getGanancias());
    }
}
