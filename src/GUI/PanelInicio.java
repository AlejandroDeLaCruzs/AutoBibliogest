package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelInicio extends JPanel {
    public PanelInicio(Ventana ventanacontenedor) {

        this.setLayout(null);
        this.setBackground(Color.BLUE);


        //Nombre de la aplicacion
        JLabel titulousuario = new JLabel("BIBLIOGEST", SwingConstants.CENTER);
        titulousuario.setOpaque(true);
        titulousuario.setBackground(Color.ORANGE);
        titulousuario.setForeground(Color.CYAN);
        titulousuario.setFont(new Font("Arial", Font.BOLD, 20));
        titulousuario.setBounds(110, 15, 170, 60);
        this.add(titulousuario);

        //Boton para iniciar sesion
        JButton butoninicsesion = new JButton("Inicio sesion");
        butoninicsesion.setBounds(105, 260, 200, 30);
        butoninicsesion.setEnabled(true);
        this.add(butoninicsesion);


        //Field para introducir el usuario
        JTextField usuario = new JTextField();
        usuario.setBounds(105, 200, 200, 30);  // Ahora el setBounds se aplica al JTextField
        usuario.setBackground(Color.BLACK);  // Fondo del JTextField en negro
        usuario.setForeground(Color.WHITE);  // Texto en blanco para mayor contraste
        System.out.println(usuario.getText());
        this.add(usuario);


        //Area para introducir la contreña
        JTextField contrasenia = new JTextField();
        contrasenia.setBounds(105, 220, 200, 30);  // Ahora el setBounds se aplica al JTextField
        contrasenia.setBackground(Color.BLACK);  // Fondo del JTextField en negro
        contrasenia.setForeground(Color.WHITE);  // Texto en blanco para mayor contraste
        this.add(contrasenia);


        //Boton para crear usuario
        JButton buttoncrearusuario = new JButton("Crear usuario");
        buttoncrearusuario.setBounds(105, 300, 200, 30);
        buttoncrearusuario.setEnabled(true);
        this.add(buttoncrearusuario);


        // CREACION DEL ACTION LISTENER DEL BOTON INICO SESION
        butoninicsesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Usuario.esvaldio(usuario.getText(), contrasenia.getText(), ventanacontenedor)) {
                    ventanacontenedor.cambiarPanel("pantallacarga");
                    PanelMisReservas panelMisReservas = new PanelMisReservas(ventanacontenedor);
                    ventanacontenedor.getPanelContenedor().add(panelMisReservas, "panelMisreservas");
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario incorrecto");
                }
            }
        });


        buttoncrearusuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanacontenedor.cambiarPanel("panelCrearUsuario");
            }
        });


        this.setVisible(true);
    }
}
