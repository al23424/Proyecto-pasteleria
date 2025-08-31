package vista;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class InterfazInventario extends JFrame {

    private JPanel panelPrincipal;
    private JTextField txtProducto;
    private JTextField txtCantidad;
    private JTextArea areaInventario;

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
        setTitle("Gestión de Inventario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(180, 180, 420, 320);
        panelPrincipal = new JPanel();
        panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(panelPrincipal);
        panelPrincipal.setLayout(null);

        JLabel lblProducto = new JLabel("Producto:");
        lblProducto.setBounds(30, 30, 80, 20);
        panelPrincipal.add(lblProducto);

        txtProducto = new JTextField();
        txtProducto.setBounds(120, 30, 150, 20);
        panelPrincipal.add(txtProducto);

        JLabel lblCantidad = new JLabel("Cantidad:");
        lblCantidad.setBounds(30, 70, 80, 20);
        panelPrincipal.add(lblCantidad);

        txtCantidad = new JTextField();
        txtCantidad.setBounds(120, 70, 150, 20);
        panelPrincipal.add(txtCantidad);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(120, 110, 100, 25);
        panelPrincipal.add(btnAgregar);

        JLabel lblInventario = new JLabel("Inventario:");
        lblInventario.setBounds(30, 150, 80, 20);
        panelPrincipal.add(lblInventario);

        areaInventario = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(areaInventario);
        scrollPane.setBounds(120, 150, 200, 100);
        panelPrincipal.add(scrollPane);

        btnAgregar.addActionListener(e -> {
            String producto = txtProducto.getText();
            String cantidad = txtCantidad.getText();
            if (!producto.isEmpty() && !cantidad.isEmpty()) {
                areaInventario.append(producto + " - Cantidad: " + cantidad + "\n");
                txtProducto.setText("");
                txtCantidad.setText("");
            }
        });
    }
}
