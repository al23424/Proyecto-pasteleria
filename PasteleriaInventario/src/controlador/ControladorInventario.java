package controlador;

import java.util.HashMap;
import java.util.Map;
//sebastian
public class ControladorInventario {

    private Map<String, Producto> inventario;

    public ControladorInventario() {
        inventario = new HashMap<>();
    }

    // Agregar un producto al inventario
    public void agregarProducto(String nombre, double precio, int cantidad) {
        if (cantidad > 0 && precio > 0) {
            Producto producto = new Producto(nombre, precio, cantidad);
            inventario.put(nombre, producto);
        } else {
            System.out.println("La cantidad y el precio deben ser mayores a 0.");
        }
    }

    // Eliminar un producto del inventario
    public void eliminarProducto(String nombre) {
        if (inventario.containsKey(nombre)) {
            inventario.remove(nombre);
            System.out.println("Producto " + nombre + " eliminado.");
        } else {
            System.out.println("El producto no existe en el inventario.");
        }
    }

    // Actualizar la cantidad de un producto
    public void actualizarCantidad(String nombre, int nuevaCantidad) {
        if (inventario.containsKey(nombre) && nuevaCantidad >= 0) {
            Producto producto = inventario.get(nombre);
            producto.setCantidad(nuevaCantidad);
            System.out.println("Cantidad del producto " + nombre + " actualizada.");
        } else {
            System.out.println("El producto no existe o la cantidad es inválida.");
        }
    }

    // Consultar el stock de un producto
    public void consultarProducto(String nombre) {
        if (inventario.containsKey(nombre)) {
            Producto producto = inventario.get(nombre);
            System.out.println(producto);
        } else {
            System.out.println("El producto no existe en el inventario.");
        }
    }

    // Mostrar todos los productos del inventario
    public void mostrarInventario() {
        if (inventario.isEmpty()) {
            System.out.println("El inventario está vacío.");
        } else {
            System.out.println("Inventario:");
            for (Producto producto : inventario.values()) {
                System.out.println(producto);
            }
        }
    }
}
