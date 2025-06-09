package RMICliente.Test;

import RMICliente.Cliente.Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GUIRmi extends JFrame {
    private JTextField txtID;
    private JTextField txtNombreCompleto;
    private JTextField txtCorreo;
    private JButton btnRegistrar;
    private JButton btnBuscar;
    private JButton btnSalir;
    private JPanel panelPrincipal;
    private JLabel lblTitulo;
    private JLabel lblID;
    private JLabel lblNombreCompleto;
    private JLabel lblCorreo;

    public GUIRmi() {
        setTitle("Sistema de Gestión de Usuarios RMI");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 350);
        setLocationRelativeTo(null);

        panelPrincipal = new JPanel(new GridLayout(6, 2, 10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(panelPrincipal);

        lblTitulo = new JLabel("Gestión de Usuarios", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        panelPrincipal.add(lblTitulo);
        panelPrincipal.add(new JLabel("")); // espacio vacío para balancear

        // Campo ID para registro (opcional)
        lblID = new JLabel("ID (solo para registro):");
        txtID = new JTextField();
        panelPrincipal.add(lblID);
        panelPrincipal.add(txtID);

        lblNombreCompleto = new JLabel("Nombre Completo:");
        txtNombreCompleto = new JTextField();
        panelPrincipal.add(lblNombreCompleto);
        panelPrincipal.add(txtNombreCompleto);

        lblCorreo = new JLabel("Correo:");
        txtCorreo = new JTextField();
        panelPrincipal.add(lblCorreo);
        panelPrincipal.add(txtCorreo);

        btnRegistrar = new JButton("Registrar");
        btnBuscar = new JButton("Buscar por correo");
        btnSalir = new JButton("Salir");

        panelPrincipal.add(btnRegistrar);
        panelPrincipal.add(btnBuscar);
        panelPrincipal.add(btnSalir);

        // Acciones
        btnRegistrar.addActionListener(this::registrarUsuario);
        btnBuscar.addActionListener(this::buscarUsuario);
        btnSalir.addActionListener(e -> System.exit(0));
    }

    private void registrarUsuario(ActionEvent e) {
        String idText = txtID.getText().trim();
        String nombreCompleto = txtNombreCompleto.getText().trim();
        String correo = txtCorreo.getText().trim();

        if (idText.isEmpty() || nombreCompleto.isEmpty() || correo.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Debe completar todos los campos (ID, Nombre y Correo)",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "El ID debe ser un número entero",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!validarCorreo(correo)) {
            JOptionPane.showMessageDialog(this,
                    "Ingrese un correo electrónico válido",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Ejecutar en segundo plano
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() {
                try {
                    boolean exito = Cliente.agregar(id, nombreCompleto, correo);
                    if (exito) {
                        JOptionPane.showMessageDialog(GUIRmi.this,
                                "Usuario registrado exitosamente.",
                                "Éxito",
                                JOptionPane.INFORMATION_MESSAGE);
                        limpiarCampos();
                    } else {
                        JOptionPane.showMessageDialog(GUIRmi.this,
                                "No se pudo registrar el usuario.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(GUIRmi.this,
                            "Error al registrar usuario: " + ex.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                return null;
            }
        }.execute();
    }

    private void buscarUsuario(ActionEvent e) {
        String correo = txtCorreo.getText().trim();

        if (correo.isEmpty()) {
            mostrarError("Debe ingresar un correo para buscar.");
            return;
        }

        if (!validarCorreo(correo)) {
            mostrarError("Ingrese un correo electrónico válido.");
            return;
        }

        try {
            String resultado = Cliente.consultar(correo);
            JOptionPane.showMessageDialog(this, resultado, "Resultado de Búsqueda", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            mostrarError("Error al buscar usuario: " + ex.getMessage());
        }
    }

    private boolean validarCorreo(String correo) {
        return correo.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    private void limpiarCampos() {
        txtID.setText("");
        txtNombreCompleto.setText("");
        txtCorreo.setText("");
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new GUIRmi().setVisible(true);
        });
    }
}