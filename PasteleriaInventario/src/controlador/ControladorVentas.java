package controlador;

import java.util.HashMap;
import java.util.Map;
//sebastian
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
            producto.setCantidad(producto.getCantidad() - cantidad); // Actualizamos inventario

            Venta venta = new Venta(nombreProducto, cantidad, totalVenta);
            ventas.put(venta.getId(), venta);

            System.out.println("Venta realizada: " + venta);
            return true;
        } else {
            System.out.println("No hay suficiente stock para realizar la venta.");
            return false;
        }
    }

    // Obtener información de una venta
    public void consultarVenta(String idVenta) {
        if (ventas.containsKey(idVenta)) {
            System.out.println(ventas.get(idVenta));
        } else {
            System.out.println("Venta no encontrada.");
        }
    }

    // Mostrar todas las ventas
    public void mostrarVentas() {
        if (ventas.isEmpty()) {
            System.out.println("No se han realizado ventas.");
        } else {
            System.out.println("Ventas realizadas:");
            for (Venta venta : ventas.values()) {
                System.out.println(venta);
            }
        }
    }
}
