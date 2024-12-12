package GUI;

import App.VentanaPrincipal;
import Core.Biblioteca;
import Core.Usuario;
import Core.Validación.Usuarios.ValidadorCrearUsuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static GUI.PanelInicio.logoApp;

/**
 * Clase que representa el panel de creación de usuarios dentro de la interfaz gráfica.
 */
public class PanelCrearUsuario extends JPanel {

    /**
     * Constructor de la clase PanelCrearUsuario.
     *
     * @param ventanaContenedor La ventana principal que contiene este panel.
     * @param biblioteca La instancia de la biblioteca que almacena los usuarios.
     */
    public PanelCrearUsuario(VentanaPrincipal ventanaContenedor, Biblioteca biblioteca) {

        // Configuración del diseño del panel
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.WHITE);

        // Espaciador superior
        this.add(Box.createRigidArea(new Dimension(0, 55)));

        // Logo de la aplicación
        add(logoApp());

        this.add(Box.createRigidArea(new Dimension(0, 20)));

        // Etiqueta de título
        JLabel etiquetaCrearCuenta = new JLabel("Crear Cuenta", SwingConstants.CENTER);
        etiquetaCrearCuenta.setForeground(Color.orange);
        etiquetaCrearCuenta.setFont(new Font("Arial", Font.PLAIN, 35));
        etiquetaCrearCuenta.setAlignmentX(Component.CENTER_ALIGNMENT); // Alineado al centro
        this.add(etiquetaCrearCuenta);

        this.add(Box.createRigidArea(new Dimension(0, 20))); // Espaciador después del título

        // Campos de texto para ingreso de datos
        String[] etiquetas = {"Nombre ", "Id de usuario ", "Dirección de e-mail "};
        JTextField[] camposTexto = new JTextField[etiquetas.length];
        for (int i = 0; i < etiquetas.length; i++) {
            JLabel label = new JLabel(etiquetas[i]);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.add(label);

            this.add(Box.createRigidArea(new Dimension(0, 10)));

            camposTexto[i] = new JTextField();
            camposTexto[i].setMaximumSize(new Dimension(270, 30));
            camposTexto[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            this.add(camposTexto[i]);

            this.add(Box.createRigidArea(new Dimension(0, 15)));
        }

        // Campo para contraseña
        JLabel labelcontrasena = new JLabel("Contraseña");
        labelcontrasena.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(labelcontrasena);

        this.add(Box.createRigidArea(new Dimension(0, 10)));

        JPasswordField campoContrasena = new JPasswordField();
        campoContrasena.setEchoChar('●');
        campoContrasena.setMaximumSize(new Dimension(270, 30)); // Ancho máximo
        campoContrasena.setBackground(Color.WHITE);
        campoContrasena.setForeground(Color.BLACK);
        campoContrasena.setAlignmentX(Component.CENTER_ALIGNMENT); //
        add(campoContrasena);

        // Campo para confirmar contraseña
        JLabel etiquetaConfirmarContrasena = new JLabel("Confirmar contraseña");
        etiquetaConfirmarContrasena.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(etiquetaConfirmarContrasena);

        this.add(Box.createRigidArea(new Dimension(0, 10)));

        JPasswordField campoConfirmarContrasena = new JPasswordField();
        campoConfirmarContrasena.setEchoChar('●');
        campoConfirmarContrasena.setMaximumSize(new Dimension(270, 30)); // Ancho máximo
        campoConfirmarContrasena.setBackground(Color.WHITE);
        campoConfirmarContrasena.setForeground(Color.BLACK);
        campoConfirmarContrasena.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(campoConfirmarContrasena);

        this.add(Box.createRigidArea(new Dimension(0, 10)));


        // Botón para crear cuenta
        JButton botonCrearCuenta = new JButton("Crear cuenta");
        botonCrearCuenta.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(botonCrearCuenta);
        this.add(botonCrearCuenta);


        botonCrearCuenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mensajeValidacion = ValidadorCrearUsuario.validarCamposTexto(camposTexto);
                if (mensajeValidacion == null) {
                    mensajeValidacion = ValidadorCrearUsuario.validarContrasenas(
                            new String(campoContrasena.getPassword()),
                            new String(campoConfirmarContrasena.getPassword()));
                }
                if (mensajeValidacion != null) {
                    JOptionPane.showMessageDialog(null, mensajeValidacion);
                } else {
                    String nuevaContrasenia = new String(campoContrasena.getPassword());
                    Usuario UsuarioNuevo = new Usuario(camposTexto[0].getText(), camposTexto[1].getText(), camposTexto[2].getText(), nuevaContrasenia);
                    UsuarioNuevo.aniadirUseralArchivo(biblioteca.getUsuarios());
                    ventanaContenedor.cambiarPanel("panelInicio");
                }
            }

        });

    }

}

