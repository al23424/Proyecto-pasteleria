package vista;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class InterfazGanancias extends JFrame {

    private JPanel panelPrincipal;
    private JTextField txtIngresos;
    private JTextField txtGastos;
    private JTextField txtGanancia;

    /**
     * Ventana para calcular las ganancias.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    InterfazGanancias frame = new InterfazGanancias();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public InterfazGanancias() {
        setTitle("Cálculo de Ganancias");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(150, 150, 400, 250);
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

        JLabel lblGanancia = new JLabel("Ganancia:");
        lblGanancia.setBounds(30, 150, 80, 20);
        panelPrincipal.add(lblGanancia);

        txtGanancia = new JTextField();
        txtGanancia.setBounds(120, 150, 150, 20);
        txtGanancia.setEditable(false);
        panelPrincipal.add(txtGanancia);

        btnCalcular.addActionListener(e -> {
            double ingresos = Double.parseDouble(txtIngresos.getText());
            double gastos = Double.parseDouble(txtGastos.getText());
            double ganancia = ingresos - gastos;
            txtGanancia.setText(String.valueOf(ganancia));
        });
    }
}
