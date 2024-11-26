package GUI;

import Core.Usuario;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * Panel de inicio de sesión para la aplicación AutoBibliogest.
 * <p>
 * Este panel permite al usuario ingresar su dirección de correo electrónico y contraseña
 * para iniciar sesión o redirigirlo a la creación de una nueva cuenta.
 */
public class PanelInicio extends JPanel {

    /**
     * Constructor del panel de inicio de sesión.
     *
     * @param ventanacontenedor referencia a la ventana principal que contiene este panel.
     */
    public PanelInicio(VentanaPrincipal ventanacontenedor) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Configuramos BoxLayout en el eje Y
        this.setBackground(Color.WHITE);
        Font fuente = new Font("Arial", Font.BOLD, 12);


        // Espacio para centrar verticalmente los elementos
        this.add(Box.createVerticalGlue());

        // Nombre de la aplicación
        JLabel titulousuario = new JLabel("AUTOBIBLIOGEST", SwingConstants.CENTER);
        titulousuario.setForeground(Color.orange);
        titulousuario.setFont(new Font("Arial", Font.TYPE1_FONT, 50));
        titulousuario.setAlignmentX(Component.CENTER_ALIGNMENT); // Alineado al centro
        this.add(titulousuario);

        // Espacio entre componentes
        this.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel etiquetaUsuario = new JLabel("Dirección de e-mail");
        etiquetaUsuario.setFont(fuente);
        etiquetaUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(etiquetaUsuario);

        // Field para introducir el usuario
        JTextField usuario = new JTextField();
        usuario.setMaximumSize(new Dimension(270, 200)); // Ancho máximo
        usuario.setBackground(Color.WHITE);
        usuario.setForeground(Color.BLACK);
        usuario.setAlignmentX(Component.CENTER_ALIGNMENT); // Alineado al centro


        Border borderNegro = new LineBorder(Color.BLACK, 1);
        usuario.setBorder(borderNegro);

        // Borde iluminado (cuando se está escribiendo)
        Border borderIluminado = new LineBorder(Color.BLUE, 2);

        // Agregar FocusListener para cambiar el borde al ganar o perder el foco
        usuario.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                // Cambiar borde a azul cuando se escribe
                usuario.setBorder(borderIluminado);
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Cambiar borde a negro cuando no se está escribiendo
                usuario.setBorder(borderNegro);
            }
        });
        this.add(usuario);

        // Espaciador entre componentes
        this.add(Box.createRigidArea(new Dimension(0, 30)));

        JLabel etiquetaContrasenia = new JLabel("Contraseña");
        etiquetaContrasenia.setFont(fuente);
        etiquetaContrasenia.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(etiquetaContrasenia);


        // Field para introducir la contraseña
        JPasswordField contrasenia = new JPasswordField();
        contrasenia.setEchoChar('●');
        contrasenia.setMaximumSize(new Dimension(270, 200)); // Ancho máximo
        contrasenia.setBackground(Color.WHITE);
        contrasenia.setForeground(Color.BLACK);
        contrasenia.setAlignmentX(Component.CENTER_ALIGNMENT); // Alineado al centro


        // Agregar FocusListener para cambiar el borde al ganar o perder el foco
        contrasenia.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                // Cambiar borde a azul cuando se escribe
                contrasenia.setBorder(borderIluminado);
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Cambiar borde a negro cuando no se está escribiendo
                contrasenia.setBorder(borderNegro);
            }
        });

        this.add(contrasenia);

        // Espaciador entre componentes
        this.add(Box.createRigidArea(new Dimension(0, 20)));

        // Botón para iniciar sesión
        JButton butoninicsesion = new JButton("Inicio sesión");
        butoninicsesion.setMaximumSize(new Dimension(270, 40));
        butoninicsesion.setBackground(Color.ORANGE);
        butoninicsesion.setOpaque(true);
        butoninicsesion.setBorderPainted(false);
        butoninicsesion.setAlignmentX(Component.CENTER_ALIGNMENT); // Alineado al centro
        this.add(butoninicsesion);

        // Espaciador entre componentes
        this.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel etiquetacrearusario = new JLabel("¿Eres nuevo en AutoBibliogest?");
        etiquetacrearusario.setFont(fuente);
        etiquetacrearusario.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(etiquetacrearusario);

        this.add(Box.createRigidArea(new Dimension(0, 20)));

        // Botón para crear usuario
        JButton buttoncrearusuario = new JButton("Crear tu cuenta de AutoBibliogest");
        buttoncrearusuario.setAlignmentX(Component.CENTER_ALIGNMENT); // Alineado al centro
        this.add(buttoncrearusuario);

        // Espaciador para centrar verticalmente los elementos
        this.add(Box.createVerticalGlue());

        // ActionListener para el botón "Inicio sesión"
        butoninicsesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Usuario.esvaldio(usuario.getText(), contrasenia.getPassword(), ventanacontenedor)) {
                    ventanacontenedor.hacervisiblemenu();
                    ventanacontenedor.cambiarPanel("pantallacarga");
                    PanelMisReservas panelMisReservas = new PanelMisReservas(ventanacontenedor);
                    ventanacontenedor.getPanelContenedor().add(panelMisReservas, "panelMisreservas");
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario incorrecto");
                }
            }
        });

        // ActionListener para el botón "Crear usuario"
        buttoncrearusuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanacontenedor.cambiarPanel("panelCrearUsuario");
            }
        });


    }
}

