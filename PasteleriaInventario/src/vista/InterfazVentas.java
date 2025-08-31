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

        btnCalcular.addActionListener(e -> {
            double cantidad = Double.parseDouble(txtCantidad.getText());
            double precio = Double.parseDouble(txtPrecio.getText());
            double total = cantidad * precio;
            txtTotal.setText(String.valueOf(total));
        });
    }
}
