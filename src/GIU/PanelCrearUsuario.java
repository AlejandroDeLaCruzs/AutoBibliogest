package GIU;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelCrearUsuario extends JPanel {

    PanelCrearUsuario(Ventana ventanacontenedor) {
        SpringLayout layout = new SpringLayout();
        setLayout(layout);
        /*setBackground(Color.BLUE);
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
        this.add(contrasenia);*/



        String[] labels = {"Name: ", "Fax: ", "Email: ", "Address: "};
        int numPairs = labels.length;
        JTextField[] textFields = new JTextField[labels.length];


        for (int i = 0; i < numPairs; i++) {
            JLabel label = new JLabel(labels[i], JLabel.TRAILING);
            add(label);
            textFields[i] = new JTextField(18); // Store each text field
            label.setLabelFor(textFields[i]);
            add(textFields[i]);


            layout.putConstraint(SpringLayout.WEST, label, 5, SpringLayout.WEST, this);
            layout.putConstraint(SpringLayout.NORTH, label, 30 * i + 5, SpringLayout.NORTH, this);

            // Posicionar todos los JTextField en la misma posición horizontal
            layout.putConstraint(SpringLayout.WEST, textFields[i], 100, SpringLayout.WEST, this); // Ajusta este valor según sea necesario
            layout.putConstraint(SpringLayout.NORTH, textFields[i], 30 * i + 5, SpringLayout.NORTH, this);

        }





        JButton botonCrearCuenta = new JButton("Crear cuenta");
        botonCrearCuenta.setEnabled(true);
        layout.putConstraint(SpringLayout.EAST, botonCrearCuenta, -10, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.SOUTH, botonCrearCuenta, -10, SpringLayout.SOUTH, this);

        this.add(botonCrearCuenta);




       botonCrearCuenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (JTextField textField : textFields) {
                    if (textField.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Todos los campos deben estar completos.");
                        return;
                    }
                }
                Usuario crearusuaio = new Usuario(textFields[0].getText(), textFields[1].getText(), textFields[2].getText(), textFields[3].getText());
                crearusuaio.aniadiruseralarchivo();
                ventanacontenedor.cambiarPanel("panelInicio");
            }
        });


    }
}
