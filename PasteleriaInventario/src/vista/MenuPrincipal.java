package vista;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        setTitle("Menú Principal - Sistema de Gestión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(200, 200, 500, 350);
        setLocationRelativeTo(null); // Centrar en pantalla
        
        panelPrincipal = new JPanel();
        panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(panelPrincipal);
        panelPrincipal.setLayout(null);

        // Título principal
        JLabel lblTitulo = new JLabel("Bienvenido al Sistema");
        lblTitulo.setBounds(180, 30, 200, 20);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        panelPrincipal.add(lblTitulo);

        // Botón Ventas
        JButton btnVentas = new JButton("Ventas");
        btnVentas.setBounds(180, 80, 120, 25);
        btnVentas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaVentas();
            }
        });
        panelPrincipal.add(btnVentas);

        // Botón Inventario
        JButton btnInventario = new JButton("Inventario");
        btnInventario.setBounds(180, 120, 120, 25);
        btnInventario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaInventario();
            }
        });
        panelPrincipal.add(btnInventario);

        // Botón Ganancias
        JButton btnGanancias = new JButton("Ganancias");
        btnGanancias.setBounds(180, 160, 120, 25);
        btnGanancias.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaGanancias();
            }
        });
        panelPrincipal.add(btnGanancias);

        // Botón Pérdidas
        JButton btnPerdidas = new JButton("Pérdidas");
        btnPerdidas.setBounds(180, 200, 120, 25);
        btnPerdidas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaPerdidas();
            }
        });
        panelPrincipal.add(btnPerdidas);

        // Botón Salir (opcional)
        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(180, 250, 120, 25);
        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int respuesta = JOptionPane.showConfirmDialog(
                    MenuPrincipal.this,
                    "¿Está seguro de que desea salir?",
                    "Confirmar salida",
                    JOptionPane.YES_NO_OPTION
                );
                if (respuesta == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        panelPrincipal.add(btnSalir);
    }

    /**
     * Abre la ventana de Ventas
     */
    private void abrirVentanaVentas() {
        try {
            InterfazVentas ventanaVentas = new InterfazVentas();
            ventanaVentas.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                this,
                "Error al abrir la ventana de Ventas: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
            e.printStackTrace();
        }
    }

    /**
     * Abre la ventana de Inventario
     */
    private void abrirVentanaInventario() {
        try {
            InterfazInventario ventanaInventario = new InterfazInventario();
            ventanaInventario.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                this,
                "Error al abrir la ventana de Inventario: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
            e.printStackTrace();
        }
    }

    /**
     * Abre la ventana de Ganancias
     */
    private void abrirVentanaGanancias() {
        try {
            InterfazGanancias ventanaGanancias = new InterfazGanancias();
            ventanaGanancias.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                this,
                "Error al abrir la ventana de Ganancias: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
            e.printStackTrace();
        }
    }

    /**
     * Abre la ventana de Pérdidas
     */
    private void abrirVentanaPerdidas() {
        try {
            InterfazPerdidas ventanaPerdidas = new InterfazPerdidas();
            ventanaPerdidas.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                this,
                "Error al abrir la ventana de Pérdidas: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
            e.printStackTrace();
        }
    }
}