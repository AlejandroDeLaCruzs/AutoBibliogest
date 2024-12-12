package GUI;

import App.VentanaPrincipal;
import Core.Biblioteca;
import Core.Usuario;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import static Core.Validación.Usuarios.ValidadorInicioSesion.esValido;


/**
 * Panel de inicio de sesión para la aplicación AutoBibliogest.
 *
 * Este panel permite al usuario ingresar su dirección de correo electrónico y contraseña
 * para iniciar sesión o redirigirlo a la creación de una nueva cuenta.
 */
public class PanelInicio extends JPanel {
    private static final int MAX_FIELD_WIDTH = 270;
    private static final int MAX_FIELD_HEIGHT = 200;
    private static final Font FONT = new Font("Arial", Font.BOLD, 12);
    /**
     * Constructor del panel de inicio de sesión.
     *
     * @param ventanaContenedora referencia a la ventana principal que contiene este panel.
     * @param biblioteca referencia al sistema de biblioteca que contiene usuarios y datos.
     */
    public PanelInicio(VentanaPrincipal ventanaContenedora, Biblioteca biblioteca) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);

        // Espacio entre componentes
        add(Box.createVerticalGlue());

        add(logoApp());

        JTextField emailField = createTextField("Dirección de e-mail");
        add(emailField);
        add(Box.createRigidArea(new Dimension(0, 30)));

        JPasswordField passwordField = createPasswordField("Contraseña");
        add(passwordField);
        add(Box.createRigidArea(new Dimension(0, 20)));

        JButton loginButton = createLoginButton(ventanaContenedora, biblioteca, emailField, passwordField);
        JButton createAccountButton = createCreateAccountButton(ventanaContenedora);
        add(loginButton);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(createAccountButton);
        add(Box.createVerticalGlue());


    }

    /**
     * Crea el panel con el logo de la aplicación y el nombre.
     *
     * @return El panel que contiene el logo y el nombre de la aplicación.
     */
    public static JPanel logoApp() {

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        JLabel imagenLabel = new JLabel();
        ImageIcon imagenIcon = new ImageIcon("./res/logo.png");
        Image imagenEscalada = imagenIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        imagenLabel.setIcon(new ImageIcon(imagenEscalada));
        imagenLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(imagenLabel);

        // Nombre de la aplicación
        JLabel nombreapliacion = new JLabel("AUTOBIBLIOGEST", SwingConstants.CENTER);
        nombreapliacion.setForeground(Color.orange);
        nombreapliacion.setFont(new Font("Arial", Font.TYPE1_FONT, 50));
        nombreapliacion.setAlignmentX(Component.CENTER_ALIGNMENT); // Alineado al centro
        panel.add(nombreapliacion);

        return panel;

    }


    /**
     * Crea un campo de texto de contraseña.
     *
     * @param labelText El texto de la etiqueta para el campo de contraseña.
     * @return El campo de texto de contraseña configurado.
     */
    private JPasswordField createPasswordField(String labelText) {
        JLabel label = new JLabel(labelText);
        label.setFont(FONT);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setEchoChar('●');
        passwordField.setMaximumSize(new Dimension(MAX_FIELD_WIDTH, MAX_FIELD_HEIGHT));
        passwordField.setBackground(Color.WHITE);
        passwordField.setForeground(Color.BLACK);
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordField.setBorder(createBorder());

        passwordField.addFocusListener(createFocusListener(passwordField));

        add(label);
        return passwordField;
    }

    /**
     * Crea un botón de inicio de sesión con su ActionListener correspondiente.
     *
     * @param ventanaContenedora Ventana principal que contiene el panel.
     * @param biblioteca Sistema de biblioteca con usuarios.
     * @param emailField Campo de texto para el correo electrónico.
     * @param passwordField Campo de texto para la contraseña.
     * @return El botón de inicio de sesión configurado.
     */
    private JButton createLoginButton(VentanaPrincipal ventanaContenedora, Biblioteca biblioteca,
                                      JTextField emailField, JPasswordField passwordField) {
        JButton loginButton = new JButton("Inicio sesión");
        loginButton.setMaximumSize(new Dimension(MAX_FIELD_WIDTH, 40));
        loginButton.setBackground(Color.ORANGE);
        loginButton.setOpaque(true);
        loginButton.setBorderPainted(false);
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (esValido(emailField.getText(), passwordField.getPassword(), ventanaContenedora, biblioteca.getUsuarios())) {
                    ventanaContenedora.hacerVisibleMenu();
                    ventanaContenedora.cambiarPanel("pantallacarga");
                    PanelMisReservas panelMisReservas = new PanelMisReservas(ventanaContenedora, biblioteca.getPrestamos());
                    ventanaContenedora.agregarPanel(panelMisReservas, "panelMisReservas");
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario incorrecto");
                }
            }
        });

        return loginButton;
    }

    /**
     * Crea un botón de creación de cuenta con su ActionListener correspondiente.
     *
     * @param ventanaContenedora Ventana principal que contiene el panel.
     * @return El botón de creación de cuenta configurado.
     */
    private JButton createCreateAccountButton(VentanaPrincipal ventanaContenedora) {
        JButton createAccountButton = new JButton("Crear tu cuenta de AutoBibliogest");
        createAccountButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaContenedora.cambiarPanel("panelCrearUsuario");
            }
        });

        return createAccountButton;
    }

    /**
     * Crea un campo de texto para ingresar la información del usuario.
     *
     * @param labelText El texto de la etiqueta para el campo.
     * @return El campo de texto configurado.
     */
    private JTextField createTextField(String labelText) {
        JLabel label = new JLabel(labelText);
        label.setFont(FONT);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField textField = new JTextField();
        textField.setMaximumSize(new Dimension(MAX_FIELD_WIDTH, MAX_FIELD_HEIGHT));
        textField.setBackground(Color.WHITE);
        textField.setForeground(Color.BLACK);
        textField.setAlignmentX(Component.CENTER_ALIGNMENT);
        textField.setBorder(createBorder());

        textField.addFocusListener(createFocusListener(textField));

        add(label);
        return textField;
    }


    /**
     * Crea un borde para los campos de texto.
     *
     * @return El borde configurado.
     */
    private Border createBorder() {
        return new LineBorder(Color.BLACK, 1);
    }

    /**
     * Crea un listener para el cambio de borde en los campos de texto.
     *
     * @param component El componente al que se le aplicará el listener.
     * @return El listener de enfoque configurado.
     */
    private FocusAdapter createFocusListener(JComponent component) {
        return new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                component.setBorder(new LineBorder(Color.BLUE, 2));  // Cambiar borde a azul
            }

            @Override
            public void focusLost(FocusEvent e) {
                component.setBorder(createBorder());  // Cambiar borde a negro
            }
        };
    }
}

