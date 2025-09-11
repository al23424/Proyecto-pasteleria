package controlador;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class ControladorVentas {

    private Map<String, Venta> ventas;
    private ControladorInventario controladorInventario;

    public ControladorVentas(ControladorInventario controladorInventario) {
        ventas = new HashMap<>();
        this.controladorInventario = controladorInventario;
    }

    // Realizar una venta
    public boolean realizarVenta(String nombreProducto, int cantidad) {
        Producto producto = controladorInventario.obtenerProducto(nombreProducto);

        if (producto != null && producto.getCantidad() >= cantidad) {
            double totalVenta = producto.getPrecio() * cantidad;
            
            // Actualizamos inventario
            controladorInventario.actualizarCantidad(nombreProducto, 
                producto.getCantidad() - cantidad);

            // Creamos la venta
            Venta venta = new Venta(nombreProducto, cantidad, totalVenta);
            ventas.put(venta.getId(), venta);

            System.out.println("Venta realizada exitosamente: " + venta);
            return true;
        } else if (producto == null) {
            System.out.println("El producto '" + nombreProducto + "' no existe en el inventario.");
            return false;
        } else {
            System.out.println("Stock insuficiente. Stock disponible: " + producto.getCantidad() + 
                             ", solicitado: " + cantidad);
            return false;
        }
    }

    // Obtener información de una venta
    public void consultarVenta(String idVenta) {
        if (ventas.containsKey(idVenta)) {
            System.out.println(ventas.get(idVenta));
        } else {
            System.out.println("Venta con ID '" + idVenta + "' no encontrada.");
        }
    }

    // Obtener una venta (útil para otras operaciones)
    public Venta obtenerVenta(String idVenta) {
        return ventas.get(idVenta);
    }

    // Mostrar todas las ventas
    public void mostrarVentas() {
        if (ventas.isEmpty()) {
            System.out.println("No se han realizado ventas.");
        } else {
            System.out.println("=== HISTORIAL DE VENTAS ===");
            for (Venta venta : ventas.values()) {
                System.out.println(venta);
            }
            System.out.println("===========================");
        }
    }

    // Calcular total de ventas realizadas
    public double calcularTotalVentas() {
        double total = 0;
        for (Venta venta : ventas.values()) {
            total += venta.getTotal();
        }
        return total;
    }

    // Obtener ventas por producto
    public List<Venta> obtenerVentasPorProducto(String nombreProducto) {
        List<Venta> ventasProducto = new ArrayList<>();
        for (Venta venta : ventas.values()) {
            if (venta.getNombreProducto().equals(nombreProducto)) {
                ventasProducto.add(venta);
            }
        }
        return ventasProducto;
    }

    // Obtener número total de ventas
    public int getTotalVentas() {
        return ventas.size();
    }

    // Mostrar resumen de ventas
    public void mostrarResumenVentas() {
        if (ventas.isEmpty()) {
            System.out.println("No hay ventas para mostrar resumen.");
        } else {
            System.out.println("=== RESUMEN DE VENTAS ===");
            System.out.println("Total de ventas: " + getTotalVentas());
            System.out.println("Ingresos totales: $" + String.format("%.2f", calcularTotalVentas()));
            System.out.println("=========================");
        }
    }
}
