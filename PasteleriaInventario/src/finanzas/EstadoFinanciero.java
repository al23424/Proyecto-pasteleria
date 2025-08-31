package finanzas;
import java.util.List;

public class EstadoFinanciero {
  public static double promedioGanancias(List<EstadoFinanciero> estados) {
        return estados.stream().mapToDouble(EstadoFinanciero::getGanancias).average().orElse(0);
    }

    public static double promedioMargen(List<EstadoFinanciero> estados) {
        return estados.stream().mapToDouble(EstadoFinanciero::getMargen).average().orElse(0);
    }

    public static EstadoFinanciero mejorPeriodo(List<EstadoFinanciero> estados) {
        return estados.stream().max((a, b) -> Double.compare(a.getGanancias(), b.getGanancias())).orElse(null);
    }
}
