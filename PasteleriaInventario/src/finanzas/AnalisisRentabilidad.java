package finanzas;

public class AnalisisRentabilidad {
  public static double calcularGanancia(double ingresos, double gastos) {
        return ingresos - gastos;
    }

    public static double calcularMargen(double ingresos, double ganancias) {
        if (ingresos == 0) return 0;
        return (ganancias / ingresos) * 100;
    }

    public static double calcularRentabilidad(double inversion, double ganancia) {
        if (inversion == 0) return 0;
        return (ganancia / inversion) * 100;
    }
}
