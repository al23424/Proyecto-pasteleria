package controlador;

import java.util.ArrayList;
import java.util.List;
//sebastian
public class ControladorGastos {

    private List<Double> listaGastos;

    public ControladorGastos() {
        listaGastos = new ArrayList<>();
    }

    // Agregar un gasto
    public void agregarGasto(double monto) {
        if (monto > 0) {
            listaGastos.add(monto);
        } else {
            System.out.println("El gasto debe ser mayor a 0.");
        }
    }

    // Calcular el total de los gastos
    public double calcularTotalGastos() {
        double total = 0.0;
        for (double gasto : listaGastos) {
            total += gasto;
        }
        return total;
    }

    // Obtener la lista de gastos
    public List<Double> obtenerListaGastos() {
        return new ArrayList<>(listaGastos);
    }

    // Mostrar todos los gastos
    public void mostrarGastos() {
        if (listaGastos.isEmpty()) {
            System.out.println("No hay gastos registrados.");
        } else {
            System.out.println("Gastos registrados:");
            for (int i = 0; i < listaGastos.size(); i++) {
                System.out.println("Gasto " + (i + 1) + ": $" + listaGastos.get(i));
            }
        }
    }

    // Reiniciar la lista de gastos
    public void reiniciar() {
        listaGastos.clear();
    }
}

