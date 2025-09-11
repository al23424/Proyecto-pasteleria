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
     * Agregar un nuevo producto al inventario
     */
    private void agregarProducto() {
        String codigo = txtCodigo.getText().trim();
        String producto = txtProducto.getText().trim();
        String cantidadStr = txtCantidad.getText().trim();

        // Validaciones
        if (codigo.isEmpty() || producto.isEmpty() || cantidadStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Por favor, complete todos los campos.", 
                "Campos incompletos", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!validarCodigo(codigo)) {
            return;
        }

        try {
            int cantidad = Integer.parseInt(cantidadStr);
            if (cantidad < 0) {
                JOptionPane.showMessageDialog(this, 
                    "La cantidad no puede ser negativa.", 
                    "Cantidad inválida", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Verificar si el código ya existe
            if (inventario.containsKey(codigo)) {
                JOptionPane.showMessageDialog(this, 
                    "Ya existe un producto con el código: " + codigo, 
                    "Código duplicado", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Agregar producto
            inventario.put(codigo, new ProductoInventario(producto, cantidad));
            JOptionPane.showMessageDialog(this, 
                "Producto agregado exitosamente:\n" + codigo + " - " + producto, 
                "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);
            
            limpiarCampos();
            actualizarListaInventario();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "La cantidad debe ser un número válido.", 
                "Formato inválido", 
                JOptionPane.ERROR_MESSAGE);
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
     * Actualizar la lista visual del inventario
     */
    private void actualizarListaInventario() {
        areaInventario.setText("");
        if (inventario.isEmpty()) {
            areaInventario.setText("El inventario está vacío.");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("INVENTARIO ACTUAL\n");
            sb.append("==========================================\n");
            for (Map.Entry<String, ProductoInventario> entry : inventario.entrySet()) {
                sb.append("Código: ").append(entry.getKey())
                  .append(" | ").append(entry.getValue().nombre)
                  .append(" | Cantidad: ").append(entry.getValue().cantidad)
                  .append("\n");
            }
            sb.append("==========================================\n");
            sb.append("Total de productos: ").append(inventario.size());
            areaInventario.setText(sb.toString());
        }
    }

    /**
     * Limpiar todos los campos de texto
     */
    private void limpiarCampos() {
        txtCodigo.setText("");
        txtProducto.setText("");
        txtCantidad.setText("");
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