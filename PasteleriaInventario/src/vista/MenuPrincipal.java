package vista;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MenuPrincipal extends JFrame {

    private JPanel panelPrincipal;

    /**
     * Ventana principal del sistema con acceso a los módulos.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                MenuPrincipal frame = new MenuPrincipal();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public MenuPrincipal() {
        setTitle("Menú Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(200, 200, 500, 350);
        panelPrincipal = new JPanel();
        panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(panelPrincipal);
        panelPrincipal.setLayout(null);

        JLabel lblTitulo = new JLabel("Bienvenido al Sistema");
        lblTitulo.setBounds(180, 30, 200, 20);
        panelPrincipal.add(lblTitulo);

        JButton btnVentas = new JButton("Ventas");
        btnVentas.setBounds(180, 80, 120, 25);
        panelPrincipal.add(btnVentas);

        JButton btnInventario = new JButton("Inventario");
        btnInventario.setBounds(180, 120, 120, 25);
        panelPrincipal.add(btnInventario);

        JButton btnGanancias = new JButton("Ganancias");
        btnGanancias.setBounds(180, 160, 120, 25);
        panelPrincipal.add(btnGanancias);

        JButton btnPerdidas = new JButton("Pérdidas");
        btnPerdidas.setBounds(180, 200, 120, 25);
        panelPrincipal.add(btnPerdidas);
    }
}
