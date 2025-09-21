package vista;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class InterfazVentas extends JFrame {

    private JPanel panelPrincipal;
    private JTextField txtProducto;
    private JTextField txtCantidad;
    private JTextField txtPrecio;
    private JTextField txtTotal;

    /**
     * Ventana para registrar y calcular ventas.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                InterfazVentas frame = new InterfazVentas();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public InterfazVentas() {
        setTitle("Registro de Ventas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(160, 160, 420, 280);
        panelPrincipal = new JPanel();
        panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(panelPrincipal);
        panelPrincipal.setLayout(null);

        JLabel lblProducto = new JLabel("Producto:");
        lblProducto.setBounds(30, 30, 100, 20);
        panelPrincipal.add(lblProducto);

        txtProducto = new JTextField();
        txtProducto.setBounds(140, 30, 150, 20);
        panelPrincipal.add(txtProducto);

        JLabel lblCantidad = new JLabel("Cantidad:");
        lblCantidad.setBounds(30, 70, 100, 20);
        panelPrincipal.add(lblCantidad);

        txtCantidad = new JTextField();
        txtCantidad.setBounds(140, 70, 150, 20);
        panelPrincipal.add(txtCantidad);

        JLabel lblPrecio = new JLabel("Precio Unitario:");
        lblPrecio.setBounds(30, 110, 100, 20);
        panelPrincipal.add(lblPrecio);

        txtPrecio = new JTextField();
        txtPrecio.setBounds(140, 110, 150, 20);
        panelPrincipal.add(txtPrecio);

        JButton btnCalcular = new JButton("Calcular Total");
        btnCalcular.setBounds(120, 150, 150, 25);
        panelPrincipal.add(btnCalcular);

        JLabel lblTotal = new JLabel("Total:");
        lblTotal.setBounds(30, 190, 100, 20);
        panelPrincipal.add(lblTotal);

        txtTotal = new JTextField();
        txtTotal.setBounds(140, 190, 150, 20);
        txtTotal.setEditable(false);
        panelPrincipal.add(txtTotal);

        btnCalcular.addActionListener(e -> calcularTotal());
        
        // Botón para limpiar campos
        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(30, 220, 100, 25);
        btnLimpiar.addActionListener(e -> limpiarCampos());
        panelPrincipal.add(btnLimpiar);
        
        // Botón para aplicar descuento
        JButton btnDescuento = new JButton("Aplicar Descuento");
        btnDescuento.setBounds(140, 220, 150, 25);
        btnDescuento.addActionListener(e -> aplicarDescuento());
        panelPrincipal.add(btnDescuento);
        
        // Ajustar tamaño de ventana
        setBounds(160, 160, 420, 300);
    }
    
    /**
     * Método sobrecargado - Calcular total sin parámetros (desde los campos de texto)
     */
    private void calcularTotal() {
        try {
            String cantidadTexto = txtCantidad.getText().trim();
            String precioTexto = txtPrecio.getText().trim();
            
            if (!validarCamposCompletos(cantidadTexto, precioTexto)) {
                return;
            }
            
            double cantidad = Double.parseDouble(cantidadTexto);
            double precio = Double.parseDouble(precioTexto);
            
            double total = calcularTotal(cantidad, precio);
            txtTotal.setText(String.format("%.2f", total));
            
        } catch (NumberFormatException e) {
            mostrarError("Error de formato", "Por favor ingrese números válidos en cantidad y precio.");
        } catch (Exception e) {
            mostrarError("Error inesperado", "Ocurrió un error al calcular el total: " + e.getMessage());
        }
    }
    
    /**
     * Método sobrecargado - Calcular total con parámetros específicos
     */
    private double calcularTotal(double cantidad, double precio) {
        if (cantidad < 0) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa");
        }
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        return cantidad * precio;
    }
    
    /**
     * Método sobrecargado - Calcular total con descuento
     */
    private double calcularTotal(double cantidad, double precio, double descuento) {
        double total = calcularTotal(cantidad, precio);
        if (descuento < 0 || descuento > 100) {
            throw new IllegalArgumentException("El descuento debe estar entre 0 y 100%");
        }
        return total * (1 - descuento / 100);
    }
    
    /**
     * Método sobrecargado - Calcular total con impuesto y descuento
     */
    private double calcularTotal(double cantidad, double precio, double descuento, double impuesto) {
        double totalConDescuento = calcularTotal(cantidad, precio, descuento);
        if (impuesto < 0) {
            throw new IllegalArgumentException("El impuesto no puede ser negativo");
        }
        return totalConDescuento * (1 + impuesto / 100);
    }
    
    /**
     * Aplicar descuento al total actual
     */
    private void aplicarDescuento() {
        try {
            String descuentoStr = JOptionPane.showInputDialog(this, 
                "Ingrese el porcentaje de descuento (0-100):", 
                "Aplicar Descuento", 
                JOptionPane.QUESTION_MESSAGE);
                
            if (descuentoStr == null || descuentoStr.trim().isEmpty()) {
                return; // Usuario canceló
            }
            
            double descuento = Double.parseDouble(descuentoStr.trim());
            
            String cantidadTexto = txtCantidad.getText().trim();
            String precioTexto = txtPrecio.getText().trim();
            
            if (!validarCamposCompletos(cantidadTexto, precioTexto)) {
                return;
            }
            
            double cantidad = Double.parseDouble(cantidadTexto);
            double precio = Double.parseDouble(precioTexto);
            
            double totalConDescuento = calcularTotal(cantidad, precio, descuento);
            txtTotal.setText(String.format("%.2f (Descuento: %.0f%%)", totalConDescuento, descuento));
            
            mostrarInformacion("Descuento Aplicado", 
                String.format("Descuento del %.0f%% aplicado correctamente.\nTotal con descuento: $%.2f", 
                descuento, totalConDescuento));
                
        } catch (NumberFormatException e) {
            mostrarError("Error de formato", "Por favor ingrese un número válido para el descuento.");
        } catch (IllegalArgumentException e) {
            mostrarError("Valor inválido", e.getMessage());
        } catch (Exception e) {
            mostrarError("Error inesperado", "Ocurrió un error al aplicar el descuento: " + e.getMessage());
        }
    }
    
    /**
     * Validar que los campos estén completos
     */
    private boolean validarCamposCompletos(String cantidad, String precio) {
        if (cantidad.isEmpty() || precio.isEmpty()) {
            mostrarError("Campos incompletos", "Por favor complete todos los campos antes de calcular.");
            return false;
        }
        return true;
    }
    
    /**
     * Limpiar todos los campos de texto
     */
    private void limpiarCampos() {
        txtProducto.setText("");
        txtCantidad.setText("");
        txtPrecio.setText("");
        txtTotal.setText("");
    }
    
    /**
     * Mostrar mensaje de error con manejo de excepciones
     */
    private void mostrarError(String titulo, String mensaje) {
        try {
            JOptionPane.showMessageDialog(this, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            System.err.println("Error al mostrar diálogo: " + e.getMessage());
        }
    }
    
    /**
     * Mostrar mensaje de información con manejo de excepciones
     */
    private void mostrarInformacion(String titulo, String mensaje) {
        try {
            JOptionPane.showMessageDialog(this, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            System.err.println("Error al mostrar diálogo: " + e.getMessage());
        }
    }
}

