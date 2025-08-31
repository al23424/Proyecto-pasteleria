package vista;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class InterfazPerdidas extends JFrame {

    private JPanel panelPrincipal;
    private JTextField txtIngresos;
    private JTextField txtGastos;
    private JTextField txtPerdida;

    /**
     * Ventana para calcular las pérdidas.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                InterfazPerdidas frame = new InterfazPerdidas();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public InterfazPerdidas() {
        setTitle("Cálculo de Pérdidas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(200, 200, 380, 230);
        panelPrincipal = new JPanel();
        panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(panelPrincipal);
        panelPrincipal.setLayout(null);

        JLabel lblIngresos = new JLabel("Ingresos:");
        lblIngresos.setBounds(30, 30, 80, 20);
        panelPrincipal.add(lblIngresos);

        txtIngresos = new JTextField();
        txtIngresos.setBounds(120, 30, 150, 20);
        panelPrincipal.add(txtIngresos);

        JLabel lblGastos = new JLabel("Gastos:");
        lblGastos.setBounds(30, 70, 80, 20);
        panelPrincipal.add(lblGastos);

        txtGastos = new JTextField();
        txtGastos.setBounds(120, 70, 150, 20);
        panelPrincipal.add(txtGastos);

        JButton btnCalcular = new JButton("Calcular");
        btnCalcular.setBounds(120, 110, 100, 25);
        panelPrincipal.add(btnCalcular);

        JLabel lblPerdida = new JLabel("Pérdida:");
        lblPerdida.setBounds(30, 150, 80, 20);
        panelPrincipal.add(lblPerdida);

        txtPerdida = new JTextField();
        txtPerdida.setBounds(120, 150, 150, 20);
        txtPerdida.setEditable(false);
        panelPrincipal.add(txtPerdida);

        btnCalcular.addActionListener(e -> {
            double ingresos = Double.parseDouble(txtIngresos.getText());
            double gastos = Double.parseDouble(txtGastos.getText());
            double perdida = gastos > ingresos ? gastos - ingresos : 0;
            txtPerdida.setText(String.valueOf(perdida));
        });
    }
}
