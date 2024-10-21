package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelCrearUsuario extends JPanel {

    PanelCrearUsuario(Ventana ventanacontenedor) {
        SpringLayout layout = new SpringLayout();
        setLayout(layout);

        String[] labels = {"Nombre: ", "Iduser: ", "Email: ", "Contraseña: "};
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
            layout.putConstraint(SpringLayout.WEST, textFields[i], 100, SpringLayout.WEST, this);
            layout.putConstraint(SpringLayout.NORTH, textFields[i], 30 * i + 5, SpringLayout.NORTH, this);

        }

        //Creacion del buton crearcuenta
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