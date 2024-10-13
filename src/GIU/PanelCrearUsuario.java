package GIU;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelCrearUsuario extends JPanel {

    PanelCrearUsuario(Ventana ventanacontenedor) {
        setLayout(null);
        setBackground(Color.BLUE);
        JLabel titulousuario = new JLabel("BIBLIOGEST", SwingConstants.CENTER);
        titulousuario.setOpaque(true);
        titulousuario.setBackground(Color.ORANGE);
        titulousuario.setForeground(Color.CYAN);
        titulousuario.setFont(new Font("Arial", Font.BOLD, 20));
        titulousuario.setBounds(110, 15, 170, 60);
        this.add(titulousuario);


        //Fiel para introducir el nombre
        JTextField nombre = new JTextField();
        nombre.setBounds(105, 120, 200, 35);  // Ahora el setBounds se aplica al JTextField
        nombre.setBackground(Color.BLACK);  // Fondo del JTextField en negro
        nombre.setForeground(Color.WHITE);  // Texto en blanco para mayor contraste
        this.add(nombre);


        JTextField correo = new JTextField();
        correo.setBounds(105, 160, 200, 35);  // Ahora el setBounds se aplica al JTextField
        correo.setBackground(Color.BLACK);  // Fondo del JTextField en negro
        correo.setForeground(Color.WHITE);  // Texto en blanco para mayor contraste
        this.add(correo);

        JTextField iduser = new JTextField();
        iduser.setBounds(105, 200, 200, 35);  // Ahora el setBounds se aplica al JTextField
        iduser.setBackground(Color.BLACK);  // Fondo del JTextField en negro
        iduser.setForeground(Color.WHITE);  // Texto en blanco para mayor contraste
        this.add(iduser);


        JTextField contrasenia = new JTextField();
        contrasenia.setBounds(105, 240, 200, 35);  // Ahora el setBounds se aplica al JTextField
        contrasenia.setBackground(Color.BLACK);  // Fondo del JTextField en negro
        contrasenia.setForeground(Color.WHITE);  // Texto en blanco para mayor contraste
        this.add(contrasenia);


        JButton butoncrearcunata = new JButton("Crear cuenta");
        butoncrearcunata.setBounds(105, 320, 200, 30);
        butoncrearcunata.setEnabled(true);
        this.add(butoncrearcunata);


        butoncrearcunata.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Usuario crearusuaio = new Usuario(nombre.getText(), correo.getText(), iduser.getText(), contrasenia.getText());
                crearusuaio.aniadiruseralarchivo();
                ventanacontenedor.cambiarPanel("panelInicio");
            }
        });


    }
}
