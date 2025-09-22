package finanzas;

public class AnalisisRentabilidad {
 Pegar en  AnalisisRentabilidad


    public static double calcularGanancia(double ingresos, double gastos) {
        return ingresos - gastos;
    }

   
    public static double calcularGanancia(double[] ingresos, double[] gastos) {
        double totalIngresos = 0;
        double totalGastos = 0;

        for (double i : ingresos) totalIngresos += i;
        for (double g : gastos) totalGastos += g;

        return totalIngresos - totalGastos;
    }

    
    public static double calcularMargen(double ingresos, double ganancias) {
        if (ingresos == 0) return 0;
        return (ganancias / ingresos) * 100;
    }

    
    public static double calcularMargen(double ingresos, double gastos, boolean directo) {
        double ganancia = ingresos - gastos;
        if (ingresos == 0) return 0;
        return (ganancia / ingresos) * 100;
    }

   
    public static double calcularRentabilidad(double inversion, double ganancia) {
        if (inversion == 0) return 0;
        return (ganancia / inversion) * 100;
    }

  
    public static double calcularRentabilidad(double inversion, double ingresos, double gastos) {
        double ganancia = ingresos - gastos;
        if (inversion == 0) return 0;
        return (ganancia / inversion) * 100;
    }
}
