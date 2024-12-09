package GUI;

import App.VentanaPrincipal;
import Core.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static GUI.PanelInicio.logoypanel;

public class PanelCrearUsuario extends JPanel {

    public PanelCrearUsuario(VentanaPrincipal ventanacontenedor) {

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.WHITE);

        this.add(Box.createRigidArea(new Dimension(0, 55)));

        add(logoypanel());

        this.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel etiquetacrearcuenta = new JLabel("Crear Cuenta", SwingConstants.CENTER);
        etiquetacrearcuenta.setForeground(Color.orange);
        etiquetacrearcuenta.setFont(new Font("Arial", Font.PLAIN, 35));
        etiquetacrearcuenta.setAlignmentX(Component.CENTER_ALIGNMENT); // Alineado al centro
        this.add(etiquetacrearcuenta);

        this.add(Box.createRigidArea(new Dimension(0, 20))); // Espaciador después del título

        String[] labels = {"Nombre: ", "Iduser: ", "Email: "};
        JTextField[] textFields = new JTextField[labels.length];
        for (int i = 0; i < labels.length; i++) {
            JLabel label = new JLabel(labels[i]);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.add(label);

            this.add(Box.createRigidArea(new Dimension(0, 10)));

            textFields[i] = new JTextField();
            textFields[i].setMaximumSize(new Dimension(270, 30));
            textFields[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            this.add(textFields[i]);

            this.add(Box.createRigidArea(new Dimension(0, 15)));
        }

        JLabel labelcontrasena = new JLabel("Contraseña");
        labelcontrasena.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(labelcontrasena);
        this.add(Box.createRigidArea(new Dimension(0, 10)));

        JPasswordField contrasenia = new JPasswordField();
        contrasenia.setEchoChar('●');
        contrasenia.setMaximumSize(new Dimension(270, 30)); // Ancho máximo
        contrasenia.setBackground(Color.WHITE);
        contrasenia.setForeground(Color.BLACK);
        contrasenia.setAlignmentX(Component.CENTER_ALIGNMENT); //
        add(contrasenia);

        JLabel labelConfirmarcontrasenia = new JLabel("Confirmar contraseña");
        labelConfirmarcontrasenia.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(labelConfirmarcontrasenia);

        this.add(Box.createRigidArea(new Dimension(0, 10)));

        JPasswordField confirmarContrasenia = new JPasswordField();
        confirmarContrasenia.setEchoChar('●');
        confirmarContrasenia.setMaximumSize(new Dimension(270, 30)); // Ancho máximo
        confirmarContrasenia.setBackground(Color.WHITE);
        confirmarContrasenia.setForeground(Color.BLACK);
        confirmarContrasenia.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(confirmarContrasenia);

        this.add(Box.createRigidArea(new Dimension(0, 10)));


        //Creacion del botón crearcuenta
        JButton botonCrearCuenta = new JButton("Crear cuenta");
        botonCrearCuenta.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(botonCrearCuenta);
        this.add(botonCrearCuenta);


        botonCrearCuenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String validationMessage = validarJtexfields(textFields, contrasenia, confirmarContrasenia);
                if (validationMessage != null) {
                    JOptionPane.showMessageDialog(null, validationMessage);
                } else {
                    String nuevaContrasenia = new String(contrasenia.getPassword());
                    Usuario crearusuaio = new Usuario(textFields[0].getText(), textFields[1].getText(), textFields[2].getText(), nuevaContrasenia);
                    crearusuaio.aniadirUseralArchivo();
                    ventanacontenedor.cambiarPanel("panelInicio");
                }
            }
        });

    }

    // Método para validar campos de texto y contraseñas
    private String validarJtexfields(JTextField[] textFields, JPasswordField contrasenia, JPasswordField confirmarContrasenia) {
        // Validar si los campos de texto están vacíos
        String validationMessage = validateTextFields(textFields);
        if (validationMessage != null) {
            return validationMessage;
        }

        // Validar contraseñas
        validationMessage = validatePasswords(contrasenia, confirmarContrasenia);
        if (validationMessage != null) {
            return validationMessage;
        }
        return null;

    }

    // Validar que los campos de texto no estén vacíos
    private String validateTextFields(JTextField[] textFields) {
        for (JTextField textField : textFields) {
            if (textField.getText().isEmpty()) {
                return "Todos los campos deben estar completos.";
            }
        }
        return null;
    }

    // Validar contraseñas
    private String validatePasswords(JPasswordField contrasenia, JPasswordField confirmarContrasenia) {
        String pass = new String(contrasenia.getPassword());
        String confirmPass = new String(confirmarContrasenia.getPassword());

        // Validar que las contraseñas no estén vacías
        if (pass.isEmpty() || confirmPass.isEmpty()) {
            return "Las contraseñas no pueden estar vacías.";
        }

        // Validar que las contraseñas coincidan
        if (!pass.equals(confirmPass)) {
            return "Las contraseñas no coinciden.";
        }

        return null;
    }
}
