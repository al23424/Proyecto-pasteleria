package vista;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class InterfazInventario extends JFrame {

    private JPanel panelPrincipal;
    private JTextField txtCodigo;
    private JTextField txtProducto;
    private JTextField txtCantidad;
    private JTextArea areaInventario;
    
    // Estructura para almacenar productos: código -> [nombre, cantidad]
    private Map<String, ProductoInventario> inventario;

    // Clase interna para representar un producto
    private class ProductoInventario {
        String nombre;
        int cantidad;
        
        public ProductoInventario(String nombre, int cantidad) {
            this.nombre = nombre;
            this.cantidad = cantidad;
        }
        
        @Override
        public String toString() {
            return "Código: " + " | " + nombre + " | Cantidad: " + cantidad;
        }
    }

    /**
     * Ventana para registrar productos en el inventario.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                InterfazInventario frame = new InterfazInventario();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public InterfazInventario() {
        inventario = new HashMap<>();
        inicializarComponentes();
    }
    
    private void inicializarComponentes() {
        setTitle("Gestión de Inventario");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cambié para no cerrar toda la aplicación
        setBounds(180, 180, 520, 450);
        setLocationRelativeTo(null);
        
        panelPrincipal = new JPanel();
        panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(panelPrincipal);
        panelPrincipal.setLayout(null);

        // Código de producto
        JLabel lblCodigo = new JLabel("Código (8 dígitos):");
        lblCodigo.setBounds(30, 30, 120, 20);
        panelPrincipal.add(lblCodigo);

        txtCodigo = new JTextField();
        txtCodigo.setBounds(160, 30, 150, 20);
        panelPrincipal.add(txtCodigo);

        // Nombre del producto
        JLabel lblProducto = new JLabel("Producto:");
        lblProducto.setBounds(30, 60, 80, 20);
        panelPrincipal.add(lblProducto);

        txtProducto = new JTextField();
        txtProducto.setBounds(160, 60, 150, 20);
        panelPrincipal.add(txtProducto);

        // Cantidad
        JLabel lblCantidad = new JLabel("Cantidad:");
        lblCantidad.setBounds(30, 90, 80, 20);
        panelPrincipal.add(lblCantidad);

        txtCantidad = new JTextField();
        txtCantidad.setBounds(160, 90, 150, 20);
        panelPrincipal.add(txtCantidad);

        // Botones de operaciones
        JButton btnAgregar = new JButton("Adicionar");
        btnAgregar.setBounds(30, 130, 100, 25);
        panelPrincipal.add(btnAgregar);

        JButton btnModificar = new JButton("Modificar");
        btnModificar.setBounds(140, 130, 100, 25);
        panelPrincipal.add(btnModificar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(250, 130, 100, 25);
        panelPrincipal.add(btnEliminar);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(360, 130, 100, 25);
        panelPrincipal.add(btnBuscar);

        JButton btnActualizar = new JButton("Reportar");
        btnActualizar.setBounds(30, 165, 120, 25);
        panelPrincipal.add(btnActualizar);

        JButton btnLimpiar = new JButton("Limpiar Campos");
        btnLimpiar.setBounds(160, 165, 120, 25);
        panelPrincipal.add(btnLimpiar);

        // Área de inventario
        JLabel lblInventario = new JLabel("Lista de Inventario:");
        lblInventario.setBounds(30, 200, 150, 20);
        panelPrincipal.add(lblInventario);

        areaInventario = new JTextArea();
        areaInventario.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaInventario);
        scrollPane.setBounds(30, 225, 450, 150);
        panelPrincipal.add(scrollPane);

        // Agregar ActionListeners
        btnAgregar.addActionListener(e -> agregarProducto());
        btnModificar.addActionListener(e -> modificarProducto());
        btnEliminar.addActionListener(e -> eliminarProducto());
        btnBuscar.addActionListener(e -> buscarProducto());
        btnActualizar.addActionListener(e -> actualizarListaInventario());
        btnLimpiar.addActionListener(e -> limpiarCampos());
    }

    /**
     * Método sobrecargado - Agregar producto desde campos de texto
     */
    private void agregarProducto() {
        String codigo = txtCodigo.getText().trim();
        String producto = txtProducto.getText().trim();
        String cantidadStr = txtCantidad.getText().trim();
        
        agregarProducto(codigo, producto, cantidadStr, true);
    }
    
    /**
     * Método sobrecargado - Agregar producto con parámetros específicos
     */
    private boolean agregarProducto(String codigo, String producto, int cantidad) {
        return agregarProducto(codigo, producto, String.valueOf(cantidad), false);
    }
    
    /**
     * Método sobrecargado - Agregar producto con validación completa
     */
    private boolean agregarProducto(String codigo, String producto, String cantidadStr, boolean mostrarMensajes) {
        try {
            // Validaciones mejoradas
            if (!validarCamposCompletos(codigo, producto, cantidadStr)) {
                if (mostrarMensajes) {
                    mostrarError("Campos incompletos", "Por favor, complete todos los campos.");
                }
                return false;
            }

            if (!validarCodigo(codigo)) {
                return false;
            }

            int cantidad = Integer.parseInt(cantidadStr);
            
            if (!validarCantidad(cantidad)) {
                if (mostrarMensajes) {
                    mostrarError("Cantidad inválida", "La cantidad debe ser un número positivo.");
                }
                return false;
            }

            // Verificar duplicados con manejo mejorado
            if (inventario.containsKey(codigo)) {
                if (mostrarMensajes) {
                    int respuesta = JOptionPane.showConfirmDialog(this,
                        "Ya existe un producto con el código: " + codigo + 
                        "\n¿Desea actualizar la cantidad existente?",
                        "Código duplicado",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                    
                    if (respuesta == JOptionPane.YES_OPTION) {
                        return actualizarCantidadExistente(codigo, cantidad);
                    }
                }
                return false;
            }

            // Agregar producto con validación de memoria
            if (!validarMemoriaDisponible()) {
                if (mostrarMensajes) {
                    mostrarError("Memoria insuficiente", "No hay suficiente memoria para agregar más productos.");
                }
                return false;
            }

            inventario.put(codigo, new ProductoInventario(producto, cantidad));
            
            if (mostrarMensajes) {
                mostrarInformacion("Éxito", 
                    String.format("Producto agregado exitosamente:\n%s - %s (Cantidad: %d)", 
                    codigo, producto, cantidad));
                limpiarCampos();
                actualizarListaInventario();
            }
            
            return true;

        } catch (NumberFormatException e) {
            if (mostrarMensajes) {
                mostrarError("Error de formato", 
                    "La cantidad debe ser un número válido. Valor ingresado: '" + cantidadStr + "'");
            }
            return false;
        } catch (OutOfMemoryError e) {
            if (mostrarMensajes) {
                mostrarError("Memoria agotada", "No hay suficiente memoria disponible.");
            }
            return false;
        } catch (Exception e) {
            if (mostrarMensajes) {
                mostrarError("Error inesperado", 
                    "Error al agregar producto: " + e.getMessage());
            }
            return false;
        }
    }
    
    /**
     * Actualizar cantidad de producto existente
     */
    private boolean actualizarCantidadExistente(String codigo, int nuevaCantidad) {
        try {
            ProductoInventario producto = inventario.get(codigo);
            int cantidadAnterior = producto.cantidad;
            producto.cantidad = nuevaCantidad;
            
            mostrarInformacion("Cantidad Actualizada", 
                String.format("Producto: %s\nCantidad anterior: %d\nNueva cantidad: %d", 
                producto.nombre, cantidadAnterior, nuevaCantidad));
                
            limpiarCampos();
            actualizarListaInventario();
            return true;
            
        } catch (Exception e) {
            mostrarError("Error al actualizar", "No se pudo actualizar la cantidad: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Validar que los campos estén completos
     */
    private boolean validarCamposCompletos(String codigo, String producto, String cantidad) {
        return !codigo.isEmpty() && !producto.isEmpty() && !cantidad.isEmpty();
    }
    
    /**
     * Validar cantidad con manejo de errores mejorado
     */
    private boolean validarCantidad(int cantidad) {
        return cantidad >= 0;
    }
    
    /**
     * Validar memoria disponible antes de agregar productos
     */
    private boolean validarMemoriaDisponible() {
        try {
            Runtime runtime = Runtime.getRuntime();
            long memoriaLibre = runtime.freeMemory();
            long memoriaUsada = runtime.totalMemory() - memoriaLibre;
            
            // Si está usando más del 85% de la memoria, mostrar advertencia
            return memoriaUsada < (runtime.totalMemory() * 0.85);
            
        } catch (Exception e) {
            // En caso de error al verificar memoria, permitir continuar
            return true;
        }
    }
    
    /**
     * Método mejorado para mostrar errores
     */
    private void mostrarError(String titulo, String mensaje) {
        try {
            JOptionPane.showMessageDialog(this, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            System.err.println("Error al mostrar diálogo de error: " + e.getMessage());
            System.err.println("Mensaje original: " + mensaje);
        }
    }
    
    /**
     * Método mejorado para mostrar información
     */
    private void mostrarInformacion(String titulo, String mensaje) {
        try {
            JOptionPane.showMessageDialog(this, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            System.err.println("Error al mostrar diálogo de información: " + e.getMessage());
        }
    }

    /**
     * Modificar un producto existente
     */
    private void modificarProducto() {
        String codigo = txtCodigo.getText().trim();
        String nuevoNombre = txtProducto.getText().trim();
        String nuevaCantidadStr = txtCantidad.getText().trim();

        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Por favor, ingrese el código del producto a modificar.", 
                "Código requerido", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!validarCodigo(codigo)) {
            return;
        }

        if (!inventario.containsKey(codigo)) {
            JOptionPane.showMessageDialog(this, 
                "No existe un producto con el código: " + codigo, 
                "Producto no encontrado", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        ProductoInventario producto = inventario.get(codigo);
        StringBuilder cambios = new StringBuilder("Cambios realizados:\n");
        boolean huboCambios = false;

        // Modificar nombre si se proporcionó
        if (!nuevoNombre.isEmpty()) {
            String nombreAnterior = producto.nombre;
            producto.nombre = nuevoNombre;
            cambios.append("- Nombre: '").append(nombreAnterior).append("' → '").append(nuevoNombre).append("'\n");
            huboCambios = true;
        }

        // Modificar cantidad si se proporcionó
        if (!nuevaCantidadStr.isEmpty()) {
            try {
                int nuevaCantidad = Integer.parseInt(nuevaCantidadStr);
                if (nuevaCantidad < 0) {
                    JOptionPane.showMessageDialog(this, 
                        "La cantidad no puede ser negativa.", 
                        "Cantidad inválida", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int cantidadAnterior = producto.cantidad;
                producto.cantidad = nuevaCantidad;
                cambios.append("- Cantidad: ").append(cantidadAnterior).append(" → ").append(nuevaCantidad).append("\n");
                huboCambios = true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, 
                    "La cantidad debe ser un número válido.", 
                    "Formato inválido", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        if (huboCambios) {
            JOptionPane.showMessageDialog(this, cambios.toString(), "Producto modificado", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
            actualizarListaInventario();
        } else {
            JOptionPane.showMessageDialog(this, 
                "No se realizaron cambios. Ingrese un nuevo nombre o cantidad.", 
                "Sin cambios", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Eliminar un producto del inventario
     */
    private void eliminarProducto() {
        String codigo = txtCodigo.getText().trim();

        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Por favor, ingrese el código del producto a eliminar.", 
                "Código requerido", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!validarCodigo(codigo)) {
            return;
        }

        if (!inventario.containsKey(codigo)) {
            JOptionPane.showMessageDialog(this, 
                "No existe un producto con el código: " + codigo, 
                "Producto no encontrado", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        ProductoInventario producto = inventario.get(codigo);
        int respuesta = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro de eliminar el producto?\n" + codigo + " - " + producto.nombre, 
            "Confirmar eliminación", 
            JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {
            inventario.remove(codigo);
            JOptionPane.showMessageDialog(this, 
                "Producto eliminado exitosamente.", 
                "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
            actualizarListaInventario();
        }
    }

    /**
     * Buscar un producto por código
     */
    private void buscarProducto() {
        String codigo = txtCodigo.getText().trim();

        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Por favor, ingrese el código del producto a buscar.", 
                "Código requerido", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!validarCodigo(codigo)) {
            return;
        }

        if (inventario.containsKey(codigo)) {
            ProductoInventario producto = inventario.get(codigo);
            JOptionPane.showMessageDialog(this, 
                "El producto \"" + producto.nombre + "\" sí existe.\n" +
                "Código: " + codigo + "\n" +
                "Cantidad: " + producto.cantidad, 
                "Producto encontrado", 
                JOptionPane.INFORMATION_MESSAGE);
            
            // Opcional: llenar los campos con la información encontrada
            txtProducto.setText(producto.nombre);
            txtCantidad.setText(String.valueOf(producto.cantidad));
        } else {
            JOptionPane.showMessageDialog(this, 
                "El producto no existe.", 
                "Producto no encontrado", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Método sobrecargado - Actualizar/Reportar inventario sin parámetros
     */
    private void actualizarListaInventario() {
        actualizarListaInventario(true, true);
    }
    
    /**
     * Método sobrecargado - Actualizar inventario con opciones específicas
     */
    private void actualizarListaInventario(boolean mostrarMensaje) {
        actualizarListaInventario(mostrarMensaje, true);
    }
    
    /**
     * Método sobrecargado - Actualizar inventario con control completo
     */
    private void actualizarListaInventario(boolean mostrarMensaje, boolean incluirEstadisticas) {
        try {
            areaInventario.setText("");
            
            if (inventario.isEmpty()) {
                String mensaje = "El inventario está vacío.";
                areaInventario.setText(mensaje);
                
                if (mostrarMensaje) {
                    mostrarInformacion("Estado del Inventario", 
                        "No hay productos registrados en el inventario.");
                }
                return;
            }

            StringBuilder sb = new StringBuilder();
            sb.append("=== REPORTE DE INVENTARIO ===\n");
            sb.append("Fecha: ").append(java.time.LocalDateTime.now().format(
                java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))).append("\n");
            sb.append("==========================================\n");
            
            // Calcular estadísticas
            int totalProductos = inventario.size();
            int totalCantidades = 0;
            int productosConBajoStock = 0;
            int limiteStock = 10; // Límite para considerar bajo stock
            
            for (Map.Entry<String, ProductoInventario> entry : inventario.entrySet()) {
                String codigo = entry.getKey();
                ProductoInventario producto = entry.getValue();
                
                sb.append("Código: ").append(codigo)
                  .append(" | ").append(producto.nombre)
                  .append(" | Cantidad: ").append(producto.cantidad);
                
                // Indicador de stock
                if (producto.cantidad == 0) {
                    sb.append(" [SIN STOCK]");
                } else if (producto.cantidad <= limiteStock) {
                    sb.append(" [STOCK BAJO]");
                    productosConBajoStock++;
                }
                
                sb.append("\n");
                totalCantidades += producto.cantidad;
            }
            
            sb.append("==========================================\n");
            
            if (incluirEstadisticas) {
                sb.append("ESTADÍSTICAS DEL INVENTARIO:\n");
                sb.append("- Total de productos diferentes: ").append(totalProductos).append("\n");
                sb.append("- Cantidad total de items: ").append(totalCantidades).append("\n");
                sb.append("- Productos con stock bajo: ").append(productosConBajoStock).append("\n");
                sb.append("- Promedio de stock por producto: ").append(
                    String.format("%.2f", (double) totalCantidades / totalProductos)).append("\n");
                sb.append("==========================================\n");
            }
            
            areaInventario.setText(sb.toString());
            
            if (mostrarMensaje) {
                String mensaje = String.format(
                    "Reporte generado exitosamente.\n" +
                    "Total de productos: %d\n" +
                    "Cantidad total de items: %d",
                    totalProductos, totalCantidades);
                    
                if (productosConBajoStock > 0) {
                    mensaje += String.format("\n⚠️ Atención: %d productos con stock bajo", productosConBajoStock);
                }
                
                mostrarInformacion("Reporte Completado", mensaje);
            }
            
        } catch (Exception e) {
            mostrarError("Error en el Reporte", 
                "No se pudo generar el reporte del inventario: " + e.getMessage());
            
            // Fallback: mostrar reporte básico
            try {
                StringBuilder basicSb = new StringBuilder();
                basicSb.append("INVENTARIO BÁSICO\n");
                basicSb.append("==========================================\n");
                
                for (Map.Entry<String, ProductoInventario> entry : inventario.entrySet()) {
                    basicSb.append(entry.getKey()).append(" - ")
                           .append(entry.getValue().nombre).append(" (")
                           .append(entry.getValue().cantidad).append(")\n");
                }
                
                areaInventario.setText(basicSb.toString());
                
            } catch (Exception fallbackError) {
                mostrarError("Error Crítico", "No se pudo mostrar el inventario");
            }
        }
    }

    /**
     * Método sobrecargado - Limpiar campos sin confirmación
     */
    private void limpiarCampos() {
        limpiarCampos(true, false);
    }
    
    /**
     * Método sobrecargado - Limpiar campos con confirmación opcional
     */
    private void limpiarCampos(boolean pedirConfirmacion) {
        limpiarCampos(pedirConfirmacion, false);
    }
    
    /**
     * Método sobrecargado - Limpiar campos con control completo
     */
    private void limpiarCampos(boolean pedirConfirmacion, boolean incluirAreaInventario) {
        try {
            // Verificar si hay datos que limpiar
            boolean hayCamposConDatos = !txtCodigo.getText().trim().isEmpty() || 
                                       !txtProducto.getText().trim().isEmpty() || 
                                       !txtCantidad.getText().trim().isEmpty();
            
            if (!hayCamposConDatos && !incluirAreaInventario) {
                mostrarInformacion("Campos Vacíos", "No hay datos para limpiar.");
                return;
            }
            
            if (pedirConfirmacion && hayCamposConDatos) {
                int respuesta = JOptionPane.showConfirmDialog(this,
                    "¿Está seguro de que desea limpiar todos los campos?",
                    "Confirmar Limpieza",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
                    
                if (respuesta != JOptionPane.YES_OPTION) {
                    return;
                }
            }
            
            // Realizar limpieza
            limpiarCamposTexto();
            
            if (incluirAreaInventario) {
                limpiarAreaInventario();
            }
            
            // Enfocar el primer campo
            txtCodigo.requestFocus();
            
            mostrarInformacion("Limpieza Completada", 
                "Los campos han sido limpiados exitosamente.");
                
        } catch (Exception e) {
            mostrarError("Error al Limpiar", 
                "Ocurrió un error al limpiar los campos: " + e.getMessage());
        }
    }
    
    /**
     * Limpiar solo los campos de texto
     */
    private void limpiarCamposTexto() {
        try {
            txtCodigo.setText("");
            txtProducto.setText("");
            txtCantidad.setText("");
        } catch (Exception e) {
            // En caso de error, intentar limpiar individualmente
            try { txtCodigo.setText(""); } catch (Exception ignored) {}
            try { txtProducto.setText(""); } catch (Exception ignored) {}
            try { txtCantidad.setText(""); } catch (Exception ignored) {}
        }
    }
    
    /**
     * Limpiar el área del inventario
     */
    private void limpiarAreaInventario() {
        try {
            areaInventario.setText("Área de inventario limpiada.");
        } catch (Exception e) {
            // Manejo silencioso en caso de error
            System.err.println("Error al limpiar área de inventario: " + e.getMessage());
        }
    }

    /**
     * Validar que el código tenga exactamente 8 dígitos
     */
    private boolean validarCodigo(String codigo) {
        if (codigo.length() != 8) {
            JOptionPane.showMessageDialog(this, 
                "El código debe tener exactamente 8 dígitos.\n" +
                "Longitud actual: " + codigo.length(), 
                "Código inválido", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!codigo.matches("\\d{8}")) {
            JOptionPane.showMessageDialog(this, 
                "El código debe contener solo números.", 
                "Código inválido", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }
}
